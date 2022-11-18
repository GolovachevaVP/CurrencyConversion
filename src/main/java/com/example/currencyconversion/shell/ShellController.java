package ru.liga.springtest.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import org.springframework.shell.standard.ShellOption;
import ru.liga.springtest.service.CurrencyService;

import java.math.BigDecimal;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {
    private final CurrencyService currencyService;

    @ShellMethod("Коды валют и их описание")
    public void code(){
        currencyService.printCurrencyCodes();
    }

    @ShellMethod("Конвертация валюты")
    public void convert(@ShellOption BigDecimal count,
                        @ShellOption String from,
                        @ShellOption String to) {


        currencyService.printCurrencyConversion(count,from,to);
    }
}
