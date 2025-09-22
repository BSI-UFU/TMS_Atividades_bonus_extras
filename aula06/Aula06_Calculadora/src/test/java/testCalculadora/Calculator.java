/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testCalculadora;



/**
 *
 * @author kensl
 */
public class Calculator {
    
    public double Add(double number1, double number2) {
        return number1 + number2;
    }
    
    public double subtract(double number1, double number2) {
        return number1 - number2;
    }

    public double multiply(double number1, double number2) {
        return number1 * number2;
    }

    public double divide(double number1, double number2) {
        if (number2 == 0) {
            
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return number1 / number2;
    }
}
