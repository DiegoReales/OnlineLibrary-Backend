package co.edu.cuc.onlinelibrary.auth.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class PermissionEntity extends AuditorEntity {

    @Id
    @SequenceGenerator(name = "seq_permissions", sequenceName = "seq_permissions_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permissions")
    @Column(name = "permission_id")
    private Integer id;
    private String name;
    private String description;

}
