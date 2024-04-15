package co.edu.cuc.onlinelibrary.books.domain.repository;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookBorrowedDto;

import java.util.List;
import java.util.Optional;

public interface IBookBorrowedRepository {
    List<BookBorrowedDto> findAll();
    Optional<BookBorrowedDto> findById(Integer bookBorrowedId);
    BookBorrowedDto save(BookBorrowedDto bookBorrowedDto);
}
