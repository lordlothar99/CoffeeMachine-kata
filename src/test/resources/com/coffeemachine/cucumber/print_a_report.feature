Feature: Print a report
	In order to tell the management how many of each drink was sold and the total amount of money earned
	As a coffee machine administrator
	I want to print a report
	
	Scenario: Print a report
		Given 4 drinks of tea have been sold
		And 2 drinks of chocolate have been sold
		When I generate a report
		Then The report must contain '4 teas sold'
		And The report must contain '2 chocolates sold'
		And The report must contain 'Total earned money: 2.6 euros'