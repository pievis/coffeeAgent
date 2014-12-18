package it.as.coffeeAgent.impl;

import it.as.coffeeAgent.interfaces.ITransporterRobot;

public class TransporterRobot implements ITransporterRobot {

	double battery = 100.0;
	
	public double getBatteryLevel() {
		return battery;
	}
	
	public void rechargeBattery(double level) {
		if(battery + level < 100.0)
			battery+= level;
		else
			battery = 100.0;
	}

}
