package training.movies.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training.movies.entities.CreateMovieCommand;
import training.movies.entities.Movie;
import training.movies.entities.MovieDto;
import training.movies.entities.UpdateMovieRatingCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieService {

    private ModelMapper modelMapper;

    private AtomicLong atomicLong = new AtomicLong();

    private List<Movie> movies = new ArrayList<>(Collections.synchronizedList(List.of(
            new Movie(atomicLong.incrementAndGet(), "Titanic", 120, new ArrayList<>(List.of(2.4, 3.6)))
    )));

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MovieDto addMovie(CreateMovieCommand command) {
        Movie movie = new Movie(atomicLong.incrementAndGet(), command.getTitle(), command.getLength(), new ArrayList<>());
        movies.add(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    public List<MovieDto> getMoviesByTitle(Optional<String> titlePrefix) {
        List<Movie> result = this.movies.stream()
                .filter(movie -> titlePrefix.isEmpty() || movie.getTitle().toLowerCase().startsWith(titlePrefix.get().toLowerCase()))
                .toList();
        return result.stream()
                .map(e -> modelMapper.map(e, MovieDto.class))
                .toList();
    }

    public MovieDto findMovieById(Long id) {
        return modelMapper.map(
                movies.stream()
                        .filter(m -> m.getId().equals(id))
                        .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot find movie with this id: " + id)),
                MovieDto.class);
    }

    public MovieDto addRatingById(Long id, UpdateMovieRatingCommand command) {
        Movie movie = movies.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot find movie with this id: " + id));
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDto.class);
    }

    public void deleteMovieById(Long id) {
        Movie movie = movies.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot find movie with this id" + id));
        movies.remove(movie);
    }
}
