# New Features

## API

### Indentation :: String

Adjusts the indentation of each line of this string based on the value of n, and normalizes line termination characters.

```java
String indent(int n)
```

### Transform :: String

This method allows the application of a function to this string.
    
```java
<R> R transform(Function<? super String,? extends R> f)
```

### Compact Number Format

The compact number format is a new feature in Java 12. It is used to format a number in a compact form.

### Teeing Collector :: Stream

This method is used to perform a transformation on the elements of the stream and then combine the results.
Previously, we can use only one collector with the stream api, but now we can use two collectors and combine them to have a single result.

![teeing.png](images%2Fteeing.png)