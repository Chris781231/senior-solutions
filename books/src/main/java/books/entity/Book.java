package books.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private String title;

    @ManyToOne
//    @JoinColumn(name = "authors_id")
    @JsonBackReference
    private Author author;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
}
