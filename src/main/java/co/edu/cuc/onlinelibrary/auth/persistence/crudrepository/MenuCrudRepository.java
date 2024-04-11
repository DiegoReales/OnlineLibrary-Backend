package co.edu.cuc.onlinelibrary.auth.persistence.crudrepository;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.MenuEntity;
import co.edu.cuc.onlinelibrary.auth.persistence.projection.MenuItemProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuCrudRepository extends CrudRepository<MenuEntity, Integer> {

    @Query(value = "SELECT m.menu_id AS id, coalesce(m.parent_id, 0) AS parentId, p.name AS permission, m.title, m.icon, m.route, m.order_item " +
            "FROM menus m INNER JOIN permissions p ON m.permission_id = p.permission_id " +
            "INNER JOIN role_permissions rp ON rp.permission_id = p.permission_id " +
            "WHERE rp.role_id = :P_ROLE_ID AND m.active = true ORDER BY m.parent_id, m.order_item", nativeQuery = true)
    List<MenuItemProjection> findMenuByRoleId(@Param("P_ROLE_ID") int roleId);

}
