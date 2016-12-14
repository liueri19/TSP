package org.sevenhills.liueri19;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Clustering {
	
	public static void main(String[] args) {
		//variables
		List<City> cities = new ArrayList<City>();
		
		//get input
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();
		////
		//remove all brackets
		input = input.replaceAll("\\[\\s", "");
		input = input.replaceAll("\\s\\]", "");
		////
		//split by comma
		String[] inputs = input.split(", ");
		//construct & add cities
		for (int i = 0; i < inputs.length; i += 3)
			cities.add(new City(inputs[i], Integer.parseInt(inputs[i+1]), Integer.parseInt(inputs[i+2])));
		////
		cities = findPath(cities);
	}
	
	/**
	 * Find a relatively short path through the list of cities.
	 * @param cities the list of cities to find path in
	 * @return a list of the same cities in the order of visit
	 */
	private static List<City> findPath(List<City> cities) {
		City origin = cities.remove(0);
		
	}
}


class City {
	private final String id;
	private final int x, y;
	
	City(String id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	/**
	 * Calculate the distance between this city and the specified city
	 * @param city the target city
	 * @return the distance between this city and the specified city
	 */
	public double getDistance(City city) {
		int deltaX = Math.abs(city.getX() - this.getX());
		int deltaY = Math.abs(city.getY() - this.getY());
		return Math.pow(Math.pow(deltaX, 2) + Math.pow(deltaY, 2), 0.5);
	}
}
