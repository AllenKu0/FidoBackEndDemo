package com.example.springboot;

import com.example.springboot.config.WebAuthProperties;
//import com.example.springboot.service.RegistrationService;
import com.yubico.webauthn.RelyingParty;
import com.yubico.webauthn.data.RelyingPartyIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    @Autowired
//    public RelyingParty relyingParty(RegistrationService regisrationRepository, WebAuthProperties properties) {
//        RelyingPartyIdentity rpIdentity = RelyingPartyIdentity.builder()
//                .id(properties.getHostName())
//                .name(properties.getDisplay())
//                .build();
//
//        return RelyingParty.builder()
//                .identity(rpIdentity)
//                .credentialRepository(regisrationRepository)
//                .origins(properties.getOrigin())
//                .build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
