package org.sevenhills.liueri19;

public class City {
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
