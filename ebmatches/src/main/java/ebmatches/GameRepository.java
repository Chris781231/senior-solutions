package ebmatches;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameRepository {

    private List<Game> games;

    public GameRepository(List<Game> games) {
        this.games = new ArrayList<>(games);
    }

    public List<Game> getGames() {
        return new ArrayList<>(games);
    }

    public void addGame(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null");
        }
        games.add(game);
    }

    public void addGamesFromFile(Path file) {

        try (BufferedReader reader = Files.newBufferedReader(file)){
            String line;
            while ((line = reader.readLine()) != null) {
                games.add(getGame(line));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file");
        }
    }

    private Game getGame(String line) {
        String [] parts = line.split(";");
        return new Game(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
    }


    public Optional<Game> getLargestGoalDiffMatch() {
        return games.stream()
                .max(Comparator.comparingInt(g -> Math.abs(g.getFirstCountryScore() - g.getSecondCountryScore())));
    }

    public int getAllKickedGoalByCountry(String country) {
        int sumOfFirstCountry = games.stream()
                .filter(c -> c.getFirstCountry().equalsIgnoreCase(country))
                .mapToInt(Game::getFirstCountryScore)
                .sum();
        int sumOfSecondCountry = games.stream()
                .filter(c -> c.getSecondCountry().equalsIgnoreCase(country))
                .mapToInt(Game::getSecondCountryScore)
                .sum();
        return sumOfFirstCountry + sumOfSecondCountry;

    }

    public int getAllKickedGoalByCountry1(String country) {
        return games.stream()
                .filter(game -> game.getFirstCountry().equals(country) || game.getSecondCountry().equals(country))
                .mapToInt(game -> game.getScoreByCountry(country))
                .sum();

    }

        public String getMostGoalKickCountry() {

        return games.stream()
                .flatMap(p -> Stream.of(p.getFirstCountry(), p.getSecondCountry()))
                .distinct()
                .max(Comparator.comparing(this::getAllKickedGoalByCountry1))
                .orElseThrow();

    }
}
