package automationexercise.pages;

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
    public void navigateToURL(){
        driver.navigate().to(HomePageURL);
    }

    // Validations
    public void verifyHomePageHeader(){
        driver.findElement(homePageHeader);
    }
    public void deleteAccount(){
        driver.findElement(deleteAccountLink).click();
    }
    public void assertLoggedInAsUsername(String username){
        Assert.assertEquals(driver.findElement(loggedUser).getText(), "Logged in as " + username);
    }
}
