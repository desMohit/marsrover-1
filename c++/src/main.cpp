#include <config.h>


#include "roverdispatcher.h"

int main (void)
{
    std::vector<std::string> input;
    input.push_back("5 5");
    input.push_back("1 2 N");
    input.push_back("LMLMLMLMM");
    input.push_back("3 3 E");
    input.push_back("MMRMMRMRRM");
    RoverDispatcher dispatcher;
    dispatcher.parseInput(input);
    dispatcher.dispatch();
    dispatcher.renderView();

    return 0;
}

/*
    puts ("Hello World!");
    puts ("This is " PACKAGE_STRING ".");

    std::cout << "This is C++" << std::endl;

    MarsRoverController controller(Point(0, 0, 0), Point(5, 5, 5));
    controller.addRover("rover1", Point(1, 2, 0), Heading(M_PI/2, M_PI/2));
	controller.turn("rover1",  Heading(M_PI/2, 0));
	controller.move("rover1", 1);
	controller.turn("rover1",  Heading(M_PI/2, 0));
	controller.move("rover1", 1);
	controller.turn("rover1",  Heading(M_PI/2, 0));
	controller.move("rover1", 1);
	controller.turn("rover1",  Heading(M_PI/2, 0));
	controller.move("rover1", 1);
	controller.move("rover1", 1);
	Rover r = controller.getRover("rover1");
    std::cout << "Rover position: " << r.getPosition() << std::endl;
    std::cout << "Rover heading: " << r.getHeading() << std::endl;

    //rover 2
	controller.addRover("rover2", Point(3, 3, 0), Heading(0, M_PI/2));
	controller.move("rover2", 1);
	controller.move("rover2", 1);
	controller.turn("rover2", Heading(-M_PI/2, 0));
	controller.move("rover2", 1);
	controller.move("rover2", 1);
	controller.turn("rover2", Heading(-M_PI/2, 0));
	controller.move("rover2", 1);
	controller.turn("rover2", Heading(-M_PI/2, 0));
	controller.turn("rover2", Heading(-M_PI/2, 0));
	controller.move("rover2", 1);
	r = controller.getRover("rover2");
    std::cout << "Rover position: " << r.getPosition() << std::endl;
    std::cout << "Rover heading: " << r.getHeading() << std::endl;


    controller.addRover("r0", Point(2, 1, 0), Heading(M_PI/2 , M_PI/2));
	controller.move("r0", 1);
	std::cout << (controller.getRover("r0").getPosition() == Point(2, 2, 0)) << std::endl;
	controller.move("r0", 3);
    std::cout << (controller.getRover("r0").getPosition() == Point(2, 5, 0)) << std::endl;
	controller.move("r0", 1);
*/
