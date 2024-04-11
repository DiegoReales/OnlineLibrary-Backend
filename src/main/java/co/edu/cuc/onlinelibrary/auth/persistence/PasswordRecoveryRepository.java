package co.edu.cuc.onlinelibrary.auth.persistence;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PasswordRecoveryDto;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IPasswordRecoveryRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.crudrepository.PasswordRecoveryCrudRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.PasswordRecoveryEntity;
import co.edu.cuc.onlinelibrary.auth.persistence.mapper.PasswordRecoveryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PasswordRecoveryRepository implements IPasswordRecoveryRepository {

	private final PasswordRecoveryCrudRepository passwordRecoveryCrudRepository;
	private final PasswordRecoveryMapper passwordRecoveryMapper;

	@Override
	public PasswordRecoveryDto save(PasswordRecoveryDto passwordRecoveryDto) {
		PasswordRecoveryEntity entity = passwordRecoveryMapper.toPasswordRecoveryEntity(passwordRecoveryDto);
		PasswordRecoveryEntity entitySaved = passwordRecoveryCrudRepository.save(entity);
		return passwordRecoveryMapper.toPasswordRecoveryDto(entitySaved);
	}

	@Override
	public Optional<PasswordRecoveryDto> findPasswordRecoveryByUserId(int userId) {
		Optional<PasswordRecoveryEntity> entity = passwordRecoveryCrudRepository.findTopByUserIdOrderByCreatedAtDesc(userId);
		return entity.map(passwordRecoveryMapper::toPasswordRecoveryDto);
	}
}
