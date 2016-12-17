package org.sevenhills.liueri19;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruteForce {
	
	public static double best = Double.MAX_VALUE;
	public static List<City> result = new ArrayList<City>();
	
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
		bruteForce(cities);
		
		for (City c : result) {
			System.out.print(c + " -> ");
		}
		System.out.print("\n\t" + best);
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
	 * Find the shortest path through the list of cities using brute force algorithm. The result of the algorithm is stored in the <code>result</code>.
	 * @param cities the list of cities to find path in
	 */
	private static void bruteForce(List<City> cities) {
		City origin = cities.remove(0);
		List<City> visited = new ArrayList<City>();
		double distance = 0;
		result.add(origin);
		visited.add(origin);
		loop(origin, visited, origin, cities, distance);
	}
	
	private static void loop(City origin, List<City> visited, City current, List<City> unvisited, double distance) {
		//if only one city left to visit
		if (unvisited.size() == 1) {
			City last = unvisited.get(0);
			distance += current.getDistance(last);
			distance += last.getDistance(origin);
			//if the result is better than the known one
			if (distance < best) {  //new best found
				best = distance;
				//result = visited cities + last city
				result = new ArrayList<City>(visited);
				result.add(last);
			}
			//testing
			for (City c : visited)
				System.out.print(c + " -> ");
			System.out.println(last);
			System.out.println(distance);
			////
			return;
		}
		
		for (City c : unvisited) { //c: next city to visit
			List<City> copy = new ArrayList<City>(unvisited);
			copy.remove(c);
			distance += current.getDistance(c);
			visited.add(c);
			loop(origin, visited, c, copy, distance);
			visited.remove(visited.size()-1); //remove the last city, go back to the previous city
		}
	}
}