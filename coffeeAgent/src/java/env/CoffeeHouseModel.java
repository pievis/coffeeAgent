package env;

import it.as.coffeeAgent.impl.CoffeeMachine;
import it.as.coffeeAgent.impl.TransporterRobot;
import it.as.coffeeAgent.interfaces.ICoffeeCup;
import it.as.coffeeAgent.interfaces.ICoffeeMachine;
import it.as.coffeeAgent.interfaces.ITransporterRobot;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/**
 * Jason provides a convenient GridWorldModel class representing the model of a
 * square environment consisting of a grid of tiles. Less conveniently, the
 * Javadoc is almost useless thus you should figure out by yourself (e.g. by
 * looking at comments in examples source code) how the things work.
 */
public class CoffeeHouseModel extends GridWorldModel {

	public static final int TABLE = 16;
	public static final int RECHARGER = 30;
	public static final int MACHINE = 32;
	public static final int GSize = 5;
	// where the environment objects are
	Location lTable = new Location(0, 0);
	Location lRecharger = new Location(0, GSize - 1);
	Location lMachine = new Location(GSize - 1, GSize - 1);
	// Physical objects
	CoffeeMachine machine;
	TransporterRobot transporter;
	// other env variables
	int cupsOnTable = 0;
	boolean isCoffeeReadyOnMachine = false;
	ICoffeeCup cupOnMachine = null;

	protected CoffeeHouseModel() {
		super(GSize, GSize, 1); // 5x5 grid with 1 mobile agent
		setAgPos(0, GSize / 2, GSize / 2); // initial location of the
											// transporter
		// initial location of static objects
		add(TABLE, lTable);
		add(RECHARGER, lRecharger);
		add(MACHINE, lMachine);

		machine = new CoffeeMachine();
		transporter = new TransporterRobot();
	}

	public ICoffeeMachine getMachine() {
		return machine;
	}

	public ITransporterRobot getTransporter() {
		return transporter;
	}

	// Model defined actions for agents

	// move the agent 0
	public boolean moveTowards(Location dest) {
		Location r1 = getAgPos(0);
		// compute where to move
		if (r1.x < dest.x)
			r1.x++;
		else if (r1.x > dest.x)
			r1.x--;
		if (r1.y < dest.y)
			r1.y++;
		else if (r1.y > dest.y)
			r1.y--;
		setAgPos(0, r1);
		return true;
	}

	public boolean depositCup() {
		if (transporter.isCarryingCup()) {
			transporter.disposeCup();
			cupsOnTable++;
			log("cups on table: " + cupsOnTable);
			return true;
		}
		log(transporter.disposeCup() + " <---");
		return false;
	}
	
	public boolean takeCoffee(){
		if(!isCoffeeReadyOnMachine)
			return false;
		transporter.takeCup(cupOnMachine);
		cupOnMachine = null;
		isCoffeeReadyOnMachine = false;
		return true;
	}

	public boolean makeCoffe() {
		if (isCoffeeReadyOnMachine)
			return false; // some coffee is ready, can't make new one, we'll
							// just make a mess
		cupOnMachine = machine.makeCoffee();
		isCoffeeReadyOnMachine = true;
		return true;
	}
	
	
	public boolean recharge() {
		transporter.rechargeBattery(10.0);
		return true;
	}
	
	public boolean turnOnLedMachine(){
		machine.turnOnErrorLed();
		return true;
	}
	

	// Other
	private void log(String text) {
		System.out.println("Env] " + text);
	}
}
