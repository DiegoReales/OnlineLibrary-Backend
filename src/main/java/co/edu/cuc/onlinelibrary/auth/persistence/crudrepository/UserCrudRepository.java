package co.edu.cuc.onlinelibrary.auth.persistence.crudrepository;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<UserEntity, Integer> {

    Page<UserEntity> findAll(Pageable pageable);
    Optional<UserEntity> findTopByUsername(String username);
    boolean existsByUsername(String username);

    @Modifying
    @Query("update UserEntity u set u.currentToken = ?1 where u.username = ?2")
    void updateCurrentTokenByUsername(String token, String username);

    @Modifying
    @Query("update UserEntity u set u.password = ?1 where u.username = ?2")
    void updatePasswordByUsername(String password, String username);
}
