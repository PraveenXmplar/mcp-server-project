package com.dcf.db.demo.db_mcp_server.service;

import com.dcf.db.demo.db_mcp_server.model.Users;
import com.dcf.db.demo.db_mcp_server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return users;
    }

    public Users addUser(Users user) {
        return userRepository.save(user);
    }
}
