package co.edu.cuc.onlinelibrary.books.persistence.entity;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.AuditorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class AuthorEntity extends AuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private Boolean active;
}
