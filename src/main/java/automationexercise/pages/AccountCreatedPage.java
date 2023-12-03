package automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountCreatedPage {

    private WebDriver driver;

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By accountCreated = By.cssSelector("h2[data-qa='account-created']");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    // Actions
    public void clickContinue(){
        driver.findElement(continueButton).click();
    }

    // Validations
    public void assertAccountCreated(String expectedResult){
        Assert.assertEquals(driver.findElement(accountCreated).getText(), expectedResult);
    }

}
