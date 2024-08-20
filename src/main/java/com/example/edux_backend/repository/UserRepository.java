package com.example.edux_backend.repository;

import com.example.edux_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
    List<User> findByFirstname(String firstname);
    List<User> findByTypeOfUser(String type);
}