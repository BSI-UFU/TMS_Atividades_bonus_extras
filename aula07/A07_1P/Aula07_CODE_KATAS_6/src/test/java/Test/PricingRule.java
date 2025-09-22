/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author kensl
 */
public class PricingRule {

    private final String item;
    private final int unitPrice;
    
    // Campos para a oferta especial
    private final int specialQuantity;
    private final int specialPrice;
    private final boolean hasSpecialOffer;

    /**
     * Construtor para itens SEM oferta especial.
     */
    public PricingRule(String item, int unitPrice) {
        this(item, unitPrice, 0, 0);
    }

    /**
     * Construtor para itens COM oferta especial.
     */
    public PricingRule(String item, int unitPrice, int specialQuantity, int specialPrice) {
        this.item = item;
        this.unitPrice = unitPrice;
        this.specialQuantity = specialQuantity;
        this.specialPrice = specialPrice;
        this.hasSpecialOffer = (specialQuantity > 0 && specialPrice > 0);
    }

    // Getters
    public int getUnitPrice() { return unitPrice; }
    public int getSpecialQuantity() { return specialQuantity; }
    public int getSpecialPrice() { return specialPrice; }
    public boolean hasSpecialOffer() { return hasSpecialOffer; }

    /**
     * Calcula o preço para uma dada quantidade deste item, aplicando a regra.
     */
    public int calculatePrice(int count) {
        int total = 0;
        if (hasSpecialOffer) {
            // Aplica a oferta especial
            total += (count / specialQuantity) * specialPrice;
            // Adiciona o restante pelo preço unitário
            total += (count % specialQuantity) * unitPrice;
        } else {
            // Sem oferta, apenas preço unitário
            total += count * unitPrice;
        }
        return total;
    }
}
