package ebmatches;

public class Game {

    private String firstCountry;
    private String secondCountry;
    private int firstCountryScore;
    private int secondCountryScore;

    public Game(String firstCountry, String secondCountry, int firstCountryScore, int secondCountryScore) {
        this.firstCountry = firstCountry;
        this.secondCountry = secondCountry;
        this.firstCountryScore = firstCountryScore;
        this.secondCountryScore = secondCountryScore;
    }

    public String getFirstCountry() {
        return firstCountry;
    }

    public void setFirstCountry(String firstCountry) {
        this.firstCountry = firstCountry;
    }

    public String getSecondCountry() {
        return secondCountry;
    }

    public void setSecondCountry(String secondCountry) {
        this.secondCountry = secondCountry;
    }

    public int getFirstCountryScore() {
        return firstCountryScore;
    }

    public void setFirstCountryScore(int firstCountryScore) {
        this.firstCountryScore = firstCountryScore;
    }

    public int getSecondCountryScore() {
        return secondCountryScore;
    }

    public void setSecondCountryScore(int secondCountryScore) {
        this.secondCountryScore = secondCountryScore;
    }

    @Override
    public String toString() {
        return "Game{" +
                "firstCountry='" + firstCountry + '\'' +
                ", secondCountry='" + secondCountry + '\'' +
                ", firstCountryScore=" + firstCountryScore +
                ", secondCountryScore=" + secondCountryScore +
                '}';
    }

    public String getWinner() {
        if (firstCountryScore > secondCountryScore) {
            return firstCountry;
        }
        if (secondCountryScore > firstCountryScore) {
            return secondCountry;
        }
        return "draw";
    }

    public int getScoreByCountry(String country) {
        int score = 0;
        if (firstCountry.equalsIgnoreCase(country)) {
            score = firstCountryScore;
        }
        if (secondCountry.equalsIgnoreCase(country)) {
            score = secondCountryScore;
        }
        return score;
    }
}
