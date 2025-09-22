/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

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
@DisplayName("Testes da classe StringManipulator")
public class StringManipulatorTest {

    private StringManipulator manipulator;

    @BeforeEach
    void setUp() {
        
        manipulator = new StringManipulator();
    }
    
    @Test
    @DisplayName("Deve inverter uma string simples (curta)")
    void testReverseShortString() {
        assertEquals("avaJ", manipulator.reverse("Java"));
    }

    @Test
    @DisplayName("Deve retornar uma string vazia ao inverter uma vazia")
    void testReverseEmptyString() {
        assertEquals("", manipulator.reverse(""));
    }

    @Test
    @DisplayName("Deve retornar nulo ao inverter uma string nula")
    void testReverseNullString() {
        assertNull(manipulator.reverse(null));
    }

    @Test
    @DisplayName("Deve inverter uma string com caracteres especiais")
    void testReverseSpecialChars() {
        assertEquals("$#@", manipulator.reverse("@#$"));
    }

    
    @Test
    @DisplayName("Deve contar as ocorrências de um caractere")
    void testCountOccurrences() {
        assertEquals(3, manipulator.countOccurrences("banana", 'a'));
        assertEquals(0, manipulator.countOccurrences("banana", 'z'));
    }

    @Test
    @DisplayName("Deve retornar 0 ao contar em string vazia")
    void testCountOccurrencesEmpty() {
        assertEquals(0, manipulator.countOccurrences("", 'a'));
    }

    @Test
    @DisplayName("Deve retornar 0 ao contar em string nula")
    void testCountOccurrencesNull() {
        assertEquals(0, manipulator.countOccurrences(null, 'a'));
    }

    @Test
    @DisplayName("Deve identificar um palíndromo simples")
    void testIsPalindromeSimple() {
        assertTrue(manipulator.isPalindrome("ovo"));
        assertFalse(manipulator.isPalindrome("Java"));
    }

    @Test
    @DisplayName("Deve identificar um palíndromo complexo (caracteres especiais e maiúsculas)")
    void testIsPalindromeComplex() {
        // Teste com diferentes tipos de caracteres
        assertTrue(manipulator.isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    @DisplayName("Deve considerar uma string longa como palíndromo")
    void testIsPalindromeLong() {
        String longPalindrome = "Socorram-me, subi no onibus em Marrocos";
        assertTrue(manipulator.isPalindrome(longPalindrome));
    }

    @Test
    @DisplayName("Deve retornar falso para string nula (não é palíndromo)")
    void testIsPalindromeNull() {
        assertFalse(manipulator.isPalindrome(null));
    }

    @Test
    @DisplayName("Deve retornar verdadeiro para string vazia (é palíndromo)")
    void testIsPalindromeEmpty() {
        assertTrue(manipulator.isPalindrome(""));
    }


    @Test
    @DisplayName("Deve converter para maiúsculas")
    void testToUpperCase() {
        assertEquals("JAVA", manipulator.toUpperCase("Java"));
        assertEquals("TESTE", manipulator.toUpperCase("TESTE"));
        assertEquals("", manipulator.toUpperCase(""));
        assertNull(manipulator.toUpperCase(null));
    }

    @Test
    @DisplayName("Deve converter para minúsculas")
    void testToLowerCase() {
        assertEquals("java", manipulator.toLowerCase("Java"));
        assertEquals("teste", manipulator.toLowerCase("TESTE"));
        assertEquals("", manipulator.toLowerCase(""));
        assertNull(manipulator.toLowerCase(null));
    }
}