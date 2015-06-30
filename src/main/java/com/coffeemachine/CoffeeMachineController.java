package com.coffeemachine;

import com.coffeemachine.check.BeverageQuantityChecker;
import com.coffeemachine.email.EmailNotifier;
import com.coffeemachine.store.DrinksSellingsDao;

public class CoffeeMachineController {

	private final DrinkMaker drinkMaker;
	private final DrinksSellingsDao drinksSellingsDao;
	private final EmailNotifier emailNotifier;
	private final BeverageQuantityChecker beverageQuantityChecker;

	public CoffeeMachineController(DrinkMaker drinkMaker, DrinksSellingsDao drinksSellingsDao,
			EmailNotifier emailNotifier, BeverageQuantityChecker beverageQuantityChecker) {
		this.drinkMaker = drinkMaker;
		this.drinksSellingsDao = drinksSellingsDao;
		this.emailNotifier = emailNotifier;
		this.beverageQuantityChecker = beverageQuantityChecker;
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
