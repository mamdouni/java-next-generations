package org.example.java.tutorial.java23.gatherers;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Gatherer;

@Slf4j
public class GatherersExample1 {

    void main() {

        var strings = List.of("one", "two", "three", "four", "five");
        var mappedStrings = strings.stream()
                .map(String::toUpperCase)
                .toList();
        log.info("Mapped strings: {}", mappedStrings);

        // let's create a gatherer that do the same as the map. We can use the Gatherer.of method like Collectors.of

        // we will use the simplest Gatherer here, we will take the method with only one element
        // https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/stream/Gatherer.html#of(java.util.stream.Gatherer.Integrator)

        // Integrator is a functional interface,and the lambda you need to implement takes three parameters. We are not using the first one,
        //for now, so let us use this underscore parameter, that is just telling that. The second one is the element of the stream you need to process,
        // and the third one models the downstream this Gatherer is pushing elements to.
        var mapper = Gatherer.<String,String>of(
                // first String is the type of the elements of the Stream this  Gatherer consumes
                // And the second one is the type of objects this Gatherer can produce.
                (_, element, downstream) -> downstream.push(element.toUpperCase())
        );
        // The lambda function must return a boolean value, true if the element was processed and false if it was not.
        // In our case it is not necessary as the push method of the downstream will always return a boolean.

        log.info("Mapped strings: {}", mappedStrings);

        log.info("---------- We've used this example with mapMulti in java 16 ------------");

        Country usa = new Country("USA", List.of(new City("New York", "USA"), new City("Los Angeles", "USA")));
        Country france = new Country("France", List.of(new City("Paris", "France"), new City("Lyon", "France")));
        Country spain = new Country("Spain", List.of(new City("Madrid", "Spain"), new City("Barcelona", "Spain")));
        var countries = List.of(usa, france, spain);

        var flatMapper = Gatherer.<Country, City>ofSequential(
                (_, country, downstream) -> {
                    country.cities().forEach(downstream::push);
                    return true;
                }
        );

        // you can use Gatherer.ofSequential or Gatherer.of

        List<City> cities = countries.stream()
                .gather(flatMapper)
                .toList();
        cities.forEach(city -> log.info("City: {}", city));
    }

    record City(String name, String country) {
    }
    record Country(String name, List<City> cities) {
    }
}