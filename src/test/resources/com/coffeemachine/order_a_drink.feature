Feature: Order a simple drink
	In order to pilot the drink maker
	As a coffee machine controller
	I want to send a command
	
	Scenario Outline: Order of a simple drink
		When a <drink> with <sugar> sugar and <cash amount> euros is ordered
		Then I should send '<command>' to the drink maker
	Examples:
    	| drink			| sugar	| cash amount	| command									|
    	| tea			| 1		| 0.4			| T:1:0										|
    	| chocolate		| 0		| 0.5			| H::										|
    	| coffee		| 2		| 0.6			| C:2:0										|
    	| tea			| 0		| 0.3			| M:Unsufficient funds : 0.1 euros missing	|
    	| chocolate		| 0		| 0.2			| M:Unsufficient funds : 0.3 euros missing	|
    	| coffee		| 0		| 0.2			| M:Unsufficient funds : 0.4 euros missing	|
    	| orange juice	| 0		| 0.6			| O::										|
    	| hot coffee	| 0		| 0.6			| Ch::										|
    	| hot chocolate	| 1		| 0.5			| Hh:1:0									|
    	| hot tea		| 2		| 0.4			| Th:2:0									|

	Scenario: Display a message on the coffee machine interface
		When I want to display 'hello world !' on the interface
		Then I should send 'M:hello world !' to the drink maker