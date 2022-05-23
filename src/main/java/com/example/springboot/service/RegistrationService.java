package com.example.springboot.service;

import com.example.springboot.entity.Authenticator;
import com.example.springboot.entity.User;
import com.example.springboot.repository.AuthenticatorRepository;
import com.example.springboot.repository.UserRepository;
import com.yubico.webauthn.CredentialRepository;
import com.yubico.webauthn.RegisteredCredential;
import com.yubico.webauthn.data.ByteArray;
import com.yubico.webauthn.data.PublicKeyCredentialDescriptor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Getter
public class RegistrationService implements CredentialRepository {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthenticatorRepository authRepository;

    //產生框框前 會進這
    @Override
    public Set<PublicKeyCredentialDescriptor> getCredentialIdsForUsername(String username) {
        Optional<User> user = userRepo.findByEmail(username);
        if (user.isPresent()){
            List<Authenticator> auth = authRepository.findAllByUser(user.get());
            return auth.stream()
                    .map(
                            credential ->
                                    PublicKeyCredentialDescriptor.builder()
                                            .id(credential.getCredentialId())
                                            .build())
                    .collect(Collectors.toSet());
        }
        else return null;

    }

    @Override
    public Optional<ByteArray> getUserHandleForUsername(String username) {
        Optional<User>  user = userRepo.findByEmail(username);
        return Optional.of(user.get().getHandle());
    }

    @Override
    public Optional<String> getUsernameForUserHandle(ByteArray userHandle) {
        Optional<User>  user = userRepo.findByHandle(userHandle);
        return Optional.of(user.get().getEmail());
    }

    //輸入完登入框框進
    @Override
    public Optional<RegisteredCredential> lookup(ByteArray credentialId, ByteArray userHandle) {
        Optional<Authenticator> auth = authRepository.findByCredentialId(credentialId);
        return auth.map(
                credential ->
                        RegisteredCredential.builder()
                                .credentialId(credential.getCredentialId())
                                .userHandle(credential.getUser().getHandle())
                                .publicKeyCose(credential.getPublicKey())
                                .signatureCount(credential.getCount())
                                .build()
        );
    }

    //輸入完框框進
    @Override
    public Set<RegisteredCredential> lookupAll(ByteArray credentialId) {
        List<Authenticator> auth = authRepository.findAllByCredentialId(credentialId);
        return auth.stream()
                .map(
                        credential ->
                                RegisteredCredential.builder()
                                        .credentialId(credential.getCredentialId())
                                        .userHandle(credential.getUser().getHandle())
                                        .publicKeyCose(credential.getPublicKey())
                                        .signatureCount(credential.getCount())
                                        .build())
                .collect(Collectors.toSet());
    }
}