package co.edu.cuc.onlinelibrary.auth.domain.repository;

import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserDto> findTopByUsername(String username);
    boolean existsByUsername(String username);

    void updateCurrentToken(String username, String token);
    void updatePassword(String username, String newPasswordEncode);

    Page<UserDto> findAll(Pageable pageable);
    Optional<UserDto> findById(int userId);
    UserDto save(UserDto userDto);
}
