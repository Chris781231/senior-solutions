package books;

import books.dto.BookDTO;
import books.entity.Book;
import books.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private ModelMapper modelMapper;

    private BookRepository bookRepo;

    public List<BookDTO> listBooks() {
        List<Book> books = bookRepo.findAll();
        Type targetListType = new TypeToken<List<BookDTO>>(){}.getType();
        return modelMapper.map(books, targetListType);
    }
}
