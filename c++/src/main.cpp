#include <cmath>
#include <config.h>
#include <iostream>
#include <stdio.h>

#include "heading.h"
#include "point.h"
#include "rover.h"
#include "marsrovercontroller.h"

int main (void)
{
    puts ("Hello World!");
    puts ("This is " PACKAGE_STRING ".");

    std::cout << "This is C++" << std::endl;

    MarsRoverController controller(Point(0, 0, 0), Point(5, 5, 5));
    Point p(1, 2, 0);
    Heading h(M_PI/2, M_PI/2);
    controller.addRover("rover1", p, h);
	//controller.turn("rover1",  Heading(M_PI/2, 0));
	controller.move("rover1", 1);
	//controller.turn("rover1",  Heading(M_PI/2, 0));
	//controller.move("rover1", 1);
	//controller.turn("rover1",  Heading(M_PI/2, 0));
	//controller.move("rover1", 1);
	//controller.turn("rover1",  Heading(M_PI/2, 0));
	//controller.move("rover1", 1);
	//controller.move("rover1", 1);
	Rover r = controller.getRover("rover1");
    std::cout << "Rover position: " << r.getPosition() << std::endl;
    std::cout << "Rover heading: " << r.getHeading() << std::endl;

    return 0;
}
