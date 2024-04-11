package co.edu.cuc.onlinelibrary.auth.domain.service;

import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.UserRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.util.CustomPage;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    UserDto findTopByUsername(String username);
    void updateCurrentToken(String username, String token);
    CustomPage<UserDto> findAll(Pageable pageable);
    UserDto findById(int userId);
    UserDto create(UserRequestBody requestBody);
    UserDto save(UserDto userDto);
    UserDto deletedById(int userId);
}
