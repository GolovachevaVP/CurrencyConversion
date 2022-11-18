package com.example.currencyconversion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyConversionDto {
    private String code;
    private int nominal;
    private BigDecimal cost;
}
