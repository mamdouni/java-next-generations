# Java Next Generations

[![Generic badge](https://img.shields.io/badge/JAVA-Version_9-green.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/JAVA-Version_10-red.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/JAVA-Version_11-blue.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/JAVA-Version_12-pink.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/JAVA-Version_13-purple.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/JAVA-Version_14-yellow.svg)](https://shields.io/)

[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://shields.io/)
[![made-with-Markdown](https://img.shields.io/badge/Made%20with-Markdown-1f425f.svg)](http://commonmark.org)

This is my personal notes about the next generations of Java (from java 8). I will try to cover the most important features of each version and provide some examples.

If you face some difficulties in running the example, try to the adequate java version (if you run an example of java 11 and there are compilation errors, use java 11 as jdk).

Most of the examples are from Sander Mark courses on Pluralsight.

## Some concepts

Here, we will cover some java concepts that are important to understand this repository.

### Preview Features

Preview features are features that are fully specified, fully implemented, and yet impermanent. 
They are available in a release, but they are not yet permanent, and they could be changed or removed in a future release.
In this repository, we will just mention the preview features, but we will not use them.
Actually, we will only use them when they become permanent.

- ![preview-features.png](images%2Fpreview-features.png)

Because of preview features, the **mvn clean install** command on the global project will not work. That's why you need to build modules one by one using the java
release that matches the module.
Check the workflows directory to see how to build the modules.
- [workflows](.github/workflows)

With your IDE, you can run the examples one by one.
If you want for example to run java 15 demos, you need to set the jdk to java 15 and enable preview features.

## Reference

- [Java 9](https://app.pluralsight.com/library/courses/java-9-whats-new/table-of-contents)
- [Java 10](https://app.pluralsight.com/library/courses/whats-new-java-10-local-variable-type-inference/table-of-contents)
- [Java 11](https://app.pluralsight.com/library/courses/java-11-whats-new/table-of-contents)
- [Java 12](https://app.pluralsight.com/library/courses/java-12-whats-new/table-of-contents)
- [Java 13](https://app.pluralsight.com/library/courses/whats-new-in-java-13/table-of-contents)
- [Java 14](https://app.pluralsight.com/library/courses/java-14-whats-new/table-of-contents)
- [Java 15](https://app.pluralsight.com/library/courses/java-15-whats-new/table-of-contents)