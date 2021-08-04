package moviesspringdatajpa;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@AllArgsConstructor
@Service
public class MovieService {

    private ModelMapper modelMapper;

    private MovieRepository movieRepo;

    public List<MovieDto> listAll() {
        List<Movie> movies = movieRepo.findAll();
        Type targetType = new TypeToken<List<MovieDto>>(){}.getType();
        return modelMapper.map(movies, targetType);
    }

    public MovieDto saveMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        movieRepo.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Transactional
    public MovieDto addRating(long id, AddRatingCommand command) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find this is"));
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDto.class);
    }
}
