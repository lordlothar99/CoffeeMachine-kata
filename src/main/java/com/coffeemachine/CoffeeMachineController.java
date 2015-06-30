package com.coffeemachine;

import com.coffeemachine.check.BeverageQuantityChecker;
import com.coffeemachine.email.EmailNotifier;
import com.coffeemachine.store.DrinksSellingsDao;

public class CoffeeMachineController {

	private static final String PROTOCOL_SEPARATOR = ":";
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
		if (isShortage(drinkOrder)) {
			handleShortage(drinkOrder);
			return;
		}

		if (isMissingMoney(drinkOrder)) {
			handleMissingMoney(drinkOrder);
			return;
		}

		makeDrink(drinkOrder);

		recordSellin(drinkOrder);
	}

	public void displayMessage(String message) {
		drinkMaker.sendCommand("M:" + message);
	}

	private float calculateMissingAmount(DrinkOrder drinkOrder) {
		return drinkOrder.getType().getPrice().add(drinkOrder.getMoney().negate()).floatValue();
	}

	private void handleMissingMoney(DrinkOrder drinkOrder) {
		float missingAmount = calculateMissingAmount(drinkOrder);
		displayMessage("Unsufficient funds : " + missingAmount + " euros missing");
	}

	private void handleShortage(DrinkOrder drinkOrder) {
		String drink = drinkOrder.getType().toString();
		emailNotifier.notifyMissingDrink(drinkOrder.getType().toString());
		displayMessage("A shortage of " + drink + " has been detected. A notification has been sent.");
	}

	private boolean isMissingMoney(DrinkOrder drinkOrder) {
		return calculateMissingAmount(drinkOrder) > 0;
	}

	private boolean isShortage(DrinkOrder drinkOrder) {
		return beverageQuantityChecker.isEmpty(drinkOrder.getType().toString());
	}

	private void makeDrink(DrinkOrder drinkOrder) {
		String command = getDrinkTypeProtocol(drinkOrder);
		command += PROTOCOL_SEPARATOR;
		command += getSugarProtocol(drinkOrder);
		command += PROTOCOL_SEPARATOR;
		command += getStickProtocol(drinkOrder);
		drinkMaker.sendCommand(command.toString());
	}

	private String getStickProtocol(DrinkOrder drinkOrder) {
		return drinkOrder.getSugarQuantity() == 0 ? "" : "0";
	}

	private String getDrinkTypeProtocol(DrinkOrder drinkOrder) {
		return drinkOrder.getType().getCode();
	}

	private String getSugarProtocol(DrinkOrder drinkOrder) {
		return drinkOrder.getSugarQuantity() == 0 ? "" : drinkOrder.getSugarQuantity() + "";
	}

	private void recordSellin(DrinkOrder drinkOrder) {
		drinksSellingsDao.addDrinkSelling(drinkOrder.getType());
	}
}
