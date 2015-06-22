package com.coffeemachine.cucumber;

import static com.coffeemachine.DrinkOrderBuilder.newOrder;
import static com.coffeemachine.DrinkType.valueOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import com.coffeemachine.CoffeeMachineController;
import com.coffeemachine.DrinkMaker;
import com.coffeemachine.DrinkOrder;
import com.coffeemachine.DrinkType;
import com.coffeemachine.store.DrinksSellingsDao;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderADrinkStepdefs {

	private CoffeeMachineController coffeeMachineController;
	private DrinkMaker drinkMaker;

	@Given("^a coffee machine with a drink maker$")
	public void a_coffee_machine_with_a_drink_maker() throws Throwable {
		drinkMaker = mock(DrinkMaker.class);
		coffeeMachineController = new CoffeeMachineController(drinkMaker, mock(DrinksSellingsDao.class));
		System.out.println("there !");
	}

	@When("^a (.*) with (\\d+) sugars and (\\d+.\\d+) euros is ordered$")
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
