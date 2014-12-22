package it.as.coffeeAgent.impl;

import it.as.coffeeAgent.interfaces.ICoffeeCup;
import it.as.coffeeAgent.interfaces.ITransporterRobot;

public class TransporterRobot implements ITransporterRobot {

	double battery = 100.0;
	ICoffeeCup carryingCup;

	public double getBatteryLevel() {
		battery -= 0.5; // I know it's stupid but this method is called every
						// cycle so...
		return battery;
	}

	public void rechargeBattery(double level) {
		if (battery + level < 100.0)
			battery += level;
		else
			battery = 100.0;
	}

	public ICoffeeCup disposeCup() {
		ICoffeeCup cup = carryingCup;
		carryingCup = null;
		return cup;
	}

	public void takeCup(ICoffeeCup cup) {
		carryingCup = cup;
	}

	public boolean isCarryingCup() {
		return carryingCup != null;
	}

}
