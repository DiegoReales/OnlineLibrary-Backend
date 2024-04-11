package co.edu.cuc.onlinelibrary.auth.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PasswordRecoveryDto {
    private Long id;
    private Integer userId;
    private String password;
    private Boolean recovered;
    private LocalDateTime expiration;
    private LocalDateTime createdAt;
}
