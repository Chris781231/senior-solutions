package ebmatches;

import java.util.Optional;

public class GameService {

    private GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Optional<Game> getLargestGoalDiffMatch() {
        return repository.getLargestGoalDiffMatch();
    }

    public int getAllKickedGoalByCountry(String country) {
        return repository.getAllKickedGoalByCountry1(country);
    }

    public String getMostGoalKickCountry() {
        return repository.getMostGoalKickCountry();
    }
}
