package co.edu.cuc.onlinelibrary.books.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpNotFoundException;
import co.edu.cuc.onlinelibrary.books.domain.dto.AuthorDto;
import co.edu.cuc.onlinelibrary.books.domain.dto.request.AuthorRequestBody;
import co.edu.cuc.onlinelibrary.books.domain.repository.IAuthorRepository;
import co.edu.cuc.onlinelibrary.books.domain.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

    private static final String NOT_FOUND_MSG = "Autor no encontrado";

    private final IAuthorRepository authorRepository;

    @Override
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream()
                .filter(e -> e.getActive().equals(Boolean.TRUE))
                .toList();
    }

    @Override
    public AuthorDto findById(Integer bookId) {
        return authorRepository.findById(bookId)
                .filter(e -> e.getActive().equals(Boolean.TRUE))
                .orElseThrow(() -> new HttpNotFoundException(NOT_FOUND_MSG));
    }

    @Override
    public AuthorDto create(AuthorRequestBody req) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.fillFromRequestBody(req);
        authorDto.setActive(true);
        return authorRepository.save(authorDto);
    }

    @Override
    public AuthorDto update(AuthorRequestBody req, Integer authorId) {
        AuthorDto authorDto = this.findById(authorId);
        authorDto.fillFromRequestBody(req);
        return authorRepository.save(authorDto);
    }

    @Override
    public void deleteById(Integer authorId) {
        AuthorDto authorDto = this.findById(authorId);
        authorDto.setActive(false);
        authorRepository.save(authorDto);
    }
}
