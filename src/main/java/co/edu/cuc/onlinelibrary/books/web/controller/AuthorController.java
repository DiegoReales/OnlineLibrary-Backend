package co.edu.cuc.onlinelibrary.books.web.controller;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.books.domain.dto.AuthorDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.AuthorRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.service.IAuthorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private static final String MODULE = "AUTHOR";

    private final IAuthorService authorService;
    private final HttpServletRequest servletRequest;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> index() {
        List<AuthorDto> response = authorService.findAll();

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los autores.")
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{author_id}")
    public ResponseEntity<AuthorDto> show(@PathVariable("author_id") Integer authorId) {
        AuthorDto response = authorService.findById(authorId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Consultar autor: " + authorId)
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> store(@RequestBody AuthorRequestBody requestBody) {
        AuthorDto response = authorService.create(requestBody);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("CREATE")
                .message("Registrar autor: " + response.getId())
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{author_id}")
    public ResponseEntity<AuthorDto> update(@RequestBody AuthorRequestBody requestBody, @PathVariable("author_id") Integer authorId) {
        AuthorDto response = authorService.update(requestBody, authorId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("UPDATE")
                .message("Modificar autor: " + authorId)
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{author_id}")
    public ResponseEntity<AuthorDto> destroy(@PathVariable("author_id") Integer authorId) {
        authorService.deleteById(authorId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("DELETE")
                .message("Eliminando autor: " + authorId)
                .build();
        servletRequest.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
