/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kata_the_bowling_game;

/**
 *
 * @author kensl
 */
public class BowlingGame {

    // Um jogo tem no máximo 21 jogadas (9 frames * 2 + 10º frame com 3 bolas)
    private int[] rolls = new int[21];
    private int currentRoll = 0;

    /**
     * Registra o número de pinos derrubados em uma jogada.
     */
    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    /**
     * Calcula a pontuação total do jogo.
     */
    public int score() {
        int score = 0;
        int frameIndex = 0; // Aponta para a primeira bola de um frame

        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex += 1; // Strike usa apenas 1 bola no frame
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2; // Spare usa 2 bolas no frame
            } else {
                score += rollsInFrame(frameIndex);
                frameIndex += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int frameIndex) {
        return rolls[frameIndex] == 10;
    }

    private boolean isSpare(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1] == 10;
    }

    private int strikeBonus(int frameIndex) {
        return rolls[frameIndex + 1] + rolls[frameIndex + 2];
    }

    private int spareBonus(int frameIndex) {
        return rolls[frameIndex + 2];
    }

    private int rollsInFrame(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1];
    }
}