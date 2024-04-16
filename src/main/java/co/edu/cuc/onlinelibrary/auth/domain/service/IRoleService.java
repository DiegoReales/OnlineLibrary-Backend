package co.edu.cuc.onlinelibrary.auth.domain.service;


import co.edu.cuc.onlinelibrary.auth.domain.dto.RoleDto;

import java.util.Collection;
import java.util.List;

public interface IRoleService {
    RoleDto findByIdWithPermission(int roleId);

    Collection<String> findPermissionsByRoleId(int roleId);
    List<RoleDto> findAll();
    RoleDto findById(int roleId);
    RoleDto save(RoleDto roleDto);
    RoleDto syncPermissionsToRole(Integer roleId, List<Integer> permissionsIds);
    RoleDto addPermissionToRole(Integer roleId, Integer permissionId);
    RoleDto removePermissionToRole(Integer roleId, Integer permissionId);

    void deletedById(int roleId);

}
