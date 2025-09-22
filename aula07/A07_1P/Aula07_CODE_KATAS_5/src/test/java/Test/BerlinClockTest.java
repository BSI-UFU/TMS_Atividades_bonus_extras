/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author kensl
 */
@DisplayName("Testes do Berlin Clock Kata")
public class BerlinClockTest {

    private BerlinClock clock;

    @BeforeEach
    void setUp() {
        clock = new BerlinClock();
    }

    @Test
    @DisplayName("Cenário de Sucesso: Meia-noite (00:00:00)")
    void testMidnight() {
        String[] expected = {
            "Y",           // Segundos (par)
            "OOOO",        // 0 horas (5-h)
            "OOOO",        // 0 horas (1-h)
            "OOOOOOOOOOO", // 0 minutos (5-m)
            "OOOO"         // 0 minutos (1-m)
        };
        assertArrayEquals(expected, clock.convert("00:00:00"));
    }
    
    @Test
    @DisplayName("Cenário de Sucesso: Meio-dia (12:00:00)")
    void testMidday() {
        String[] expected = {
            "Y",
            "RROO",        // 10 horas (5-h)
            "RROO",        // 2 horas (1-h)
            "OOOOOOOOOOO",
            "OOOO"
        };
        assertArrayEquals(expected, clock.convert("12:00:00"));
    }

    @Test
    @DisplayName("Cenário de Sucesso: Quase meia-noite (23:59:59)")
    void testAlmostMidnight() {
        String[] expected = {
            "O",           // Segundos (ímpar)
            "RRRR",        // 20 horas (5-h)
            "RRRO",        // 3 horas (1-h)
            "YYRYYRYYRYY", // 55 minutos (5-m)
            "YYYY"         // 4 minutos (1-m)
        };
        assertArrayEquals(expected, clock.convert("23:59:59"));
    }

    @Test
    @DisplayName("Cenário de Sucesso: Hora específica (16:37:16)")
    void testSpecificTime() {
        String[] expected = {
            "Y",           // 16 segundos (par)
            "RRRO",        // 15 horas (5-h)
            "ROOO",        // 1 hora (1-h)
            "YYRYYRYOOOO", // 35 minutos (5-m)
            "YYOO"         // 2 minutos (1-m)
        };
        assertArrayEquals(expected, clock.convert("16:37:16"));
    }

    @Test
    @DisplayName("Cenário de Falha: Entradas inválidas")
    void testInvalidInputs() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            clock.convert("12:34");
        });

        
        assertThrows(IllegalArgumentException.class, () -> {
            clock.convert("99:99:99");
        });

       
        assertThrows(IllegalArgumentException.class, () -> {
            clock.convert("abcde");
        });
        
        
        assertThrows(IllegalArgumentException.class, () -> {
            clock.convert(null);
        });
    }
}