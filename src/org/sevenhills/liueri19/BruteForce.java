package org.sevenhills.liueri19;

import java.util.ArrayList;
import java.util.List;

public class BruteForce {
	
	private double best = Double.MAX_VALUE;
	private List<City> cities;
	private List<City> result = new ArrayList<City>();
	
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
	 * @param argCities the list of cities to find path in
	 * @return a list of cities in the order of visit
	 */
	public List<City> solve(List<City> argCities) {
		cities = new ArrayList<City>(argCities);
		City origin = cities.remove(0);
		List<City> visited = new ArrayList<City>();
		result.add(origin);
		visited.add(origin);
		loop(origin, visited, origin, cities, 0);
		return result;
	}
	
	private void loop(City origin, List<City> visited, City current, List<City> unvisited, double distance) {
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
			double currentToC = current.getDistance(c);
			List<City> copy = new ArrayList<City>(unvisited);
			copy.remove(c);
			distance += currentToC;
			visited.add(c);
			loop(origin, visited, c, copy, distance);
			visited.remove(c); //remove the last city, go back to the previous city
			distance -= currentToC;
		}
	}
	
//	public List<City> solve(List<City> argCities) {
//		cities = new ArrayList<City>(argCities);
//		City origin = cities.remove(0);
//		//rule out all duplicate routes?
//		return result;
//	}
	
	public double getBestDistance() {
		return best;
	}
}