# Java 13

## ByteBuffer improvements

The ByteBuffer API has been improved in Java 13. The ByteBuffer API is used to read and write data in a buffer.
You can find the improvements here :
- https://app.pluralsight.com/ilx/video-courses/e1d5e19c-5eb8-44dc-be43-0e8a4c13c7fc/c0b1dde3-3b6d-4796-8b7e-a336a9b7ce6e/af240842-d2ca-4b1f-b290-d0c906ec7758

## Security

These classes has been moved to another package. From :
- javax.security.cert.Certificate
- javax.security.cert.X509Certificate

to :
- java.security.cert.Certificate
- java.security.cert.X509Certificate

details here :
- https://app.pluralsight.com/ilx/video-courses/e1d5e19c-5eb8-44dc-be43-0e8a4c13c7fc/c0b1dde3-3b6d-4796-8b7e-a336a9b7ce6e/af240842-d2ca-4b1f-b290-d0c906ec7758

## Switch Expressions

The switch feature remains in preview in Java 13. You can find the issues with the old switch api in the **java12** module.
In java 13, you can use the switch expression with the yield keyword instead of beak (which creates a confusion).
- ![switch.png](images%2Fswitch.png)

## Unicode 12.1

Java 13 supports Unicode 12.1. It is used to support the new characters in the Unicode standard.
- ![switch.png](images%2Fswitch.png)

Find more here : 
- https://app.pluralsight.com/ilx/video-courses/e1d5e19c-5eb8-44dc-be43-0e8a4c13c7fc/c0b1dde3-3b6d-4796-8b7e-a336a9b7ce6e/5909ac8a-6df8-48b0-bdb9-f74e3ee6afaf

## ZGC

Z Garbage Collector (ZGC) is a scalable low-latency garbage collector.
It has been introduced in Java 11 and it is available as an experimental feature in Java 13.
An issue with un-commit unused memory has been fixed in Java 13.
find more here :
- https://app.pluralsight.com/ilx/video-courses/e1d5e19c-5eb8-44dc-be43-0e8a4c13c7fc/c0b1dde3-3b6d-4796-8b7e-a336a9b7ce6e/5909ac8a-6df8-48b0-bdb9-f74e3ee6afaf

## Text Blocks

Text Blocks is a new feature in Java 13. It is used to write multi-line strings in a more readable way.
It is a preview feature in Java 13, so you need to enable it in your IDE.
It is a good feature to write SQL queries, JSON, HTML, XML, etc.
Before and to write a json, you need to escape the double quotes, but with text blocks, you can write it directly.
Before :
- ![text-block-1.png](images%2Ftext-block-1.png)
After :
- ![text-block-2.png](images%2Ftext-block-2.png)
As you can see, the last result show the printed string with the indentation of the text and not your source code.

Find more here :
- https://app.pluralsight.com/ilx/video-courses/e1d5e19c-5eb8-44dc-be43-0e8a4c13c7fc/e886f010-8cad-4cb3-8e0d-59bf70b52d63/6ce2662c-bc58-4e3c-8355-ed335489f9fe

and a demo here :
- https://app.pluralsight.com/ilx/video-courses/e1d5e19c-5eb8-44dc-be43-0e8a4c13c7fc/e886f010-8cad-4cb3-8e0d-59bf70b52d63/fb6a0a8d-02b9-4429-bdda-bc005449f289

## API Changes

### String :: stripIndent

The stripIndent method is used to remove the common leading white space from each line of the string.

### String :: translateEscapes

The translateEscapes method is used to translate escape sequences in the string.

### String :: formatted

The formatted method is used to format the string using the specified format string and arguments.