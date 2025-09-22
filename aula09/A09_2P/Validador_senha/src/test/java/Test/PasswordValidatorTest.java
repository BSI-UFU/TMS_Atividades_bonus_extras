/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.validador_senha.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kensl
 */

class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PasswordValidator();
    }

    @Test
    @DisplayName("Deve aprovar uma senha que atende todos os critérios")
    void testValidPasswordPasses() {
        // Contém >= 8 chars, 1 maiúscula, 1 minúscula, 1 número, 1 especial, sem espaço
        assertTrue(validator.isValid("Valid@1pass"));
    }

    @Test
    @DisplayName("Deve rejeitar senha nula")
    void testFailsIfPasswordIsNull() {
        assertFalse(validator.isValid(null));
    }

    @ParameterizedTest
    @ValueSource(strings = { 
        "Short@1",     // 1. Falha por ter menos de 8 caracteres [cite: 60]
        "noupper@1",   // 2. Falha por não ter letra maiúscula [cite: 61]
        "NOLOWER@1",   // 3. Falha por não ter letra minúscula [cite: 62]
        "NoDigit@pass",// 4. Falha por não ter número [cite: 63]
        "NoSpecial1",  // 5. Falha por não ter caractere especial [cite: 64]
        "Has Space@1"  // 6. Falha por conter espaço em branco [cite: 65]
    })
    @DisplayName("Deve rejeitar senhas que falham em qualquer um dos critérios")
    void testInvalidPasswordsFail(String invalidPassword) {
        assertFalse(validator.isValid(invalidPassword));
    }
}