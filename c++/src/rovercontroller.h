#ifndef __ROVERCONTROLLER_H_INCLUDED__
#define __ROVERCONTROLLER_H_INCLUDED__

#include <map>

#include "rover.h"

class RoverController
{
    public:
        RoverController(const Point &origin, const Point &vertex)
            : origin(origin), vertex(vertex) {}
        virtual void move(std::string roverId, int distance) =0;
        virtual void turn(std::string roverId, Heading heading) =0;
        void addRover(std::string roverId, Point &position, Heading &heading);
        std::map<std::string, Rover>::const_iterator getRover(std::string roverId);
        
    private:
        /* member variables */
        Point origin;
        Point vertex;
        std::map<std::string, Rover> rovers;

        /* member method */
        void checkPosition(Point &position);
        bool inGrid(Point &position);
        bool isEmpty(Point &position);

};

#endif
