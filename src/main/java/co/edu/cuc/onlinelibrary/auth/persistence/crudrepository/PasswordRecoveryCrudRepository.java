package co.edu.cuc.onlinelibrary.auth.persistence.crudrepository;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.PasswordRecoveryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PasswordRecoveryCrudRepository extends CrudRepository<PasswordRecoveryEntity, Integer> {
    Optional<PasswordRecoveryEntity> findTopByUserIdOrderByCreatedAtDesc(int userId);
}
