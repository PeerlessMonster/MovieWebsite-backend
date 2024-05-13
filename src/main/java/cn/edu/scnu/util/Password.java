package cn.edu.scnu.util;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
/* https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/argon2/Argon2PasswordEncoder.html */

public class Password {
    private static final Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    public static String encrypt(String password) {
        return encoder.encode(password);
    }

    public static boolean decrypt(String password, String passwordHash) {
        return encoder.matches(password, passwordHash);
    }
}
