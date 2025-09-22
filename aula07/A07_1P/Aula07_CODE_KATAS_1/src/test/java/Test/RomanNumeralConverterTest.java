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
@DisplayName("Testes do Conversor de Algarismos Romanos")
public class RomanNumeralConverterTest {

    private RomanNumeralConverter converter;

    @BeforeEach
    void setUp() {
        converter = new RomanNumeralConverter();
    }

    @DisplayName("Testa conversões simples e diretas")
    @ParameterizedTest(name = "{0} deve ser {1}")
    @CsvSource({
        "1, I",
        "5, V",
        "10, X",
        "50, L",
        "100, C",
        "500, D",
        "1000, M"
    })
    void testSimpleConversions(int number, String expectedRoman) {
        assertEquals(expectedRoman, converter.convert(number));
    }

    @DisplayName("Testa números com repetição")
    @Test
    void testRepetition() {
        assertEquals("III", converter.convert(3));
        assertEquals("XX", converter.convert(20));
        assertEquals("CCC", converter.convert(300));
    }

    @DisplayName("Testa números com regras de subtração")
    @ParameterizedTest(name = "{0} deve ser {1}")
    @CsvSource({
        "4, IV",
        "9, IX",
        "40, XL",
        "90, XC",
        "400, CD",
        "900, CM"
    })
    void testSubtractionRules(int number, String expectedRoman) {
        assertEquals(expectedRoman, converter.convert(number));
    }

    @DisplayName("Testa números complexos e variados")
    @ParameterizedTest(name = "{0} deve ser {1}")
    @CsvSource({
        "47, XLVII",
        "99, XCIX",
        "1984, MCMLXXXIV",
        "3999, MMMCMXCIX"
    })
    void testComplexNumbers(int number, String expectedRoman) {
        assertEquals(expectedRoman, converter.convert(number));
    }

    @DisplayName("Testa cenários de falha (entradas inválidas)")
    @Test
    void testFailureScenarios() {
        // Teste para número zero
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(0);
        }, "Zero não é um algarismo romano válido");

        // Teste para número negativo
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(-10);
        }, "Números negativos não são válidos");

        // Teste para número acima do limite
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(4000);
        }, "Números acima de 3999 não são válidos");
    }
}