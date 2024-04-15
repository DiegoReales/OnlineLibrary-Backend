package co.edu.cuc.onlinelibrary.books.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book_status")
public class BookStatusEntity {
    @Id
    private Integer id;
    private String description;
}
