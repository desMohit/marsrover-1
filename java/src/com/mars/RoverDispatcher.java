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
	private ArrayList<Rover> rovers;
	private ArrayList<String> instructions;

	
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
		// TODO Auto-generated method stub
		System.out.println("Oi this is compiling");

	}

}
