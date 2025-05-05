package com.dcf.db.demo.db_mcp_server.config;

import com.dcf.db.demo.db_mcp_server.controller.MetadataController;
import com.dcf.db.demo.db_mcp_server.controller.UserController;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpToolsConfig {

    private final UserController userController;
    private final MetadataController metadataController;

    public McpToolsConfig(UserController userController, MetadataController metadataController) {
        this.userController = userController;
        this.metadataController = metadataController;
    }

    @Bean
    public ToolCallbackProvider controllerTools() {
        return MethodToolCallbackProvider.builder()
                .toolObjects(userController, metadataController)
                .build();
    }
}
