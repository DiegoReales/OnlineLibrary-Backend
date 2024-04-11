package co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserRequestBody {

    @NotBlank(message = "El usuario es obligatorio")
    private String username;

    @NotNull(message = "El rol es obligatorio")
    private Integer roleId;

    private Boolean active = true;

    @NotBlank(message = "El nombre del usuario es obligatorio")
    private String firstname;

    private String secondname;

    @NotBlank(message = "El primer apellido del usuario es obligatorio")
    private String firstsurname;

    private String secondsurname;

    @NotBlank(message = "El dni del usuario es obligatorio")
    private String dni;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dniexpdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private String email;

    private String celularphone;

    private String localphone;
}
