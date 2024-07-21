# Jshell

Jshell is a REPL.
REPL : A read–eval–print loop (REPL), also termed an interactive toplevel or language shell, is a simple interactive computer
programming environment that takes single user inputs, executes them, and returns the result to the user; a program written in a
REPL environment is executed piecewise.

## Commands

run jshell :

```
$ jshell
```

exit jshell :

```
$ /exit OR ctrl+d
```

You can use tab for autocompletion. example : Sys and tab shows System
You don't need to catch checked exceptions in jshell as it already doing it
For methods with multiples parameters, type the method name and tab. All forms of that method will be shown

execute a statement :

```
jshell> 1 + 1
$1 ==> 2
```

You can use $1 in your next statements. Example :

```
$ System.out.print($1)
```

Example with regular expressions (use \ to escape a character):
```
$ "ab c".matches("[ab]+b\\sc")
```

## Declarations

```
jshell> int x = 1
x ==> 1

jshell> x = 2
x ==> 2

jshell> int y = x + 1
y ==> 3

jshell> System.out.println(y)
3
```

Show all variables :

```
jshell> /vars
|    int x = 2
|    int y = 3
```

### Declare methods

we will declare a new method whichModule which will show the module of the class passed in parameter.

```
jshell> Module whichModule(Class clazz) {
   ...>     return clazz.getModule();
   ...> }
|  created method whichModule(Class)

jshell> whichModule(String.class)
$2 ==> module java.base

jshell> whichModule(java.util.logging.LogManager.class)
$3 ==> module java.logging
```

Why we should specify the package name here ?
Well because it is not imported by shell. To see the list of imported classes, type this :

```
jshell> /imports
|    import java.io.*
|    import java.math.*
|    import java.net.*
|    import java.nio.file.*
|    import java.util.*
|    import java.util.concurrent.*
|    import java.util.function.*
|    import java.util.prefs.*
|    import java.util.regex.*
|    import java.util.stream.*
```
You can import the LogManager class if you want :
```
jshell> import java.util.logging.LogManager

jshell> whichModule(LogManager.class)
$5 ==> module java.logging 
```
You can get the methods via :
```
jshell> /methods
|    Module whichModule(Class)
```

### Declare method with non defined variables

In jshell you can declare a method with an unknown variable and declare it later.

```
jshell> int timesThree(int i) {
   ...>     return i * three;
   ...> }
|  created method timesThree(int), however, it cannot be invoked until variable three is declared

jshell> timesThree(2)
|  attempted to call method timesThree(int) which cannot be invoked until variable three is declared

jshell> int three = 3
three ==> 3

jshell> timesThree(2)
$9 ==> 6

jshell> /vars
|    Module $2 = module java.base
|    Module $3 = module java.logging
|    Module $5 = module java.logging
|    int $7 = 0
|    int three = 3
|    int $9 = 6 
```

### Class declarations

Here's how you can declare a class via jshell :

```
jshell> class Person {
   ...>     private String name;
   ...>     public Person(String name){
   ...>         this.name = name;
   ...>     }
   ...>     public String toString() {
   ...>         return "The person name is " + this.name;
   ...>     }
   ...> }
|  created class Person

jshell> new Person("Sander")
$2 ==> The person name is Sander
```
To show declared classes :
```
jshell> /types
|    class Person
```

## Sessions and classpath

You can add a jar to the jshell classpath like this (importing commons lang):

```
$ jshell --class-path commons-lang3-3.9.jar
|  Welcome to JShell -- Version 16.0.2
|  For an introduction type: /help intro
```
I'm in the /tmp directory and the jar commons also.

```
jshell> import org.apache.commons.lang3.StringUtils

jshell> StringUtils.leftPad("Hello",10)
$2 ==> "     Hello"

jshell> class Person {
   ...>     String name;
   ...> }
|  created class Person

jshell> /save mysession.jsh
```

At the end, I will save my session into a file called mysession.jsh. The next time i open this file, it will re-import classes,
variables and types defined in my session. The side effect is that it will also execute the statements of you session so be carefull
if you are deleting files or creating them or anything that can have a side effect.

Now we can open that session like this :

```
Mohamed-Ali@FRL-38JDNF2 MINGW64 /tmp
$ ls | grep my
mysession.jsh

Mohamed-Ali@FRL-38JDNF2 MINGW64 /tmp
$ jshell --class-path commons-lang3-3.9.jar
|  Welcome to JShell -- Version 16.0.2
|  For an introduction type: /help intro

jshell> /open mysession.jsh

jshell> /imports
|    import java.io.*
|    import java.math.*
|    import java.net.*
|    import java.nio.file.*
|    import java.util.*
|    import java.util.concurrent.*
|    import java.util.function.*
|    import java.util.prefs.*
|    import java.util.regex.*
|    import java.util.stream.*
|    import org.apache.commons.lang3.StringUtils

jshell> / types
|  Command: '/' is ambiguous: /list, /edit, /drop, /save, /open, /vars, /methods, /types, /imports, /exit, /env, /reset, /reload, /history, /debug, /help, /set, /?, /!
|  Type /help for help.

jshell> /types
|    class Person
```

JShell provides an API also which can be used from you java source code. Find more at the end of this video :
- https://app.pluralsight.com/course-player?clipId=5522f1aa-12f3-4924-b247-b2a5c1adfe67