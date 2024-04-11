package co.edu.cuc.onlinelibrary.auth.persistence.crudrepository;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RoleCrudRepository extends CrudRepository<RoleEntity, Integer> {

    @Query(value = "SELECT p.name FROM permissions p " +
            "INNER JOIN role_permissions rp ON rp.permission_id = p.permission_id " +
            "WHERE rp.role_id = :P_ROLE_ID", nativeQuery = true)
    Collection<String> findPermissionsByRoleId(@Param("P_ROLE_ID") int roleId);
}
