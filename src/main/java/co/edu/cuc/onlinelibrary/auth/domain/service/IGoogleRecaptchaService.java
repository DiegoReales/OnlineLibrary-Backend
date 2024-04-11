package co.edu.cuc.onlinelibrary.auth.domain.service;

public interface IGoogleRecaptchaService {
    boolean validateToken(String token, String ipRemote);
}
