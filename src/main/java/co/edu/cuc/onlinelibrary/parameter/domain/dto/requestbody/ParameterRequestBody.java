package co.edu.cuc.onlinelibrary.parameter.domain.dto.requestbody;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterRequestBody {
    @NotBlank(message = "El valor no puede ser vacio")
    private String value;
}
