package books.controller;

import books.command.CreateAuthorCommand;
import books.command.CreateBookToAuthorCommand;
import books.dto.AuthorDTO;
import books.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> listAll() {
        return authorService.listAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO findAuthorById(@PathVariable long id) {
        return authorService.findAuthorById(id);
    }

    @PostMapping
    public AuthorDTO saveAuthor(@RequestBody @Valid CreateAuthorCommand command) {
        return authorService.saveAuthor(command);
    }

    @PostMapping("/{id}/books")
    public AuthorDTO addBookToAuthor(@PathVariable long id, @RequestBody @Valid CreateBookToAuthorCommand command) {
        return authorService.addBookToAuthor(id, command);
    }

    @DeleteMapping
    public void deleteAll() {
        authorService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable long id) {
        authorService.deleteAuthorById(id);
    }
}
