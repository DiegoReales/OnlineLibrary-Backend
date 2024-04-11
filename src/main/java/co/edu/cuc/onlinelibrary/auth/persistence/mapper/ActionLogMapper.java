package co.edu.cuc.onlinelibrary.auth.persistence.mapper;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.ActionLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActionLogMapper {

    ActionLogDto toActionLogDto(ActionLogEntity actionLogEntity);

    @Mapping(target = "afterValue", ignore = true)
    @Mapping(target = "beforeValue", ignore = true)
    ActionLogEntity toActionLogEntity(ActionLogDto actionLogDto);
}
