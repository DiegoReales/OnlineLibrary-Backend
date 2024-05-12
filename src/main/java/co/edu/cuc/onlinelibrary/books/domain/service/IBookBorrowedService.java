package co.edu.cuc.onlinelibrary.books.domain.service;

import co.edu.cuc.onlinelibrary.books.domain.dto.BookBorrowedDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookCheckOutRequestBody;

import java.util.List;

public interface IBookBorrowedService {
    List<BookBorrowedDto> findAll();

    List<BookBorrowedDto> findPending();
    List<BookBorrowedDto> findPendingByUseId(int userId);

    List<BookBorrowedDto> findFinished();

    BookBorrowedDto findById(Integer bookBorrowedId);

    BookBorrowedDto checkOut(BookCheckOutRequestBody requestBody);
    BookBorrowedDto checkIn(Integer bookBorrowedId);

}
