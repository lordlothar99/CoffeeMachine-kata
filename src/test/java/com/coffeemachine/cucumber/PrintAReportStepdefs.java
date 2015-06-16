package com.coffeemachine.cucumber;

import static com.coffeemachine.DrinkOrderBuilder.newOrder;
import static com.coffeemachine.DrinkType.valueOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.coffeemachine.CoffeeMachineController;
import com.coffeemachine.DrinkMaker;
import com.coffeemachine.DrinkOrder;
import com.coffeemachine.DrinkType;
import com.coffeemachine.ReportManager;
import com.coffeemachine.ReportManagerImpl;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PrintAReportStepdefs {

	private CoffeeMachineController coffeeMachineController;
	private ReportManager reportManager;
	private String report;

	@Before
	public void init() {
		reportManager = spy(new ReportManagerImpl());
		when(reportManager.generateReport()).then(new Answer<String>() {
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				report = (String) invocation.callRealMethod();
				return report;
			}
		});
		coffeeMachineController = new CoffeeMachineController(mock(DrinkMaker.class), reportManager);
	}

	@Given("^(\\d+) drinks of (.*) have been sold$")
	public void drinks_of_tea_have_been_sold(int soldCount, String drinkType) throws Throwable {
		DrinkType _drinkType = getDrinkType(drinkType);
		DrinkOrder order = newOrder().of(_drinkType)//
				.withCashAmount(_drinkType.getPrice())//
				.asOrder();
		for (int i = 0; i < soldCount; i++) {
			coffeeMachineController.orderDrink(order);
		}
	}

	private DrinkType getDrinkType(String drinkType) {
		return valueOf(drinkType.replace(' ', '_').toUpperCase());
	}

	@When("^I generate a report$")
	public void I_generate_a_report() throws Throwable {
		coffeeMachineController.printReport();
	}

	@Then("^The report must contain '(.*)'$")
	public void The_report_must_contain(String contents) throws Throwable {
		Assert.assertTrue(report.contains(contents));
	}
}
