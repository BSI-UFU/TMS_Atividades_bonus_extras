/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author kensl
 */
@DisplayName("Testes do Fizz Buzz Kata")
public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @DisplayName("Testa números que devem retornar 'Fizz'")
    @Test
    void testReturnsFizz() {
        assertEquals("Fizz", fizzBuzz.play(3));
        assertEquals("Fizz", fizzBuzz.play(6));
        assertEquals("Fizz", fizzBuzz.play(9));
    }

    @DisplayName("Testa números que devem retornar 'Buzz'")
    @Test
    void testReturnsBuzz() {
        assertEquals("Buzz", fizzBuzz.play(5));
        assertEquals("Buzz", fizzBuzz.play(10));
        assertEquals("Buzz", fizzBuzz.play(20));
    }

    @DisplayName("Testa números que devem retornar 'FizzBuzz' (cenário excepcional)")
    @Test
    void testReturnsFizzBuzz() {
        assertEquals("FizzBuzz", fizzBuzz.play(15));
        assertEquals("FizzBuzz", fizzBuzz.play(30));
        assertEquals("FizzBuzz", fizzBuzz.play(45));
    }

    @DisplayName("Testa números que devem retornar eles mesmos")
    @Test
    void testReturnsNumber() {
        assertEquals("1", fizzBuzz.play(1));
        assertEquals("2", fizzBuzz.play(2));
        assertEquals("4", fizzBuzz.play(4));
        assertEquals("7", fizzBuzz.play(7));
    }

    @DisplayName("Testa vários cenários de uma vez com teste parametrizado")
    @ParameterizedTest(name = "play({0}) deve retornar {1}")
    @CsvSource({
        "1, 1",
        "2, 2",
        "3, Fizz",    // Múltiplo de 3
        "4, 4",
        "5, Buzz",    // Múltiplo de 5
        "6, Fizz",    // Múltiplo de 3
        "10, Buzz",   // Múltiplo de 5
        "15, FizzBuzz", // Múltiplo de 15
        "0, FizzBuzz"   // Caso de entrada excepcional (0)
    })
    void testMultipleScenarios(int input, String expected) {
        assertEquals(expected, fizzBuzz.play(input));
    }
}