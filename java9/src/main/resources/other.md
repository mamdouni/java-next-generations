# Other features

A lot of features has been added to java 9 and we cannot talk about them all like :

- Process and ProcessHandle Api
- Reactive Streams (important topic about data stream)
- StackWalker
- G1 garbage collector has been introduced in java 6 and it is the one used by default now in java 9. It's more stable and performant. check this video for details 
https://app.pluralsight.com/course-player?clipId=35a8f669-c00f-4907-bbde-ec5ca676d178
- String performance :
  - The string class is no more represented by the char[] but with a byte[]. This will save 10% to 15% of the heap space (if you are not using a full unicode characters)
  - string concatenation : by default when you perform String a = "a" + "b" the compiler will create a StringBuilder and call the append method. This behavior has been changed to determine the class and method to call at runtime and not compile time
  - check this video for details : https://app.pluralsight.com/course-player?clipId=0db7c7d2-2126-4b6a-b913-957dc7dcb083
- Security :
  - Serialization was a nightmare before java 9. Imagine that you will deserialize an Object from network. This will make you vulnerable as an attacker can change the byte code to add large nested Objects (denial service attack). In java 9 you can now
use filters so you can decide how many nested objects you can scan and what packages to import. find more here : https://app.pluralsight.com/course-player?clipId=a07e3dfe-367c-4efb-8a8a-83b1b0857b26
  - TLS improvement like ALPN , DTLS, OCSP, Disabling SHA-1 certificated and add the support of SHA-3 ; https://app.pluralsight.com/course-player?clipId=d92be049-65a7-495d-b38d-674405ae5a46

Check the java enhancement proposals of java 9 here : http://openjdk.java.net/projects/jdk9/
Or check more details about these JEPs in oracle site : https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-0CA9D45F-31BE-4C5A-B85D-8A638B687617 

A full wrap up of this module from sander here (he talks about his book about modularity and his tutorial also) : https://app.pluralsight.com/course-player?clipId=2c17db02-9d25-4c6a-84db-bc5ea4d18e5b

Check this course on pluralsight : *What's New in Java 9 - By Sander Mak*