package com.webproject.model.repository;

import com.webproject.model.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByLogin(String login);
}
