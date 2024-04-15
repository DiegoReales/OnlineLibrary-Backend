package co.edu.cuc.onlinelibrary.books.persistence.crudrespository;

import co.edu.cuc.onlinelibrary.books.persistence.entity.BookBorrowedEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookBorrowedCrudRepository extends CrudRepository<BookBorrowedEntity, Integer> {
}
