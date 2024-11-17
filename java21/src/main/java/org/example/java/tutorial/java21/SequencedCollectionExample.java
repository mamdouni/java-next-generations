package org.example.java.tutorial.java21;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class SequencedCollectionExample {

    public static void main(String[] args) {

        SequencedCollection<Integer> integerList = List.of(1, 2, 3, 4, 5);
        logFirstAndLastElementCollection(integerList);

        SequencedSet<Integer> integerSet = new LinkedHashSet<>(Set.of(1, 2, 3, 4, 5));
        logFirstAndLastElementSet(integerSet);

        SequencedMap<Integer, String> integerStringMap = new LinkedHashMap<>(Map.of(1, "One", 2, "Two", 3, "Three"));
        logFirstAndLastElementMap(integerStringMap);

        // As you can see, to use the new methods, your collection must define encounter order.
        // If you use a non-encounter order collection like HashSet, the methods will throw an UnsupportedOperationException.

        log.info("--------- SequencedSet ----------");
        integerSet.addFirst(99);
        integerSet.addLast(100);
        logFirstAndLastElementSet(integerSet);
        log.info("{}", integerSet.removeFirst());
        log.info("{}", integerSet.removeLast());
        logFirstAndLastElementSet(integerSet);
        logFirstAndLastElementSet(integerSet.reversed());

        log.info("--------- SequencedMap ----------");
        integerStringMap.putFirst(0, "Zero");
        integerStringMap.putLast(4, "Four");
        logFirstAndLastElementMap(integerStringMap);
        log.info("{}", integerStringMap.pollFirstEntry());
        log.info("{}", integerStringMap.pollLastEntry());
        logFirstAndLastElementMap(integerStringMap);
        logFirstAndLastElementMap(integerStringMap.reversed());

        log.info("--------- SequencedMap::Keys ----------");
        integerStringMap.sequencedKeySet().forEach(key -> log.info("Key : {}", key));

        log.info("--------- SequencedMap::Values ----------");
        integerStringMap.sequencedValues().forEach(value -> log.info("Value : {}", value));

        log.info("--------- SequencedMap::Entries ----------");
        integerStringMap.sequencedEntrySet().forEach(entry -> log.info("Entry : {}", entry));
    }

    static void logFirstAndLastElementCollection(SequencedCollection<?> collection){
        log.info("First Collection element is : {} and the Last Collection element is : {}", collection.getFirst(), collection.getLast());
    }

    static void logFirstAndLastElementSet(SequencedSet<?> collection){
        log.info("First Set element is : {} and the Last Set element is : {}", collection.getFirst(), collection.getLast());
    }

    static void logFirstAndLastElementMap(SequencedMap<?,?> map){
        log.info("First Map entry is : {} and the Last Map entry is : {}", map.firstEntry(), map.lastEntry());
    }
}
