/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
/**
 *
 * @author kensl
 */
@DisplayName("Testes do Rock Paper Scissors Kata")
public class RockPaperScissorsTest {

    private RockPaperScissors game;

    @BeforeEach
    void setUp() {
        game = new RockPaperScissors();
    }

    @DisplayName("Testa cenários de empate")
    @Test
    void testDraws() {
        assertEquals(RockPaperScissors.Result.DRAW, game.play(RockPaperScissors.Move.ROCK, RockPaperScissors.Move.ROCK));
        assertEquals(RockPaperScissors.Result.DRAW, game.play(RockPaperScissors.Move.PAPER, RockPaperScissors.Move.PAPER));
        assertEquals(RockPaperScissors.Result.DRAW, game.play(RockPaperScissors.Move.SCISSORS, RockPaperScissors.Move.SCISSORS));
    }

    @DisplayName("Testa vitórias do Jogador 1")
    @ParameterizedTest(name = "{0} ganha de {1}")
    @CsvSource({
        "ROCK, SCISSORS",
        "SCISSORS, PAPER",
        "PAPER, ROCK"
    })
    void testPlayer1Wins(RockPaperScissors.Move p1Move, RockPaperScissors.Move p2Move) {
        assertEquals(RockPaperScissors.Result.PLAYER1_WINS, game.play(p1Move, p2Move));
    }

    @DisplayName("Testa vitórias do Jogador 2")
    @ParameterizedTest(name = "{1} ganha de {0}")
    @CsvSource({
        "SCISSORS, ROCK",
        "PAPER, SCISSORS",
        "ROCK, PAPER"
    })
    void testPlayer2Wins(RockPaperScissors.Move p1Move, RockPaperScissors.Move p2Move) {
        assertEquals(RockPaperScissors.Result.PLAYER2_WINS, game.play(p1Move, p2Move));
    }

    @DisplayName("Testa cenário de falha (entrada inválida nula)")
    @Test
    void testNullInputFailure() {
        // Verifica se a exceção é lançada quando a entrada é nula
        assertThrows(IllegalArgumentException.class, () -> {
            game.play(RockPaperScissors.Move.ROCK, null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            game.play(null, RockPaperScissors.Move.PAPER);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            game.play(null, null);
        });
    }
}