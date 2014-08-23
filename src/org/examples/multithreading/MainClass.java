package org.examples.multithreading;

import java.util.ArrayList;

public class MainClass {
	public static ArrayList<String> Chairs = new ArrayList<>();
	static int iNumberOfThread = 10;

	public static void main(String[] args) {

		Chairs.add("Chair-Red");
		Chairs.add("Chair-Yellow");
		Chairs.add("Chair-Blue");

		for (int i = 0; i < iNumberOfThread; i++) {
			Thread t = new Thread(new SimpleTask());
			t.start();
		}

	}

}
