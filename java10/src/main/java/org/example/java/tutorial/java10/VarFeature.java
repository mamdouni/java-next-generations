package org.example.java.tutorial.java10;

import java.io.Serializable;
import java.util.List;

public class VarFeature {

	// var works only on a local variable
	//var book = Book.getBook();


	public static void main(String[] args) {

		//var num; you have to complete the right side so the compiler can infer the type

		var a = Book.getBook().getAuthors().stream().findFirst().map(String::length).orElse(0);
		// well this code is not clear and you can split it as below

		var authors = Book.getBook().getAuthors();
		var firstAuthor = authors.stream().findFirst();
		var firstAuthorLength = firstAuthor.map(String::length).orElse(0);
		// the semantic is perfectly clean here

		// Non-denotable types : type cannot be inferred
		/*

		var empty = null;


		var as = new Object(){};
		as = new Object();

		 */
		var list = List.of(1, 2.0, "3");
		// be careful with types intersection as it will not generate a list of Object like the example above.
		Serializable serializable;
		serializable = list.get(0);
	}
}
