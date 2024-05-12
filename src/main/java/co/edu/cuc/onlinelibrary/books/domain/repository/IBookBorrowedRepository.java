package co.edu.cuc.onlinelibrary.books.domain.repository;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookBorrowedDto;

import java.util.List;
import java.util.Optional;

public interface IBookBorrowedRepository {
    List<BookBorrowedDto> findAll();

    List<BookBorrowedDto> findByStatus(Integer status);
    List<BookBorrowedDto> findByUserIdAndStatus(int userId, Integer status);

    Optional<BookBorrowedDto> findById(Integer bookBorrowedId);
    BookBorrowedDto save(BookBorrowedDto bookBorrowedDto);

}
