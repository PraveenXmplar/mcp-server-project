package com.dcf.db.demo.db_mcp_server.repository;

import com.dcf.db.demo.db_mcp_server.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}
