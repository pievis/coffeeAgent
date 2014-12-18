package env;

import jason.environment.grid.GridWorldModel;

/**
 * Jason provides a convenient GridWorldModel class representing
 * the model of a square environment consisting of a grid of tiles.
 * Less conveniently, the Javadoc is almost useless thus you should
 * figure out by yourself (e.g. by looking at comments in examples
 * source code) how the things work.
 */
public class CoffeeModel extends GridWorldModel {

	public static final int GSize = 5;
	
	protected CoffeeModel(int arg0, int arg1, int arg2) {
		super(GSize, GSize, 1); //5x5 grid with 1 mobile agent
		setAgPos(0, GSize / 2, GSize / 2);
		// initial location of the coffee machine and the coffee table
		//...
	}
	
}
