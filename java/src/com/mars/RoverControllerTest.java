package com.mars;

public class RoverControllerTest extends junit.framework.TestCase {
	
	public void testAddRover() throws Exception {
		TestRoverController trc = new TestRoverController(new Point(0, 0, 0), new Point(5, 5, 5));
		trc.addRover("r0", new Point(1, 2, 3), new Heading(0 ,0));
		
		// tests sucessful add
		assertNotNull(trc.getRover("r0"));
		
		// add Rover with same id ... pretty hacky way of testing in hind sight custom exceptions probably way to go
		try {
			trc.addRover("r0", new Point(2, 5, 3), new Heading(0 ,0));
			fail("No exception caught.");
		} catch (Exception e) {
			assertEquals(e.getMessage(), String.format("A Rover with id %s already exists", "r0"));
		}
		
		// add Rover in preoccupied position
		Point position = new Point(1, 2, 3);
		try {
			trc.addRover("r1", position, new Heading(0 ,0));
			fail("No exception caught.");
		} catch (Exception e) {
			assertEquals(e.getMessage(), String.format("Rover already occupies %s", position.toString()));
		}
		
		//add Rover out of grid
		Point[] points = {new Point(-1, 2, 3), new Point(6, 5, 5), new Point(5, -1, 5), new Point(5, 6, 5),
							new Point(5, 1, 10), new Point(5, 1, -10)};
		for (Point point : points) {
			try {
				trc.addRover("r1", point, new Heading(0, 0));
				fail("No exception caught.");
			} catch (Exception e) {
				assertNotNull(e);
			}
		}

	}
	
	public void testGetRover() throws Exception {
		TestRoverController trc = new TestRoverController(new Point(0, 0, 0), new Point(5, 5, 5));
		trc.addRover("r0", new Point(1, 2, 3), new Heading(0 ,0));
		assertNotNull(trc.getRover("r0"));
		assertNull(trc.getRover("r1"));
	}
	
	/** 
	 * Concrete test implementation.
	 * 
	 * @author mat
	 *
	 */
	public class TestRoverController extends RoverController {

		public TestRoverController(Point origin, Point vertex) {
			super(origin, vertex);
		}

		@Override
		public void move(String roverId, int distance) throws Exception {}

		@Override
		public void turn(String roverId, Heading heading) throws Exception {}
	}

}
