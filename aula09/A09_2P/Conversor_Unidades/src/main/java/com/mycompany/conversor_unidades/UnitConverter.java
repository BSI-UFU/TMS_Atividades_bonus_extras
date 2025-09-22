/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conversor_unidades;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author kensl
 */

public class UnitConverter {

    // Enum para categorizar as unidades
    private enum UnitType {
        LENGTH, WEIGHT, TEMPERATURE
    }

    // Mapas para armazenar a unidade base e a taxa de conversão para ela
    private static final Map<String, UnitType> unitTypes = new HashMap<>();
    private static final Map<String, Double> conversionRates = new HashMap<>(); // Fator para a unidade base

    static {
        // --- 1. Comprimento (Unidade Base: Metro "m") ---
        unitTypes.put("m", UnitType.LENGTH);
        unitTypes.put("km", UnitType.LENGTH);
        unitTypes.put("cm", UnitType.LENGTH);
        unitTypes.put("mi", UnitType.LENGTH);
        unitTypes.put("in", UnitType.LENGTH);
        
        conversionRates.put("m", 1.0);
        conversionRates.put("km", 1000.0);
        conversionRates.put("cm", 0.01);
        conversionRates.put("mi", 1609.34);
        conversionRates.put("in", 0.0254);

        // --- 2. Peso (Unidade Base: Grama "g") ---
        unitTypes.put("g", UnitType.WEIGHT);
        unitTypes.put("kg", UnitType.WEIGHT);
        unitTypes.put("lb", UnitType.WEIGHT);
        unitTypes.put("oz", UnitType.WEIGHT);
        
        conversionRates.put("g", 1.0);
        conversionRates.put("kg", 1000.0);
        conversionRates.put("lb", 453.592);
        conversionRates.put("oz", 28.3495);

        // --- 3. Temperatura (Lógica separada, sem "taxa") ---
        unitTypes.put("C", UnitType.TEMPERATURE);
        unitTypes.put("F", UnitType.TEMPERATURE);
        unitTypes.put("K", UnitType.TEMPERATURE);
    }

    /**
     * Converte um valor de uma unidade para outra.
     */
    public double convert(double value, String fromUnit, String toUnit) {
        UnitType fromType = unitTypes.get(fromUnit);
        UnitType toType = unitTypes.get(toUnit);

        // Validação de Erros
        if (fromType == null) throw new IllegalArgumentException("Unidade desconhecida: " + fromUnit);
        if (toType == null) throw new IllegalArgumentException("Unidade desconhecida: " + toUnit);
        if (fromType != toType) {
            throw new IllegalArgumentException("Conversão incompatível: " + fromType + " para " + toType);
        }

        // Delega a lógica para o método específico do tipo
        switch (fromType) {
            case LENGTH:
            case WEIGHT:
                return convertScaleable(value, fromUnit, toUnit);
            case TEMPERATURE:
                return convertTemperature(value, fromUnit, toUnit);
            default:
                throw new IllegalStateException("Tipo de unidade não implementado");
        }
    }

    /**
     * Converte unidades escaláveis (Peso e Comprimento) usando uma unidade base.
     */
    private double convertScaleable(double value, String fromUnit, String toUnit) {
        // 1. Converte o valor de entrada para a unidade base (ex: "km" -> "m")
        double valueInBaseUnit = value * conversionRates.get(fromUnit);
        
        // 2. Converte da unidade base para a unidade de saída (ex: "m" -> "in")
        return valueInBaseUnit / conversionRates.get(toUnit);
    }

    /**
     * Converte unidades de Temperatura (C, F, K).
     */
    private double convertTemperature(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) return value;

        // Para simplificar, convertemos tudo primeiro para Celsius ("C")
        double valueInCelsius;
        switch (fromUnit) {
            case "C":
                valueInCelsius = value;
                break;
            case "F":
                valueInCelsius = (value - 32) * 5.0 / 9.0;
                break;
            case "K":
                valueInCelsius = value - 273.15;
                break;
            default:
                throw new IllegalStateException("Unidade de temperatura desconhecida");
        }

        // Agora, convertemos de Celsius para a unidade de saída
        switch (toUnit) {
            case "C":
                return valueInCelsius;
            case "F":
                return (valueInCelsius * 9.0 / 5.0) + 32;
            case "K":
                return valueInCelsius + 273.15;
            default:
                throw new IllegalStateException("Unidade de temperatura desconhecida");
        }
    }
}