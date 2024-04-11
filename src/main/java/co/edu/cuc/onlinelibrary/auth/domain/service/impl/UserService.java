package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.UserRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.enums.AuthParams;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpBadRequestException;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpNotFoundException;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IUserRepository;
import co.edu.cuc.onlinelibrary.auth.domain.service.IPasswordRecovery;
import co.edu.cuc.onlinelibrary.auth.domain.service.IUserService;
import co.edu.cuc.onlinelibrary.auth.domain.util.CustomPage;
import co.edu.cuc.onlinelibrary.parameter.domain.service.IParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private static final String NOT_FOUND_TEXT = "Usuario no encontrado";

    private final IUserRepository userRepository;
    private final IPasswordRecovery passwordRecovery;
    private final IParameterService parameterService;

    @Override
    public UserDto findTopByUsername(String username) {
        return userRepository.findTopByUsername(username)
                .orElseThrow(() -> new HttpNotFoundException(NOT_FOUND_TEXT));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCurrentToken(String username, String token) {
        userRepository.updateCurrentToken(username,token);
    }

    @Override
    public CustomPage<UserDto> findAll(Pageable pageable) {
        Page<UserDto> listUser = userRepository.findAll(pageable);
        return new CustomPage<>(listUser.getContent(), pageable, listUser.getTotalElements(),null);
    }

    @Override
    public UserDto findById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new HttpNotFoundException(NOT_FOUND_TEXT));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto create(UserRequestBody requestBody) {
        boolean existsUsername = userRepository.existsByUsername(requestBody.getUsername());

        if (existsUsername) {
            throw new HttpBadRequestException("¡Nombre de usuario " + requestBody.getUsername() + " ya existe!");
        }

        UserDto userDto = new UserDto();
        userDto.fillFromUserRequestBody(requestBody);
        userDto.setPassword("no_password");
        userDto.setSingleSession(true);
        userDto.setChangePassword(true);

        userDto = this.save(userDto);

        int passwordLength = parameterService.getIntegerValueByKey(AuthParams.AUTH_PASSWORD_LENGTH);
        long expirationMinutes = parameterService.getIntegerValueByKey(AuthParams.AUTH_PASSWORD_EXPMIN);
        this.passwordRecovery.createPasswordRecovery(userDto.getId(), passwordLength, expirationMinutes);

        return userDto;
    }

    @Override
    public UserDto save(UserDto userDto) {
        return userRepository.save(userDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto deletedById(int userId) {
        UserDto user = userRepository.findById(userId).
                orElseThrow(() -> new HttpNotFoundException(NOT_FOUND_TEXT));
        user.setActive(false);
        return userRepository.save(user);
    }

}
