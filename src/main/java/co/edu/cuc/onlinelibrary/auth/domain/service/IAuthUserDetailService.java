package co.edu.cuc.onlinelibrary.auth.domain.service;

import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuBranchDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.PasswordRecoveryDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.RoleDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface IAuthUserDetailService extends UserDetailsService {
    RoleDto getRole(int roleId) throws UsernameNotFoundException;

    Collection<String> getPermissionsByRoleId(int roleId);

    Collection<MenuBranchDto> getMenuTreeByRoleId(int roleId);

    Optional<PasswordRecoveryDto> findPasswordRecovery(int userId);

    void updateCurrentToken(String username, String token);
    void changePassword(String username, String newPasswordEncode);
    void savePasswordRecovery(PasswordRecoveryDto passwordRecovery);

}
