package com.dcf.db.demo.db_mcp_server.service;

import com.dcf.db.demo.db_mcp_server.dto.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseMetadataService {
    private final DataSource dataSource;
    public DatabaseMetadataService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DatabaseMetadataDto fetchMetadata() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            List<TableDto> tables = new ArrayList<>();

            // 1. Tables
            try (ResultSet rs = meta.getTables(null, null, "%", new String[]{"TABLE"})) {
                while (rs.next()) {
                    String tableName = rs.getString("TABLE_NAME");

                    // 2. Columns
                    List<ColumnDto> cols = new ArrayList<>();
                    try (ResultSet crs = meta.getColumns(null, null, tableName, "%")) {
                        while (crs.next()) {
                            cols.add(new ColumnDto(
                                    crs.getString("COLUMN_NAME"),
                                    crs.getString("TYPE_NAME"),
                                    "YES".equals(crs.getString("IS_NULLABLE"))
                            ));
                        }
                    }

                    // 3. Constraints (PK & FK)
                    List<ConstraintDto> cons = new ArrayList<>();
                    try (ResultSet pks = meta.getPrimaryKeys(null, null, tableName)) {
                        while (pks.next()) {
                            cons.add(new ConstraintDto(
                                    pks.getString("PK_NAME"),
                                    "PRIMARY_KEY",
                                    pks.getString("COLUMN_NAME"),
                                    null, null
                            ));
                        }
                    }
                    try (ResultSet fks = meta.getImportedKeys(null, null, tableName)) {
                        while (fks.next()) {
                            cons.add(new ConstraintDto(
                                    fks.getString("FK_NAME"),
                                    "FOREIGN_KEY",
                                    fks.getString("FKCOLUMN_NAME"),
                                    fks.getString("PKTABLE_NAME"),
                                    fks.getString("PKCOLUMN_NAME")
                            ));
                        }
                    }

                    tables.add(new TableDto(tableName, cols, cons));
                }
            }

            // 4. Stored Procedures
            List<ProcedureDto> procs = new ArrayList<>();
            try (ResultSet prs = meta.getProcedures(null, null, "%")) {
                while (prs.next()) {
                    procs.add(new ProcedureDto(
                            prs.getString("PROCEDURE_NAME"),
                            prs.getString("PROCEDURE_TYPE")
                    ));
                }
            }

            return new DatabaseMetadataDto(tables, procs);
        }
    }
}
