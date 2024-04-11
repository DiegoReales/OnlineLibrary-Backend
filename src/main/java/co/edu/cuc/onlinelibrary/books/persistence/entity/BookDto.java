package co.edu.cuc.onlinelibrary.books.persistence.entity;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.AuditorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookDto extends AuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer authorId;
    private String isbn;
    private String title;
    private String description;
    private Integer stock;
    private Integer available;
    private Integer status;
    private Boolean active;
}
