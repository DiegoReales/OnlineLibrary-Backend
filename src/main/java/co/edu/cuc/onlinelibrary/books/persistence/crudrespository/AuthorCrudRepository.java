package co.edu.cuc.onlinelibrary.books.persistence.crudrespository;

import co.edu.cuc.onlinelibrary.books.persistence.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorCrudRepository extends CrudRepository<AuthorEntity, Integer> {
}
