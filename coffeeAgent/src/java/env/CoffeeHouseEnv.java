package env;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.environment.grid.Location;

public class CoffeeHouseEnv extends Environment {
	// this is the environment of our autonomous system
	public static CoffeeHouseModel model;
	//Literals we are going to use, as belief or for actions
	public static final Literal at_table = Literal.parseLiteral("at(trans,table)");
	public static final Literal at_machine = Literal.parseLiteral("at(trans,machine)");
	public static final Literal at_recharger = Literal.parseLiteral("at(trans,recharger)");
	public static final Literal ready_cup = Literal.parseLiteral("cup_ready(machine)");
	public static final String water_levelStr = "water(X)";
	public static final String coffee_levelStr = "coffee(X)";
	public static final String battery_levelStr = "battery(X)";
	//actions
	public static final Literal take_cupLit = Literal.parseLiteral("take(cup)");
	public static final Literal deposit_cupLit = Literal.parseLiteral("dispose(cup)");
	public static final Literal make_coffeeLit = Literal.parseLiteral("make(coffee)");
	public static final Literal turnon_ledLit = Literal.parseLiteral("turnOnLed");
	public static final Literal rechargeLit = Literal.parseLiteral("recharge");
	public static final String move_towardsStr = "move_towards";
	
	@Override
	public void init(String[] args) {
		model = new CoffeeHouseModel();
		clearAllPercepts();
	}

	void updatePercepts() {
		// Here we update all the perceptions for our agents
		clearPercepts("machine");
		clearPercepts("trans");
		clearAllPercepts();

		//transporter location
		Location tl = model.getAgPos(0);
		if(tl.equals(model.lMachine))
			addPercept(at_machine);
		if(tl.equals(model.lRecharger))
			addPercept(at_recharger);
		if(tl.equals(model.lTable))
			addPercept(at_table);
		
		//Coffee and water level for the machine
		//and battery for transporter
		double cl = model.machine.getCoffeeLelvel();
		double wl = model.machine.getWaterLevel();
		double bl = model.transporter.getBatteryLevel();
		addPercept("machine", Literal.parseLiteral(coffee_levelStr.replace("X", cl+"")));
		addPercept("machine", Literal.parseLiteral(water_levelStr.replace("X", wl+"")));
		addPercept("trans", Literal.parseLiteral(battery_levelStr.replace("X", bl+"")));
		//cup on the machine
		if(model.isCoffeeReadyOnMachine)
			addPercept(ready_cup);
		
	}

	@Override
	public boolean executeAction(String agName, Structure act) {
		boolean result = false;

		//let's map the external action from jason agent to java
		//result = false -> the action failed
		
		if(act.equals(make_coffeeLit)){
			result = model.makeCoffe();
		}
		else if(act.equals(deposit_cupLit)){
			result = model.depositCup();
		}
		else if(act.equals(take_cupLit)){
			result = model.takeCoffee();
		}
		else if(act.equals(turnon_ledLit)){
			result = model.turnOnLedMachine();
		}
		else if(act.equals(rechargeLit)){
			result = model.recharge();
		}
		else if(act.getFunctor().equals(move_towardsStr)){
			String l = act.getTerm(0).toString(); // get where to move
			Location dest = null;
			if (l.equals("recharger")) {
				dest = model.lRecharger;
			} else if (l.equals("table")) {
				dest = model.lTable;
			}else if (l.equals("machine")) {
				dest = model.lMachine;
			}
			result = model.moveTowards(dest);
		}
		
		// only if action completed successfully, update agents' percepts
		if (result) {
			updatePercepts();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
		return result;
	}
}

