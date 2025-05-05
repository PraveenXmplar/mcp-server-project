package com.dcf.db.demo.db_mcp_server.controller;

import com.dcf.db.demo.db_mcp_server.dto.DatabaseMetadataDto;
import com.dcf.db.demo.db_mcp_server.service.DatabaseMetadataService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/metadata")
public class MetadataController {

    private final DatabaseMetadataService service;

    public MetadataController(DatabaseMetadataService service) {
        this.service = service;
    }

    @Tool(description = "Get Metadata from Database - DCF Copilot Meeting Demo", name = "getDBMetadata")
    @GetMapping
    public DatabaseMetadataDto getMetadata() throws SQLException {
        return service.fetchMetadata();
    }
}
