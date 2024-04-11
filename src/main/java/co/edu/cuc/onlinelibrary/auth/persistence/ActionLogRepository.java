package co.edu.cuc.onlinelibrary.auth.persistence;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IActionLogRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.crudrepository.ActionLogCrudRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.ActionLogEntity;
import co.edu.cuc.onlinelibrary.auth.persistence.mapper.ActionLogMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ActionLogRepository implements IActionLogRepository {

    private final ActionLogCrudRepository actionLogCrudRepository;
    private final ActionLogMapper actionLogMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void save(ActionLogDto actionLogDto) {
        ActionLogEntity entity = actionLogMapper.toActionLogEntity(actionLogDto);
        entity.setAfterValue(mapValue(actionLogDto.getAfterValue()));
        entity.setBeforeValue(mapValue(actionLogDto.getBeforeValue()));

        actionLogCrudRepository.save(entity);
    }

    private String mapValue(Object object) {
        String mappedValue = null;
        if (object != null) {
            try {
                mappedValue = objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                log.error("No fue posible convertir un valor a string para auditoría", e);
            }
        }
        return mappedValue;
    }
}
