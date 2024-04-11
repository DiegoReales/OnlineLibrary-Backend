package co.edu.cuc.onlinelibrary.books.persistence.entity;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.AuditorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books_borrowed")
public class BookBorrowed extends AuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private Integer checkOut;
    private Integer checkIn;
    private Integer status;
}
