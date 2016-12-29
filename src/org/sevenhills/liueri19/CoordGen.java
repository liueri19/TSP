package org.sevenhills.liueri19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CoordGen {
	private static final String capitalsArray[] = 
		{ "Beijing", "Rome", "Tokyo", "Moscow", "Washington", "London", "Paris", "Berlin", "Vienna", "Singapore" };
	private static List<String> capitals = new ArrayList<String>(Arrays.asList(capitalsArray));
	private static final int cities = 10;

	public static void main(String[] args) {
		Random random = new Random();
		
		System.out.print("[ ");
		if (cities <= capitalsArray.length) {
			for (int i = 0; i < cities; i++) {
				int x = random.nextInt(100);
				int y = random.nextInt(100);
				int index = random.nextInt(capitals.size());
				System.out.printf("[ %s, %d, %d ]", capitals.remove(index), x, y);
				if (i != cities-1)
					System.out.print(", ");
			}
		}
		else {
			for (int i = 0; i < cities; i++) {
				int x = random.nextInt(100);
				int y = random.nextInt(100);
				System.out.printf("[ City_%d, %d, %d ]", i, x, y);
				if (i != cities-1)
					System.out.print(", ");
			}
		}
		System.out.print(" ]");
	}

}
