package com.dcf.db.demo.db_mcp_server;

import com.dcf.db.demo.db_mcp_server.controller.UserController;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = "com.dcf.db.demo.db_mcp_server.model")
public class DbMcpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbMcpServerApplication.class, args);
	}
	@Bean
	public ToolCallbackProvider userTools(UserController userController) {
		return MethodToolCallbackProvider.builder()
				.toolObjects(userController)
				.build();
	}
}
