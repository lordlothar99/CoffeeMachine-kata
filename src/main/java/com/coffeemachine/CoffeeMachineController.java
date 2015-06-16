package com.coffeemachine;

import com.coffeemachine.store.DrinksSellingsDao;

public class CoffeeMachineController {

	private final DrinkMaker drinkMaker;
	private final DrinksSellingsDao drinksSellingsDao;

	public CoffeeMachineController(DrinkMaker drinkMaker, DrinksSellingsDao drinksSellingsDao) {
		this.drinkMaker = drinkMaker;
		this.drinksSellingsDao = drinksSellingsDao;
	}

	public void orderDrink(DrinkOrder drinkOrder) {
		float missingAmount = calculateMissingAmount(drinkOrder);
		if (missingAmount > 0) {
			this.displayMessage("Unsufficient funds : " + missingAmount + " euros missing");
		} else {
			String command = drinkOrder.getType().getCode();
			command += getSugar(drinkOrder);
			drinkMaker.sendCommand(command.toString());
			drinksSellingsDao.addDrinkSelling(drinkOrder.getType());
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
