package co.edu.cuc.onlinelibrary.books.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpBadRequestException;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpNotFoundException;
import co.edu.cuc.onlinelibrary.books.domain.dto.BookBorrowedDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.enums.BorrowStatusEnum;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookCheckOutRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.repository.IBookBorrowedRepository;
import co.edu.cuc.onlinelibrary.books.domain.service.IBookBorrowedService;
import co.edu.cuc.onlinelibrary.books.domain.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookBorrowedService implements IBookBorrowedService {

    private static final String NOT_FOUND_MSG = "Prestamo no encontrado.";

    private final IBookService bookService;
    private final IBookBorrowedRepository bookBorrowedRepository;

    @Override
    public List<BookBorrowedDto> findAll() {
        return bookBorrowedRepository.findAll();
    }

    @Override
    public List<BookBorrowedDto> findPending() {
        return bookBorrowedRepository.findByStatus(BorrowStatusEnum.PENDING.getValue());
    }

    @Override
    public List<BookBorrowedDto> findFinished() {
        return bookBorrowedRepository.findByStatus(BorrowStatusEnum.FINISHED.getValue());
    }

    @Override
    public BookBorrowedDto findById(Integer bookBorrowedId) {
        return bookBorrowedRepository.findById(bookBorrowedId)
                .orElseThrow(() -> new HttpNotFoundException(NOT_FOUND_MSG));
    }

    @Override
    @Transactional
    public BookBorrowedDto checkOut(BookCheckOutRequestBody requestBody) {
        BookBorrowedDto borrowedDto = new BookBorrowedDto();
        borrowedDto.fillFromCheckOutRequestBody(requestBody);

        BookDto book = bookService.findById(borrowedDto.getBookId());
        Integer available = book.getAvailable();

        if (available == 0) {
            throw new HttpBadRequestException("Libro solicitado no se encuentra disponible.");
        }

        book.decrementAvailable();
        bookService.save(book);

        borrowedDto.setStatus(BorrowStatusEnum.PENDING.getValue());
        borrowedDto.setCheckOut(LocalDateTime.now());
        return bookBorrowedRepository.save(borrowedDto);
    }

    @Override
    @Transactional
    public BookBorrowedDto checkIn(Integer bookBorrowedId) {
        BookBorrowedDto borrowedDto = bookBorrowedRepository.findById(bookBorrowedId)
                .orElseThrow(() -> new HttpNotFoundException(NOT_FOUND_MSG));

        BookDto book = bookService.findById(borrowedDto.getBookId());
        book.incrementAvailable();
        bookService.save(book);

        borrowedDto.setStatus(BorrowStatusEnum.FINISHED.getValue());
        borrowedDto.setCheckIn(LocalDateTime.now());
        return bookBorrowedRepository.save(borrowedDto);
    }
}
