package co.edu.cuc.onlinelibrary.auth.domain.repository;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PasswordRecoveryDto;

import java.util.Optional;

public interface IPasswordRecoveryRepository {
	PasswordRecoveryDto save(PasswordRecoveryDto passwordRecoveryDto);
	Optional<PasswordRecoveryDto> findPasswordRecoveryByUserId(int userId);
}
