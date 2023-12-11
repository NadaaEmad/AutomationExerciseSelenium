package automationexercise.pages;

import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;
    String HomePageURL = "https://automationexercise.com/";

    // Locators
    private final By homePageHeader = By.xpath("(//h1)[1]");
    private final By loggedUser = By.xpath("(//a)[11]");
    private final By deleteAccountLink = By.cssSelector("a[href='/delete_account']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    @Step("navigate To URL")
    public void navigateToURL(){
        driver.navigate().to(HomePageURL);
    }

    // Validations
    @Step("verify Home Page Header")
    public void verifyHomePageHeader(){
        driver.findElement(homePageHeader);
    }
    @Step("delete Account")
    public void deleteAccount(){
        driver.findElement(deleteAccountLink).click();
    }
    @Step("assert Logged In As Username")
    public void assertLoggedInAsUsername(String username){
        Assert.assertEquals(driver.findElement(loggedUser).getText(), "Logged in as " + username);
    }
}
