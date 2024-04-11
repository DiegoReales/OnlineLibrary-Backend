package co.edu.cuc.onlinelibrary.auth.persistence.mapper;

import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "menu", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "role.permissions", ignore = true)
    UserDto toUserDto(UserEntity userEntity);
    
    UserEntity toUserEntity(UserDto userDto);

    List<UserEntity> listUserEntity(List<UserDto> userDtoList);
    List<UserDto> listUserDto(List<UserEntity> userEntityList);


}
