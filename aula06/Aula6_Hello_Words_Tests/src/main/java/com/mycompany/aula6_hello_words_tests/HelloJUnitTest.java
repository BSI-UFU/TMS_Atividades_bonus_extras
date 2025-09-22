/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.aula6_hello_words_tests;

/**
 *
 * @author kensl
 */
public class HelloJUnitTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HelloJUnit test = new HelloJUnit();

        try {
            // Executa o método
            String resultado = test.sayHello();

            // Verifica manualmente o resultado esperado
            if ("Hello, Worlds to Tests!".equals(resultado)) {
                System.out.println("Hello, Worlds to Tests!");
            } else {
                System.out.println("Teste falhou! Resultado: " + resultado);
            }

        } catch (Exception e) {
            System.out.println("Ocorreu uma exceção durante o teste:");
            e.printStackTrace();
        }
    }
    
}
