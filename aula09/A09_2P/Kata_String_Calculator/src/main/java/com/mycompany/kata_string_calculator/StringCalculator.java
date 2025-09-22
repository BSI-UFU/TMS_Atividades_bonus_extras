/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kata_string_calculator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kensl
 */


public class StringCalculator {

    /**
     * Soma números em uma string, conforme os requisitos do Kata.
     */
    public int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        // Usa Regex para aceitar vírgula ou nova linha como delimitador
        String[] parts = numbers.split("[,\n]");
        
        int sum = 0;
        List<String> negatives = new ArrayList<>();

        for (String part : parts) {
            int n = Integer.parseInt(part);
            
            if (n < 0) {
                negatives.add(part);
            }
            sum += n;
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException(
                "negatives not allowed: " + String.join(",", negatives)
            );
        }

        return sum;
    }
}
