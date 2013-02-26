#include <cmath>
#include <config.h>
#include <iostream>
#include <stdio.h>

#include "heading.h"
#include "point.h"
#include "rover.h"

int main (void)
{
    puts ("Hello World!");
    puts ("This is " PACKAGE_STRING ".");

    std::cout << "This is C++" << std::endl;

    Point position(1, 2, 3);
    Heading heading(M_PI/2, M_PI/2);

    Rover r(position, heading);
    std::cout << "Rover position: " << r.getPosition() << std::endl;
    std::cout << "Rover heading: " << r.getHeading() << std::endl;

    return 0;
}
