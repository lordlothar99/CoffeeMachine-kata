package com.coffeemachine;

public class CoffeeMachineController {

	private final CoffeeMachine coffeeMachine;

	public CoffeeMachineController(CoffeeMachine drinkMachine) {
		this.coffeeMachine = drinkMachine;
	}

	public void displayMessage(String message) {
		coffeeMachine.sendCommand("M:" + message);
	}

	public void orderDrink(DrinkOrder drinkOrder) {
		float missingAmount = drinkOrder.getType().getPrice() - drinkOrder.getCashAmount();
		if (missingAmount > 0) {
			this.displayMessage("Unsufficient funds : " + missingAmount + "€ missing");
		} else {
			StringBuilder command = new StringBuilder();
			command.append(drinkOrder.getType().getCode());
			command.append(drinkOrder.getSugar().getCode());
			coffeeMachine.sendCommand(command.toString());
		}
	}
}
