/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.aula07_code_katas_4.BingoBoard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author kensl
 */
// BingoBoardTest.java


public class BingoBoardTest {

    @Test
    public void testRowBingo() {
        int[][] mat = new int[5][5];
        int base = 1;
        for (int r=0;r<5;r++) for (int c=0;c<5;c++) mat[r][c] = base++;

        BingoBoard b = new BingoBoard(mat, false);
        // marca a linha 2 completamente
        for (int c=0;c<5;c++) b.mark(mat[2][c]);
        assertTrue(b.hasBingo(), "Deveria haver bingo por linha");
    }

    @Test
    public void testColumnBingo() {
        int[][] mat = new int[5][5];
        int base = 1;
        for (int r=0;r<5;r++) for (int c=0;c<5;c++) mat[r][c] = base++;

        BingoBoard b = new BingoBoard(mat, false);
        // marca a coluna 1 completamente
        for (int r=0;r<5;r++) b.mark(mat[r][1]);
        assertTrue(b.hasBingo(), "Deveria haver bingo por coluna");
    }

    @Test
    public void testDiagonalBingo() {
        int[][] mat = new int[5][5];
        int val = 1;
        for (int r=0;r<5;r++) for (int c=0;c<5;c++) mat[r][c] = val++;
        BingoBoard b = new BingoBoard(mat, false);
        // marca diagonal principal
        for (int i=0;i<5;i++) b.mark(mat[i][i]);
        assertTrue(b.hasBingo(), "Deveria haver bingo por diagonal");
    }

    @Test
    public void testNoBingo() {
        int[][] mat = new int[5][5];
        int val = 1;
        for (int r=0;r<5;r++) for (int c=0;c<5;c++) mat[r][c] = val++;
        BingoBoard b = new BingoBoard(mat, false);
        // marca alguns números, mas não forma linha/coluna/diagonal
        b.mark(mat[0][0]);
        b.mark(mat[1][1]);
        b.mark(mat[2][3]);
        assertFalse(b.hasBingo(), "Não deveria haver bingo");
    }

    @Test
    public void testFreeCenter() {
        int[][] mat = new int[5][5];
        int val = 1;
        for (int r=0;r<5;r++) for (int c=0;c<5;c++) mat[r][c] = val++;
        BingoBoard b = new BingoBoard(mat, true); // centro livre
        // marca a linha que contém o centro (linha 2)
        b.mark(mat[2][0]);
        b.mark(mat[2][1]);
        b.mark(mat[2][3]);
        b.mark(mat[2][4]);
        assertTrue(b.hasBingo(), "Deveria haver bingo na linha porque o centro é free");
    }
}
