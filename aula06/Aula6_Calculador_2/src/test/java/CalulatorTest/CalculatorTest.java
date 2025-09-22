/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package CalulatorTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author kensl
 */
public class CalculatorTest {
    
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);
        assertEquals(60, result, 0);
    }

    @Test
    @DisplayName("Testa a operação de subtração (caso válido)")
    public void testSubtract() {
        Calculator calculator = new Calculator();
        double result = calculator.subtract(50, 10);
        assertEquals(40, result, 0, "A subtração 50 - 10 deve ser 40");
    }

    @Test
    @DisplayName("Testa a operação de multiplicação (caso válido)")
    public void testMultiply() {
        Calculator calculator = new Calculator();
        double result = calculator.multiply(5, 10);
        assertEquals(50, result, 0, "A multiplicação 5 * 10 deve ser 50");
    }

    @Test
    @DisplayName("Testa a operação de divisão (caso válido)")
    public void testDivide() {
        Calculator calculator = new Calculator();
        double result = calculator.divide(50, 10);
        assertEquals(5, result, 0, "A divisão 50 / 10 deve ser 5");
    }

    @Test
    public void testDivideByZero() {
        Calculator calculator = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        }, "Dividir por zero deve lançar IllegalArgumentException");
    }



    /**
     * Teste para a funcionalidade de potenciação.
     */
    @Test
    public void testPower() {
        Calculator calculator = new Calculator();
        
        assertEquals(8, calculator.power(2, 3), 0);
       
        assertEquals(1.44, calculator.power(1.2, 2), 0.001);
       
        assertEquals(1, calculator.power(100, 0), 0);
    }

    /**
     * Teste para a funcionalidade de raíz quadrada.
     */
    @Test
    public void testSquareRoot() {
        Calculator calculator = new Calculator();
      
        assertEquals(5, calculator.squareRoot(25), 0);
     
        assertEquals(1.5, calculator.squareRoot(2.25), 0.001);
        
        assertEquals(0, calculator.squareRoot(0), 0);
    }

    /**
     * Teste para a situação inválida de raíz quadrada.
     * Cobre o cenário de "operações com números negativos".
     */
    @Test
    public void testSquareRootNegative() {
        Calculator calculator = new Calculator();
        // 
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.squareRoot(-9);
        });
    }

  
    @Test
    public void testLimitValues() {
        Calculator calculator = new Calculator();
        assertEquals(Double.POSITIVE_INFINITY, calculator.multiply(Double.MAX_VALUE, 2), 0);
        assertEquals(1.3407807929942596E154, calculator.squareRoot(Double.MAX_VALUE), 0);
    }
}


