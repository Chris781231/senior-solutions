package books;

import books.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping
    public List<BookDTO> listAll() {
        return bookService.listBooks();
    }
}
