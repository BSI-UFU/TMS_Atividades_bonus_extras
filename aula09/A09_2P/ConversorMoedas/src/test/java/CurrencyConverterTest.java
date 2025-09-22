/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author kensl
 */


@ExtendWith(MockitoExtension.class)
class CurrencyConverterTest {

    @Mock
    private ExchangeRateService mockRateService; // O "dublê" 

    private CurrencyConverter converter;

    @BeforeEach
    void setUp() {
        // Injeta o serviço mockado no conversor
        converter = new CurrencyConverter(mockRateService);
    }

    @Test
    void testBasicConversionWithCodes() {
        // 1. Configuração do Mock (Stub)
        // Exemplo: 10 USD para BRL com taxa 5.0 [cite: 42]
        when(mockRateService.getRate("USD", "BRL")).thenReturn(5.0);

        // 2. Execução
        double result = converter.convert(10.0, "USD", "BRL");

        // 3. Verificação
        assertEquals(50.0, result);
        verify(mockRateService).getRate("USD", "BRL"); // Verifica se o mock foi chamado
    }

    @Test
    void testConversionWithFriendlyNames() {
        // Testa a funcionalidade "amigável" [cite: 49]
        when(mockRateService.getRate("USD", "BRL")).thenReturn(5.0);

        // O usuário pode não saber os códigos [cite: 49]
        double result = converter.convert(10.0, "Dólar Americano", "Real Brasileiro");

        assertEquals(50.0, result);
        // O conversor deve traduzir "Dólar Americano" para "USD" [cite: 48]
        verify(mockRateService).getRate("USD", "BRL");
    }

    @Test
    void testHandlesDifferentCurrencyTypes() {
        // Testa a diferenciação entre moedas com nomes similares [cite: 47]
        // "Dólar Americano" vs "Dólar Australiano"
        when(mockRateService.getRate("AUD", "BRL")).thenReturn(3.5);

        double result = converter.convert(10.0, "Dólar Australiano", "Real Brasileiro");

        assertEquals(35.0, result);
        verify(mockRateService).getRate("AUD", "BRL");
    }

    @Test
    void testThrowsExceptionForUnknownCurrency() {
        // Testa a falha para moedas desconhecidas
        when(mockRateService.getRate("BTC", "BRL"))
            .thenThrow(new IllegalArgumentException("Taxa não encontrada"));

        // O usuário tenta converter uma moeda que o serviço não conhece
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(10.0, "Bitcoin", "Real Brasileiro");
        });

        assertTrue(exception.getMessage().contains("Taxa não encontrada"));
    }
    
    @Test
    void testHandlesSameCurrencyConversion() {
        // Teste de caso extremo: converter USD para USD
        // O serviço externo não precisa ser chamado
        double result = converter.convert(100.0, "USD", "USD");
        assertEquals(100.0, result);

        // Verifica que o serviço (dependência externa) NÃO foi chamado
        verify(mockRateService, never()).getRate(anyString(), anyString());
    }
}