package co.edu.cuc.onlinelibrary.books.persistence.crudrespository;

import co.edu.cuc.onlinelibrary.books.persistence.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookCrudRepository extends CrudRepository<BookEntity, Integer> {
}
