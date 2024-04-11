package co.edu.cuc.onlinelibrary.auth.domain.dto;

import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.UserRequestBody;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String firstname;
    private String secondname;
    private String firstsurname;
    private String secondsurname;
    private String dni;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dniexpdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private String email;
    private String celularphone;
    private String localphone;

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
        this.firstname = requestBody.getFirstname();
        this.secondname = requestBody.getSecondname();
        this.firstsurname = requestBody.getFirstsurname();
        this.secondsurname = requestBody.getSecondsurname();
        this.dni = requestBody.getDni();
        this.dniexpdate = requestBody.getDniexpdate();
        this.birthdate = requestBody.getBirthdate();
        this.email = requestBody.getEmail();
        this.celularphone = requestBody.getCelularphone();
        this.localphone = requestBody.getLocalphone();
    }


}
