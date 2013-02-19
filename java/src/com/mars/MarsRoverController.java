/**
 * This file is part of a solution to the Mars Rover Exercise
 * (http://thefundoowriter.com/2009/10/01/the-mars-rover-problem/).
 * 
 * Matthew Baker <mu.beta.06@gmail.com> 2013
 * 
 * This module defines an implementation of base class RoverController.
 * 
 * The MarsRoverController implementation of a RoverController sees the restriction
 * of Rover's movements to orthogonal adjacent unit steps in a rectangular grid in
 * a forward only direction.
 */

package com.mars;

public class MarsRoverController extends RoverController {

	public MarsRoverController(Point origin, Point vertex) {
		super(origin, vertex);
	}

	/**
	 * The movement of a Rover is parametric and differential, i.e. the
	 * Rover will be moved throughout the co-ordinate space the specified
	 * distance from it's current position at it's current heading. In this
	 * implementation a Rover can only move in the forward direction
	 * dimensionless integer number of unit lengths.
	 * 
	 * In addition to enforcing these restrictions the RoverController is
	 * responsible for appropriately updating the Rovers co-ordinates, ensuring
	 * that movement of the Rover is not going to see it moving outside of the
	 * grid limits and not into another Rover.
	 * @throws Exception 
	 */
	@Override
	public void move(String roverId, int distance) throws Exception {
		if (distance > 0) {
			Rover r = this.getRover(roverId);
			double x, y, z;
			x = r.getPosition().getX();
			y = r.getPosition().getY();
			z = r.getPosition().getZ();
			x += Math.sin(r.getHeading().getZenith())*Math.cos(r.getHeading().getAzimuth());
            y += Math.sin(r.getHeading().getZenith())*Math.sin(r.getHeading().getAzimuth());
            z += Math.cos(r.getHeading().getZenith());
            Point position = new Point(Math.round(x), Math.round(y), Math.round(z));
            this.checkPosition(position);
            r.setPosition(position);
            this.move(roverId, distance - 1);
		} else if (distance < 0) {
			throw new Exception("Rover can only move in the forward direction");
		}
	}

	/**
	 * The turning of a Rover is also differential, i.e. the Rover will be
	 * turned from the current heading by the amount specified by Azimuth and
	 * Zenith angles. In this implementation a Rover can only turn orthogonally
	 * thus, distance all nonzero Azimuth and Zenith angles must be divisible
	 * by pi/2.
	 * @throws Exception 
	 */
	@Override
	public void turn(String roverId, Heading heading) throws Exception {
		if (heading.getAzimuth() % (Math.PI/2) == 0.0 && heading.getZenith() % (Math.PI/2) == 0.0) {
            Rover r = this.getRover(roverId);
            double az, z;
            az = r.getHeading().getAzimuth();
            z = r.getHeading().getZenith();
            az += heading.getAzimuth();
            z += heading.getZenith();
            r.setHeading(new Heading(az, z));
		} else {
            throw new Exception("Rover can only turn orthognally");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MarsRoverController controller = new MarsRoverController(new Point(0, 0, 0), new Point(5, 5, 0));

	    try {
            //rover 1
            controller.addRover("rover1", new Point(1, 2, 0), new Heading(Math.PI/2, Math.PI/2));
            controller.turn("rover1",  new Heading(Math.PI/2, 0));
            controller.move("rover1", 1);
            controller.turn("rover1",  new Heading(Math.PI/2, 0));
            controller.move("rover1", 1);
            controller.turn("rover1",  new Heading(Math.PI/2, 0));
            controller.move("rover1", 1);
            controller.turn("rover1",  new Heading(Math.PI/2, 0));
            controller.move("rover1", 1);
            controller.move("rover1", 1);
            Rover r = controller.getRover("rover1");
            System.out.printf("Rover position: %s\n", r.getPosition());
            System.out.printf("Rover heading: %s\n", r.getHeading());

            //rover 2
            controller.addRover("rover2", new Point(3, 3, 0), new Heading(0, Math.PI/2));
            controller.move("rover2", 1);
            controller.move("rover2", 1);
            controller.turn("rover2", new Heading(-Math.PI/2, 0));
            controller.move("rover2", 1);
            controller.move("rover2", 1);
            controller.turn("rover2", new Heading(-Math.PI/2, 0));
            controller.move("rover2", 1);
            controller.turn("rover2", new Heading(-Math.PI/2, 0));
            controller.turn("rover2", new Heading(-Math.PI/2, 0));
            controller.move("rover2", 1);
            r = controller.getRover("rover2");
            System.out.printf("Rover position: %s\n", r.getPosition());
            System.out.printf("Rover heading: %s\n", r.getHeading());
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
