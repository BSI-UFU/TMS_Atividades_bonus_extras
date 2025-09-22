/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.kata_string_calculator.StringCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author kensl
 */


class StringCalculatorTest {

    private StringCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new StringCalculator();
    }

    @Test
    @DisplayName("Deve retornar 0 para string vazia")
    void testEmptyStringReturnsZero() {
        assertEquals(0, calc.Add(""));
    }

    @Test
    @DisplayName("Deve retornar o próprio número para um único número")
    void testSingleNumberReturnsValue() {
        assertEquals(1, calc.Add("1"));
        assertEquals(5, calc.Add("5"));
    }

    @Test
    @DisplayName("Deve retornar a soma de dois números delimitados por vírgula")
    void testTwoNumbersReturnsSum() {
        assertEquals(3, calc.Add("1,2"));
    }

    @Test
    @DisplayName("Deve retornar a soma de uma quantidade desconhecida de números")
    void testUnknownAmountOfNumbersReturnsSum() {
        assertEquals(15, calc.Add("1,2,3,4,5"));
    }

    @Test
    @DisplayName("Deve suportar delimitador de nova linha (\\n)")
    void testNewLineDelimiterSupport() {
        assertEquals(6, calc.Add("1\n2,3"));
    }

    @Test
    @DisplayName("Deve lançar exceção para números negativos")
    void testNegativeNumbersThrowException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            calc.Add("-1,2,-3");
        });
        // O requisito especifica que a mensagem deve conter os negativos
        assertEquals("negatives not allowed: -1,-3", ex.getMessage());
    }
}