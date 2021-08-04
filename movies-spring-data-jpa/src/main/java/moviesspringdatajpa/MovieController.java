package moviesspringdatajpa;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    @GetMapping
    public List<MovieDto> listAll() {
        return movieService.listAll();
    }

    @PostMapping
    public MovieDto saveMovie(@RequestBody CreateMovieCommand command) {
        return movieService.saveMovie(command);
    }

    @PostMapping("/{id}/rating")
    public MovieDto addRating(@PathVariable long id, @RequestBody AddRatingCommand command) {
        return movieService.addRating(id, command);
    }
}
