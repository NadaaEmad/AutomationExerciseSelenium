package automationexercise.tests;

import automationexercise.pages.*;
import framework.engine.JsonFileManager;
import framework.engine.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AutomationExerciseTests{
    private static WebDriver driver;
    private HomePage homePage;
    private SignUpLoginPage signUpLoginPage;
    private SignUpInfoPage signUpInfoPage;
    private AccountCreatedPage accountCreatedPage;
    private DeleteAccountPage deleteAccountPage;

    JsonFileManager testData;

    /////Test Cases/////
    @Test
    public void verifySignUp(){
        homePage.verifyHomePageHeader();
        signUpLoginPage.navigateSignUpLogin();
        signUpLoginPage.assertNewUserSignUp(testData.getTestData("signUpLoginPage.newUserAssertion"));
        signUpLoginPage.signUpAction(testData.getTestData("signUpLoginPage.name"), testData.getTestData("signUpLoginPage.email"));
        signUpInfoPage.assertEnterAccountInfo(testData.getTestData("signUpInfoPage.accountInfoAssertion"));
        signUpInfoPage.fillUserInfo(testData.getTestData("signUpInfoPage.password"), testData.getTestData("signUpInfoPage.days"),
                testData.getTestData("signUpInfoPage.months"),  testData.getTestData("signUpInfoPage.years"));
        signUpInfoPage.fillAddressInfo( testData.getTestData("signUpInfoPage.firstName"), testData.getTestData("signUpInfoPage.lastName"),
                testData.getTestData("signUpInfoPage.company"), testData.getTestData("signUpInfoPage.address1"), testData.getTestData("signUpInfoPage.address2"),
                testData.getTestData("signUpInfoPage.company"), testData.getTestData("signUpInfoPage.state"),
                testData.getTestData("signUpInfoPage.city"), testData.getTestData("signUpInfoPage.zipcode"), testData.getTestData("signUpInfoPage.mobileNumber"));
        signUpInfoPage.clickCreateAccount();

        accountCreatedPage.assertAccountCreated(testData.getTestData("accountCreatedPage.accountCreatedAssertion"));
        accountCreatedPage.clickContinue();
        homePage.assertLoggedInAsUsername(testData.getTestData("homePage.loggedUserAssertion"));
        homePage.deleteAccount();
        deleteAccountPage.assertDeleteAccount(testData.getTestData("deleteAccountPage.deleteAccountAssertion"));
        deleteAccountPage.clickContinue();

    }

    ////Configurations////
    @BeforeClass
    public void beforeClass() {
        PropertiesReader.loadProperties();
        testData = new JsonFileManager("src/test/resources/TestData.json");
    }
    @BeforeMethod
    public void beforeMethod(){
        driver = initDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        signUpLoginPage = new SignUpLoginPage(driver);
        signUpInfoPage = new SignUpInfoPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);

        homePage.navigateToURL();
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    /////Core///////
    private static WebDriver initDriver(String browserType, Boolean maximize, Boolean headless) {
        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = null;
            if (headless) {
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
            } else {
                driver = new ChromeDriver();
            }
        } else if (browserType.equalsIgnoreCase("firefox")) {
            FirefoxOptions ffOptions = null;
            if (headless){
                ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--headless");
                driver = new FirefoxDriver(ffOptions);
            } else {
                driver = new FirefoxDriver();
            }
        } else if (browserType.equalsIgnoreCase("edge") ) {
            driver = new EdgeDriver();
        } else {
            driver = new SafariDriver();
        }
        if (Boolean.TRUE.equals(maximize)) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver initDriver(String browserType) {
        return initDriver(browserType,true, false);
    }

    public static WebDriver initDriver() {
        return initDriver(
                System.getProperty("browserType"),
                Boolean.valueOf(System.getProperty("maximizeWindow")),
                Boolean.valueOf(System.getProperty("headlessExecution"))
        );
    }

}
