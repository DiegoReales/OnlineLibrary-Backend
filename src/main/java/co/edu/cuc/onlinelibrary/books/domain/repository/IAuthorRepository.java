package co.edu.cuc.onlinelibrary.books.domain.repository;



import co.edu.cuc.onlinelibrary.books.domain.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository {
    List<AuthorDto> findAll();
    Optional<AuthorDto> findById(Integer authorId);
    AuthorDto save(AuthorDto author);
}
