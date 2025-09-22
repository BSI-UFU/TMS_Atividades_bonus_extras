/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aula07_code_katas_4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 *
 * @author kensl
 */
// BingoBoard.java

public class BingoBoard {
    private final int SIZE = 5;
    private final int[][] board;
    private final boolean[][] marked;
    private final boolean freeCenter;

    // Construtor a partir de matriz
    public BingoBoard(int[][] initial, boolean freeCenter) {
        if (initial == null || initial.length != SIZE) {
            throw new IllegalArgumentException("Board must be a 5x5 matrix");
        }
        for (int[] row : initial) {
            if (row == null || row.length != SIZE) throw new IllegalArgumentException("Board must be a 5x5 matrix");
        }

        this.board = new int[SIZE][SIZE];
        this.marked = new boolean[SIZE][SIZE];
        this.freeCenter = freeCenter;

        // Checa duplicatas (opcional: a kata normalmente exige valores únicos)
        Set<Integer> seen = new HashSet<>();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                int v = initial[r][c];
                if (seen.contains(v)) {
                    throw new IllegalArgumentException("Board values must be unique");
                }
                seen.add(v);
                board[r][c] = v;
                marked[r][c] = false;
            }
        }

        if (freeCenter) {
            marked[SIZE/2][SIZE/2] = true; // marca o centro como livre
        }
    }

    // Utilitário: cria um tabuleiro aleatório simples (1..75 distribuídos sem repetição)
    public static BingoBoard generateRandom(boolean freeCenter) {
        int SIZE = 5;
        int[] pool = new int[75];
        for (int i = 0; i < 75; i++) pool[i] = i + 1;
        shuffle(pool);

        int[][] mat = new int[SIZE][SIZE];
        int idx = 0;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                mat[r][c] = pool[idx++];
            }
        }
        return new BingoBoard(mat, freeCenter);
    }

    private static void shuffle(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }
    }

    // Marca todas as células que têm o número
    public void mark(int number) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == number) {
                    marked[r][c] = true;
                }
            }
        }
    }

    // Retorna true se houver bingo (linha, coluna ou diagonal completa)
    public boolean hasBingo() {
        // linhas
        for (int r = 0; r < SIZE; r++) {
            boolean all = true;
            for (int c = 0; c < SIZE; c++) {
                if (!marked[r][c]) { all = false; break; }
            }
            if (all) return true;
        }

        // colunas
        for (int c = 0; c < SIZE; c++) {
            boolean all = true;
            for (int r = 0; r < SIZE; r++) {
                if (!marked[r][c]) { all = false; break; }
            }
            if (all) return true;
        }

        // diagonal principal
        boolean all = true;
        for (int i = 0; i < SIZE; i++) {
            if (!marked[i][i]) { all = false; break; }
        }
        if (all) return true;

        // diagonal secundária
        all = true;
        for (int i = 0; i < SIZE; i++) {
            if (!marked[i][SIZE - 1 - i]) { all = false; break; }
        }
        return all;
    }

    // Helpers para testes / debug
    public int getCell(int row, int col) { return board[row][col]; }
    public boolean isMarked(int row, int col) { return marked[row][col]; }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < SIZE; r++) {
            sb.append(Arrays.toString(board[r])).append("  |  ");
            sb.append(Arrays.toString(marked[r])).append("\n");
        }
        return sb.toString();
    }
}

