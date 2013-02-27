#include <cmath>
#include "heading.h"

Heading::Heading(double azimuth, double zenith){
    this->setAzimuth(azimuth);
    this->setZenith(zenith);
}

bool Heading::operator== (Heading heading)  {
    if (this->getAzimuth() == heading.getAzimuth() &&
        this->getZenith() == heading.getZenith()) {
        return true;
    } else {
        return false;    
    }
}

double Heading::getAzimuth() const {
    return this->azimuth;
}

double Heading::getZenith() const {
    return this->zenith;
}

void Heading::setAzimuth(double azimuth) {
    this->azimuth = fmod(azimuth, (2*M_PI));
}

void Heading::setZenith(double zenith) {
    this->zenith = fmod(zenith, (2*M_PI));
}
