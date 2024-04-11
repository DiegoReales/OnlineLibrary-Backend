package co.edu.cuc.onlinelibrary.auth.persistence.mapper;

import co.edu.cuc.onlinelibrary.auth.domain.dto.RoleDto;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    RoleDto toRoleDto(RoleEntity roleEntity);

    RoleDto toRoleWithPermissionsDto(RoleEntity roleEntity);

    @Mapping(target = "permissions", ignore = true)
    RoleEntity toRoleEntity(RoleDto roleDto);
}
