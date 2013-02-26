#ifndef __ROVER_H_INCLUDED__
#define __ROVER_H_INCLUDED__

#include "heading.h"
#include "point.h"

class Rover
{
    public:
        Rover(const Point &position, const Heading &heading);
        Point getPosition();
        Heading getHeading();
        void setPosition(Point position);
        void setHeading(Heading heading);
    private:
        Point position;
        Heading heading;

};

#endif
