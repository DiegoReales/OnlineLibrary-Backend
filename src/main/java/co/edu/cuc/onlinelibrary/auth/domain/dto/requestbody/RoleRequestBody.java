package co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestBody {
    @NotEmpty(message = "El campo code es obligatorio")
    private String code;
    @NotEmpty(message = "El campo nombre es obligatorio")
    private String name;
    @NotEmpty(message = "El campo descripción es obligatorio")
    private String description;
}
