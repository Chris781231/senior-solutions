package training.movies.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training.movies.entities.CreateMovieCommand;
import training.movies.entities.Movie;
import training.movies.entities.MovieDto;
import training.movies.entities.UpdateMovieRatingCommand;
import training.movies.services.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping()
    public List<MovieDto> getMoviesByTitle(@RequestParam Optional<String> titlePrefix) {
        return service.getMoviesByTitle(titlePrefix);
    }

    @GetMapping("/{id}")
    public MovieDto findMovieById(@PathVariable Long id) {
        return service.findMovieById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@RequestBody CreateMovieCommand command) {
        return service.addMovie(command);
    }

    @PutMapping("/{id}/rating")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MovieDto addRatingById(@PathVariable Long id, @RequestBody UpdateMovieRatingCommand command) {
        return service.addRatingById(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable Long id) {
        service.deleteMovieById(id);
    }
}
