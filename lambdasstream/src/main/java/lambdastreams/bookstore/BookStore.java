package lambdastreams.bookstore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookStore {

    List<Book> books;

    public BookStore(List<Book> books) {
        this.books = books;
    }

    public int getNumberOfBooks() {
        return books.stream()
                .reduce(0, (i, b) -> i + b.getNumberOfBook(), Integer::sum);
    }

    public Optional<Book> findNewestBook() {
        return books.stream()
                .max(Comparator.comparingInt(Book::getYearOfPublish));
    }

    public int getTotalValue() {
        return books.stream()
                .reduce(0, (i, b) -> i + (b.getPrice() * b.getNumberOfBook()), Integer::sum);
    }

    public List<Book> getByYearOfPublish(int year) {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getYearOfPublish)).get(year);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}
