package books;

import books.command.CreateAuthorCommand;
import books.command.CreateBookToAuthorCommand;
import books.dto.AuthorDTO;
import books.dto.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerRestIT {

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    void init() {
        template.delete("/api/authors");
    }

    @Test
    void testListAll() {
        List<AuthorDTO> authors = template.exchange("/api/authors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AuthorDTO>>() {
                }).getBody();

        assertThat(authors)
                .hasSize(0);
    }

    @Test
    void testSaveThenList() {
        AuthorDTO result = template.postForObject("/api/authors",
                new CreateAuthorCommand("Stephen King"),
                AuthorDTO.class);

        AuthorDTO author = template.exchange("/api/authors/" + result.getId(),
                HttpMethod.GET,
                null,
                AuthorDTO.class).getBody();
        System.out.println(author);
        assert author != null;
        assertEquals("Stephen King", author.getName());
    }

    @Test
    void testAddBookToAuthor() {
        AuthorDTO author = template.postForObject("/api/authors",
                new CreateAuthorCommand("Stephen King"),
                AuthorDTO.class);

        AuthorDTO authorWithBook = template.postForObject("/api/authors/" + author.getId() + "/books",
                new CreateBookToAuthorCommand("A-1234", "Ragyogás"),
                AuthorDTO.class);

        assertEquals("Ragyogás",
                authorWithBook.getBooks().stream()
                        .filter(bookDTO -> bookDTO.getIsbn().equals("A-1234"))
                        .map(BookDTO::getTitle)
                        .toList().get(0));
    }

    @Test
    void testDeleteAuthorById() {
        AuthorDTO author = template.postForObject("/api/authors",
                new CreateAuthorCommand("Stephen King"),
                AuthorDTO.class);
        AuthorDTO authorWithBook = template.postForObject("/api/authors/" + author.getId() + "/books",
                new CreateBookToAuthorCommand("A-1234", "Ragyogás"),
                AuthorDTO.class);

        List<BookDTO> savedBooks = template.exchange("/api/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>() {
                }).getBody();

        assertThat(savedBooks)
                .hasSize(1);

        template.delete("/api/authors/" + author.getId());

        savedBooks = template.exchange("/api/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>() {
                }).getBody();

        assertThat(savedBooks)
                .hasSize(0);
    }
}
