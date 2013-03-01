#include <cmath>

#include "marsrovercontroller.h"

double round(double r) {
    return (r > 0.0) ? floor(r + 0.5) : ceil(r - 0.5);
}

void MarsRoverController::move(std::string roverId, int distance) {
    if (distance > 0) {
        double x, y, z;        
        Rover r = this->getRover(roverId);
        x = r.getPosition().getX();
        y = r.getPosition().getY();
        z = r.getPosition().getZ();
        x += sin(r.getHeading().getZenith())*cos(r.getHeading().getAzimuth());
        y += sin(r.getHeading().getZenith())*sin(r.getHeading().getAzimuth());
        z += cos(r.getHeading().getZenith());
        Point position(round(x), round(y), round(z));
        this->checkPosition(position);
        r.setPosition(position);
        this->rovers[roverId] = r;
        this->move(roverId, distance - 1);
	} else if (distance < 0) {
        throw "Rover can only move in the forward direction";
	}
}

void MarsRoverController::turn(std::string roverId, Heading heading) {
    if (fmod(heading.getAzimuth(), M_PI/2) == 0.0 && fmod(heading.getZenith(), M_PI/2) == 0.0) {
        double az, z;
        Rover r = this->getRover(roverId);
        az = r.getHeading().getAzimuth();
        z = r.getHeading().getZenith();
        az += heading.getAzimuth();
        z += heading.getZenith();
        r.setHeading(Heading(az, z));
        this->rovers[roverId] = r;
    } else {
        throw "Rover can only turn orthognally";
    }
}
