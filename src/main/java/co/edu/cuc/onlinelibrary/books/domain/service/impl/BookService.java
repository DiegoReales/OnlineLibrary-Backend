package co.edu.cuc.onlinelibrary.books.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpNotFoundException;
import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.repository.IBookRepository;
import co.edu.cuc.onlinelibrary.books.domain.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private static final String NOT_FOUND_MSG = "Libro no encontrado";

    private final IBookRepository bookRepository;

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public BookDto findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .filter(e -> e.getActive().equals(Boolean.TRUE))
                .orElseThrow(() -> new HttpNotFoundException(NOT_FOUND_MSG));
    }

    @Override
    public BookDto create(BookRequestBody req) {
        BookDto book = new BookDto();
        book.fillFromRequestBody(req);
        return bookRepository.save(book);
    }

    @Override
    public BookDto update(BookRequestBody req, Integer bookId) {
        BookDto book = this.findById(bookId);
        book.fillFromRequestBody(req);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Integer bookId) {
        BookDto book = this.findById(bookId);
        bookRepository.deleteById(book.getId());
    }
}
