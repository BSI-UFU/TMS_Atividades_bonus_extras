/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kensl
 */
/**
 * Interface que representa a dependência externa (dublê) que
 * fornece as taxas de câmbio dinâmicas.
 */
public interface ExchangeRateService {
    /**
     * Obtém a taxa de câmbio entre duas moedas.
     * @param fromCurrency Código da moeda de origem (ex: "USD") 
     * @param toCurrency Código da moeda de destino (ex: "BRL") 
     * @return A taxa de câmbio.
     */
    double getRate(String fromCurrency, String toCurrency);
}
