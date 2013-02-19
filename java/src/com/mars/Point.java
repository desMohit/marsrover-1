package com.mars;

/**
 * Container class to house Cartesian co-ordinate
 * 
 * @author mat
 *
 */
public class Point {

	private double x;
	private double y;
	private double z;
	
	public Point(double x, double y, double z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == this.getClass()) {
			Point p = (Point) obj;
			if (p.getX() == this.getX() && p.getY() == this.getY() && p.getZ() == this.getZ())
				return true;
			else
				return false;
		} else {
			return super.equals(obj);
		}
	}
	
	@Override
	public String toString() {
		return String.format("(%f, %f, %f)", this.getX(), this.getY(), this.getZ());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

}
