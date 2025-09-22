/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.conversor_unidades.UnitConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kensl
 */


class UnitConverterTest {

    private UnitConverter converter;

    @BeforeEach
    void setUp() {
        converter = new UnitConverter();
    }

    // --- 1. Testes de Comprimento ---
    @ParameterizedTest(name = "{0} {1} -> {2} {3}")
    @CsvSource({
        // Requisito: metros ("m"), quilômetros ("km"), centímetros ("cm")
        "1000, m, 1, km",
        "1, km, 100000, cm",
        "500, cm, 5, m",
        // Requisito: milhas ("mi"), e polegadas ("in")
        "1, mi, 1609.34, m",
        "1, m, 39.3701, in",
        "1, in, 2.54, cm"
    })
    @DisplayName("Deve converter unidades de Comprimento (m, km, cm, mi, in)")
    void testLengthConversions(double fromValue, String fromUnit, double expectedValue, String toUnit) {
        assertEquals(expectedValue, converter.convert(fromValue, fromUnit, toUnit), 0.01);
    }

    // --- 2. Testes de Peso ---
    @ParameterizedTest(name = "{0} {1} -> {2} {3}")
    @CsvSource({
        // Requisito: gramas ("g"), quilogramas ("kg")
        "1000, g, 1, kg",
        "2.5, kg, 2500, g",
        // Requisito: libras ("lb"), e onças ("oz")
        "1, lb, 453.592, g",
        "1, kg, 2.20462, lb",
        "1, oz, 28.3495, g"
    })
    @DisplayName("Deve converter unidades de Peso (g, kg, lb, oz)")
    void testWeightConversions(double fromValue, String fromUnit, double expectedValue, String toUnit) {
        assertEquals(expectedValue, converter.convert(fromValue, fromUnit, toUnit), 0.01);
    }

    // --- 3. Testes de Temperatura ---
    @ParameterizedTest(name = "{0} {1} -> {2} {3}")
    @CsvSource({
        // Requisito: Celsius ("C"), Fahrenheit ("F"), e Kelvin ("K")
        "0, C, 32, F",
        "100, C, 212, F",
        "32, F, 0, C",
        "212, F, 100, C",
        "0, C, 273.15, K",
        "273.15, K, 0, C",
        "-40, F, 233.15, K" // Teste F -> K
    })
    @DisplayName("Deve converter unidades de Temperatura (C, F, K)")
    void testTemperatureConversions(double fromValue, String fromUnit, double expectedValue, String toUnit) {
        assertEquals(expectedValue, converter.convert(fromValue, fromUnit, toUnit), 0.01);
    }

    // --- 4. Testes de Erro ---
    @Test
    @DisplayName("Deve lançar exceção para conversões de tipos incompatíveis")
    void testThrowsExceptionForIncompatibleTypes() {
        // Exemplo: metros (Comprimento) para quilogramas (Peso)
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(10.0, "m", "kg");
        });
        assertEquals("Conversão incompatível: LENGTH para WEIGHT", ex.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção para unidades desconhecidas")
    void testThrowsExceptionForUnknownUnits() {
        // Exemplo: "parsecs"
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(10.0, "m", "parsecs");
        });
        assertEquals("Unidade desconhecida: parsecs", ex.getMessage());
    }
}