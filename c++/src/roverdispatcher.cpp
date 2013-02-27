#include <cmath>

#include "roverdispatcher.h"

std::string RoverDispatcher::mapControllerHeading(double heading) {
    std::string output;
    if (std::abs(heading) == 0.0)
        output = "E";
    else if (std::abs(heading) == M_PI/2)
        output = "N";
    else if (std::abs(heading) == 3*M_PI/2)
        output = "W";
    else if (std::abs(heading) == M_PI)
        output = "S";
    return output;
}

std::string RoverDispatcher::renderView() {
    std::string output("");
    for(int i=0; i < this->rovers.size(); i++) {
        Rover r = this->controller.getRover(this->rovers[i]);
        Point p = r.getPosition();
        std::string h = this->mapControllerHeading(r.getHeading().getAzimuth());
    }
    return output;
}

void RoverDispatcher::turnLeft(std::string roverId) {
    this->controller.turn(roverId, Heading(M_PI/2, 0));
}

void RoverDispatcher::turnRight(std::string roverId) {
    this->controller.turn(roverId, Heading(-M_PI/2, 0));
}
