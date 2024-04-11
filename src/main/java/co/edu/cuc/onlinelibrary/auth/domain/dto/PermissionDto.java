package co.edu.cuc.onlinelibrary.auth.domain.dto;

import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.PermissionRequestBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDto extends AuditorDto {

    private Integer id;
    private String name;
    private String description;

    public void fillFromPermissionRequestBody(PermissionRequestBody requestBody) {
        this.name = requestBody.getName();
        this.description = requestBody.getDescription();
    }
}
