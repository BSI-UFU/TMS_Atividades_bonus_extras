/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kensl
 */


class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        // A senha padrão é "SenhaSegura123" (usando SHA-256)
        usuario.setSenha("SenhaSegura123");
        usuario.setNome("Usuario Padrao");
        usuario.setEmail("padrao@dominio.com");
    }

    // --- PASSO 1: @ValueSource para autenticação ---
    @ParameterizedTest(name = "Tentativa de login com senha: {0}")
    @ValueSource(strings = {"senha_errada", "12345", "qwerty", "SenhaSegura123 "})
    @DisplayName("Deve falhar autenticação para senhas incorretas")
    void testAutenticacaoFalha_ValueSource(String senhaIncorreta) {
        // assertDoesNotThrow() trata a checked exception
        assertDoesNotThrow(() -> {
            // Testa a autenticação padrão (com lógica de bloqueio)
            assertFalse(usuario.autenticar(senhaIncorreta));
        });
    }

    // --- PASSO 2: Teste da exceção da sobrecarga ---
    @ParameterizedTest(name = "Algoritmo desconhecido: {0}")
    @ValueSource(strings = {"MD-foo", "INVALID_ALGORITHM"}) 
    @DisplayName("Deve lançar NoSuchAlgorithmException para algoritmos desconhecidos")
    void testAutenticacaoAlgoritmoInexistente_ValueSource(String algoritmo) {
        assertThrows(NoSuchAlgorithmException.class, () -> {
            usuario.autenticar("SenhaSegura123", algoritmo);
        });
    }
    @Test
    @DisplayName("Deve falhar autenticação com SHA-512 (algoritmo válido, hash diferente)")
    void testAutenticacaoComSHA512() {
        assertDoesNotThrow(() -> {
            assertFalse(usuario.autenticar("SenhaSegura123", "SHA-512"));
        });
    }

    // --- PASSO 3: @EnumSource para a sobrecarga ---
    @ParameterizedTest(name = "Algoritmo: {0} (Sucesso)")
    @EnumSource(value = AlgoritmoHash.class, names = {"SHA_256"})
    @DisplayName("Deve autenticar com algoritmo correto via Enum")
    void testAutenticacaoComAlgoritmo_EnumSucesso(AlgoritmoHash algoritmo) {
        assertDoesNotThrow(() -> {
            assertTrue(usuario.autenticar("SenhaSegura123", algoritmo.getNome()));
        });
    }

    @ParameterizedTest(name = "Algoritmo: {0} (Falha)")
    @EnumSource(value = AlgoritmoHash.class, names = {"MD5", "SHA_1"})
    @DisplayName("Deve falhar autenticação com algoritmo incorreto via Enum")
    void testAutenticacaoComAlgoritmo_EnumFalha(AlgoritmoHash algoritmo) {
        assertDoesNotThrow(() -> {
            // A senha está em SHA-256, então falhará se tentar MD5 ou SHA-1
            assertFalse(usuario.autenticar("SenhaSegura123", algoritmo.getNome()));
        });
    }

    // --- PASSO 4: @CsvSource para validação ---
    @ParameterizedTest(name = "Email: {0} | Nome: {1} | Esperado: {2}")
    @CsvSource({
        "teste@dominio.com, Usuario Valido, true",
        "teste.valido@dominio.com, Outro Valido, true",
        "usuario_1.com, Nome, false", // Email inválido
        "usuario@dominio invalido.com, Nome, false", // Email inválido
        "usuario.invalido@dominio.com, Nome123, false" // Nome inválido
    })
    @DisplayName("Deve validar combinações de Email e Nome via CsvSource")
    void testValidacaoUsuario_CsvSource(String email, String nome, boolean esperado) {
        if (esperado) {
            assertDoesNotThrow(() -> {
                usuario.setEmail(email);
                usuario.setNome(nome);
            });
        } else {
            assertThrows(IllegalArgumentException.class, () -> {
                // Testa ambos os setters; falhará no primeiro inválido
                usuario.setEmail(email);
                usuario.setNome(nome);
            });
        }
    }

    // --- PASSO 4: @CsvFileSource para validação ---
    @ParameterizedTest(name = "Arquivo: Email: {0} | Nome: {1} | Esperado: {2}")
    @CsvFileSource(resources = "/validacao_usuarios.csv", numLinesToSkip = 1) // Pula o cabeçalho
    @DisplayName("Deve validar combinações de Email e Nome via CsvFileSource")
    void testValidacaoUsuario_CsvFileSource(String email, String nome, boolean esperado) {
        // A lógica é idêntica ao teste CsvSource
        if (esperado) {
            assertDoesNotThrow(() -> {
                usuario.setEmail(email);
                usuario.setNome(nome);
            });
        } else {
            assertThrows(IllegalArgumentException.class, () -> {
                usuario.setEmail(email);
                usuario.setNome(nome);
            });
        }
    }

    // --- TESTE DE BLOQUEIO (CORRIGIDO) ---
    @Test
    @DisplayName("Deve bloquear conta após 3 tentativas falhas em 30s")
    void testAutenticacaoLogicaDeBloqueio() {
        
        // As 3 primeiras tentativas falham, mas não bloqueiam
        // Usamos assertDoesNotThrow para tratar a checked exception
        assertDoesNotThrow(() -> {
            assertFalse(usuario.autenticar("errada1"), "1a falha deveria retornar false");
            assertFalse(usuario.autenticar("errada2"), "2a falha deveria retornar false");
            assertFalse(usuario.autenticar("errada3"), "3a falha deveria retornar false");
        });

        // A 4a tentativa falha e DEVE bloquear (lançar a exceção)
        assertThrows(ExceededAttemptsException.class, () -> {
            usuario.autenticar("errada4");
        }, "4a falha deveria bloquear e lançar ExceededAttemptsException");

        // Imediatamente após, a conta está bloqueada (mesmo com a senha correta)
        assertThrows(ExceededAttemptsException.class, () -> {
            usuario.autenticar("SenhaSegura123");
        }, "Conta deveria estar bloqueada imediatamente apos a 4a falha");
    }
}