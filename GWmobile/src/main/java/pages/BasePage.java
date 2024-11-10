package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class BasePage {

    protected WebDriver webDriver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        webDriver.get(url);
        handleCookiePopup(webDriver);
    }

    public void handleCookiePopup(WebDriver driver) {
        try {
            WebElement closeButton = driver.findElement(By.id("cookiescript_accept"));
            closeButton.click();
        }
        catch(NoSuchElementException  e){}
    }

    public void selectOptionFromDropdown(By dropdownLocator, String option) {
        WebElement dropdown = webDriver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }

    public void waitForAjaxToComplete() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (typeof jQuery === 'undefined' || jQuery.active === 0);");
            }
        });
    }
}
