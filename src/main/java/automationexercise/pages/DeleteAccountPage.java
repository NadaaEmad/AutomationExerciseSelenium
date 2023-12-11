package automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DeleteAccountPage {

    private WebDriver driver;

    public DeleteAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By deleteAccount = By.cssSelector("h2[data-qa='account-deleted']");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    // Actions
    @Step("Click Continue")
    public void clickContinue(){
        driver.findElement(continueButton).click();
    }

    // Validations
    @Step("assert Delete Account")
    public void assertDeleteAccount(String expectedResult){
        Assert.assertEquals(driver.findElement(deleteAccount).getText(), expectedResult);
    }
}
