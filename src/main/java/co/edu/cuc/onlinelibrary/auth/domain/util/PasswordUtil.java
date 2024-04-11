package co.edu.cuc.onlinelibrary.auth.domain.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordUtil {

    private final PasswordEncoder passwordEncoder;

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789*#@&()[]";

    public PasswordUtil(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String generatePassword(int size) {
        StringBuilder response = new StringBuilder();
        SecureRandom sr = new SecureRandom();
        for (int i = 0; i < size; i++) {
            response.append(CHARS.charAt(sr.nextInt(CHARS.length())));
        }
        return response.toString();
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
