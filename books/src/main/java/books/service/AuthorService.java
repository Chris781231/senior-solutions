package books.service;

import books.command.CreateAuthorCommand;
import books.command.CreateBookToAuthorCommand;
import books.dto.AuthorDTO;
import books.entity.Author;
import books.entity.Book;
import books.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private ModelMapper modelMapper;

    private AuthorRepository authorRepo;

    public List<AuthorDTO> listAll() {
        List<Author> authors = authorRepo.findAll();
        Type targetListType = new TypeToken<List<AuthorDTO>>(){}.getType();
        return modelMapper.map(authors, targetListType);
    }

    public AuthorDTO findAuthorById(long id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cannot find author with %d id", id)));
        return modelMapper.map(author, AuthorDTO.class);
    }

    public AuthorDTO saveAuthor(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        authorRepo.save(author);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Transactional
    public AuthorDTO addBookToAuthor(long id, CreateBookToAuthorCommand command) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cannot find author with %d id", id)));
        Book book = new Book(command.getIsbn(), command.getTitle());
        author.addBook(book);
        return modelMapper.map(author, AuthorDTO.class);
    }

    public void deleteAll() {
        authorRepo.deleteAll();
    }

    public void deleteAuthorById(long id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cannot find author with %d id", id)));
        authorRepo.delete(author);
    }
}
