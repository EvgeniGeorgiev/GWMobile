Feature: Verify Search functionality

 #Task: Create a Selenium script that finds the number of Volkswagen Golfs with four wheel drive on sale on mobile.bg and writes the number in the console
 # Optional - output in log instead of console
 # Optional - use page objects
 # Optional - count the promoted (VIP or TOP) ads and write the number in the console/log

  Scenario: Search for Volkswagen Golf with four wheel drive
    #https://www.mobile.bg/search/avtomobili-dzhipove
    Given the user is on the cars and jeeps search page
    When the user searches for make "VW" and model "Golf" with filter "4x4"
    Then the amount of available products is output in the logs
    Then the amount of available TOP and VIP products is output in the logs