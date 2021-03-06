#ifndef __ROVERDISPATCHER_H_INCLUDED__
#define __ROVERDISPATCHER_INCLUDED__

#include <vector>

#include "marsrovercontroller.h"

class RoverDispatcher {
    public:
        void dispatch();
        void parseInput(std::vector<std::string> input);
        std::string renderView();
        
    private:
        std::string mapControllerHeading(double heading);
        double mapUserHeading(std::string heading);
        void move(std::string roverId);
        void parseInstruction(std::string input);
        void parseRover(std::string input);
        void parseVertex(std::string input);
        void turnLeft(std::string roverId);
        void turnRight(std::string roverId);

        MarsRoverController controller;
        std::vector<std::string> rovers;
        std::vector<std::string> instructions;
        Point vertex;
};

#endif
