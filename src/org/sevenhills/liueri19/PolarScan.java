package org.sevenhills.liueri19;

import java.util.ArrayList;
import java.util.List;

/**
 * Scan the 2-dimensional space counter-clockwise, producing two candidate solutions.
 * Compare the solutions for the better one, if any difference exists.
 */
public class PolarScan {
	private int[] center = new int[2];	//average of cities
	private List<PSCity> cities = new ArrayList<PSCity>();
	private City origin;
	private double distance;
	
	public List<City> solve(List<City> argCities) {
		//get average coordinate
		int sumX = 0, sumY = 0;
		for (City c : argCities) {
			sumX += c.getX();
			sumY += c.getY();
		}
		center[0] = sumX / argCities.size();
		center[1] = sumY / argCities.size();
		////
		//parse cities
		for (City c : argCities) {
			cities.add(new PSCity(c, center[0], center[1]));
		}
		////
		origin = cities.get(0);
		//order cities
		cities.sort(null);
		//reorder cities
		int endIndex = cities.indexOf(origin);
		List<PSCity> tempList = new ArrayList<PSCity>(cities.subList(0, endIndex));
		cities.removeAll(tempList);
		cities.addAll(tempList);
		////
		//parse back to List<City>, calculate distance
		List<City> result = new ArrayList<City>();
		distance += origin.getDistance(cities.get(1));
		result.add(origin);
		for (int i = 1; i < cities.size(); i++) {
			distance += cities.get(i-1).getDistance(cities.get(i));
			result.add(cities.get(i));
		}
		////
		return result;
	}
	
	public double getBestDistance() {
		return distance;
	}
}

class PSCity extends City implements Comparable<PSCity> {
	private double angle;	//radians
	private int relativeX, relativeY;
	
	/**This constructor should never be used. Use the superclass City instead.*/
	@Deprecated
	PSCity(String id, int x, int y) {
		super(id, x, y);
	}
	
	PSCity(City city, int centerX, int centerY) {
		super(city.toString(), city.getX(), city.getY());
		relativeX = getX() - centerX;
		relativeY = getY() - centerY;
		//first quadrant
		if (getRelativeX() >= 0 && getRelativeY() >= 0) {
			if (getRelativeX() != 0)
				angle = Math.atan(getRelativeY() *1d / getRelativeX());
			else
				angle = Math.PI/2;
		}
		//second quadrant
		else if (getRelativeX() < 0 && getRelativeY() >= 0) {
				angle = Math.PI - Math.atan(getRelativeY() *1d / getRelativeX());
		}
		//third quadrant
		else if (getRelativeX() < 0 && getRelativeY() < 0) {
				angle = Math.atan(getRelativeY() *1d / getRelativeX()) + Math.PI;
		}
		//fourth quadrant
		else {
			if (getRelativeX() != 0)
				angle = 2*Math.PI - Math.atan(getRelativeY() *1d / getRelativeX());
			else
				angle = 1.5 * Math.PI;
		}
	}
	
	public double getAngle() {
		return angle;
	}
	
	public int getRelativeX() {
		return relativeX;
	}
	
	public int getRelativeY() {
		return relativeY;
	}

	@Override
	public int compareTo(PSCity c) {
		if (getAngle() < c.getAngle())
			return -1;
		if (getAngle() > c.getAngle())
			return 1;
		return 0;
	}
}
