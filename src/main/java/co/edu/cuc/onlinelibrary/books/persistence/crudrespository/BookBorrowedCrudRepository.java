package co.edu.cuc.onlinelibrary.books.persistence.crudrespository;

import co.edu.cuc.onlinelibrary.books.persistence.entity.BookBorrowedEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookBorrowedCrudRepository extends CrudRepository<BookBorrowedEntity, Integer> {
    List<BookBorrowedEntity> findByStatus(Integer status);
    List<BookBorrowedEntity> findByUserIdAndStatus(Integer userId, Integer status);
}
