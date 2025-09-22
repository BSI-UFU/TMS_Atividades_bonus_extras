/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author kensl
 */

public class CurrencyConverter {

    private final ExchangeRateService rateService;
    private final Map<String, String> friendlyNameMap;
    private final Set<String> knownCodes;

    public CurrencyConverter(ExchangeRateService rateService) {
        this.rateService = rateService;
        this.friendlyNameMap = new HashMap<>();
        // Popula o mapa com base nos requisitos
        populateCurrencyMap();
        // Armazena os códigos conhecidos para validação
        this.knownCodes = Set.of("USD", "BRL", "EUR", "AUD", "CAD", "JPY", "CHF", "CNY", "ARS", "CLP");
    }

    
    private void populateCurrencyMap() {
        friendlyNameMap.put("Real Brasileiro", "BRL");
        friendlyNameMap.put("Dólar Americano", "USD"); 
        friendlyNameMap.put("Euro", "EUR");
        friendlyNameMap.put("Dólar Australiano", "AUD"); 
        friendlyNameMap.put("Dólar Canadense", "CAD");
        friendlyNameMap.put("Iene Japonês", "JPY");
        friendlyNameMap.put("Franco Suíço", "CHF");
        friendlyNameMap.put("Yuan Chinês", "CNY");
        friendlyNameMap.put("Peso Argentino", "ARS"); 
        friendlyNameMap.put("Peso Chileno", "CLP"); 
        
    }

    
    public double convert(double amount, String fromCurrency, String toCurrency) {
        // 1. Traduz nomes amigáveis para códigos ISO
        String fromCode = getCode(fromCurrency);
        String toCode = getCode(toCurrency);

        // 2. Caso base: se as moedas são iguais, não é necessária conversão.
        if (fromCode.equals(toCode)) {
            return amount;
        }

        // 3. Busca a taxa na dependência externa (dublê) 
        try {
            double rate = rateService.getRate(fromCode, toCode);
            // 4. Realiza o cálculo (Ex: 10 USD * 5.0 BRL/USD = 50 BRL) 
            return amount * rate;
        } catch (Exception e) {
            // Repassa exceções do serviço (ex: taxa não encontrada)
            throw new IllegalArgumentException("Falha na conversão: " + e.getMessage());
        }
    }

    
    private String getCode(String currencyNameOrCode) {
        // Se já for um código conhecido (ex: "USD"), retorna diretamente
        if (knownCodes.contains(currencyNameOrCode)) {
            return currencyNameOrCode;
        }
        
        // Se for um nome amigável (ex: "Dólar Americano"), busca no mapa
        String code = friendlyNameMap.get(currencyNameOrCode);
        if (code != null) {
            return code;
        }

        // Se não for encontrado, lança um erro
        throw new IllegalArgumentException("Moeda desconhecida: " + currencyNameOrCode);
    }
}