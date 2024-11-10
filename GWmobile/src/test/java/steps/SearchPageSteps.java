package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.SearchPage;

public class SearchPageSteps {

    private static final Logger logger = LoggerFactory.getLogger(SearchPageSteps.class);
    private WebDriver driver;
    private SearchPage searchPage;

    public SearchPageSteps() {
        this.driver = Hooks.getDriver();
        this.searchPage = new SearchPage(driver);
    }

    @Given("the user is on the cars and jeeps search page")
    public void theUserIsOnTheCarsAndJeepsSearchPage() {
        searchPage.navigateTo("https://www.mobile.bg/search/avtomobili-dzhipove");
    }

    @When("the user searches for make {string} and model {string} with filter {string}")
    public void theUserSearchesForMakeAndModelWithFilter(String make, String model, String filter) {
        searchPage.selectMake(make);
        searchPage.selectModel(model);
        searchPage.selectFilter(filter);
        searchPage.clickSearchButton();
    }

    @Then("the amount of available products is output in the logs")
    public void theAmountOfAvailableProductsIsOutputInTheLogs() {
        String productCount = searchPage.getTotalResultsCount();
        System.out.println("Total products found: " + productCount);
        logger.info("Total products found: {}", productCount);
    }

    @Then("the amount of available TOP and VIP products is output in the logs")
    public void theAmountOfAvailableTOPAndVIPProductsIsOutputInTheLogs() {
        int totalPageCount = searchPage.getPageCount();
        int totalTOPOffers = 0;
        int totalVIPOffers = 0;

        for(int i=0; i<totalPageCount; i++){
            int getCurrentPageTOPOffers = searchPage.getTOPOffersCount();
            int getCurrentPageVIPOffers = searchPage.getVIPOffersCount();

            totalTOPOffers += getCurrentPageTOPOffers;
            totalVIPOffers += getCurrentPageVIPOffers;

            if (getCurrentPageTOPOffers==0&&getCurrentPageVIPOffers==0)
            {
                break;
            }
            else {
                searchPage.nextSearchPage();
            }
        }

        System.out.println("Total TOP products found: " + totalTOPOffers);
        System.out.println("Total VIP products found: " + totalVIPOffers);
        logger.info("Total TOP products found: {}", totalTOPOffers);
        logger.info("Total VIP products found: {}", totalVIPOffers);
    }
}
