package co.edu.cuc.onlinelibrary.books.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpBadRequestException;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpNotFoundException;
import co.edu.cuc.onlinelibrary.books.domain.dto.BookBorrowedDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookCheckOutRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.repository.IBookBorrowedRepository;
import co.edu.cuc.onlinelibrary.books.domain.repository.IBookRepository;
import co.edu.cuc.onlinelibrary.books.domain.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private static final String NOT_FOUND_MSG = "Libro no encontrado";

    private final IBookRepository bookRepository;
    private final IBookBorrowedRepository bookBorrowedRepository;

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .filter(e -> e.getActive().equals(Boolean.TRUE))
                .toList();
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
        book.setActive(true);
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
        book.setActive(false);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public BookBorrowedDto checkOut(BookCheckOutRequestBody requestBody) {
        BookBorrowedDto borrowedDto = new BookBorrowedDto();
        borrowedDto.fillFromCheckOutRequestBody(requestBody);

        BookDto book = this.findById(borrowedDto.getBookId());
        Integer available = book.getAvailable();

        if (available == 0) {
            throw new HttpBadRequestException("Libro solicitado no se encuentra disponible.");
        }

        book.decrementAvailable();
        bookRepository.save(book);
        borrowedDto.setCheckOut(LocalDateTime.now());
        return bookBorrowedRepository.save(borrowedDto);
    }

    @Override
    @Transactional
    public BookBorrowedDto checkIn(Integer bookBorrowedId) {
        BookBorrowedDto borrowedDto = bookBorrowedRepository.findById(bookBorrowedId)
                .orElseThrow(() -> new HttpNotFoundException("Prestamo no encontrado."));

        BookDto book = this.findById(borrowedDto.getBookId());
        book.incrementAvailable();
        bookRepository.save(book);

        borrowedDto.setCheckIn(LocalDateTime.now());
        return bookBorrowedRepository.save(borrowedDto);
    }
}
