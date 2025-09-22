/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jogo_velha;

/**
 *
 * @author kensl
 */
public class TicTacToeGame {

    // Enums para representar os jogadores e o estado do jogo
    public enum Player { X, O }
    public enum GameStatus { ONGOING, X_WINS, O_WINS, DRAW }

    private Player[][] board;
    private Player currentPlayer;
    private GameStatus status;
    private int movesMade;

    /**
     * Inicializa o tabuleiro 3x3 e define o jogador inicial. 
     */
    public TicTacToeGame() {
        this.board = new Player[3][3]; // [cite: 107]
        this.currentPlayer = Player.X; // X sempre começa [cite: 108]
        this.status = GameStatus.ONGOING;
        this.movesMade = 0;
    }

    /**
     * Retorna o jogador que ocupa uma célula.
     */
    public Player getCell(int r, int c) {
        return board[r][c];
    }

    /**
     * Retorna o jogador atual.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Retorna o status atual do jogo.
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Tenta realizar um movimento no tabuleiro.
     */
    public void makeMove(int r, int c) {
        // 5. Rejeitar movimentos inválidos 
        if (status != GameStatus.ONGOING) {
            throw new IllegalStateException("O jogo já terminou.");
        }
        if (r < 0 || r > 2 || c < 0 || c > 2) {
            throw new IllegalArgumentException("Movimento fora do tabuleiro 3x3.");
        }
        if (board[r][c] != null) {
            throw new IllegalArgumentException(String.format("Célula (%d, %d) já está ocupada.", r, c));
        }

        // Realiza o movimento
        Player playerWhoMoved = currentPlayer;
        board[r][c] = playerWhoMoved;
        movesMade++;

        // 3. Verificação de vitória após cada movimento 
        if (checkWin(r, c, playerWhoMoved)) {
            status = (playerWhoMoved == Player.X) ? GameStatus.X_WINS : GameStatus.O_WINS;
        } 
        // 4. Detecção de empate [cite: 118, 110]
        else if (movesMade == 9) { 
            status = GameStatus.DRAW;
        } 
        // 2. Lógica para alternar entre os jogadores 
        else {
            currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
        }
    }

    /**
     * Verifica se o último movimento resultou em vitória. [cite: 109, 117]
     */
    private boolean checkWin(int r, int c, Player p) {
        // Checar Linha (Horizontal)
        if (board[r][0] == p && board[r][1] == p && board[r][2] == p) return true;
        
        // Checar Coluna (Vertical)
        if (board[0][c] == p && board[1][c] == p && board[2][c] == p) return true;

        // Checar Diagonal Principal (só se o movimento foi nela)
        if (r == c) {
            if (board[0][0] == p && board[1][1] == p && board[2][2] == p) return true;
        }

        // Checar Diagonal Secundária (só se o movimento foi nela)
        if (r + c == 2) {
            if (board[0][2] == p && board[1][1] == p && board[2][0] == p) return true;
        }

        return false;
    }
}