package co.edu.cuc.onlinelibrary.auth.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menus")
public class MenuEntity {

    @Id
    @SequenceGenerator(name = "seq_menus", sequenceName = "seq_menus_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_menus")
    @Column(name = "menu_id")
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "permission_id")
    private Integer permissionId;

    private String route;
    private String title;
    private String icon;
    @Column(insertable = false)
    private Boolean active;
    @Column(name = "order_item")
    private Integer order;
}
