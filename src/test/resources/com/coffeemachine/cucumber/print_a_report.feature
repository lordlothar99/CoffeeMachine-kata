Feature: Print a report
	In order to tell the management how many of each drink was sold and the total amount of money earned
	As a coffee machine administrator
	I want to print a report
	
	Scenario: Print a report with one drink type sold only
		Given 2 chocolates have been sold
		When I generate a report
		Then The report must contain '2 chocolates sold'
		And The report must contain 'Total earned money: 1.0 euro'
	
	Scenario: Print a report with several drink types sold
		Given 4 tea have been sold
		And 1 chocolate have been sold
		When I generate a report
		Then The report must contain '1 chocolate sold'
		And The report must contain '4 teas sold'
		And The report must contain 'Total earned money: 2.1 euros'