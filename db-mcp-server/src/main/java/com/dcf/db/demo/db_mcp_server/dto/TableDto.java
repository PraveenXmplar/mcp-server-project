package com.dcf.db.demo.db_mcp_server.dto;

import java.util.List;

public record TableDto(String name,
                       List<ColumnDto> columns,
                       List<ConstraintDto> constraints) {
}
