/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validador_senha;

/**
 *
 * @author kensl
 */
import java.util.regex.Pattern;

public class PasswordValidator {

    // Compilar os padrões de Regex uma vez para melhor performance
    
   
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    
    
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    
    
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    
    
    // Vamos simplificar para um conjunto comum.
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[@#$%^&+=!]");
    
    
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");

    
    public boolean isValid(String password) {
        if (password == null) {
            return false;
        }

        
        if (password.length() < 8) {
            return false;
        }

        
        if (WHITESPACE_PATTERN.matcher(password).find()) {
            return false;
        }

        
        if (!UPPERCASE_PATTERN.matcher(password).find()) {
            return false;
        }

        if (!LOWERCASE_PATTERN.matcher(password).find()) {
            return false;
        }

        if (!DIGIT_PATTERN.matcher(password).find()) {
            return false;
        }

        if (!SPECIAL_CHAR_PATTERN.matcher(password).find()) {
            return false;
        }

        return true;
    }
}