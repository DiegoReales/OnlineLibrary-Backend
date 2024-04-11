package co.edu.cuc.onlinelibrary.auth.domain.repository;


import co.edu.cuc.onlinelibrary.auth.domain.dto.RoleDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IRoleRepository {
    Collection<String> findPermissionsByRoleId(int roleId);
    List<RoleDto> findAll();
    Optional<RoleDto> findById(int roleId);
    Optional<RoleDto> findByIdWithPermission(int roleId);
    RoleDto save(RoleDto roleDto);
    RoleDto syncPermissionsToRole(Integer roleId, List<Integer> permissionIds);
    RoleDto addPermissionToRole(Integer roleId, Integer permissionId);
    RoleDto removePermissionToRole(Integer roleId, Integer permissionId);
    void deletedById(int roleId);

}
