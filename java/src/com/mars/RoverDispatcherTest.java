package com.mars;

import java.util.ArrayList;

public class RoverDispatcherTest extends junit.framework.TestCase {
	
	public void testDispatch() {
		
	}
	
	public void testEndtoEnd() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("5 5");
		input.add("1 2 N");
		input.add("LMLMLMLMM");
		input.add("3 3 E");
		input.add("MMRMMRMRRM");
		String output = "1 3 N\n5 1 E\n";
		
		RoverDispatcher dispatcher = new RoverDispatcher();
		try {
			dispatcher.parseInput(input);
			dispatcher.dispatch();
			assertEquals(dispatcher.renderView(), output);
		} catch (Exception e) {
			fail("something went wrong.");
		}
	}

}
