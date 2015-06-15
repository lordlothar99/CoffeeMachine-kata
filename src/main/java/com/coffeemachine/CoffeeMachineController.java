package com.coffeemachine;

public class CoffeeMachineController {

	private final DrinkMaker drinkMaker;

	public CoffeeMachineController(DrinkMaker drinkMaker) {
		this.drinkMaker = drinkMaker;
	}

	public void orderDrink(DrinkOrder drinkOrder) {
		float missingAmount = calculateMissingAmount(drinkOrder);
		if (missingAmount > 0) {
			this.displayMessage("Unsufficient funds : " + missingAmount + "€ missing");
		} else {
			String command = drinkOrder.getType().getCode();
			command += getSugar(drinkOrder);
			drinkMaker.sendCommand(command.toString());
		}
	}

	private String getSugar(DrinkOrder drinkOrder) {
		return drinkOrder.getSugarQuantity() == 0 ? ":" : drinkOrder.getSugarQuantity() + ":0";
	}

	private float calculateMissingAmount(DrinkOrder drinkOrder) {
		return drinkOrder.getType().getPrice().add(drinkOrder.getCashAmount().negate()).floatValue();
	}

	public void displayMessage(String message) {
		drinkMaker.sendCommand("M:" + message);
	}
}
