/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author kensl
 */
public class RomanNumeralConverter {
    private static final Map<Integer, String> ROMAN_MAP = new LinkedHashMap<>();
    static {
        ROMAN_MAP.put(1000, "M");
        ROMAN_MAP.put(900, "CM");
        ROMAN_MAP.put(500, "D");
        ROMAN_MAP.put(400, "CD");
        ROMAN_MAP.put(100, "C");
        ROMAN_MAP.put(90, "XC");
        ROMAN_MAP.put(50, "L");
        ROMAN_MAP.put(40, "XL");
        ROMAN_MAP.put(10, "X");
        ROMAN_MAP.put(9, "IX");
        ROMAN_MAP.put(5, "V");
        ROMAN_MAP.put(4, "IV");
        ROMAN_MAP.put(1, "I");
    }

   
    public String convert(int number) {
        
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("Número fora do intervalo suportado (1-3999)");
        }

        StringBuilder roman = new StringBuilder();
        
       
        for (Map.Entry<Integer, String> entry : ROMAN_MAP.entrySet()) {
            int value = entry.getKey();
            String symbol = entry.getValue();
            
            while (number >= value) {
                roman.append(symbol);
                number -= value;
            }
        }

        return roman.toString();
    }
}
