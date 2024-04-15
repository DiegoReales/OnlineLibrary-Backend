package co.edu.cuc.onlinelibrary.books.persistence.entity;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.AuditorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity extends AuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "author_id")
    private Integer authorId;

    private String isbn;
    private String title;
    private String description;
    private Integer stock;
    private Integer available;
    private Integer status;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private AuthorEntity author;

    @ManyToOne
    @JoinColumn(name = "status", insertable = false, updatable = false)
    private BookStatusEntity bookStatus;
}
