package co.edu.cuc.onlinelibrary.auth.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "action_log")
public class ActionLogEntity {

    @Id
    @SequenceGenerator(name = "seq_action_log", sequenceName = "seq_action_log_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_action_log")
    @Column(name = "action_log_id")
    private Long id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    private String username;
    private String module;
    private String action;
    private String message;
    private Integer responseStatus;
    private String ipAddress;
    private String ipLocal;
    private String method;
    private String endpoint;
    private String beforeValue;
    private String afterValue;
}
