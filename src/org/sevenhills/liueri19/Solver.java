package org.sevenhills.liueri19;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Solver extends JPanel {
	private static final int width = 800;
	private static final int height = 800;
	public static List<City> cities = new ArrayList<City>();
	
	public Solver() {
		super(true);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public static void main(String[] args) {
		//variables
		BruteForce bf = new BruteForce();
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
		//find path
		cities = bf.bruteForce(cities);
		//print result
		System.out.print("\nRoute: " + cities.get(0));
		for (int i = 1; i < cities.size(); i++)
			System.out.print(" -> " + cities.get(i));
		System.out.println("\nDistance: " + bf.getBest());
		////
		//draw result
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				(new Solver()).setUp();
			}
		});
		////
	}
	
	public void setUp() {
		JFrame frame = new JFrame("TSP");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//set background to black
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		////
		//draw cities
		g.setColor(Color.WHITE);
		//horizontal half / vertical half
		g.fillRect(width/2-1, 0, 2, height);
		g.fillRect(0, height/2-1, width, 2);
		////
		//draw cities & path
		int prevX = (int) (cities.get(cities.size()-1).getX() / 100d * width);
		int prevY = (int) (height - cities.get(cities.size()-1).getY() / 100d * height);
		for (City c : cities) {
			int scaledX = (int) (c.getX() / 100d * width);
			int scaledY = (int) (height - c.getY() / 100d * height);
			g.fillRect(scaledX - 5, scaledY - 5, 10, 10);
			g.drawString(c.toString(), scaledX + 5, scaledY - 10);
			g.drawLine(prevX, prevY, scaledX, scaledY);
			prevX = scaledX;
			prevY = scaledY;
		}
		////
	}
}
