/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.conversao_arabica_romonos.RomanConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kensl
 */


class RomanConverterTest {

    private RomanConverter converter;

    @BeforeEach
    void setUp() {
        converter = new RomanConverter();
    }

    // --- Testes para Arábico -> Romano  ---

    @ParameterizedTest
    @CsvSource({
        "1, I",
        "2, II",
        "3, III",
        "4, IV",   // Teste de combinação subtrativa [cite: 75]
        "5, V",    // Teste de numeral simples [cite: 74]
        "9, IX",   // Teste de combinação subtrativa [cite: 75]
        "10, X",   // Teste de numeral simples [cite: 74]
        "40, XL",  // Teste de combinação subtrativa [cite: 75]
        "50, L",   // Teste de numeral simples [cite: 74]
        "90, XC",  // Teste de combinação subtrativa [cite: 75]
        "100, C",  // Teste de numeral simples [cite: 74]
        "400, CD", // Teste de combinação subtrativa [cite: 75]
        "500, D",  // Teste de numeral simples [cite: 74]
        "900, CM", // Teste de combinação subtrativa [cite: 75]
        "1000, M"  // Teste de numeral simples [cite: 74]
    })
    @DisplayName("Deve converter numerais arábicos básicos para romanos")
    void testBasicArabicToRoman(int arabic, String expectedRoman) {
        assertEquals(expectedRoman, converter.toRoman(arabic));
    }

    @Test
    @DisplayName("Deve converter números arábicos complexos (Exemplos do PDF)")
    void testComplexArabicToRoman() {
        // "MMXXIV" [cite: 71]
        assertEquals("MMXXIV", converter.toRoman(2024)); 
        // "capítulo 159" [cite: 72]
        assertEquals("CLIX", converter.toRoman(159)); 
    }

    @Test
    @DisplayName("Deve lançar exceção para arábicos fora do range (ex: 0 ou 4000+)")
    void testInvalidArabicInput() {
        assertThrows(IllegalArgumentException.class, () -> converter.toRoman(0));
        assertThrows(IllegalArgumentException.class, () -> converter.toRoman(4000));
        assertThrows(IllegalArgumentException.class, () -> converter.toRoman(-10));
    }


    // --- Testes para Romano -> Arábico  ---

    @ParameterizedTest
    @CsvSource({
        "I, 1",
        "II, 2",
        "III, 3",
        "IV, 4",   // Teste de combinação subtrativa [cite: 75]
        "V, 5",    // Teste de numeral simples [cite: 74]
        "IX, 9",   // Teste de combinação subtrativa [cite: 75]
        "X, 10",   // Teste de numeral simples [cite: 74]
        "XL, 40",  // Teste de combinação subtrativa [cite: 75]
        "L, 50",   // Teste de numeral simples [cite: 74]
        "XC, 90",  // Teste de combinação subtrativa [cite: 75]
        "C, 100",  // Teste de numeral simples [cite: 74]
        "CD, 400", // Teste de combinação subtrativa [cite: 75]
        "D, 500",  // Teste de numeral simples [cite: 74]
        "CM, 900", // Teste de combinação subtrativa [cite: 75]
        "M, 1000"  // Teste de numeral simples [cite: 74]
    })
    @DisplayName("Deve converter numerais romanos básicos para arábicos")
    void testBasicRomanToArabic(String roman, int expectedArabic) {
        assertEquals(expectedArabic, converter.toArabic(roman));
    }

    @Test
    @DisplayName("Deve converter números romanos complexos (Exemplos do PDF)")
    void testComplexRomanToArabic() {
        // "MMXXIV" [cite: 71]
        assertEquals(2024, converter.toArabic("MMXXIV"));
        // Teste de combinação 
        assertEquals(159, converter.toArabic("CLIX"));
    }

    @Test
    @DisplayName("Deve lançar exceção para romanos inválidos (Exemplo do PDF)")
    void testInvalidRomanInput() {
        // Exemplo "XLAC" 
        assertThrows(IllegalArgumentException.class, () -> converter.toArabic("XLAC"));
        assertThrows(IllegalArgumentException.class, () -> converter.toArabic("Z"));
        assertThrows(IllegalArgumentException.class, () -> converter.toArabic(""));
        assertThrows(IllegalArgumentException.class, () -> converter.toArabic(null));
        // Teste para combinação inválida (embora os caracteres sejam válidos)
        assertThrows(IllegalArgumentException.class, () -> converter.toArabic("IIII"));
        assertThrows(IllegalArgumentException.class, () -> converter.toArabic("VV"));
    }
}