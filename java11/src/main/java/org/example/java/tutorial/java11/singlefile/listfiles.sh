import java.nio.file.*;
public class ListFiles {
  public static void main(String[] args) throws Exception{
    Files.walk(Paths.get(args[0])).forEach(System.out::println);
  }
}

/*
if you try to run the script with simple java ./listfiles.sh you will face this error
Erreur : impossible de trouver ou de charger la classe principale .\listfiles.sh
Causé par : java.lang.ClassNotFoundException: /\listfiles/sh

The source option is mandatory in this case. You need to pass it :

> java --source 11 .\listfiles.sh .
.
.\HelloWorld.java
.\listfiles.sh

*/