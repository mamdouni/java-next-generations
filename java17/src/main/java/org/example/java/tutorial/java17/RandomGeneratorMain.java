package org.example.java.tutorial.java17;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@Slf4j
public class RandomGeneratorMain {

    public static void main(String[] args) {

        // source : https://www.baeldung.com/java-17-random-number-generators
        generateRandomNumbers(RandomGenerator.getDefault());

        log.info("--------------------");

        generateRandomNumbers(RandomGenerator.of("L128X256MixRandom"));

        log.info("--------------------");

        // you can get a list of all available random number generators using the RandomGeneratorFactory.all() method.
        RandomGeneratorFactory.all()
                .sorted(Comparator.comparing(RandomGeneratorFactory::name))
                .forEach(factory -> System.out.println("%s\t%s\t%s\t%s".formatted(
                        factory.group(),
                        factory.name(),
                        factory.isJumpable(),
                        factory.isSplittable())
                        )
                );

    }

    private static void generateRandomNumbers(RandomGenerator generator) {
        log.info("{}",generator.nextLong()); // Long.MIN_VALUE to Long.MAX_VALUE
        log.info("{}",generator.nextLong(10)); // 0 to 9
        log.info("{}",generator.nextLong(10, 100)); // 10 to 99
        log.info("{}",generator.nextInt()); // Integer.MIN_VALUE to Integer.MAX_VALUE
        log.info("{}",generator.nextFloat()); // 0.0 to 1.0
        log.info("{}",generator.nextFloat(10)); // 0.0 to 9.0
        log.info("{}",generator.nextDouble()); // 0.0 to 1.0
    }
}
