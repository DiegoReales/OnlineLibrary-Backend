package co.edu.cuc.onlinelibrary.auth.persistence;

import co.edu.cuc.onlinelibrary.auth.domain.dto.RoleDto;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IRoleRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.crudrepository.RoleCrudRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.crudrepository.RolePermissionCrudRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.RoleEntity;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.RolePermissionEntity;
import co.edu.cuc.onlinelibrary.auth.persistence.mapper.RoleMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RoleRepository implements IRoleRepository {

    private final RoleCrudRepository roleCrudRepository;
    private final RolePermissionCrudRepository rolePermissionCrudRepository;

    private final RoleMapper roleMapper;

    private final EntityManager entityManager;

    @Override
    public Collection<String> findPermissionsByRoleId(int roleId) {
        return roleCrudRepository.findPermissionsByRoleId(roleId);
    }

    @Override
    public List<RoleDto> findAll() {
        List<RoleEntity> entities = (List<RoleEntity>) roleCrudRepository.findAll();
        return entities.stream()
                .map(roleMapper::toRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDto> findById(int roleId) {
        return roleCrudRepository.findById(roleId).map(roleMapper::toRoleDto);
    }

    @Override
    public Optional<RoleDto> findByIdWithPermission(int roleId) {
        return roleCrudRepository.findById(roleId).map(r -> {
            Hibernate.initialize(r.getPermissions());
            return roleMapper.toRoleWithPermissionsDto(r);
        });
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        RoleEntity entity = roleMapper.toRoleEntity(roleDto);
        return roleMapper.toRoleDto(roleCrudRepository.save(entity));
    }

    @Override
    public RoleDto syncPermissionsToRole(Integer roleId, List<Integer> permissionIds) throws EntityNotFoundException {
        if (!roleCrudRepository.existsById(roleId)) {
            throw new EntityNotFoundException("Role no encontrado con id: " + roleId);
        }

        List<RolePermissionEntity> rolePermissions = permissionIds.stream()
                .map(e -> new RolePermissionEntity(roleId, e))
                .toList();

        rolePermissionCrudRepository.deleteByRoleId(roleId);
        rolePermissionCrudRepository.saveAll(rolePermissions);

        entityManager.flush();
        entityManager.clear();

        return this.findByIdWithPermission(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role no encontrado con id: " + roleId));
    }

    @Override
    public RoleDto addPermissionToRole(Integer roleId, Integer permissionId) {
        if (!roleCrudRepository.existsById(roleId)) {
            throw new EntityNotFoundException("Role no encontrado con id: " + roleId);
        }

        if (!rolePermissionCrudRepository.existsByRoleIdAndPermissionId(roleId, permissionId)) {
            RolePermissionEntity rolePermission = new RolePermissionEntity(roleId, permissionId);
            rolePermissionCrudRepository.save(rolePermission);
        }

        return this.findByIdWithPermission(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role no encontrado con id: " + roleId));
    }

    @Override
    public RoleDto removePermissionToRole(Integer roleId, Integer permissionId) {
        if (!roleCrudRepository.existsById(roleId)) {
            throw new EntityNotFoundException("Role no encontrado con id: " + roleId);
        }

        rolePermissionCrudRepository.deleteByRoleIdAndPermissionId(roleId, permissionId);

        return this.findByIdWithPermission(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role no encontrado con id: " + roleId));
    }

    @Override
    public void deletedById(int roleId) { roleCrudRepository.deleteById(roleId); }
}
