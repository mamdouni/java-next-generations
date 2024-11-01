package org.example.java.tutorial.java15.java9.language;

import java.util.ArrayList;

public class AnonymousClassTypeInference {

	public static void main(String[] args) {

		// java 5/6
		ArrayList<String> list6 = new ArrayList<String>();

		// java 7/8
		ArrayList<String> list7 = new ArrayList<>();	// no need for type - we are using the diamond operator or the diamond type inference
		ArrayList<String> list8 = new ArrayList<String>(){

			// for anonymous classes we can't use the diamond operator in java 8 , we have to specify the type
			@Override
			public boolean add(String s) {
				System.out.println("Adding ; " + s);
				return super.add(s);
			}
		};

		// java 9
		ArrayList<String> list9 = new ArrayList<>(){

			// in java 9 we can use diamond operator even in the anonymous classes
			@Override
			public boolean add(String s) {
				System.out.println("Adding ; " + s);
				return super.add(s);
			}
		};
	}
}
