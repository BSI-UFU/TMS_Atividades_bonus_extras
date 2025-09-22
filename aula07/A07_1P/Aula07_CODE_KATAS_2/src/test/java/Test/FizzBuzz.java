/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author kensl
 */
public class FizzBuzz {

    /**
     * Retorna a string "Fizz", "Buzz", "FizzBuzz" ou o número,
     * de acordo com as regras do jogo.
     *
     * @param number O número a ser avaliado.
     * @return A string correspondente.
     */
    public String play(int number) {
        // Cenário de falha/exceção: múltiplos de 15 
        if (number % 15 == 0) {
            return "FizzBuzz";
        }
        // Cenário de sucesso: múltiplos de 3 
        if (number % 3 == 0) {
            return "Fizz";
        }
        // Cenário de sucesso: múltiplos de 5 
        if (number % 5 == 0) {
            return "Buzz";
        }
        // Cenário de sucesso: outros números 
        return String.valueOf(number);
    }
}