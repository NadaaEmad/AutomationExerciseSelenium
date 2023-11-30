import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterUserTestLinearDesign {
    private WebDriver driver;
    String webSiteURL = "https://automationexercise.com/";

    ////Element Locators////
    private final By homePageHeader = By.xpath("(//h1)[1]");
    private final By signUpLogin = By.xpath("(//li)[4]");
    private final By signUpNewUser = By.xpath("(//h2)[3]");
    private final By name = By.name("name");
    private final By email = By.cssSelector("input[data-qa='signup-email']");
    private final By signUpButton = By.cssSelector("button[data-qa='signup-button']");
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
    private final By accountCreated = By.cssSelector("h2[data-qa='account-created']");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");
    private final By loggedUser = By.xpath("(//a)[11]");
    private final By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private final By deleteAccount = By.cssSelector("h2[data-qa='account-deleted']");



    /////Test Cases/////
    @Test
    public void verifySignUp(){
        verifyHomePageHeader();
        navigateSignUpLogin();
        assertNewUserSignUp("New User Signup!");
        signUpAction("Nada", "nada.emad@GizaSystems.com");
        assertEnterAccountInfo("ENTER ACCOUNT INFORMATION");
        fillUserInfo("1234","25","8","2000");
        fillAddressInfo("Nada","Emad","Giza Systems","New Cairo","Second sector",
                "India","Bihar","Bihar","123456","01005457869");
        clickCreateAccount();
        assertAccountCreated("ACCOUNT CREATED!");
        assertLoggedInAsUsername("Nada");
        deleteAccount();
        assertDeleteAccount("ACCOUNT DELETED!");

    }


    ////Configurations////
    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        navigateToURL();
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    ////Actions////
    public void navigateToURL(){
        driver.navigate().to(webSiteURL);
    }

    public void verifyHomePageHeader(){
        driver.findElement(homePageHeader);
    }

    public void navigateSignUpLogin(){
        driver.findElement(signUpLogin).click();
    }
    public void assertNewUserSignUp(String expectedResult){
        Assert.assertEquals(driver.findElement(signUpNewUser).getText(), expectedResult);
    }

    public void signUpAction(String name, String email){
        driver.findElement(this.name).sendKeys(name);
        driver.findElement(this.email).sendKeys(email);
        driver.findElement(signUpButton).click();
    }

    public void assertEnterAccountInfo(String expectedResult){
        Assert.assertEquals(driver.findElement(accountInfo).getText(), expectedResult);
    }

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

    public void assertAccountCreated(String expectedResult){
        Assert.assertEquals(driver.findElement(accountCreated).getText(), expectedResult);
    }

    public void assertLoggedInAsUsername(String username){
        driver.findElement(continueButton).click();
        Assert.assertEquals(driver.findElement(loggedUser).getText(), "Logged in as " + username);
    }

    public void deleteAccount(){
        driver.findElement(deleteAccountLink).click();
    }

    public void assertDeleteAccount(String expectedResult){
        Assert.assertEquals(driver.findElement(deleteAccount).getText(), expectedResult);
        driver.findElement(continueButton).click();
    }
}
