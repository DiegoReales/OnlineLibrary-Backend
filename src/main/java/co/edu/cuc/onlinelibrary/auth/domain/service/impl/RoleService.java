package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.RoleDto;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpNotFoundException;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IRoleRepository;
import co.edu.cuc.onlinelibrary.auth.domain.service.IRoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository iRoleRepository) {
        this.roleRepository = iRoleRepository;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleDto findById(int roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new HttpNotFoundException("Role no encontrado"));
    }

    @Override
    public RoleDto findByIdWithPermission(int roleId) {
        return roleRepository.findByIdWithPermission(roleId)
                .orElseThrow(() -> new HttpNotFoundException("Permiso no encontrado"));
    }

    @Override
    public Collection<String> findPermissionsByRoleId(int roleId) {
        return roleRepository.findPermissionsByRoleId(roleId);
    }



    @Override
    public RoleDto save(RoleDto roleDto) {
        return roleRepository.save(roleDto);
    }

    @Override
    @Transactional
    public RoleDto syncPermissionsToRole(Integer roleId, List<Integer> permissionsIds) {
        try {
            return roleRepository.syncPermissionsToRole(roleId, permissionsIds);
        } catch (EntityNotFoundException e) {
            throw new HttpNotFoundException("Rol no encontrado");
        }
    }

    @Override
    @Transactional
    public RoleDto addPermissionToRole(Integer roleId, Integer permissionId) {
        try {
            return roleRepository.addPermissionToRole(roleId, permissionId);
        } catch (EntityNotFoundException e) {
            throw new HttpNotFoundException("Rol no encontrado");
        }
    }

    @Override
    @Transactional
    public RoleDto removePermissionToRole(Integer roleId, Integer permissionId) {
        try {
            return roleRepository.removePermissionToRole(roleId, permissionId);
        } catch (EntityNotFoundException e) {
            throw new HttpNotFoundException("Rol no encontrado");
        }
    }


    @Override
    public void deletedById(int roleId) {
        roleRepository.deletedById(roleId);
    }
}
