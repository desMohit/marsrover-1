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
        void addRover(std::string roverId, const Point &position, const Heading &heading);
        Rover getRover(std::string roverId);

    protected:
        void checkPosition(const Point &position);        
        std::map<std::string, Rover> rovers;
        
    private:
        bool inGrid(const Point &position);
        bool isEmpty(const Point &position);
        Point origin;
        Point vertex;

};

#endif
