package co.edu.cuc.onlinelibrary.books.web.controller;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.books.domain.dto.AuthorDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.BookDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.BookRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.service.IBookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private static final String MODULE = "BOOK";

    private final IBookService bookService;
    private final HttpServletRequest servletRequest;

    @GetMapping
    public ResponseEntity<List<BookDto>> indexBook() {
        List<BookDto> response = bookService.findAll();

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los libros.")
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getBookAvailable() {
        List<BookDto> response = bookService.findAvailable();

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los libros disponibles.")
                .build();

        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookDto> show(@PathVariable("book_id") Integer bookId) {
        BookDto response = bookService.findById(bookId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Consultar libro: " + bookId)
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BookDto> store(@RequestBody BookRequestBody requestBody) {
        BookDto response = bookService.create(requestBody);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("CREATE")
                .message("Registrar libro: " + response.getId())
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{book_id}")
    public ResponseEntity<BookDto> update(@RequestBody BookRequestBody requestBody, @PathVariable("book_id") Integer bookId) {
        BookDto response = bookService.update(requestBody, bookId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("UPDATE")
                .message("Modificar libro: " + bookId)
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<AuthorDto> destroy(@PathVariable("book_id") Integer bookId) {
        bookService.deleteById(bookId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("DELETE")
                .message("Eliminando libro: " + bookId)
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
