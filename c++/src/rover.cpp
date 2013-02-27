#include "rover.h"

Rover::Rover(const Point &position, const Heading &heading){
    this->setPosition(position);
    this->setHeading(heading);
}

Point Rover::getPosition() const {
    return this->position;
}

Heading Rover::getHeading() const {
    return this->heading;
}

void Rover::setPosition(Point position) {
    this->position = position;
}

void Rover::setHeading(Heading heading) {
    this->heading = heading;
}
