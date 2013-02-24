package com.mars;

public class RoverTest extends junit.framework.TestCase {

	Point position = new Point(2, 2, 0);
	Heading heading = new Heading(Math.PI/2, Math.PI/2);
	Rover r = new Rover(position, heading);
	
    public void testCreation() {
    	assertEquals(position, r.getPosition());
    	assertEquals(heading, r.getHeading());
    }

}
