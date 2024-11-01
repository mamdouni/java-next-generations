#!/usr/bin/java --source 11
import java.nio.file.*;
public class ListFiles {
  public static void main(String[] args) throws Exception{
    Files.walk(Paths.get(args[0])).forEach(System.out::println);
  }
}

/*
Or using the shebang notation on an unix operating system

> chmod +x ./listfiles2.sh
> ./listfiles2.sh .

*/