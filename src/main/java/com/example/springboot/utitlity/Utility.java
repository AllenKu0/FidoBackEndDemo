package com.example.springboot.utitlity;

import com.yubico.webauthn.data.ByteArray;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class Utility {
    private static final SecureRandom random = new SecureRandom();

    public static ByteArray generateRandom(int length) {
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return new ByteArray(bytes);
    }
}
