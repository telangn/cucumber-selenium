@tableHeader 
Feature: Test assertion of Table Headers 

@setup 
Scenario: Navigate to webpage 
	Given I am on the "http://toolsqa.com/automation-practice-table/" Website 
	
@destroy
Scenario: Test Table Header assertion 
	Then table with id "content" has header values of 
	
		| Structure |
		| Country | 
		| City |
		| Height | 
		| Built | 
		| Rank |
		| … |
		|Total|
		|Burj Khalifa|
		|Clock Tower Hotel|
		|Taipei 101|
		|Financial Center|