/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.kata_the_bowling_game.BowlingGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kensl
 */


class BowlingGameTest {

    private BowlingGame game;

    @BeforeEach
    void setUp() {
        game = new BowlingGame();
    }

    // Função auxiliar para rolar a bola várias vezes
    private void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++) {
            game.roll(pins);
        }
    }

    @Test
    @DisplayName("Deve pontuar 0 para um 'Gutter Game' (todas na canaleta)")
    void testGutterGame() {
        rollMany(20, 0); // 20 jogadas, 0 pinos
        assertEquals(0, game.score());
    }

    @Test
    @DisplayName("Deve pontuar 20 para um jogo 'All Ones' (todos 1 pino)")
    void testAllOnes() {
        rollMany(20, 1); // 20 jogadas, 1 pino
        assertEquals(20, game.score());
    }

    @Test
    @DisplayName("Deve calcular corretamente o bônus de um Spare")
    void testOneSpare() {
        game.roll(5);
        game.roll(5); // Spare
        game.roll(3); // Bônus do spare é 3
        rollMany(17, 0); // Resto do jogo
        
        // (5+5) + 3 (bônus) + 3 = 16
        assertEquals(16, game.score());
    }

    @Test
    @DisplayName("Deve calcular corretamente o bônus de um Strike")
    void testOneStrike() {
        game.roll(10); // Strike
        game.roll(3);
        game.roll(4); // Bônus do strike são 3 e 4
        rollMany(16, 0); // Resto do jogo
        
        // (10) + (3+4) (bônus) + (3+4) = 24
        assertEquals(24, game.score());
    }

    @Test
    @DisplayName("Deve calcular um Jogo Perfeito (300 pontos)")
    void testPerfectGame() {
        rollMany(12, 10); // 12 strikes (10 frames + 2 bolas bônus)
        assertEquals(300, game.score());
    }
}