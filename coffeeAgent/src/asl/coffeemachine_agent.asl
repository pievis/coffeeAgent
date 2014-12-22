// Agent coffeemachine_agent in project coffeeAgent

//Perceptions:
//water(X) where X is the level
//coffee(X) where X is the level


/* Initial beliefs and rules */
work. //I believe I've to work and make coffee
canMakeCup(CL, WL) :- CL > 10.0 & WL > 15.0. 

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("Starting my initial goal");
					!makeCoffee.

					

+!makeCoffee : work <- 	.print("Making some coffee!");
						make(coffee);
						!repeat.

-!makeCoffee: water(WL) & coffee(CL) & canMakeCup(CL,WL) <- .print("Can't make coffee now, I'll wait");
															.wait(3000); !makeCoffee. 

+work : water(WL) & coffee(CL) & canMakeCup(CL,WL) <- !start. // In case someone take care of the machine 

+!repeat : true <- .wait(1000); //cooldown a bit
					!makeCoffee. 

//since it's defined after, this recovery plan will be executed when
//the machine is over the limit of water or coffee 															
-!makeCoffee: true <- .print("I'm out of coffee or water"); 
						turnOnLed; 
						-work.
				
//after turning off the led I tell myself I cannot work in this conditions


					
