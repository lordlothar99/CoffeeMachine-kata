package com.coffeemachine.cucumber;

import static com.coffeemachine.DrinkType.valueOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.coffeemachine.DrinkType;
import com.coffeemachine.report.ReportManager;
import com.coffeemachine.report.ReportManagerImpl;
import com.coffeemachine.store.DrinksSellings;
import com.coffeemachine.store.DrinksSellingsDao;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PrintAReportStepdefs {

	private ReportManager reportManager;
	private String report;
	private DrinksSellings drinksSellings;

	@Before
	public void init() {
		DrinksSellingsDao drinksSellingsDao = mock(DrinksSellingsDao.class);
		reportManager = new ReportManagerImpl(drinksSellingsDao);
		drinksSellings = new DrinksSellings();
		when(drinksSellingsDao.getDrinksSellings()).thenReturn(drinksSellings);
	}

	@Given("^(\\d+) (.*) have been sold$")
	public void drinks_of_tea_have_been_sold(int soldCount, String drinkType) {
		drinksSellings.add(getDrinkType(drinkType), soldCount);
	}

	private DrinkType getDrinkType(String drinkType) {
		if ('s' == drinkType.charAt(drinkType.length() - 1)) {
			drinkType = drinkType.substring(0, drinkType.length() - 1);
		}
		return valueOf(drinkType.replace(' ', '_').toUpperCase());
	}

	@When("^I generate a report$")
	public void I_generate_a_report() {
		report = reportManager.generateReport();
	}

	@Then("^The report must contain '(.*)'$")
	public void The_report_must_contain(String contents) {
		assertNotNull("Null report", report);
		assertTrue("Missing content '" + contents + "' in report : " + report, report.contains(contents));
	}
}
