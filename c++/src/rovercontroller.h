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
        Rover getRover(std::string roverId);

    protected:
        void checkPosition(Point &position);        
        
    private:
        bool inGrid(Point &position);
        bool isEmpty(Point &position);
        Point origin;
        Point vertex;
        std::map<std::string, Rover> rovers;

};

#endif
