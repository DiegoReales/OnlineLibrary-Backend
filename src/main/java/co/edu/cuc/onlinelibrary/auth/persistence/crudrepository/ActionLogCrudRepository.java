package co.edu.cuc.onlinelibrary.auth.persistence.crudrepository;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.ActionLogEntity;
import org.springframework.data.repository.CrudRepository;

public interface ActionLogCrudRepository extends CrudRepository<ActionLogEntity, Long> {
}
