/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author kensl
 */
@DisplayName("Testes do Checkout Kata")
public class CheckoutTest {

    private static Map<String, PricingRule> rules;
    private Checkout checkout;

    /**
     * Configura as regras de preço uma vez para todos os testes.
     */
    @BeforeAll
    static void setUpAll() {
        rules = new HashMap<>();
        rules.put("A", new PricingRule("A", 50, 3, 130));
        rules.put("B", new PricingRule("B", 30, 2, 45));
        rules.put("C", new PricingRule("C", 20));
        rules.put("D", new PricingRule("D", 15));
    }

    /**
     * Cria um novo checkout (carrinho vazio) antes de cada teste.
     */
    @BeforeEach
    void setUp() {
        checkout = new Checkout(rules);
    }

    @Test
    @DisplayName("Cenário de Sucesso: Carrinho vazio deve custar 0")
    void testEmptyCart() {
        assertEquals(0, checkout.getTotal());
    }

    @Test
    @DisplayName("Cenário de Sucesso: Itens simples sem oferta (C + D)")
    void testSimpleItemsNoOffer() {
        checkout.scan("C");
        checkout.scan("D");
        assertEquals(35, checkout.getTotal()); // 20 + 15
    }

    @Test
    @DisplayName("Cenário de Sucesso: Múltiplos itens sem oferta (C + C)")
    void testMultipleItemsNoOffer() {
        checkout.scan("C");
        checkout.scan("C");
        assertEquals(40, checkout.getTotal()); // 20 + 20
    }

    @Test
    @DisplayName("Cenário de Sucesso: Deve aplicar a oferta 3 por 130 (A x 3)")
    void testOfferExactMultiple() {
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        assertEquals(130, checkout.getTotal());
    }

    @Test
    @DisplayName("Cenário de Sucesso: Deve aplicar a oferta + restante (A x 4)")
    void testOfferWithRemainder() {
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        assertEquals(180, checkout.getTotal()); // 130 (para 3) + 50 (para 1)
    }

    @Test
    @DisplayName("Cenário de Sucesso: Deve aplicar a oferta duas vezes (A x 6)")
    void testOfferMultipleTimes() {
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        assertEquals(260, checkout.getTotal()); // 130 + 130
    }
    
    @Test
    @DisplayName("Cenário de Sucesso: Deve aplicar a oferta 2 por 45 (B x 2)")
    void testOfferItemB() {
        checkout.scan("B");
        checkout.scan("B");
        assertEquals(45, checkout.getTotal());
    }
    
    @Test
    @DisplayName("Cenário de Sucesso: Deve aplicar a oferta B + restante (B x 3)")
    void testOfferItemBWithRemainder() {
        checkout.scan("B");
        checkout.scan("B");
        checkout.scan("B");
        assertEquals(75, checkout.getTotal()); // 45 (para 2) + 30 (para 1)
    }

    @Test
    @DisplayName("Cenário de Sucesso: Carrinho complexo (A, B, A, C, B, A)")
    void testComplexCart() {
        checkout.scan("A"); // 50
        checkout.scan("B"); // 30
        checkout.scan("A"); // 100
        checkout.scan("C"); // 120
        checkout.scan("B"); // 150 (B+B=45) -> Total: 50+45+50+20 = 165
        checkout.scan("A"); // 200 (A+A+A=130) -> Total: 130 + 45 + 20 = 195

        // Carrinho final: 3x 'A', 2x 'B', 1x 'C'
        // Preço: 130 (A) + 45 (B) + 20 (C) = 195
        assertEquals(195, checkout.getTotal());
    }
    
    @Test
    @DisplayName("Cenário de Falha: Deve lançar exceção para item nulo ou vazio")
    void testScanNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            checkout.scan(null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            checkout.scan("");
        });
    }

    @Test
    @DisplayName("Cenário de Falha: Deve lançar exceção para item desconhecido (Z)")
    void testScanUnknownItem() {
        assertThrows(IllegalArgumentException.class, () -> {
            checkout.scan("Z");
        });
    }
}