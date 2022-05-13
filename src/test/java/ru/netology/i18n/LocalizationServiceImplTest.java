package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void locale() {
        LocalizationService ls = new LocalizationServiceImpl();

        String result1 = ls.locale(Country.RUSSIA);
        String result2 = ls.locale(Country.USA);
        String exp1 = "Добро пожаловать";
        String exp2 = "Welcome";

        assertEquals(exp1, result1);
        assertEquals(exp2, result2);
    }
}