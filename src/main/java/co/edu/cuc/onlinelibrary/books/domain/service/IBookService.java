package co.edu.cuc.onlinelibrary.books.domain.service;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookRequestBody;

import java.util.List;

public interface IBookService {
    List<BookDto> findAll();

    BookDto findById(Integer bookId);

    BookDto create(BookRequestBody req);
    BookDto update(BookRequestBody req, Integer bookId);
    void deleteById(Integer bookId);
}
