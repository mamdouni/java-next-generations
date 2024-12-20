package org.example.java.tutorial.java9.features.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public class Iterate {

	public static void main(String[] args) {

		// java 8 : public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
		Stream.iterate(0, n -> n + 1)
				.limit(10)
				.forEach(s -> log.info("{}",s));

		System.out.printf("%n	-------------------------%n");

		// java 9 : public static<T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
		// instead of using the limit you can now add a predicate to stop the stream from generating elements.
		Stream.iterate(0, x -> x < 10, n -> n + 1)
				.forEach(s -> log.info("{}",s));
	}
}
