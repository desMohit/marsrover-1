package com.mars;

/**
 * Container class to house Rover heading angle parameterisation.
 * 
 * @author mat
 *
 */
public class Heading {
	
	private double azimuth;
	private double zenith;
	
	public Heading(double azimuth, double zenith) {
		this.setAzimuth(azimuth);
		this.setZenith(zenith);
	}
	
	@Override
	public String toString() {
		return String.format("(%f, %f)", this.getAzimuth(), this.getZenith());
	}

	public double getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(double azimuth) {
		this.azimuth = azimuth % (2*Math.PI);
	}

	public double getZenith() {
		return zenith;
	}

	public void setZenith(double zenith) {
		this.zenith = zenith % (2*Math.PI);
	}


}
