package ru.liga.springtest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.springtest.dto.CurrencyCodesDto;
import ru.liga.springtest.dto.CurrencyConversionDto;
import ru.liga.springtest.repository.CurrencyRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final int DECIMAL_PLACES = 2;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void printCurrencyCodes() {
        List<CurrencyCodesDto> currencyCodes = currencyRepository.getCurrencyCodes();
        currencyCodes.forEach(codes -> System.out.println(codes.getCode() + " " + codes.getName()));
    }

    public void printCurrencyConversion(BigDecimal count, String from, String to) {
        CurrencyConversionDto currencyCodesFrom = currencyRepository.getCurrencyConversion(from);
        CurrencyConversionDto currencyCodesTo = currencyRepository.getCurrencyConversion(to);
        BigDecimal rub = conversionCurrencyToRub(count, currencyCodesFrom);
        System.out.println(currencyCodesTo.getCode()+": "+conversionRubToCurrency(rub, currencyCodesTo));
    }

    private BigDecimal conversionCurrencyToRub(BigDecimal count, CurrencyConversionDto currencyCodesFrom) {
        return count.multiply(currencyCodesFrom.getCost()
                .divide(BigDecimal.valueOf(currencyCodesFrom.getNominal()),DECIMAL_PLACES, RoundingMode.HALF_UP));

    }

    private BigDecimal conversionRubToCurrency(BigDecimal rub, CurrencyConversionDto currencyCodesTo) {
        return rub.multiply(BigDecimal.valueOf(currencyCodesTo.getNominal()))
                .divide(currencyCodesTo.getCost(), DECIMAL_PLACES, RoundingMode.HALF_UP);

    }


}





