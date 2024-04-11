package co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequestBody {

    private Integer parentId;

    @NotEmpty(message = "El campo título es obligatorio")
    private String title;

    @NotEmpty(message = "El campo ruta es obligatorio")
    private String route;

    @NotNull(message = "El campo permissionId es obligatorio")
    private Integer permissionId;

    @NotNull(message = "El campo orden es obligatorio")
    private Integer order;

   @NotNull(message = "El campo active es obligatorio")
    private Boolean active;

}