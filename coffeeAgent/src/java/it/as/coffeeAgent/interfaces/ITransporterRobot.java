package it.as.coffeeAgent.interfaces;
/**
 * Structure of our coffee transporter, represent the structure of 
 * the physical part of our agent. The mind will be the jason asl file.
 * @author Pierluigi
 *
 */
public interface ITransporterRobot {

	public double getBatteryLevel();
	public void rechargeBattery(double level);
}
