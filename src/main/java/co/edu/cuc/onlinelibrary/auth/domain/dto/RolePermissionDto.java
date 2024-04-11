package co.edu.cuc.onlinelibrary.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionDto {
    private Integer roleId;
    private Integer permissionId;
}
