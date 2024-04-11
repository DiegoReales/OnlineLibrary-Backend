package co.edu.cuc.onlinelibrary.auth.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "password_recovery")
public class PasswordRecoveryEntity {

    @Id
    @SequenceGenerator(name = "seq_password_recovery", sequenceName = "seq_password_recovery_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_password_recovery")
    @Column(name = "password_recovery_id")
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    private String password;
    private Boolean recovered;
    private LocalDateTime expiration;
    private LocalDateTime createdAt;
}
