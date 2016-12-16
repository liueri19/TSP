package org.sevenhills.liueri19;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruteForce {
	
	private static double best;
	
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
		cities = bruteForce(cities);
		for (City c : cities) {
			System.out.print(c + " -> ");
		}
	}
	
	//pseudo code
	/* double distance = 0;
	 * double best = Integer.MAX;
	 * for (City c0 : cities) {
	 * 	distance += origin.getDistance(c0);
	 * 	for (City c1 : cities.remove(c0)) {
	 * 		distance += c0.getDistance(c1);
	 * 		...
	 * 		for (City cN : cities.remove(c0).remove(c1)...) {
	 * 			//only one city in the list
	 * 			distance += cN-1.getDistance(cN);
	 * 			distance += cN.getDistance(origin);
	 * 			if (distance < best)
	 * 				best = distance;
	 * 		}
	 * 	}
	 * }
	 */
	
	/**
	 * Find the shortest path through the list of cities using brute force algorithm.
	 * @param cities the list of cities to find path in
	 * @return a list of the same cities in the order of visit
	 */
	private static List<City> bruteForce(List<City> cities) {
		City origin = cities.remove(0);
		List<City> result = new ArrayList<City>();
		double distance = 0;
		loop(origin, result, origin, cities, distance);
		
		return result;
	}
	
	private static void loop(City origin, List<City> visited, City current, List<City> cities, double distance) {
		if (cities.size() == 1) {
			City last = cities.get(0);
			distance += current.getDistance(last);
			visited.add(last);
			distance += last.getDistance(origin);
			if (distance < best)  //new best found
				best = distance;
			return;
		}
		for (City c : cities) {
			List<City> copy = new ArrayList<City>(cities);
			copy.remove(c);
			visited.add(c);
			loop(origin, visited, c, copy, distance);
		}
	}
}