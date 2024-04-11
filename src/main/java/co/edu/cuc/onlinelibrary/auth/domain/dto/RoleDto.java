package co.edu.cuc.onlinelibrary.auth.domain.dto;


import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.RoleRequestBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto extends AuditorDto {

    private Integer id;
    private String code;
    private String name;
    private String description;

    private List<PermissionDto> permissions;

    public void fillFromRoleRequestBody(RoleRequestBody requestBody) {
        this.code = requestBody.getCode();
        this.name = requestBody.getName();
        this.description = requestBody.getDescription();
    }

}
