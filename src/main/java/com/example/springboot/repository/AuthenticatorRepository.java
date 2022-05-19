package com.example.springboot.repository;

import com.example.springboot.entity.Authenticator;
import com.example.springboot.entity.User;
import com.yubico.webauthn.data.ByteArray;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthenticatorRepository extends CrudRepository<Authenticator, Long> {
    Optional<Authenticator> findByCredentialId(ByteArray credentialId);
    List<Authenticator> findAllByUser (User user);
    List<Authenticator> findAllByCredentialId(ByteArray credentialId);
}
