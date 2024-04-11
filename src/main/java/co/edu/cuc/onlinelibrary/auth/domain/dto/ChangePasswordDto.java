package co.edu.cuc.onlinelibrary.auth.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {

    @NotBlank(message = "La contraseña actual es obligatoria")
    private String currentPassword;

    @NotBlank(message = "La contraseña nueva es obligatoria")
    private String newPassword;

    @NotBlank(message = "La confirmacion de contraseña es obligatoria")
    private String confirmNewPassword;
}
