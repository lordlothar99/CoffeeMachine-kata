package com.coffeemachine;

import static com.coffeemachine.DrinkOrderBuilder.newOrder;
import static com.coffeemachine.DrinkType.valueOf;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.mockito.Mockito;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderADrinkStepdefs {

	private CoffeeMachineController coffeeMachineController;
	private DrinkMaker drinkMaker;

	@Before
	public void init() {
		drinkMaker = Mockito.mock(DrinkMaker.class);
		coffeeMachineController = new CoffeeMachineController(drinkMaker);
	}

	@When("^a (.*) with (\\d+) sugar and (\\d+.\\d+) euros is ordered$")
	public void a_drink_is_ordered(String drinkType, int sugarQuantity, String cashAmount) {
		DrinkOrder order = newOrder().of(getDrinkType(drinkType))//
				.withSugarQuantity(sugarQuantity)//
				.withCashAmount(new BigDecimal(cashAmount))//
				.asOrder();
		coffeeMachineController.orderDrink(order);
	}

	private DrinkType getDrinkType(String drinkType) {
		return valueOf(drinkType.replace(' ', '_').toUpperCase());
	}

	@When("^I want to display '(.+)' on the interface$")
	public void I_want_to_display_a_message_on_the_interface(String message) throws Throwable {
		coffeeMachineController.displayMessage(message);
	}

	@Then("^I should send '(.+)' to the drink maker$")
	public void i_should_send_the_expected_command(String command) throws Throwable {
		verify(drinkMaker).sendCommand(command);
	}
}
