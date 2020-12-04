@pitchfork 
Feature: Test Pitchfork Website

@setup 
Scenario: Navigate to webpage 
	Given I am on the "http://www.gata.org/" Website 
	
@destroy
Scenario: Test assertion
	Then element with id "content" is displayed 
	And page title is "Gold Anti-Trust Action Committee | Exposing the long-term manipulation of the gold market"