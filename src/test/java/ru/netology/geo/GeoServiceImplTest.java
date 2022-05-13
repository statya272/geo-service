package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byIpLocalhost() {
        GeoService geoService = new GeoServiceImpl();

        Location result = geoService.byIp(GeoServiceImpl.LOCALHOST);
        Location expected = new Location(null, null, null, 0);

        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getStreet(), result.getStreet());
        assertEquals(expected.getBuiling(), result.getBuiling());
    }

    @Test
    void byIpMoscow() {
        GeoService geoService = new GeoServiceImpl();

        Location result = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        Location expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);

        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getStreet(), result.getStreet());
        assertEquals(expected.getBuiling(), result.getBuiling());
    }

    @Test
    void byIpNewYork() {
        GeoService geoService = new GeoServiceImpl();

        Location result = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        Location expected = new Location("New York", Country.USA, " 10th Avenue", 32);

        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getStreet(), result.getStreet());
        assertEquals(expected.getBuiling(), result.getBuiling());
    }

    @Test
    void byIp172() {
        GeoService geoService = new GeoServiceImpl();

        Location result = geoService.byIp("172.0.0.1");
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);

        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getStreet(), result.getStreet());
        assertEquals(expected.getBuiling(), result.getBuiling());
    }

    @Test
    void byIp96() {
        GeoService geoService = new GeoServiceImpl();

        Location result = geoService.byIp("96.0.0.1");
        Location expected = new Location("New York", Country.USA, null, 0);

        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getStreet(), result.getStreet());
        assertEquals(expected.getBuiling(), result.getBuiling());
    }

    @Test
    void byIpDefault() {
        GeoService geoService = new GeoServiceImpl();

        assertNull(geoService.byIp("123.0.0.1"));
    }

    @Test
    void byCoordinates() {
        Assertions.assertThrows(RuntimeException.class, () ->
            new GeoServiceImpl().byCoordinates(0.0, 0.0)
        );
    }
}