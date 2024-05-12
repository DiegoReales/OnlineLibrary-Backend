package co.edu.cuc.onlinelibrary.books.persistence.crudrespository;

import co.edu.cuc.onlinelibrary.books.persistence.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookCrudRepository extends CrudRepository<BookEntity, Integer> {
    List<BookEntity> findByStatus(Integer status);

}
