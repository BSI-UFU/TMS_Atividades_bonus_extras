/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author kensl
 */
public enum AlgoritmoHash {
    MD5("MD5"),
    SHA_1("SHA-1"),
    SHA_256("SHA-256");

    private final String nomeAlgoritmo;

    AlgoritmoHash(String nome) {
        this.nomeAlgoritmo = nome;
    }

    public String getNome() {
        return nomeAlgoritmo;
    }
}
