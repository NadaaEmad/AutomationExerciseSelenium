package automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignUpInfoPage {

    private WebDriver driver;

    // Locators
    private final By accountInfo = By.xpath("(//b)[1]");
    private final By gender = By.cssSelector("input[id='id_gender2']");
    private final By password = By.id("password");
    private final By days = By.id("days");
    private final By months = By.id("months");
    private final By years =  By.id("years");
    private final By newsletter = By.id("newsletter");
    private final By option = By.id("optin");
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobile_number = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("button[data-qa='create-account']");

    public SignUpInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions

    public void fillUserInfo(String password, String days, String months, String years){
        driver.findElement(gender).click();
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(this.days).sendKeys(days);
        driver.findElement(this.months).sendKeys(months);
        driver.findElement(this.years).sendKeys(years);
        driver.findElement(this.newsletter).click();
        driver.findElement(this.option).click();
    }
    public void fillAddressInfo(String firstName,String lastName,String company,String address1,String address2,
                                String country,String state,String city,String zipcode,String mobile_number){
        driver.findElement(this.firstName).sendKeys(firstName);
        driver.findElement(this.lastName).sendKeys(lastName);
        driver.findElement(this.company).sendKeys(company);
        driver.findElement(this.address1).sendKeys(address1);
        driver.findElement(this.address2).sendKeys(address2);
        driver.findElement(this.country).sendKeys(country);
        driver.findElement(this.state).sendKeys(state);
        driver.findElement(this.city).sendKeys(city);
        driver.findElement(this.zipcode).sendKeys(zipcode);
        driver.findElement(this.mobile_number).sendKeys(mobile_number);

    }
    public void clickCreateAccount(){
        driver.findElement(createAccountButton).click();
    }

    // Validations
    public void assertEnterAccountInfo(String expectedResult){
        Assert.assertEquals(driver.findElement(accountInfo).getText(), expectedResult);
    }

}
