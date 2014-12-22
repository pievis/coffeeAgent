// Agent transporter_agent in project coffeeAgent

//Perceptions:
//at(trans,X) where X = machine, table, recharger
//battery(X)
//cup_ready(machine) -> a cup is ready at the machine

/* Initial beliefs and rules */
canTransportWithBattery(BL) :-  BL > 25.0.

/* Initial goals */

!start.

/* Plans */

+!start : true <- .wait(3000); //wait a moment
					.print("I'm ready to start"); 
					!doSomething.

+!doSomething : battery(BL) & canTransportWithBattery(BL) <- !transportCoffee.

+!doSomething : true <- !goRecharging.

+!goRecharging : true <- .print("moving to the recharger"); 
						!at(trans,recharger).

+!transportCoffee : carrying_cup <- .print("moving to the table"); 
									!at(trans,table).

+!transportCoffee : not carrying_cup <- .print("moving to the machine"); 
										!at(trans,machine).														
																	
+!tryTaking : cup_ready(machine) <- take(cup); +carrying_cup; 
												.print("took the cup of coffee");
												!transportCoffee.
												

/* NOTICE: "at", "cup_ready(machine)" beliefs are "perceptions",
 * thus they are automagically added by Jason runtime,
 * provided that YOU have correctly implemented method "updatePercepts()"
 */

+!at(trans,P) // if NOT arrived at destination (P = "table" | "machine" ...)
	: not at(trans,P)
	<- .print("moving..."); move_towards(P); !at(trans,P). // ...continue attempting to reach destination
	
	+!at(trans,P) // if arrived at destination...
	: at(trans,P)
	<- true. // ...that's all, do nothing, the "original" intention (the "context") can continue

+at(trans,machine) : not carrying_cup <- !tryTaking.
+at(trans,table) : carrying_cup <- dispose(cup); 
									-carrying_cup; 
									.print("put the cup on the table");
									!doSomething.
									
+at(trans,recharger) : not carrying_cup <- .print("I'm at the recharger"); 
											!checkCharge.

+!checkCharge : battery(BL) & BL < 90 <- recharge; 
											?battery(X); 
											.print("battery level: ", X);
											.wait(500); 
											!checkCharge.
											
+!checkCharge : battery(BL) & BL >= 90 <- !doSomething.

-!tryTaking : not cup_ready(machine) <- ?battery(X);
										 .print("Waiting for a cup of coffee at the machine. battery: ", X);
										.wait(5000); !tryTaking.									