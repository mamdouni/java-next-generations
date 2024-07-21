package org.example.modules.main;

import org.example.modules.hello.HelloModules;

public class MainApp {
	public static void main(String[] args) {
		HelloModules.doSomething();
	}
}

// run this command from the root directory of the project to compile modules
// javac -d outDir --module-source-path simple-modules $(find simple-modules -name "*.java")

// run modules
// java --module-path outDir -m main.app/org.example.modules.main.MainApp