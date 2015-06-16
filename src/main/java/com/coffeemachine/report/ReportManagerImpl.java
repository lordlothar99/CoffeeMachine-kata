package com.coffeemachine.report;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import com.coffeemachine.DrinkType;
import com.coffeemachine.store.DrinkSelling;
import com.coffeemachine.store.DrinksSellings;
import com.coffeemachine.store.DrinksSellingsDao;

public class ReportManagerImpl implements ReportManager {

	private final DrinksSellingsDao drinksSellingsDao;

	public ReportManagerImpl(DrinksSellingsDao drinksSellingsDao) {
		this.drinksSellingsDao = drinksSellingsDao;
	}

	@Override
	public String generateReport() {
		DrinksSellings drinksSellings = drinksSellingsDao.getDrinksSellings();

		String report = "";
		for (DrinkSelling drinkSelling : drinksSellings.iterator()) {
			report += drinkReport(drinkSelling.getQuantity(), drinkSelling.getDrinkType()) + "\n";
		}

		BigDecimal totalEarnedMoney = totalEarnedMoney(drinksSellings);
		report += "Total earned money: " + totalEarnedMoney + " " + plurifyIfNeeded("euro", totalEarnedMoney);

		return report;
	}

	private String drinkReport(int soldDrinkCount, DrinkType drinkType) {
		String drinkReport = soldDrinkCount + " ";
		drinkReport += plurifyIfNeeded(drinkType.toString().toLowerCase(), soldDrinkCount);
		drinkReport += " sold";
		return drinkReport;
	}

	private static String plurifyIfNeeded(String string, Number count) {
		return count.doubleValue() <= 1 ? string : string + "s";
	}

	private BigDecimal totalEarnedMoney(DrinksSellings drinksSellings) {
		BigDecimal earnedMoney = ZERO;
		for (DrinkSelling drinkSelling : drinksSellings.iterator()) {
			int soldDrinkCount = drinkSelling.getQuantity();
			DrinkType drinkType = drinkSelling.getDrinkType();

			earnedMoney = earnedMoney.add(computeEarnedMoney(soldDrinkCount, drinkType));
		}

		return earnedMoney;
	}

	private BigDecimal computeEarnedMoney(int soldDrinkCount, DrinkType drinkType) {
		return drinkType.getPrice().multiply(new BigDecimal(soldDrinkCount));
	}
}
