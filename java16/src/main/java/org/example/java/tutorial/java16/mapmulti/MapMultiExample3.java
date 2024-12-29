package org.example.java.tutorial.java16.mapmulti;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MapMultiExample3 {

    public static void main(String[] args) {

        Country usa = new Country("USA", List.of(new City("New York", "USA"), new City("Los Angeles", "USA")));
        Country france = new Country("France", List.of(new City("Paris", "France"), new City("Lyon", "France")));
        Country spain = new Country("Spain", List.of(new City("Madrid", "Spain"), new City("Barcelona", "Spain")));
        var countries = List.of(usa, france, spain);

        List<City> cities = countries.stream()
                .flatMap(country -> country.cities().stream())
                .toList();
        cities.forEach(city -> log.info("City: {}", city));

        log.info("----------------------");

        // you can achieve the same result using mapMulti
        List<Object> cities2 = countries.stream()
                .mapMulti((country, downstream) -> country.cities().forEach(downstream::accept))
                .toList();
        // it returns a List of objects because we didn't specify the type of the downstream Consumer which is Consumer<Object> by default
        // you can specify the type of the downstream when you call the mapMulti method like this:
        cities2.forEach(city -> log.info("City: {}", city));

        log.info("----------------------");

        List<City> cities3 = countries.stream()
                .<City>mapMulti((country, downstream) -> country.cities().forEach(downstream::accept))
                .toList();
        cities3.forEach(city -> log.info("City: {}", city));
    }

    record City(String name, String country) {
    }
    record Country(String name, List<City> cities) {
    }
}
