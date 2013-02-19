package com.mars;

import java.util.HashMap;

/**
 * This file is part of a solution to the Mars Rover Exercise
 * (http://thefundoowriter.com/2009/10/01/the-mars-rover-problem/).
 * 
 * Matthew Baker <mu.beta.06@gmail.com> 2013
 * 
 * This module defines the base class for the RoverController.
 * 
 * The RoverController is responsible for implementing the rules that govern the
 * movement of Rovers throughout a given co-ordinate system, moreover, the
 * RoverController is responsible for restricting Rover(s) movement. In addition to
 * restricting Rover movement the RoverController should ensure that Rover's do not
 * collide and, furthermore, do not stray outside the nominated co-ordinate system.
 * If co-ordinate translation is required a affine matrix can be layered onto
 * either side of this Controller.
 * 
 * @author mat
 *
 */

public abstract class RoverController {
	
	private HashMap<String, Rover> rovers = new HashMap<String, Rover>();
	private Point origin;
	private Point vertex;
	
	public RoverController(Point origin, Point vertex) {
		this.setOrigin(origin);
		this.setVertex(vertex);
	}
	
	public abstract void move(String roverId, int distance);
	public abstract void turn(String roverId, Heading heading);

	/**
	 * Add a Rover to the RoverController. When a Rover is added the 
	 * RoverController needs to check that the initial position is not 
	 * occupied and rover id is unique.
	 */
	public void addRover(String roverId, Point position, Heading heading) {
		
	}
	
	/**
	 * Checks the specified position raising the appropriate exception if
	 * position is illegal.
	 * 
	 * @param position
	 * @throws Exception 
	 */
	protected void checkPosition(Point position) throws Exception {
		// need to check that points of position is integers
		if (!this.isEmpty(position)) {
			throw new Exception(String.format("Rover already occupies %s", position.toString()));
		} else if (!this.inGrid(position)) {
			throw new Exception(String.format("Position %s is out of grid", position.toString()));
		}
	}

	/**
	 * Checks if the specified position is within the legal constraints of 
	 * the imposed grid. Returns True if position is within grid False 
	 * otherwise. 
	 * 
	 * @param postion
	 * @return
	 */
	private boolean inGrid(Point position) {
		if ((this.getOrigin().getX() <= position.getX() && position.getX() <= this.getVertex().getX()) ||
				(this.getVertex().getX() <= position.getX() && position.getX() <= this.getOrigin().getX()) &&
				(this.getOrigin().getY() <= position.getY() && position.getY() <= this.getVertex().getY()) ||
				(this.getVertex().getY() <= position.getY() && position.getY() <= this.getOrigin().getY()) &&
				(this.getOrigin().getZ() <= position.getZ() && position.getZ() <= this.getVertex().getZ()) ||
				(this.getVertex().getZ() <= position.getZ() && position.getZ() <= this.getOrigin().getZ())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the specified position is empty or not. Returns true if
	 * empty otherwise false.
	 * 
	 * @param position
	 * @return
	 */
	private boolean isEmpty(Point position) {
		for (Rover r: this.getRovers().values()) {
			if (r.getPosition().equals(position)) {
				return false;
			}
		}
		return true;
	}
	
	// getters / setters
	public HashMap<String, Rover> getRovers() {
		return rovers;
	}

	public void setRovers(HashMap<String, Rover> rovers) {
		this.rovers = rovers;
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public Point getVertex() {
		return vertex;
	}

	public void setVertex(Point vertex) {
		this.vertex = vertex;
	}

}
