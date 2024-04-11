package co.edu.cuc.onlinelibrary.auth.persistence.mapper;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PermissionDto;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.PermissionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto toPermissionDto(PermissionEntity entity);
    List<PermissionDto> toListPermissionDto(List<PermissionEntity> entity);

    PermissionEntity toPermissionEntity(PermissionDto dto);
    List<PermissionEntity> toListPermissionEntity(List<PermissionDto> dto);
}
