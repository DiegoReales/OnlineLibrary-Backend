package co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestBody {
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatorio")
    private String password;

    @NotBlank(message = "La confirmación de contraseña es obligatorio")
    private String passwordConfirm;

    @NotBlank(message = "El nombre del usuario es obligatorio")
    private String name;

    @NotBlank(message = "El apellido del usuario es obligatorio")
    private String lastname;

    @NotBlank(message = "El dni del usuario es obligatorio")
    private String dni;
}
