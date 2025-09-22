/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author kensl
 */
public class Usuario {

    private String nome;
    private String email;
    private String senhaHash;

    // Constantes para a lógica de autenticação 
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long ATTEMPT_WINDOW_MS = 30 * 1000; // 30 segundos
    private static final long BLOCK_DURATION_MS = 60 * 1000; // 1 minuto

    // Campos para rastrear tentativas de login
    private final List<Long> failedAttemptTimestamps = new ArrayList<>();
    private long blockUntilTime = 0;

    // Padrões de Regex para validação
    // [cite: 924] (Nome: apenas letras e espaços)
    private static final Pattern NOME_PATTERN = Pattern.compile("^[A-Za-z ]+$");
    // [cite: 925, 926, 927] (Email: usuario@dominio, com regras específicas)
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9_.]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*$");

    
    // --- Métodos de Validação ---

    private void validarString(String valor, String campo) throws IllegalArgumentException {
        // [cite: 923] (Campos do tipo String não podem ser nulos ou vazios)
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " não pode ser nulo ou vazio.");
        }
    }

    private void validarNome(String nome) throws IllegalArgumentException {
        // [cite: 924] (Nome não pode conter caracteres especiais ou números)
        if (!NOME_PATTERN.matcher(nome).matches()) {
            throw new IllegalArgumentException("Nome inválido. Use apenas letras e espaços.");
        }
    }

    private void validarEmail(String email) throws IllegalArgumentException {
        // [cite: 925, 926, 927] (Validação do padrão de e-mail)
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Formato de e-mail inválido. Use usuario@dominio.");
        }
    }

    // --- Getters e Setters ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        validarString(nome, "Nome");
        validarNome(nome);
        this.nome = nome.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        validarString(email, "E-mail");
        validarEmail(email);
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    /**
     * Define a senha, calculando seu hash SHA-256.
     * Baseado no fragmento de código da atividade [cite: 916-919].
     */
    public void setSenha(String senha) {
        validarString(senha, "Senha");
        this.senhaHash = hashSenha(senha, "SHA-256");
    }

  
    public boolean autenticar(String senha) throws ExceededAttemptsException {
        long now = System.currentTimeMillis();

        //  (Verifica se a conta está bloqueada)
        if (now < blockUntilTime) {
            throw new ExceededAttemptsException("Conta bloqueada. Tente novamente após " + 
                                                ((blockUntilTime - now) / 1000) + " segundos.");
        }

        String hashFornecido = hashSenha(senha, "SHA-256");

        if (this.senhaHash.equals(hashFornecido)) {
            // Sucesso na autenticação
            resetFailedAttempts();
            return true;
        } else {
            // Falha na autenticação
            handleFailedAttempt(now);
            return false;
        }
    }

    /**
     * Processa uma tentativa de login falhada, aplicando as regras de bloqueio.
     * 
     */
    private void handleFailedAttempt(long now) throws ExceededAttemptsException {
       
        failedAttemptTimestamps.removeIf(timestamp -> (now - timestamp) > ATTEMPT_WINDOW_MS);
        failedAttemptTimestamps.add(now);

        if (failedAttemptTimestamps.size() > MAX_FAILED_ATTEMPTS) {
            blockUntilTime = now + BLOCK_DURATION_MS;
            // Limpa o histórico de tentativas após o bloqueio
            failedAttemptTimestamps.clear(); 
            throw new ExceededAttemptsException("Número máximo de tentativas (3) excedido. " +
                                                "Conta bloqueada por 1 minuto.");
        }
    }

    private void resetFailedAttempts() {
        failedAttemptTimestamps.clear();
        blockUntilTime = 0;
    }

    // --- Método Auxiliar de Hash ---

    /**
     * Gera o hash de uma string usando o algoritmo especificado.
     * Baseado em[cite: 917, 918, 919].
     */
    private String hashSenha(String senha, String algoritmo) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algoritmo);
            byte[] hashBytes = digest.digest(senha.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            // Em uma aplicação real, tratar esta exceção de forma mais robusta
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }
}