package com.coffeemachine;

public class CoffeeMachineController {

	private final DrinkMaker drinkMaker;

	public CoffeeMachineController(DrinkMaker drinkMaker) {
		this.drinkMaker = drinkMaker;
	}

	public void displayMessage(String message) {
		drinkMaker.makeDrink("M:" + message);
	}

	public void orderDrink(DrinkOrder drinkOrder) {
		float missingAmount = getMissingAmount(drinkOrder);
		if (missingAmount > 0) {
			this.displayMessage("Unsufficient funds : " + missingAmount + "€ missing");
		} else {
			StringBuilder command = new StringBuilder();
			command.append(drinkOrder.getType().getCode());
			command.append(drinkOrder.getSugar().getCode());
			drinkMaker.makeDrink(command.toString());
		}
	}

	private float getMissingAmount(DrinkOrder drinkOrder) {
		return drinkOrder.getType().getPrice() - drinkOrder.getCashAmount();
	}
}
