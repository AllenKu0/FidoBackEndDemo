package com.example.springboot.repository;

import com.example.springboot.entity.User;
import com.yubico.webauthn.data.ByteArray;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByHandle(ByteArray handle);
    Optional<User> findById(Long id);

    Optional<User> findByDisplayName(String displayName);

}
