#ifndef __MARSROVERCONTROLLER_H_INCLUDED__
#define __MARSROVERCONTROLLER_H_INCLUDED__

#include "rovercontroller.h"

class MarsRoverController: public RoverController {
    public:
        MarsRoverController(const Point &origin, const Point &vertex)
        : RoverController(origin, vertex) {}
        void move(std::string roverId, int distance);
        void turn(std::string roverId, Heading heading);
};

#endif
