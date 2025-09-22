/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author kensl
 */
public class RockPaperScissors {

    
    public enum Move {
        ROCK,
        PAPER,
        SCISSORS
    }

    
    public enum Result {
        PLAYER1_WINS,
        PLAYER2_WINS,
        DRAW
    }

    
    public Result play(Move player1Move, Move player2Move) {
       
        if (player1Move == null || player2Move == null) {
            throw new IllegalArgumentException("A jogada não pode ser nula");
        }

        
        if (player1Move == player2Move) {
            return Result.DRAW;
        }

        
        if ((player1Move == Move.ROCK && player2Move == Move.SCISSORS) ||
            (player1Move == Move.SCISSORS && player2Move == Move.PAPER) ||
            (player1Move == Move.PAPER && player2Move == Move.ROCK)) {
            return Result.PLAYER1_WINS;
        }

        
        return Result.PLAYER2_WINS;
    }
}