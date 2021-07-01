package ebmatches;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getWinnerDrawTest() {
        Game game = new Game("Magyarország", "Portugália", 3, 3);

        assertEquals("draw", game.getWinner());

    }

    @Test
    void getWinnerFirstWinTest() {
        Game game = new Game("Magyarország", "Portugália", 6, 3);

        assertEquals("Magyarország", game.getWinner());

    }

    @Test
    void getWinnerSecondWinTest() {
        Game game = new Game("Magyarország", "Portugália", 3, 6);

        assertEquals("Portugália", game.getWinner());

    }

    @Test
    void getScoreByCountryTest() {
        assertEquals(3, new Game("Hungary", "Italy", 3, 2).getScoreByCountry("Hungary"));
        assertEquals(2, new Game("Hungary", "Italy", 3, 2).getScoreByCountry("Italy"));
        assertEquals(0, new Game("Hungary", "Italy", 3, 2).getScoreByCountry("Portugal"));
    }

    @Test
    void getScoreByCountryWithNullTest() {
        assertEquals(0, new Game("Hungary", "Italy", 3, 2).getScoreByCountry(null));
    }
}