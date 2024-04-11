package co.edu.cuc.onlinelibrary.parameter.persistence.crudrepository;

import co.edu.cuc.onlinelibrary.parameter.persistence.entity.ParameterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParameterCrudRepository extends CrudRepository<ParameterEntity, String> {
    Optional<ParameterEntity> findTopByKey(String key);

    @Query(value = "SELECT value FROM parameters WHERE parameter_key = :P_KEY LIMIT 1", nativeQuery = true)
    Optional<String> getValueByKey(@Param("P_KEY") String key);
}
