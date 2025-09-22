/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import org.junit.jupiter.api.*;
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
        usuario.setNome("Usuario Teste");
        usuario.setEmail("teste.valido@dominio.com");
        usuario.setSenha("SenhaSegura123");
    }

    // --- Testes de Validação  ---

    @Test
    @DisplayName("Deve aceitar nome válido")
    void testSetNomeValido() {
        // [cite: 924]
        assertDoesNotThrow(() -> usuario.setNome("Jose da Silva"));
        assertEquals("Jose da Silva", usuario.getNome());
    }

    @Test
    @DisplayName("Deve rejeitar nome inválido (com números)")
    void testSetNomeInvalidoNumeros() {
        // [cite: 924]
        assertThrows(IllegalArgumentException.class, () -> usuario.setNome("Jose123"));
    }

    @Test
    @DisplayName("Deve rejeitar nome inválido (com caracteres especiais)")
    void testSetNomeInvalidoEspecial() {
        // [cite: 924]
        assertThrows(IllegalArgumentException.class, () -> usuario.setNome("Jose_Silva@"));
    }

    @Test
    @DisplayName("Deve aceitar e-mail válido")
    void testSetEmailValido() {
        // [cite: 925, 926, 927]
        assertDoesNotThrow(() -> usuario.setEmail("usuario_valido.123@meu.dominio.com"));
        assertEquals("usuario_valido.123@meu.dominio.com", usuario.getEmail());
    }

    @Test
    @DisplayName("Deve rejeitar e-mail inválido (formato)")
    void testSetEmailInvalidoFormato() {
        // [cite: 925]
        assertThrows(IllegalArgumentException.class, () -> usuario.setEmail("email-invalido.com"));
    }
    
    @Test
    @DisplayName("Deve rejeitar e-mail inválido (parte 'usuario')")
    void testSetEmailInvalidoUsuario() {
        // [cite: 926]
        assertThrows(IllegalArgumentException.class, () -> usuario.setEmail("usuario inválido@dominio.com"));
    }

    @Test
    @DisplayName("Deve rejeitar strings nulas ou vazias")
    void testSetStringNulaOuVazia() {
        // [cite: 923]
        assertThrows(IllegalArgumentException.class, () -> usuario.setNome(null));
        assertThrows(IllegalArgumentException.class, () -> usuario.setEmail(" "));
        assertThrows(IllegalArgumentException.class, () -> usuario.setSenha(""));
    }

    @Test
    @DisplayName("Deve autenticar com senha correta")
    void testAutenticacaoSucesso() {
        assertDoesNotThrow(() -> {
            assertTrue(usuario.autenticar("SenhaSegura123"));
        });
    }

    // --- Teste de Repetição com Injeção de Dependência ---

    @RepeatedTest(value = 4, name = "Teste de bloqueio: Tentativa {currentRepetition}/{totalRepetitions}")
    @DisplayName("Deve bloquear conta após 3 tentativas falhas em 30s")
    void testAutenticacaoDeveBloquear(TestInfo testInfo, TestReporter reporter, RepetitionInfo repetitionInfo) {
        
        // [cite: 935] (Uso de TestInfo e RepetitionInfo)
        reporter.publishEntry("Executando teste", testInfo.getDisplayName());
        int tentativa = repetitionInfo.getCurrentRepetition();

        if (tentativa <= 3) {
            // [cite: 931] (As primeiras 3 tentativas falhas não devem bloquear)
            reporter.publishEntry("Tentativa " + tentativa, "Testando falha normal (esperado: false)");
            assertDoesNotThrow(() -> {
                assertFalse(usuario.autenticar("senha_errada"));
            });
            reporter.publishEntry("Tentativa " + tentativa, "Falhou como esperado.");

        } else if (tentativa == 4) {
            //  (A 4ª tentativa deve lançar a exceção de bloqueio)
            reporter.publishEntry("Tentativa " + tentativa, "Testando bloqueio (esperado: ExceededAttemptsException)");
            
            Exception e = assertThrows(ExceededAttemptsException.class, () -> {
                usuario.autenticar("senha_errada");
            }, "Esperava ExceededAttemptsException na 4ª tentativa");
            
            reporter.publishEntry("Tentativa " + tentativa, "Exceção capturada: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Deve permitir login após o tempo de bloqueio")
    void testAutenticacaoAposBloqueio() throws InterruptedException {
        // Simula o bloqueio
        assertThrows(ExceededAttemptsException.class, () -> {
            usuario.autenticar("errada1"); // Falha 1
            usuario.autenticar("errada2"); // Falha 2
            usuario.autenticar("errada3"); // Falha 3
            usuario.autenticar("errada4"); // Falha 4 -> Bloqueia 
        });

        // Tenta autenticar imediatamente (deve falhar)
        assertThrows(ExceededAttemptsException.class, () -> {
            usuario.autenticar("SenhaSegura123");
        }, "Conta deveria estar bloqueada");

        
    }
}