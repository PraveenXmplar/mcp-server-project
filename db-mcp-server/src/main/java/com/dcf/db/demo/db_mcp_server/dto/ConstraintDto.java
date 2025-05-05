package com.dcf.db.demo.db_mcp_server.dto;

public record ConstraintDto(String name,
                            String type,
                            String columnName,
                            String referencedTable,
                            String referencedColumn) {
}
