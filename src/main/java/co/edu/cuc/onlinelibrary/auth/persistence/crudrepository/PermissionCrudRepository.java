package co.edu.cuc.onlinelibrary.auth.persistence.crudrepository;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.PermissionEntity;
import org.springframework.data.repository.CrudRepository;

public interface PermissionCrudRepository extends CrudRepository<PermissionEntity, Integer> {
}
