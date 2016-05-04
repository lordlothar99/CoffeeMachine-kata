package com.coffeemachine.cucumber;

import static com.coffeemachine.DrinkOrderBuilder.newOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.coffeemachine.CoffeeMachineController;
import com.coffeemachine.DrinkMaker;
import com.coffeemachine.DrinkOrder;
import com.coffeemachine.check.BeverageQuantityChecker;
import com.coffeemachine.email.EmailNotifier;
import com.coffeemachine.store.DrinksSellingsDao;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderADrinkStepdefs {

	private CoffeeMachineController coffeeMachineController;
	private DrinkMaker drinkMaker;
	private EmailNotifier emailNotifier;
	private BeverageQuantityChecker beverageQuantityChecker;

	@Given("^a coffee machine with a drink maker$")
	public void a_coffee_machine_with_a_drink_maker() {
		drinkMaker = mock(DrinkMaker.class);
		emailNotifier = mock(EmailNotifier.class);
		beverageQuantityChecker = mock(BeverageQuantityChecker.class);
		coffeeMachineController = new CoffeeMachineController(drinkMaker, mock(DrinksSellingsDao.class), emailNotifier,
				beverageQuantityChecker);
	}

	@When("^a (.*) with (\\d+) sugars? and (\\d+.\\d+) euros is ordered$")
	public void a_drink_is_ordered(String drinkType, int sugarQuantity, String money) {
		DrinkOrder order = newOrder().ofDrink(drinkType)//
				.withSugarQuantity(sugarQuantity)//
				.withMoney(money)//
				.asOrder();
		coffeeMachineController.orderDrink(order);
	}

	@When("^I want to display '(.+)' on the interface$")
	public void I_want_to_display_a_message_on_the_interface(String message) {
		coffeeMachineController.displayMessage(message);
	}

	@When("^there is no more ([^\"]*)$")
	public void there_is_no_more_(String drinkType) {
		when(beverageQuantityChecker.isEmpty(drinkType)).thenReturn(true);
	}

	@Then("^I should send an email notification for \"([^\"]*)\"$")
	public void I_should_send_an_email_notification_for(String drinkType) {
		verify(emailNotifier).notifyMissingDrink(drinkType);
	}

	@Then("^I should send '(.+)' to the drink maker$")
	public void i_should_send_the_expected_command(String command) {
		verify(drinkMaker).sendCommand(command);
	}
}
