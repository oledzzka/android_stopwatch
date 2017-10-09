package com.example.oleg.homework1;

/**
 * Created by oleg on 30.09.17.
 */

class NumberToWord {
    private static final String[] units = { "", "Один", "Два", "Три", "Четыре", "Пять",
            "Шесть", "Семь", "Восемь", "Девять", "Десять", "Одиннадцать", "Двенадцать",
            "Тринадцать", "Четырнадцать", "Пятнадцать", "Шестнадцать", "Семнадцать",
            "Восемнадцать", "Девятнадцать"};
    private static final String[] tens = { "", "", "Двадцать", "Тридцать", "Сорок",
            "Пятьдесят", "Шестьдесять", "Семьдесят", "Восемьдесят", "Девяносто"};
    private static final String[] hundreds = { "", "Сто", "Двести", "Триста", "Четыреста",
            "Пятьсот", "Шестьсот", "Семьсот", "Восемьсот", "Деятьсот"};
    static final String thousand = "Тысяча";


    static String convert(final int n) {
        if (n < 0) {
            return "Минус " + convert(-n);
        }
        if (n < 20) {
            return units[n];
        }
        if (n < 100) {
            return tens[n / 10] + ((n % 20 != 0) ? " " : "") + convert(n % 10);
        }
        if (n < 1000) {
            return hundreds[n/100] + ((n % 100 != 0) ? " " : "") + convert(n % 100);
        }
        return thousand;
    }
}
