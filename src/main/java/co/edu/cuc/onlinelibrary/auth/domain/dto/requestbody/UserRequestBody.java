package co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestBody {

    @NotBlank(message = "El usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatorio")
    private String password;

    @NotBlank(message = "La confirmación de contraseña es obligatorio")
    private String passwordConfirm;

    @NotNull(message = "El rol es obligatorio")
    private Integer roleId;

    private Boolean active = true;

    @NotBlank(message = "El nombre del usuario es obligatorio")
    private String name;

    @NotBlank(message = "El apellido del usuario es obligatorio")
    private String lastname;

    @NotBlank(message = "El dni del usuario es obligatorio")
    private String dni;
}
