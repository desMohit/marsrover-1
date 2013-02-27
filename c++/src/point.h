#ifndef __POINT_H_INCLUDED__
#define __POINT_H_INCLUDED__

#include <string>
#include <iostream>

class Point
{
    public:
        Point() {};
        Point(double x, double y, double z);
        bool operator == (Point);
        friend std::ostream& operator<<(std::ostream& os, Point p) {
            os << "(" << p.getX() << ", " << p.getY() << ", " << p.getZ() << ")";
            os.flush();
            return os;
        }
        double getX() const;
        double getY() const;
        double getZ() const;
        void setX(double x);
        void setY(double y);
        void setZ(double z);
        
    private:
        double x, y, z;

};

#endif
