package com.mars;

public class MarsRoverControllerTest extends junit.framework.TestCase {

	public void testMove() throws Exception {
		MarsRoverController mrc = new MarsRoverController(new Point(0, 0, 0), new Point(5, 5, 5));
		mrc.addRover("r0", new Point(2, 1, 0), new Heading(Math.PI/2 , Math.PI/2));
		mrc.move("r0", 1);
		assertEquals(mrc.getRover("r0").getPosition(), new Point(2, 2, 0));
		mrc.move("r0", 3);
		assertEquals(mrc.getRover("r0").getPosition(), new Point(2, 5, 0));
		try {
			mrc.move("r0", 1);
			fail("No exception caught.");
		} catch (Exception e) {}
	}
	
	public void testMoveBackwards() throws Exception {
		MarsRoverController mrc = new MarsRoverController(new Point(0, 0, 0), new Point(5, 5, 5));
		mrc.addRover("r0", new Point(2, 1, 0), new Heading(Math.PI/2 , Math.PI/2));
		try {
			mrc.move("r0", -1);
			fail("No exception caught.");
		} catch (Exception e) {}
	}
	
	public void testTurn() throws Exception {
		MarsRoverController mrc = new MarsRoverController(new Point(0, 0, 0), new Point(5, 5, 5));
		mrc.addRover("r0", new Point(2, 1, 0), new Heading(Math.PI/2 , Math.PI/2));
		// turn left
		mrc.turn("r0", new Heading(Math.PI/2, 0));
		assertEquals(mrc.getRover("r0").getHeading(), new Heading(Math.PI, Math.PI/2));
		// turn right
		mrc.turn("r0", new Heading(-Math.PI/2, 0));
		assertEquals(mrc.getRover("r0").getHeading(), new Heading(Math.PI/2, Math.PI/2));
		// no non orthogonal turning w.r.t. 0 rads
		try {
			mrc.turn("r0", new Heading(-1.1*Math.PI/2, 0));
			fail("No exception caught.");
		} catch (Exception e) {}
	}
}
