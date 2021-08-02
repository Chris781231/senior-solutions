package books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private Long id;

    private String name;

    @ToString.Exclude
    private Set<BookDTO> books;
}
