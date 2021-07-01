package training.movies.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.OptionalDouble;

@Data
@NoArgsConstructor
public class Movie {

    private Long id;

    private String title;

    private double length;

    private List<Double> ratings;

    private @Setter(AccessLevel.NONE) double avgRating;

    public Movie(String title, double length, List<Double> ratings) {
        this.title = title;
        this.length = length;
        this.ratings = ratings;
        setAvgRating();
    }

    public Movie(Long id, String title, double length, List<Double> ratings) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.ratings = ratings;
        setAvgRating();
    }

    public void addRating(double rating) {
        ratings.add(rating);
        setAvgRating();
    }

    public void setAvgRating() {
        OptionalDouble average = ratings.stream()
                .mapToDouble(Double::doubleValue)
                .average();
        if (average.isPresent()) {
            avgRating = average.getAsDouble();
        } else {
            avgRating = 0;
        }
    }
}
