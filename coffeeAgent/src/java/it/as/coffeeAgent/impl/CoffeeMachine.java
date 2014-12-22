package it.as.coffeeAgent.impl;

import it.as.coffeeAgent.interfaces.ICoffeeCup;
import it.as.coffeeAgent.interfaces.ICoffeeMachine;

public class CoffeeMachine implements ICoffeeMachine {

	private final long COFFEE_WAIT_TIME = 2500;
	private final double COFFEE_PER_CUP = 1.0;
	private final double WATER_PER_CUP = 1.5;
	//
	double waterLevel = 100.0;
	double coffeeLevel = 100.0;
	boolean errorLedOn = false;
	
	public ICoffeeCup makeCoffee() {
		ICoffeeCup cc = new CoffeeCup();
		
		if(coffeeLevel < COFFEE_PER_CUP)
			return null;
		if(waterLevel < WATER_PER_CUP)
			return null;
		
		coffeeLevel-= COFFEE_PER_CUP;
		waterLevel-= WATER_PER_CUP;
		try {
			Thread.sleep(COFFEE_WAIT_TIME); //really this shouldn't be done, but it's a simulation so...
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return cc;
	}

	public double getWaterLevel() {
		return waterLevel;
	}

	public double getCoffeeLelvel() {
		return coffeeLevel;
	}

	public void turnOnErrorLed() {
		errorLedOn = true;
	}

	public void turnOffErrorLed() {
		errorLedOn = false;
	}

	public boolean isErrorLedOn() {
		return errorLedOn;
	}

}
