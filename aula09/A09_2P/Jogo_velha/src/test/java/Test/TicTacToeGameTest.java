/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.jogo_velha.TicTacToeGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kensl
 */


class TicTacToeGameTest {

    private TicTacToeGame game;

    @BeforeEach
    void setUp() {
        game = new TicTacToeGame();
    }

    @Test
    @DisplayName("Deve inicializar o tabuleiro 3x3 vazio e começar com o Jogador X")
    void testGameInitialization() { // 
        assertEquals(TicTacToeGame.Player.X, game.getCurrentPlayer());
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertNull(game.getCell(r, c));
            }
        }
        assertEquals(TicTacToeGame.GameStatus.ONGOING, game.getStatus());
    }

    @Test
    @DisplayName("Deve alternar o jogador após um movimento válido")
    void testPlayerAlternation() { // 
        assertEquals(TicTacToeGame.Player.X, game.getCurrentPlayer());
        game.makeMove(0, 0); // X joga
        assertEquals(TicTacToeGame.Player.O, game.getCurrentPlayer());
        game.makeMove(1, 1); // O joga
        assertEquals(TicTacToeGame.Player.X, game.getCurrentPlayer());
    }

    @Test
    @DisplayName("Deve rejeitar movimentos em células já ocupadas")
    void testRejectsMoveOnOccupiedCell() { // 
        game.makeMove(0, 0); // X joga
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            game.makeMove(0, 0); // O tenta jogar no mesmo lugar
        });
        assertEquals("Célula (0, 0) já está ocupada.", ex.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar movimentos fora do tabuleiro 3x3")
    void testRejectsMoveOutOfBounds() { // [cite: 107]
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(3, 0));
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(0, -1));
    }

    @Test
    @DisplayName("Deve detectar vitória por linha (horizontal)")
    void testDetectsRowVictory() { // [cite: 109, 117]
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        // X vence na linha 0
        game.makeMove(0, 2); // X
        assertEquals(TicTacToeGame.GameStatus.X_WINS, game.getStatus());
    }

    @Test
    @DisplayName("Deve detectar vitória por coluna (vertical)")
    void testDetectsColumnVictory() { // [cite: 109, 117]
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 1); // O
        // X vence na coluna 0
        game.makeMove(2, 0); // X
        assertEquals(TicTacToeGame.GameStatus.X_WINS, game.getStatus());
    }

    @Test
    @DisplayName("Deve detectar vitória por diagonal")
    void testDetectsDiagonalVictory() { // [cite: 109, 117]
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 1); // X
        game.makeMove(0, 2); // O
        // X vence na diagonal principal
        game.makeMove(2, 2); // X
        assertEquals(TicTacToeGame.GameStatus.X_WINS, game.getStatus());
    }

    @Test
    @DisplayName("Deve detectar empate (jogo sem vencedor)")
    void testDetectsDraw() { // [cite: 110, 118]
        // Simulação de um jogo que termina em empate
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(0, 2); // X
        game.makeMove(1, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 2); // O
        game.makeMove(2, 1); // X
        game.makeMove(2, 0); // O
        game.makeMove(2, 2); // X (Última jogada, sem vencedor)
        
        assertEquals(TicTacToeGame.GameStatus.DRAW, game.getStatus());
    }

    @Test
    @DisplayName("Deve rejeitar movimentos após o fim do jogo")
    void testRejectsMoveAfterGameEnds() { // 
        // X Vence
        game.makeMove(0, 0); game.makeMove(1, 0);
        game.makeMove(0, 1); game.makeMove(1, 1);
        game.makeMove(0, 2);
        
        assertEquals(TicTacToeGame.GameStatus.X_WINS, game.getStatus());
        
        // O tenta jogar
        Exception ex = assertThrows(IllegalStateException.class, () -> {
            game.makeMove(2, 2);
        });
        assertEquals("O jogo já terminou.", ex.getMessage());
    }
}