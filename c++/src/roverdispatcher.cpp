#include <cmath>
#include <sstream>
#include <stdlib.h>

#include "roverdispatcher.h"

std::vector<std::string> &split(const std::string &s, char delim, std::vector<std::string> &elems) {
    std::stringstream ss(s);
    std::string item;
    while(std::getline(ss, item, delim)) {
        elems.push_back(item);
    }
    return elems;
}

std::vector<std::string> split(const std::string &s, char delim) {
    std::vector<std::string> elems;
    return split(s, delim, elems);
}

void RoverDispatcher::dispatch() {
    std::string rover;
    std::string instruction;
    for(int j=0; j < this->rovers.size(); j++) {
        rover = this->rovers[j];
        instruction = this->instructions[j];
        for (int i = 0; i < instruction.size(); i++) {
            char c = instruction[i];
            if (c == 'L') {
                this->turnLeft(rover);
            } else if (c == 'R') {
                this->turnRight(rover);
            } else if (c == 'M') {
                this->move(rover);
            } else {
                throw "unknown instruction " + c;
            }
        }
    }
}

std::string RoverDispatcher::mapControllerHeading(double heading) {
    std::string output;
    if (fabs(heading) == 0.0) {
        output = "E";
    } else if (fabs(heading) == M_PI/2) {
        output = "N";
    } else if (fabs(heading) == 3*M_PI/2) {
        output = "S";
    } else if (fabs(heading) == M_PI) {
        output = "W";
    } else {
        throw "unknown controller heading";
    }
    return output;
}

double RoverDispatcher::mapUserHeading(std::string heading) {
    double output;
    if (heading == "N") {
        output = M_PI/2;
    } else if (heading == "S") {
        output = 3*M_PI/2;    
    } else if (heading == "E") {
        output = 0.0;
    } else if (heading == "W") {
        output = M_PI;
    } else {
        throw "unknown user heading " + heading;
    }
    return output;
}

void RoverDispatcher::move(std::string roverId) {
    this->controller.move(roverId, 1);
}

void RoverDispatcher::parseInput(std::vector<std::string> input) {
    this->parseVertex(input[0]);
    this->controller = MarsRoverController(Point(0, 0, 0), this->vertex);
    for (int i = 1; i < input.size(); i+=2) {
		this->parseRover(input[i]);
	}
	for (int i = 2; i < input.size(); i+=2) {
        this->parseInstruction(input[i]);
	}
}

void RoverDispatcher::parseInstruction(std::string input) {
    this->instructions.push_back(input);
}

void RoverDispatcher::parseRover(std::string input) {
    std::vector<std::string> rover = split(input, ' ');
    if (rover.size() == 3) {
        Point position(atoi(rover[0].c_str()), atoi(rover[1].c_str()), 0);
        Heading heading(this->mapUserHeading(rover[2]), M_PI/2);
        this->controller.addRover(input, position, heading);
        this->rovers.push_back(input);
    } else {
        throw "Incorrectly specified Rover.";
    }
}

void RoverDispatcher::parseVertex(std::string input) {
    std::vector<std::string> v = split(input, ' ');
    if (v.size() == 2) {
        this->vertex = Point(atoi(v[0].c_str()), atoi(v[1].c_str()), 0);
    } else {
        throw "vertex must be specified int int.";
    }
}

std::string RoverDispatcher::renderView() {
    std::string output;
    for(int i=0; i < this->rovers.size(); i++) {
        Rover r = this->controller.getRover(this->rovers[i]);
        Point p = r.getPosition();
        std::string h = this->mapControllerHeading(r.getHeading().getAzimuth());
        std::ostringstream pos;
        pos << (int) p.getX() << " " << (int) p.getY() << " ";
        output += pos.str() + h + "\n";
    }
    return output;
}

void RoverDispatcher::turnLeft(std::string roverId) {
    this->controller.turn(roverId, Heading(M_PI/2, 0));
}

void RoverDispatcher::turnRight(std::string roverId) {
    this->controller.turn(roverId, Heading(-M_PI/2, 0));
}
