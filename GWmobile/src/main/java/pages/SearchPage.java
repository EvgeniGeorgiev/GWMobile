package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage extends BasePage {

    private By makeDropdown = By.cssSelector("[name='f5']");
    private By modelDropdown = By.cssSelector("[name='f6']");
    private By fourWheelDriveFilter = By.id("eimg88");
    private By searchButton = By.cssSelector("[type='button']");
    private By resultsSummary = By.xpath("/html/body/form[3]/div[1]");
    private By nextButtonCTA = By.cssSelector("[class='saveSlink next']");
    private By topOffers = By.cssSelector("[class='item TOP ']");
    private By vipOffers = By.cssSelector("[class='item VIP ']");
    private By lastPageNumber = By.cssSelector("[class='a saveSlink gray']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void selectMake(String make) {
        selectOptionFromDropdown(makeDropdown, make);
    }

    public void selectModel(String model) {
        selectOptionFromDropdown(modelDropdown, model);
    }

    public void selectFilter(String filter) {
        if (filter.equals("4x4")) {
            applyFourWheelDriveFilter();
        }
    }

    private void applyFourWheelDriveFilter() {
        WebElement filterCheckbox = webDriver.findElement(fourWheelDriveFilter);
        if (!filterCheckbox.isSelected()) {
            filterCheckbox.click();
        }
    }

    public void clickSearchButton() {
        webDriver.findElement(searchButton).click();
        waitForAjaxToComplete();
    }

    public String getTotalResultsCount() {
        String resultsText = webDriver.findElement(resultsSummary).getText();
        String totalResults = resultsText.split("от общо ")[1].split(" ")[0];
        return totalResults;
    }

    public void nextSearchPage()
    {
        List<WebElement> nextButtons = webDriver.findElements(nextButtonCTA);
        if (!nextButtons.isEmpty()) {
            WebElement nextButton = nextButtons.get(0);
            nextButton.click();
        }
    }

    public int getTOPOffersCount() {
        return webDriver.findElements(topOffers).size();
    }

    public int getVIPOffersCount() {
        return webDriver.findElements(vipOffers).size();
    }

    public int getPageCount() {
        String text = webDriver.findElement(lastPageNumber).getText();
        int pageCount = Integer.parseInt(text);
        return pageCount;
    }
}
