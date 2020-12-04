@runsecondfile 
Feature: Navigate to xxxx and Filter Operations 


Scenario Outline: Navigate to All Pages 
	When I click element with id "<Link>" 
	Then element with id "<Page>" is displayed 
	
	Examples: 
		| Link                      | Page                |
		| at-linkNavQMHome 			| at-hdr-xxx     |
		
Scenario: Filter xxx with Dropdowns
	Given element with id "at-offloadsreceived" is displayed 
	When I select option "string:xxxx" from dropdown "at-xxxxx" 
	And I click element with id "at-filterBtn" 
	Then element with id "at-hdr-xxx" is displayed
	And element with id "at-link-xxxx-20" is displayed 
	
	
	
@destroy 
Scenario: Navigate to Home and Shutdown 
	Given I am on the "/#/ted/home" page 
	Then element with id "landing_header" is displayed 
	
	
