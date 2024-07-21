# New Features

## API

### Indentation : String

Adjusts the indentation of each line of this string based on the value of n, and normalizes line termination characters.

```java
String indent(int n)
```

### Transform : String

This method allows the application of a function to this string.
    
```java
<R> R transform(Function<? super String,? extends R> f)
```