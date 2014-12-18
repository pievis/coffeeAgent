package it.as.coffeeAgent.interfaces;

/**
 * Structure of our coffee machine, represent the structure of 
 * the physical part of our agent, the mind will be the jason asl file.
 * @author Pierluigi
 *
 */
public interface ICoffeeMachine {

	public ICoffeeCup makeCoffee();
	public double getWaterLevel();
	public double getCoffeeLelvel();
	public void turnOnErrorLed();
	public void turnOffErrorLed();
	public boolean isErrorLedOn();
	
}
