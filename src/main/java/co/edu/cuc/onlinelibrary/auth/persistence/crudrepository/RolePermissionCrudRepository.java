package co.edu.cuc.onlinelibrary.auth.persistence.crudrepository;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RolePermissionCrudRepository extends JpaRepository<RolePermissionEntity, Integer> {

    boolean existsByRoleIdAndPermissionId(Integer roleId, Integer permissionId);

    @Modifying
    @Query(value = "DELETE FROM RolePermissionEntity r WHERE r.roleId = ?1")
    void deleteByRoleId(Integer roleId);

    @Modifying
    @Query("DELETE FROM RolePermissionEntity r WHERE r.roleId = ?1 AND r.permissionId = ?2")
    void deleteByRoleIdAndPermissionId(Integer roleId, Integer permissionId);
}
