package co.edu.cuc.onlinelibrary.auth.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends AuditorEntity {

    @Id
    @SequenceGenerator(name = "seq_users", sequenceName = "seq_users_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users")
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    private String username;
    private String password;

    @Column(name = "change_password")
    private Boolean changePassword;

    @Column(name = "single_session")
    private Boolean singleSession;

    @Column(name = "current_token")
    private String currentToken;

    private Boolean active;

    private String firstname;
    private String secondname;
    private String firstsurname;
    private String secondsurname;
    private String dni;
    private LocalDate dniexpdate;
    private LocalDate birthdate;
    private String email;
    private String celularphone;
    private String localphone;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private RoleEntity role;
}
