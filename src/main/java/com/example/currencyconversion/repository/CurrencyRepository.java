package com.example.currencyconversion.repository;

import com.example.currencyconversion.dto.CurrencyCodesDto;
import com.example.currencyconversion.dto.CurrencyConversionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CurrencyRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<CurrencyCodesDto> getCurrencyCodes() {
        String sql = "SELECT currency_code, currency_name FROM exchange_rates";
        List<CurrencyCodesDto> currencyCodesDtoList = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new CurrencyCodesDto(rs.getString("currency_code"),
                        rs.getString("currency_name")));

        return currencyCodesDtoList;
    }

    public CurrencyConversionDto getCurrencyConversion(String currencyCode) {
        String sql = "SELECT currency_code,nominal, cost  FROM exchange_rates WHERE currency_code = :currencyCode";
        Map<String, String> params = new HashMap<>();
        params.put("currencyCode", currencyCode);

        CurrencyConversionDto currencyConversionDto = jdbcTemplate.queryForObject(
                sql,
                params,
                (rs, rowNum) -> new CurrencyConversionDto(rs.getString("currency_code"),
                        rs.getInt("nominal"),rs.getBigDecimal("cost")));

        return currencyConversionDto;
    }
}

