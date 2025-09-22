/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testCalculadora;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kensl
 */
public class CalculatorTest {
    
    public CalculatorTest() {
    }
    
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.Add(10, 50);
        assertEquals(60, result, 0);
    }

    
    @Test
    public void testSubtract() {
        Calculator calculator = new Calculator();
        double result = calculator.subtract(50, 10);
        assertEquals(40, result, 0, "A subtração 50 - 10 deve ser 40");
    }

    @Test
    public void testMultiply() {
        Calculator calculator = new Calculator();
        double result = calculator.multiply(5, 10);
        assertEquals(50, result, 0, "A multiplicação 5 * 10 deve ser 50");
    }

    @Test
    public void testDivide() {
        Calculator calculator = new Calculator();
        double result = calculator.divide(50, 10);
        assertEquals(5, result, 0, "A divisão 50 / 10 deve ser 5");
    }

    @Test
    public void testDivideByZero() {
        Calculator calculator = new Calculator();
        
        // Verifica se a exceção esperada é lançada 
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        }, "Dividir por zero deve lançar IllegalArgumentException");
    }
    
}
