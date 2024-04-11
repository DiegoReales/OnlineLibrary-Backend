package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PasswordRecoveryDto;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IPasswordRecoveryRepository;
import co.edu.cuc.onlinelibrary.auth.domain.service.IPasswordRecovery;
import co.edu.cuc.onlinelibrary.auth.domain.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordRecovery implements IPasswordRecovery {

    private final IPasswordRecoveryRepository passwordRecoveryRepository;
    private final PasswordUtil passwordUtil;

    @Override
    public void createPasswordRecovery(int userId, int passwordLength, long expirationMinutes) {
        String password = passwordUtil.generatePassword(passwordLength);
        log.info("Password Recovery Created: {}", password);

        PasswordRecoveryDto passwordRecoveryDto = new PasswordRecoveryDto();

        passwordRecoveryDto.setUserId(userId);
        passwordRecoveryDto.setRecovered(false);
        passwordRecoveryDto.setPassword(passwordUtil.encode(password));
        passwordRecoveryDto.setCreatedAt(LocalDateTime.now());
        passwordRecoveryDto.setExpiration(LocalDateTime.now().plusMinutes(expirationMinutes));

        this.save(passwordRecoveryDto);
    }

    @Override
    public PasswordRecoveryDto save(PasswordRecoveryDto passwordRecovery) {
        return passwordRecoveryRepository.save(passwordRecovery);
    }
}
