package co.edu.cuc.onlinelibrary.books.domain.service;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookBorrowedDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookCheckOutRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookRequestBody;

import java.util.List;

public interface IBookService {
    List<BookDto> findAll();
    List<BookDto> findAvailable();

    BookDto findById(Integer bookId);

    BookDto create(BookRequestBody req);
    BookDto update(BookRequestBody req, Integer bookId);

    BookDto save(BookDto book);

    void deleteById(Integer bookId);

}
