#include "rovercontroller.h"

void RoverController::addRover(std::string roverId, Point &position, Heading &heading) {
	this->checkPosition(position);
    if (this->rovers.find(roverId) == this->rovers.end()) {
        this->rovers.insert(std::pair<std::string, Rover>(roverId, Rover(position, heading)));
    } else {
        // throw exception
        std::cout << "Throw exception rover already exists" << std::endl;
    }
}

Rover RoverController::getRover(std::string roverId) {
    std::map<std::string, Rover>::const_iterator iter;
    iter = this->rovers.find(roverId);
    if (iter == this->rovers.end()) {
        std::cout << "Throw exception rover does not exist" << std::endl;
    } else {
    return iter->second;}
}

void RoverController::checkPosition(Point &position) {
	if (!this->isEmpty(position)) {
        // throw exception
        std::cout << "Rover already occupies " << position << std::endl;
	} else if (!this->inGrid(position)) {
        // throw exception
        std::cout << "Position " << position << " is out of grid " << std::endl;
	}
}

bool RoverController::inGrid(Point &position) {
	if (((this->origin.getX() <= position.getX() && position.getX() <= this->vertex.getX()) ||
        (this->vertex.getX() <= position.getX() && position.getX() <= this->origin.getX())) &&
        ((this->origin.getY() <= position.getY() && position.getY() <= this->vertex.getY()) ||
        (this->vertex.getY() <= position.getY() && position.getY() <= this->origin.getY())) &&
        ((this->origin.getZ() <= position.getZ() && position.getZ() <= this->vertex.getZ()) ||
        (this->vertex.getZ() <= position.getZ() && position.getZ() <= this->origin.getZ()))) {
		return true;
	} else {
		return false;
	}
}

bool RoverController::isEmpty(Point &position) {
    std::map<std::string, Rover>::const_iterator iter;
    for (iter=this->rovers.begin(); iter != this->rovers.end(); ++iter) {
        Rover r = iter->second;
        if (r.getPosition() == position) {
            return false;        
        }        
    }
    return true;
}
