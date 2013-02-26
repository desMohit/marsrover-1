#include "point.h"

Point::Point(double x, double y, double z){
    this->setX(x);
    this->setY(y);
    this->setZ(z);
}

bool Point::operator== (Point point)  {
    if (this->getX() == point.getX() &&
        this->getY() == point.getY() &&
        this->getZ() == point.getZ()) {
        return true;
    } else {
        return false;    
    }
}

double Point::getX() {
    return this->x;
}

double Point::getY() {
    return this->y;
}

double Point::getZ() {
    return this->z;
}

void Point::setX(double x) {
    this->x = x;
}

void Point::setY(double y) {
    this->y = y;
}

void Point::setZ(double z) {
    this->z = z;
}
