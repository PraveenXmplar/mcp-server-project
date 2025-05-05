package com.dcf.db.demo.db_mcp_server.controller;

import com.dcf.db.demo.db_mcp_server.model.Users;
import com.dcf.db.demo.db_mcp_server.service.UserService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Tool(description = "Get all users from Database - DCF Copilot Meeting Demo", name = "getAllUsers")
    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public Users addUser(@RequestBody Users user) {
        return userService.addUser(user);
    }

    @GetMapping("/")
    public String rootEndpoint() {
        return "MCP Server is running";
    }
}
