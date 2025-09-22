/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kensl
 */
public class Checkout {

    private final Map<String, PricingRule> rules;
    private final Map<String, Integer> cart; // Armazena <Item, Quantidade>

    /**
     * Cria um novo sistema de checkout com um conjunto de regras de preço.
     */
    public Checkout(Map<String, PricingRule> rules) {
        this.rules = rules;
        this.cart = new HashMap<>();
    }

    /**
     * "Escaneia" um item e o adiciona ao carrinho.
     * @param item O SKU do item (ex: "A", "B").
     * @throws IllegalArgumentException se o item for nulo, vazio ou desconhecido.
     */
    public void scan(String item) {
        // Cenário de falha (entrada inválida)
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("Item não pode ser nulo ou vazio");
        }
        if (!rules.containsKey(item)) {
            throw new IllegalArgumentException("Item '" + item + "' não possui regra de preço");
        }

        // Adiciona o item ao carrinho (ou incrementa a quantidade)
        this.cart.put(item, this.cart.getOrDefault(item, 0) + 1);
    }

    /**
     * Calcula o preço total do carrinho, aplicando todas as regras.
     * @return O preço total em centavos.
     */
    public int getTotal() {
        int total = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String item = entry.getKey();
            int count = entry.getValue();
            
            PricingRule rule = rules.get(item);
            total += rule.calculatePrice(count);
        }
        return total;
    }
    
    /**
     * Limpa o carrinho.
     */
    public void clear() {
        this.cart.clear();
    }
}