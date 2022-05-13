package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    void send() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.0.0.1"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp("96.0.0.1"))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers1 = new HashMap<String, String>() {{
            put("x-real-ip", "172.0.0.1");
        }};
        Map<String, String> headers2 = new HashMap<String, String>() {{
            put("x-real-ip", "96.0.0.1");
        }};

        String result1 = messageSender.send(headers1);
        String result2 = messageSender.send(headers2);

        String expected1 = "Добро пожаловать";
        String expected2 = "Welcome";

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }
}