package org.example.java.tutorial.java15.java9.features.streams;

import java.util.Set;
import java.util.stream.Stream;

public class TakeDropWhile {

	public static void main(String[] args) {

		/*
			takeWhile is similar to filter in the sense that it expects a predicate and returns a new stream consisting only of the elements that match the given predicate.
			But there’s a catch. In an ordered stream, takeWhile takes elements from the initial stream while the predicate holds true.
			Meaning that when an element is encountered that does not match the predicate, the rest of the stream is discarded.
		*/
		Stream.of(2, 4, 6, 8, 9, 10, 12)
				.takeWhile(n -> n % 2 == 0)
				.forEach(System.out::print);	// 10 and 12 will not be shown as the predicate at 9 returns false
		System.out.printf("%n	-------------------------%n");
		/*
			dropWhile is essentially the opposite of takeWhile. Instead of taking elements from the stream until the first element which does not match the predicate,
			dropWhile drops these elements and includes the remaining elements in the returned stream.
		*/
		Stream.of(2, 4, 6, 8, 9, 10, 12)
				.dropWhile(n -> n % 2 == 0)
				.forEach(System.out::print);	// 10 and 12 will not be removed and the predicate at 9 returns false
		System.out.printf("%n	-------------------------%n");

		// Be careful ; takesWhile and dropWile works on ordered elements. If the element are unordered than the operation will be nondeterministic
		Set.of(2, 4, 6, 3, 8)
				.stream()
				.takeWhile(n -> n % 2 == 0)
				.forEach(System.out::print);	// should return 2 , 4 and 6. Run the code and check if the result is different each time
	}
}
