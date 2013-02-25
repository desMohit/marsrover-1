/**
 * This file is part of a solution to the Mars Rover Exercise
 * (http://thefundoowriter.com/2009/10/01/the-mars-rover-problem/).
 * 
 * Matthew Baker <mu.beta.06@gmail.com> 2013
 * 
 * This module defines the base class for the RoverDispatcher.
 * 
 * Responsible for interpretting user data into controller data.
 * 
 */

package com.mars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class RoverDispatcher {
	
	//private static final String INSTRUCTIONS[] = {"L", "R", "M"};
	private static final HashMap<String, Double> HEADINGS = new HashMap<String, Double>();
	
	static
    {
		HEADINGS.put("E", 0.0);
		HEADINGS.put("N", Math.PI/2);
		HEADINGS.put("W", Math.PI);
		HEADINGS.put("S", 3*Math.PI/2);
    }
	
	private MarsRoverController controller;
	private ArrayList<String> rovers = new ArrayList<String>();
	private ArrayList<String> instructions = new ArrayList<String>();
	private Point vertex;

	/**
	 * Dispatch Rover input to RoverController.
	 * @throws Exception 
	 */
	public void dispatch() throws Exception {
		String rover;
		String instruction;
		for (int j = 0; j < rovers.size(); j++) {
			rover = this.rovers.get(j);
			instruction = this.instructions.get(j);
			for (int i = 0; i < instruction.length(); i++){
			    char c = instruction.charAt(i);
			    if (c == 'L') {
			    	this.turn_left(rover);
			    } else if (c == 'R') {
			    	this.turn_right(rover);
			    } else if (c == 'M') {
			    	this.move(rover);
			    } else {
			    	throw new Exception(String.format("unknown instruction %s", c));
			    }
			}
		}
	}
	
	private String mapControllerHeading(double heading) {
		for (String h : HEADINGS.keySet()) {
			// abs to deal with -0
			if (HEADINGS.get(h).equals(Math.abs(heading))) {
				return h;
			}
		}
		System.out.println("heading: " + heading);
		return null;
	}

	/**
	 * Move the nominated Rover forward 1 position.
	 * 
	 * @param roverId
	 * @throws Exception 
	 */
	private void move(String roverId) throws Exception {
		this.controller.move(roverId, 1);
	}
	
	/**
	 * Parse input string.
	 * @throws Exception 
	 */
	public void parseInput(ArrayList<String> input) throws Exception {
		this.parseVertex(input.get(0));
		this.controller = new MarsRoverController(new Point(0, 0, 0), this.vertex);
		for (int i = 1; i < input.size(); i+=2) {
			this.parseRover(input.get(i));
		}
		for (int i = 2; i < input.size(); i+=2) {
			this.parseInstruction(input.get(i));
		}
	}
	
	/**
	 * Parse and add Instruction string.
	 * 
	 * @param input
	 */
	private void parseInstruction(String input) {
		input = input.trim();
		this.instructions.add(input);
	}
	
	/**
	 * Parse and add Rover.
	 * 
	 * @param input
	 * @throws Exception
	 */
	private void parseRover(String input) throws Exception {
		input = input.trim();
		String[] rover = input.split(" ");
		if (rover.length == 3) {
			Point position;
			Heading heading;
			try {
				position = new Point(Integer.parseInt(rover[0]), Integer.parseInt(rover[1]), 0);
				
			} catch (Exception e) {
				throw new Exception("Rover position must be specified int int.");
			}
			if (HEADINGS.get(rover[2]) != null) {
				heading = new Heading(HEADINGS.get(rover[2]), Math.PI/2);
			} else {
				throw new Exception(String.format("unknown user heading %s", rover[2]));
			}
			this.controller.addRover(input, position, heading);
			this.rovers.add(input);
			
		} else {
			throw new Exception("Incorrectly specified Rover.");
		}
	}
	
	/**
	 * Parse and add the vertex specific input.
	 * 
	 * @param input
	 * @throws Exception
	 */
	private void parseVertex(String vertex) throws Exception {
		vertex = vertex.trim();
		String[] v = vertex.split(" ");
		if (v.length == 2) {
			try {
				this.vertex = new Point(Integer.parseInt(v[0]), Integer.parseInt(v[1]), 0);
			} catch (Exception e) {
				throw new Exception("vertex must be specified int int.");
			}
		} else {
			throw new Exception("vertex must be specified int int.");
		}
	}
	
	/**
	 * Renders view to user.
	 * 
	 * @return
	 */
	public String renderView() {
		String output = "";
		for (String roverId: this.rovers) {
			Rover r = this.controller.getRover(roverId);
			Point p = r.getPosition();
			String h = this.mapControllerHeading(r.getHeading().getAzimuth());
			output += String.format("%d %d %s\n", (int) p.getX(), (int) p.getY(), h);
		}
		return output;
	}
	
	
	/**
	 * Turn nominated Rover Left.
	 * 
	 * @param roverId
	 * @throws Exception 
	 */
	private void turn_left(String roverId) throws Exception {
		this.controller.turn(roverId, new Heading(Math.PI/2, 0));
	}
	
	/**
	 * Turn nominated Rover Right.
	 * 
	 * @param roverId
	 * @throws Exception 
	 */
	private void turn_right(String roverId) throws Exception {
		this.controller.turn(roverId, new Heading(-Math.PI/2, 0));
	}
	
	/**
	 * Main Program
	 * 
	 * Build:		ant dist
	 * 
	 * Usage:
	 * Interactive 	java -jar MarsRoverProject.jar
	 * Batch       	cat inputfile.txt | java -jar MarsRoverProject.jar
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> input = new ArrayList<String>();
		if (System.console() != null) {
			input.add(System.console().readLine("Please enter grid vertex e.g. x y: ").trim());
			while (true) {
				String r = System.console().readLine("Please enter Rover e.g. x y H (enter to dispatch): ");
				if (r.equals("")) {
					break;
				} else {
					input.add(r);
				}
				input.add(System.console().readLine("Please enter Rover instructions:").trim());
			}
			
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    String line;
			try {
				while ((line = in.readLine()) != null) {
					input.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		RoverDispatcher dispatcher = new RoverDispatcher();
		try {
			dispatcher.parseInput(input);
			dispatcher.dispatch();
			System.out.println(dispatcher.renderView());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
