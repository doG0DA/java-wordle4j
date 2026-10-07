package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleGameTest {

    private WordleGame game;

    @BeforeEach
    void setUp() {
        PrintWriter log = new PrintWriter(System.out, true);
        WordleDictionary dictionary = new WordleDictionary(
                List.of("герой", "гонец", "пирог", "город"));
        game = new WordleGame(dictionary, "герой", log);
    }

    @Test
    void newGame() {
        assertEquals(6, game.getSteps());
        assertFalse(game.isFinished());
    }

    @Test
    void makeMove() throws Exception {
        assertEquals("+^-^-", game.makeMove("гонец"));
        assertEquals(5, game.getSteps());
    }

    @Test
    void win() throws Exception {
        game.makeMove("герой");
        assertTrue(game.getWin());
        assertTrue(game.isFinished());
    }


}