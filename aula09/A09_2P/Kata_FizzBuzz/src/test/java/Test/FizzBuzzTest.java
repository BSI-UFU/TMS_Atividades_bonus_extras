/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.kata_fizzbuzz.FizzBuzz;
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


class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    @DisplayName("Deve retornar o número para números normais")
    void testReturnsNumber() {
        assertEquals("1", fizzBuzz.compute(1));
        assertEquals("2", fizzBuzz.compute(2));
        assertEquals("4", fizzBuzz.compute(4));
    }

    @Test
    @DisplayName("Deve retornar 'Fizz' para múltiplos de 3")
    void testReturnsFizzForMultiplesOfThree() {
        assertEquals("Fizz", fizzBuzz.compute(3));
        assertEquals("Fizz", fizzBuzz.compute(6));
        assertEquals("Fizz", fizzBuzz.compute(9));
    }

    @Test
    @DisplayName("Deve retornar 'Buzz' para múltiplos de 5")
    void testReturnsBuzzForMultiplesOfFive() {
        assertEquals("Buzz", fizzBuzz.compute(5));
        assertEquals("Buzz", fizzBuzz.compute(10));
    }

    @Test
    @DisplayName("Deve retornar 'FizzBuzz' para múltiplos de 3 e 5")
    void testReturnsFizzBuzzForMultiplesOfThreeAndFive() {
        assertEquals("FizzBuzz", fizzBuzz.compute(15));
        assertEquals("FizzBuzz", fizzBuzz.compute(30));
    }
}