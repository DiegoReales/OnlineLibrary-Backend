package co.edu.cuc.onlinelibrary.books.web.controller;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.books.domain.dto.BookBorrowedDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookCheckOutRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.service.IBookBorrowedService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/borrowed")
@RequiredArgsConstructor
public class BookBorrowedController {

    private static final String MODULE = "BOOKS_BORROWED";

    private final IBookBorrowedService bookBorrowedService;
    private final HttpServletRequest servletRequest;

    @GetMapping
    public ResponseEntity<List<BookBorrowedDto>> index() {
        List<BookBorrowedDto> response = bookBorrowedService.findAll();

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los libros prestados.")
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<BookBorrowedDto>> getAllBooksBorrowedPending() {
        List<BookBorrowedDto> response = bookBorrowedService.findPending();

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los libros prestados pendientes.")
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/finished")
    public ResponseEntity<List<BookBorrowedDto>> getAllBooksBorrowedFinished() {
        List<BookBorrowedDto> response = bookBorrowedService.findFinished();

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los libros prestados entregados.")
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{borrowed_id}")
    public ResponseEntity<BookBorrowedDto> getAllBooksBorrowedFinished(@PathVariable("borrowed_id") Integer bookBorrowedId) {
        BookBorrowedDto response = bookBorrowedService.findById(bookBorrowedId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Consultar libro prestado " + bookBorrowedId)
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkout")
    public ResponseEntity<BookBorrowedDto> checkout(@RequestBody BookCheckOutRequestBody requestBody) {
        BookBorrowedDto response = bookBorrowedService.checkOut(requestBody);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("CREATE")
                .message("CheckOut libro: " + response.getBookId() + ", id_prestamo: " + response.getId())
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/checkin/{borrowed_id}")
    public ResponseEntity<BookBorrowedDto> update(@PathVariable("borrowed_id") Integer bookBorrowedId) {
        BookBorrowedDto response = bookBorrowedService.checkIn(bookBorrowedId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("UPDATE")
                .message("CheckIn libro: " + response.getBookId() + ", id_prestamo: " + response.getId())
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);

        return ResponseEntity.ok(response);
    }
}
