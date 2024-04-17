package co.edu.cuc.onlinelibrary.auth.domain.dto;

import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.UserRequestBody;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends AuditorDto implements UserDetails {

    private Integer id;

    @JsonIgnore
    private Integer roleId;

    private String username;

    @JsonIgnore
    private String password;
    private Boolean changePassword;

    private Boolean singleSession;

    @JsonIgnore
    private String currentToken;

    private Boolean active;

    private String name;
    private String lastname;
    private String dni;

    private RoleDto role;

    private Collection<String> permissions;

    private Collection<MenuBranchDto> menu;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return !this.changePassword;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.active;
    }

    public void fillFromUserRequestBody(UserRequestBody requestBody){
        this.username= requestBody.getUsername();
        this.roleId = requestBody.getRoleId();
        this.active = requestBody.getActive();
        this.name = requestBody.getName();
        this.lastname = requestBody.getLastname();
        this.dni = requestBody.getDni();
    }


}
