/**
 * This file is part of a solution to the Mars Rover Exercise
 * (http://thefundoowriter.com/2009/10/01/the-mars-rover-problem/).
 *
 * Matthew Baker <mu.beta.06@gmail.com> 2013
 *
 * This module defines the base class for the Rover Model.
 */

package com.mars;

/**
 * Base class for representing Rover Model. This implementation of the Rover
 * utilises a three dimensioanl Cartisan co-ordinate system in right-hand or
 * positive orientation (as per specified here
 * http://en.wikipedia.org/wiki/Cartesian_coordinate_system). Accordingly, the
 * Rover's position is indicated using a tuple of 3 (x, y, z) and the Rover's
 * heading is represented by a tuple of two angles (Azimuth, Zenith). The
 * Azimuth angle is defined as the angle subtended in a counter-clockwise
 * direction from the x axis. The Zenith angle or Polar angle is the angle
 * between the zenith direction (z axis) and the line segment formed by the
 * Rover's position and the origin (as per specified here
 * http://en.wikipedia.org/wiki/Spherical_coordinate_system). Both angles are
 * defined in modulo 2pi radians.
 * 
 * @author mat
 *
 */
public class Rover {

	private Point position;
	private Heading heading;

	public Rover(Point position, Heading heading) {
		this.setPosition(position);
		this.setHeading(heading);
	}
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Heading getHeading() {
		return heading;
	}

	public void setHeading(Heading heading) {
		this.heading = heading;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Rover r = new Rover(new Point(2.0, 2.0, 0.0), new Heading(Math.PI/2, Math.PI/2));
	    System.out.printf("Rover position: %s\n", r.getPosition());
	    System.out.printf("Rover heading: %s\n", r.getHeading());

	}

}
