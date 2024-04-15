package co.edu.cuc.onlinelibrary.books.persistence.entity;

import co.edu.cuc.onlinelibrary.auth.persistence.entity.AuditorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "books_borrowed")
public class BookBorrowedEntity extends AuditorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "checkouts")
    private LocalDateTime checkOut;

    @Column(name = "checkin")
    private LocalDate checkIn;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private BookUserEntity user;

    @ManyToOne
    @JoinColumn(name = "status", insertable = false, updatable = false)
    private BorrowStatusEntity borrowStatus;
}
