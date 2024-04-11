package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuBranchDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuItemDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.PasswordRecoveryDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.RoleDto;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IMenuRepository;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IPasswordRecoveryRepository;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IRoleRepository;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IUserRepository;
import co.edu.cuc.onlinelibrary.auth.domain.service.IAuthUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AuthUserDetailService implements IAuthUserDetailService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IMenuRepository menuRepository;

    @Autowired
    private IPasswordRecoveryRepository passwordRecoveryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findTopByUsername(username)
                .filter(e -> e.getActive().equals(true))
                .orElseThrow(() -> new UsernameNotFoundException("Not found user into database"));
    }

    @Override
    public RoleDto getRole(int roleId) throws UsernameNotFoundException {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new UsernameNotFoundException("Not found role into database"));
    }

    @Override
    public Collection<String> getPermissionsByRoleId(int roleId) {
        return roleRepository.findPermissionsByRoleId(roleId);
    }

    @Override
    public Collection<MenuBranchDto> getMenuTreeByRoleId(int roleId) {
        List<MenuItemDto> menu = menuRepository.findByRoleId(roleId);
        return MenuBranchDto.buildNestedMenu(menu, 0);
    }

    @Override
    public Optional<PasswordRecoveryDto> findPasswordRecovery(int userId) {
        return passwordRecoveryRepository.findPasswordRecoveryByUserId(userId)
                .filter(e -> !e.getExpiration().isBefore(LocalDateTime.now()))
                .filter(e -> !e.getRecovered());
    }

    @Override
    public void updateCurrentToken(String username, String token) {
        userRepository.updateCurrentToken(username, token);
    }

    @Override
    public void changePassword(String username, String newPasswordEncode) {
        userRepository.updatePassword(username, newPasswordEncode);
    }

    @Override
    public void savePasswordRecovery(PasswordRecoveryDto passwordRecovery) {
        passwordRecoveryRepository.save(passwordRecovery);
    }

}
