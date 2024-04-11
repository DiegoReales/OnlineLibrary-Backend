package co.edu.cuc.onlinelibrary.parameter.persistence.mapper;

import co.edu.cuc.onlinelibrary.parameter.domain.dto.ParameterDto;
import co.edu.cuc.onlinelibrary.parameter.persistence.entity.ParameterEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParameterMapper {
    ParameterDto toParameterDto(ParameterEntity entity);
    ParameterEntity toParameterEntity(ParameterDto dto);
}
