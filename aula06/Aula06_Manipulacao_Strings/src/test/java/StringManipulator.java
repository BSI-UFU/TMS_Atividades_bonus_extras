/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kensl
 */
public class StringManipulator {

    /**
     * Inverte uma string.
     */
    public String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Conta o número de ocorrências de um caractere.
     */
    public int countOccurrences(String str, char c) {
        if (str == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    /**
     * Verifica se uma string é um palíndromo.
     * Ignora maiúsculas/minúsculas e caracteres não alfanuméricos.
     */
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        // Limpa a string (remove caracteres especiais/espaços)
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    /**
     * Converte uma string para maiúsculas.
     */
    public String toUpperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * Converte uma string para minúsculas.
     */
    public String toLowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }
}