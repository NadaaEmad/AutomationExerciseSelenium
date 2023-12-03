package automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignUpLoginPage {
    private WebDriver driver;

    // Locators
    private final By signUpLogin = By.xpath("(//li)[4]");
    private final By signUpNewUser = By.xpath("(//h2)[3]");
    private final By name = By.name("name");
    private final By email = By.cssSelector("input[data-qa='signup-email']");
    private final By signUpButton = By.cssSelector("button[data-qa='signup-button']");

    public SignUpLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void navigateSignUpLogin(){
        driver.findElement(signUpLogin).click();
    }

    public void signUpAction(String name, String email){
        driver.findElement(this.name).sendKeys(name);
        driver.findElement(this.email).sendKeys(email);
        driver.findElement(signUpButton).click();
    }

    // Validations

    public void assertNewUserSignUp(String expectedResult){
        Assert.assertEquals(driver.findElement(signUpNewUser).getText(), expectedResult);
    }
}
