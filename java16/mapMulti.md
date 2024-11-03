# mapMulti() Method in Java 16

The mapMulti method in Java’s Stream API, introduced in Java 16, allows you to transform each element in a stream into multiple elements in a flexible and efficient way. 
This method is useful when you want to perform a one-to-many transformation (e.g., turning each element into zero, one, or multiple elements), which is not possible with map alone.

## Syntax of mapMulti

The mapMulti method has the following syntax:

```java
Stream<T> stream.mapMulti(BiConsumer<T, Consumer<R>> mapper)
```

Here:
- **mapper** is a BiConsumer that accepts an element of the stream and a Consumer to add transformed elements.
- Inside the **mapper**, you call consumer.accept(...) as many times as needed to generate the elements.

## Example Usages of mapMulti

Let's look at some common examples to understand how mapMulti works.

### Example 1: Expanding Each Element to Multiple Elements

Suppose we have a list of sentences, and we want to split each sentence into individual words in the stream:

```java
List<String> sentences = List.of("Java Stream API", "mapMulti is useful", "Java 16 feature");

List<String> words = sentences.stream()
    .mapMulti((sentence, consumer) -> {
        // Split the sentence into words and pass each word to the consumer
        for (String word : sentence.split(" ")) {
            consumer.accept(word);
        }
    })
    .collect(Collectors.toList());

System.out.println(words);
```
Output:
```
[Java, Stream, API, mapMulti, is, useful, Java, 16, feature]
```

In this example, mapMulti splits each sentence into words, and each word is passed individually to the downstream consumer, effectively “flattening” the list of words.

### Example 2: Filtering and Expanding Elements Conditionally

Let’s say you have a list of integers, and for each integer, if it’s even, you want to add both the integer and its half to the stream. If it’s odd, ignore it.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

List<Integer> result = numbers.stream()
    .mapMulti((number, consumer) -> {
        if (number % 2 == 0) {
            consumer.accept(number);       // Add the even number itself
            consumer.accept(number / 2);   // Add half of the even number
        }
    })
    .collect(Collectors.toList());

System.out.println(result);
```
Output:
```
[2, 1, 4, 2, 6, 3]
```
In this example, mapMulti adds both the number and its half if the number is even, allowing conditional and dynamic expansion of each element.

### Example 3: Flattening Nested Lists

If you have a list of lists and want to flatten it into a single list with mapMulti, it’s straightforward to achieve. For example:

```java
List<List<String>> listOfLists = List.of(
    List.of("Java", "Stream"),
    List.of("API", "mapMulti"),
    List.of("Example")
);

List<String> flatList = listOfLists.stream()
    .mapMulti((list, consumer) -> list.forEach(consumer))
    .collect(Collectors.toList());

System.out.println(flatList);
```
Output:
```
[Java, Stream, API, mapMulti, Example]
```
In this case, each sub-list is passed to mapMulti, which in turn passes each element of the sub-list to the downstream consumer, flattening the structure.

## Key Differences Between mapMulti and flatMap
- mapMulti is often more efficient than flatMap for one-to-many transformations because it doesn’t create intermediate streams or collections. It operates directly on the elements by passing them to the downstream consumer.
- flatMap transforms each element into a stream and then flattens the streams into a single stream. mapMulti offers more control and is ideal for custom transformations where you can decide on a per-element basis how many results to produce.

## When to Use mapMulti

Use mapMulti when:
- You need a one-to-many mapping but want to avoid the overhead of creating intermediate collections.
- You need fine-grained control over the transformation, allowing you to skip or add multiple elements per original element.
- You are performing conditional transformations where you may add zero, one, or multiple elements based on certain criteria.

mapMulti is an efficient and flexible option for complex transformations in Java Streams, especially where flatMap is limited or less efficient.

## Conclusion

The mapMulti method in Java 16’s Stream API provides a powerful way to transform each element in a stream into multiple elements in a flexible and efficient manner. It allows you to perform one-to-many transformations with fine-grained control, making it a valuable addition to the Stream API.






