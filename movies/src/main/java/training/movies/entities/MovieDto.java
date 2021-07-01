package training.movies.entities;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private String title;

    private double length;

    private double avgRating;
}
