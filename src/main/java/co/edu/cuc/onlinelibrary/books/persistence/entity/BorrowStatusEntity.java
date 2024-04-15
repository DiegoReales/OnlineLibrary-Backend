package co.edu.cuc.onlinelibrary.books.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "borrow_status")
public class BorrowStatusEntity {
    @Id
    private Integer id;
    private String description;
}
