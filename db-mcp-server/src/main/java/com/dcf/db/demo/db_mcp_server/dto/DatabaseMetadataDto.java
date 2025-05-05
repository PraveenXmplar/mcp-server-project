package com.dcf.db.demo.db_mcp_server.dto;

import java.util.List;

public record DatabaseMetadataDto(List<TableDto> tables,
                                  List<ProcedureDto> procedures) {
}
