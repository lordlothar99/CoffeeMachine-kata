package com.coffeemachine;

import static com.coffeemachine.DrinkOrderBuilder.newOrder;
import static com.coffeemachine.DrinkType.valueOf;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderASimpleBeverageStepdefs {

	private CoffeeMachineController coffeeMachineController;
	private DrinkMaker drinkMaker;

	@Before
	public void init() {
		drinkMaker = Mockito.mock(DrinkMaker.class);
		coffeeMachineController = new CoffeeMachineController(drinkMaker);
	}

	@When("^a (.*) is ordered$")
	public void a_drink_is_ordered(String drink) {
		DrinkType drinkType = valueOf(drink.toUpperCase());
		coffeeMachineController.orderDrink(newOrder().of(drinkType).withCashAmount(drinkType.getPrice()).asOrder());
	}

	@Then("^i should send the command \"(.*)\" to the drink maker$")
	public void i_should_send_the_expected_command(String command) throws Throwable {
		verify(drinkMaker).sendCommand(command);
	}
}
