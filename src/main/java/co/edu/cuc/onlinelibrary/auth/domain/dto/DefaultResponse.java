package co.edu.cuc.onlinelibrary.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse {
    private String status;
    private String message;

    public static DefaultResponse createOkMessage() {
        return new DefaultResponse("OK", "Operación exitosa");
    }
}
