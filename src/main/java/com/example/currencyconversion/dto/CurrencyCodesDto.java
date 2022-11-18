package ru.liga.springtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyCodesDto {
    private String code;
    private String name;
}
