package com.coffeemachine.cucumber;

import static com.coffeemachine.DrinkOrderBuilder.newOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.coffeemachine.CoffeeMachineController;
import com.coffeemachine.DrinkMaker;
import com.coffeemachine.DrinkOrder;
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
	}

	@When("^a (.*) with (\\d+) sugars and (\\d+.\\d+) euros is ordered$")
	public void a_drink_is_ordered(String drinkType, int sugarQuantity, String cashAmount) {
		DrinkOrder order = newOrder().ofDrink(drinkType)//
				.withSugarQuantity(sugarQuantity)//
				.withCashAmount(cashAmount)//
				.asOrder();
		coffeeMachineController.orderDrink(order);
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
