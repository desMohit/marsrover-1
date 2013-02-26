#ifndef __HEADING_H_INCLUDED__
#define __HEADING_H_INCLUDED__

#include <string>
#include <iostream>

class Heading
{
    public:
        Heading() {};
        Heading(double azimuth, double zenith);
        bool operator == (Heading);
        friend std::ostream& operator<<(std::ostream& os, Heading h) {
            os << "(" << h.getAzimuth() << ", " << h.getZenith() << ")" << std::endl;
            os.flush();
            return os;
        }
        double getAzimuth();
        double getZenith();
        void setAzimuth(double azimuth);
        void setZenith(double zenith);
        
    private:
        double azimuth, zenith;

};

#endif
