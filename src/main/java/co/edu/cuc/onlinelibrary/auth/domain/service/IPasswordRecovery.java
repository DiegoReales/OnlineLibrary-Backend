package co.edu.cuc.onlinelibrary.auth.domain.service;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PasswordRecoveryDto;

public interface IPasswordRecovery {
    void createPasswordRecovery(int userId, int passwordLength, long expirationMinutes);
    PasswordRecoveryDto save(PasswordRecoveryDto passwordRecovery);
}
