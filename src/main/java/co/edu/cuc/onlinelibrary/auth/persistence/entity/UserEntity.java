package co.edu.cuc.onlinelibrary.auth.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private String name;
    private String lastname;
    private String dni;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private RoleEntity role;
}
