package org.sevenhills.liueri19;

import java.util.Random;

public class CoordGen {

	public static void main(String[] args) {
		Random random = new Random();
		
		System.out.print("[ ");
		for (int i = 0; i < 10; i++) {
			int x = random.nextInt(100);
			int y = random.nextInt(100);
			System.out.printf("[ City_%d, %d, %d ]", i, x, y);
			if (i != 9)
				System.out.print(", ");
		}
		System.out.print(" ]");
	}

}
