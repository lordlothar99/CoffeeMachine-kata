package com.coffeemachine.junit;

import static com.coffeemachine.DrinkType.CHOCOLATE;
import static com.coffeemachine.DrinkType.TEA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.coffeemachine.store.DrinksSellings;

public class ReportManagerTest extends AbstractCoffeeMachineTest {

	@Test
	public void should_report_contain_sold_drinks_details_and_total_earned_money_quantity_when_user_asks_for_a_report_after_one_sell() {
		DrinksSellings drinksSellings = new DrinksSellings();
		drinksSellings.add(CHOCOLATE, 1);
		when(drinksSellingsDao.getDrinksSellings()).thenReturn(drinksSellings);

		String report = reportManager.generateReport();

		assertThat(report).isEqualTo("1 chocolate sold\nTotal earned money: 0.5 euro");
	}

	@Test
	public void should_report_contain_sold_drinks_details_and_total_earned_money_quantity_when_user_asks_for_a_report_after_two_sells() {
		DrinksSellings drinksSellings = new DrinksSellings();
		drinksSellings.add(CHOCOLATE, 2);
		when(drinksSellingsDao.getDrinksSellings()).thenReturn(drinksSellings);

		String report = reportManager.generateReport();

		assertThat(report).isEqualTo("2 chocolates sold\nTotal earned money: 1.0 euro");
	}

	@Test
	public void should_report_contain_sold_drinks_details_and_total_earned_money_quantity_when_user_asks_for_a_report_after_different_sells() {
		DrinksSellings drinksSellings = new DrinksSellings();
		drinksSellings.add(TEA, 4);
		drinksSellings.add(CHOCOLATE, 1);
		when(drinksSellingsDao.getDrinksSellings()).thenReturn(drinksSellings);

		String report = reportManager.generateReport();

		assertThat(report).isEqualTo("1 chocolate sold\n4 teas sold\nTotal earned money: 2.1 euros");
	}
}
