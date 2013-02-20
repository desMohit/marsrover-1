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

import java.util.ArrayList;
import java.util.HashMap;

public class RoverDispatcher {
	
	private static final String INSTRUCTIONS[] = {"L", "R", "M"};
	private static final HashMap<String, Double> HEADINGS = new HashMap();
	
	static
    {
		HEADINGS.put("E", 0.0);
		HEADINGS.put("N", Math.PI/2);
		HEADINGS.put("W", Math.PI);
		HEADINGS.put("S", 3*Math.PI/2);
    }
	
	private MarsRoverController controller;
	private ArrayList<String> rovers;
	private ArrayList<String> instructions;

	/**
	 * Dispatch Rover input to RoverController.
	 * @throws Exception 
	 */
	private void dispatch() throws Exception {
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
			    	throw new Exception(String.format("'unknown instruction %s", c));
			    }
			}
		}
	}
	
	private String mapControllerHeading(double heading) {
		for (String h : HEADINGS.keySet()) {
			if (HEADINGS.get(h).equals(heading)) {
				return h;
			}
		}
		return null;
	}
	
	private double mapUserHeading(String heading) {
		return HEADINGS.get(heading);
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
	 * Renders view to user.
	 * 
	 * @return
	 */
	private String renderView() {
		String output = "";
		for (String roverId: this.rovers) {
			Rover r = this.controller.getRover(roverId);
			Point p = r.getPosition();
			String h = this.mapControllerHeading(r.getHeading().getAzimuth());
			output += String.format("%d %d %s\n", p.getX(), p.getY(), h);
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
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
