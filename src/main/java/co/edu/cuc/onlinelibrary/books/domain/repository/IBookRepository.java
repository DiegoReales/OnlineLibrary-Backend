package co.edu.cuc.onlinelibrary.books.domain.repository;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface IBookRepository {
    List<BookDto> findAll();
    List<BookDto> findAvailable();
    Optional<BookDto> findById(Integer bookId);
    BookDto save(BookDto book);
}
