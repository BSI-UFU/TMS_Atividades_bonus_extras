/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kata_fizzbuzz;

/**
 *
 * @author kensl
 */
public class FizzBuzz {

    /**
     * Calcula o resultado do FizzBuzz para um único número.
     * @param number O número a ser avaliado.
     * @return "FizzBuzz", "Fizz", "Buzz", ou o número como String.
     */
    public String compute(int number) {
        // O TDD (especificamente o teste para 15) força
        // a verificação do múltiplo de 15 PRIMEIRO.
        if (number % 15 == 0) {
            return "FizzBuzz";
        }
        if (number % 3 == 0) {
            return "Fizz";
        }
        if (number % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(number);
    }
}