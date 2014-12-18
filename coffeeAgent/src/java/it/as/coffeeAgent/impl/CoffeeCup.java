package it.as.coffeeAgent.impl;

import it.as.coffeeAgent.interfaces.ICoffeeCup;

public class CoffeeCup implements ICoffeeCup {

	boolean isFull;
	
	public CoffeeCup() {
		isFull = true;
	}
	
	public void dispose() {
		isFull = false;
	}

}
