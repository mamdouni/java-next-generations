package org.example.java.tutorial.java15.java9.features.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactoryMethods {

	public static void main(String[] args) {

		// some factory methods has been added to collectors. You can create a collection with elements in a single statement. This is true for lists, sets, maps
		List<Integer> ints = List.of(1, 2, 3); // this creates an immutable list. until now we don't have a method that creates a mutable collection in java
		System.out.println(ints.getClass());	// ImmutableCollections$ListN
		System.out.println(List.of(1).getClass());	// ImmutableCollections$List12
		// We have multiple implementations depends on the number of elements for optimization concerns

		Set<String> stringSet = Set.of("first", "second");	// make sure to not pass null or duplicated elements in the constructor otherwise you'll have a runtime exception
		System.out.println(stringSet.getClass());
		// check the implementation of Set.of and you'll find that :
		// if you pass less than 2 elements => return new ImmutableCollections.Set12<>(e1, e2) , more than 2 elements => return new ImmutableCollections.SetN<>(e1, e2, e3)
		// Set.of takes until 10 parameters without creating an intermediate array (that's why there is multiple overloaded methods), if you pass more you'll use <E> Set<E> of(E... elements)

		Map<String,Integer> map = Map.of("Key1", 1, "Key2", 2);			// up to 10 key/values
		map = Map.ofEntries(Map.entry("Key1", 1), Map.entry("Key2", 2));	// unbounded number of entries : ofEntries(Entry<? extends K, ? extends V>... entries)
		System.out.println(map);
		System.out.println(map.getClass());

		// warning : Iteration order for Sets and Maps are not guaranteed
	}
}
