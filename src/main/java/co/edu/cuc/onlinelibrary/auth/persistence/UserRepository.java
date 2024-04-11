package co.edu.cuc.onlinelibrary.auth.persistence;

import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IUserRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.crudrepository.UserCrudRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.UserEntity;
import co.edu.cuc.onlinelibrary.auth.persistence.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final UserCrudRepository userCrudRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserDto> findTopByUsername(String username) {
        Optional<UserEntity> entity = userCrudRepository.findTopByUsername(username);
        return entity.map(userMapper::toUserDto);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userCrudRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public void updateCurrentToken(String username, String token) {
        userCrudRepository.updateCurrentTokenByUsername(token, username);
    }

    @Override
    public void updatePassword(String username, String newPasswordEncode) {
        userCrudRepository.updatePasswordByUsername(newPasswordEncode, username);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        Page<UserEntity> userEntityList = userCrudRepository.findAll(pageable);
        List<UserDto> userDtoList = userMapper.listUserDto(userEntityList.getContent());
        return new PageImpl<>(userDtoList, pageable, userEntityList.getTotalElements());
    }

    @Override
    public Optional<UserDto> findById(int userId) {
        return userCrudRepository.findById(userId)
                .map(userMapper::toUserDto);
    }

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        return userMapper.toUserDto(userCrudRepository.save(userEntity));
    }

}
