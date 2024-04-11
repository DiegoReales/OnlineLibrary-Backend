package co.edu.cuc.onlinelibrary.auth.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ActionLogDto {
    private Long id;
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
    private Object beforeValue;
    private Object afterValue;
}
