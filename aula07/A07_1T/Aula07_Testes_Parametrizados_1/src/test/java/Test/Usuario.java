/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
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
    private String algoritmoUtilizado; 

    // ... (Constantes e campos de lógica de bloqueio da atividade anterior) ...
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long ATTEMPT_WINDOW_MS = 30 * 1000;
    private static final long BLOCK_DURATION_MS = 60 * 1000;
    private final List<Long> failedAttemptTimestamps = new ArrayList<>();
    private long blockUntilTime = 0;

    // ... (Patterns de Regex da atividade anterior) ...
    private static final Pattern NOME_PATTERN = Pattern.compile("^[A-Za-z ]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9_.]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)+$");
  
    
    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getAlgoritmoUtilizado() {
        return algoritmoUtilizado;
    }

    public void setAlgoritmoUtilizado(String algoritmoUtilizado) {
        this.algoritmoUtilizado = algoritmoUtilizado;
    }

    public long getBlockUntilTime() {
        return blockUntilTime;
    }

    public void setBlockUntilTime(long blockUntilTime) {
        this.blockUntilTime = blockUntilTime;
    }

        
    public void setSenha(String senha) {
        validarString(senha, "Senha");
        // Define SHA-256 como padrão
        this.algoritmoUtilizado = "SHA-256"; 
        try {
            this.senhaHash = hashSenha(senha, this.algoritmoUtilizado);
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 é garantido, mas por segurança
            throw new RuntimeException("Algoritmo padrão SHA-256 não encontrado", e);
        }
    }
    
    // ... (Métodos setNome, setEmail, getNome, getEmail...) ...

    /**
     * Autenticação principal (com lógica de bloqueio).
     * Usa o algoritmo padrão (SHA-256).
     */
    public boolean autenticar(String senha) throws ExceededAttemptsException {
        long now = System.currentTimeMillis();

        if (now < blockUntilTime) {
            throw new ExceededAttemptsException("Conta bloqueada...");
        }

        String hashFornecido;
        try {
             hashFornecido = hashSenha(senha, this.algoritmoUtilizado);
        } catch (NoSuchAlgorithmException e) {
             throw new RuntimeException("Algoritmo padrão não encontrado", e);
        }

        if (this.senhaHash.equals(hashFornecido)) {
            resetFailedAttempts();
            return true;
        } else {
            handleFailedAttempt(now);
            return false;
        }
    }

    /**
     * NOVA SOBRECARGA (Passo 2 da Atividade)
     * Autentica o usuário permitindo informar o algoritmo.
     * Esta versão NÃO implementa a lógica de bloqueio de tentativas.
     */
    public boolean autenticar(String senha, String algoritmo) throws NoSuchAlgorithmException {
        // (Testa o lançamento de exceções caso o algoritmo seja desconhecido)
        String hashFornecido = hashSenha(senha, algoritmo);
        return this.senhaHash.equals(hashFornecido) && this.algoritmoUtilizado.equals(algoritmo);
    }

    // ... (Lógica de handleFailedAttempt e resetFailedAttempts) ...
    
    /**
     * Método auxiliar de Hash (Modificado para lançar NoSuchAlgorithmException).
     */
    private String hashSenha(String senha, String algoritmo) throws NoSuchAlgorithmException {
        // (lançamento de exceções caso o algoritmo seja desconhecido)
        try {
            MessageDigest digest = MessageDigest.getInstance(algoritmo);
            byte[] hashBytes = digest.digest(senha.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            // Lança a exceção verificada para o método de teste capturar
            throw e; 
        } catch (Exception e) {
            throw new RuntimeException("Erro interno ao gerar hash", e);
        }
    }
    
    private void validarNome(String nome) throws IllegalArgumentException {
        // (Req 2: Nome não pode conter caracteres especiais ou números)
        if (!NOME_PATTERN.matcher(nome).matches()) {
            // A FALHA PROVAVELMENTE ESTÁ AQUI: Verifique se esta linha existe
            throw new IllegalArgumentException("Nome inválido. Use apenas letras e espaços.");
        }
    }

    private void validarEmail(String email) throws IllegalArgumentException {
        // (Req 3, 4, 5: Validação do padrão de e-mail)
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            // A FALHA PROVAVELMENTE ESTÁ AQUI: Verifique se esta linha existe
            throw new IllegalArgumentException("Formato de e-mail inválido. Use usuario@dominio.");
        }
    }
    
    private void handleFailedAttempt(long now) throws ExceededAttemptsException {
        // Remove tentativas falhas mais antigas que 30 segundos
        failedAttemptTimestamps.removeIf(timestamp -> (now - timestamp) > ATTEMPT_WINDOW_MS);

        // Adiciona a tentativa falha atual
        failedAttemptTimestamps.add(now);

        // (Req 3: se 4 ou mais tentativas forem realizadas...)
        if (failedAttemptTimestamps.size() > MAX_FAILED_ATTEMPTS) { // MAX_FAILED_ATTEMPTS = 3
            blockUntilTime = now + BLOCK_DURATION_MS;
            failedAttemptTimestamps.clear(); 

            // A FALHA PROVAVELMENTE ESTÁ AQUI: Verifique se esta linha existe
            throw new ExceededAttemptsException("Número máximo de tentativas (3) excedido. " +
                                                "Conta bloqueada por 1 minuto.");
        }
    }
    
    private void validarString(String valor, String campo) throws IllegalArgumentException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " não pode ser nulo ou vazio.");
        }
    }
    
    private void resetFailedAttempts() {
        failedAttemptTimestamps.clear();
        blockUntilTime = 0;
    }
  
    public String getNome() { return nome; }
    public void setNome(String nome) { validarString(nome, "Nome"); validarNome(nome); this.nome = nome.trim(); }
    public String getEmail() { return email; }
    public void setEmail(String email) { validarString(email, "E-mail"); validarEmail(email); this.email = email; }
}

