package org.example.java.tutorial.java9.language;

import java.io.FileInputStream;
import java.io.IOException;

public class TryWithResources {

	// java 8
	public void doWithFile8(FileInputStream fis) throws IOException {

		// in java 8 you have to declare a new field to hold the passed parameter
		try(FileInputStream fisTmp = fis){
			fis.read();
		}
	}

	// java 9
	public void doWithFile9(FileInputStream fis) throws IOException {

		// in java 9 you don't have to declare a new field
		// fis must be final or effectively final
		try(fis){
			fis.read();
		}
	}
}
