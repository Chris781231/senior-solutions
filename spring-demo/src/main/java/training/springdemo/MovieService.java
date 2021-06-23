package training.springdemo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private List<Movie> movies = List.of(
            new Movie("Titanic", 120, 4.7),
            new Movie("Thor", 121, 4.5)
    );

    public List<Movie> listMovies() {
        return movies;
    }
}
