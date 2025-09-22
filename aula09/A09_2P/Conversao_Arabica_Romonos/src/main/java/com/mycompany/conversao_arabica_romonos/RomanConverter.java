/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conversao_arabica_romonos;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author kensl
 */

public class RomanConverter {

    // Usamos LinkedHashMap porque a ordem de iteração é crucial para a conversão
    // Arábico -> Romano. Começamos do maior (1000) para o menor (1).
    private static final LinkedHashMap<Integer, String> arabicToRomanMap = new LinkedHashMap<>();
    
    // Mapa para Romano -> Arábico
    private static final Map<Character, Integer> romanToArabicMap = new HashMap<>();

    // Padrão Regex para validar a forma geral de um número romano
    // (Impede combinações como "IIII" ou "VV" ou "IXI")
    private static final Pattern ROMAN_PATTERN = Pattern.compile(
        "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    static {
        // Inicializa os mapas
        // A ordem aqui é do maior para o menor
        arabicToRomanMap.put(1000, "M");
        arabicToRomanMap.put(900, "CM");
        arabicToRomanMap.put(500, "D");
        arabicToRomanMap.put(400, "CD");
        arabicToRomanMap.put(100, "C");
        arabicToRomanMap.put(90, "XC");
        arabicToRomanMap.put(50, "L");
        arabicToRomanMap.put(40, "XL");
        arabicToRomanMap.put(10, "X");
        arabicToRomanMap.put(9, "IX");
        arabicToRomanMap.put(5, "V");
        arabicToRomanMap.put(4, "IV");
        arabicToRomanMap.put(1, "I");

        
        romanToArabicMap.put('I', 1);
        romanToArabicMap.put('V', 5);
        romanToArabicMap.put('X', 10);
        romanToArabicMap.put('L', 50);
        romanToArabicMap.put('C', 100);
        romanToArabicMap.put('D', 500);
        romanToArabicMap.put('M', 1000);
    }

    
    public String toRoman(int arabicNumber) {
        // Números romanos válidos são tipicamente de 1 a 3999
        if (arabicNumber <= 0 || arabicNumber > 3999) {
            throw new IllegalArgumentException("Número arábico inválido. (Deve estar entre 1 e 3999)");
        }

        StringBuilder roman = new StringBuilder();
        
        // Itera pelo mapa (que está ordenado)
        for (Map.Entry<Integer, String> entry : arabicToRomanMap.entrySet()) {
            // Enquanto o número for maior ou igual ao valor do mapa,
            // subtraia o valor e adicione o numeral romano.
            while (arabicNumber >= entry.getKey()) {
                roman.append(entry.getValue());
                arabicNumber -= entry.getKey();
            }
        }
        return roman.toString();
    }

   
    public int toArabic(String romanNumber) {
        if (romanNumber == null || romanNumber.isEmpty()) {
            throw new IllegalArgumentException("Input romano não pode ser nulo ou vazio");
        }

        // Validação da entrada 
        // "XLAC" falhará aqui porque 'A' não é um caractere permitido
        if (!romanNumber.matches("^[IVXLCDM]+$")) {
            throw new IllegalArgumentException("Input romano contém caracteres inválidos.");
        }
        
        // Validação da forma (ex: "IIII" ou "VV" são inválidos)
        if (!ROMAN_PATTERN.matcher(romanNumber).matches()) {
            throw new IllegalArgumentException("Input romano tem formato inválido.");
        }

        int result = 0;
        int previousValue = 0;

        // Iteramos pela string DE TRÁS PARA FRENTE
        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            char currentChar = romanNumber.charAt(i);
            int currentValue = romanToArabicMap.get(currentChar);

            // Se o valor atual (ex: 'I' em "IV") for menor que o anterior (V),
            // nós subtraímos.
            if (currentValue < previousValue) {
                result -= currentValue;
            } else {
                // Caso contrário (ex: 'V' em "VI" ou 'I' em "II"), nós somamos.
                result += currentValue;
            }
            previousValue = currentValue;
        }

        return result;
    }
}