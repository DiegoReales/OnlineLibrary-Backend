package co.edu.cuc.onlinelibrary.auth.persistence.mapper;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PasswordRecoveryDto;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.PasswordRecoveryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PasswordRecoveryMapper {
	PasswordRecoveryEntity toPasswordRecoveryEntity(PasswordRecoveryDto dto);
	PasswordRecoveryDto toPasswordRecoveryDto(PasswordRecoveryEntity entity);
}
