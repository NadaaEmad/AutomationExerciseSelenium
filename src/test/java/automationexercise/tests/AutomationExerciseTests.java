package automationexercise.tests;

import automationexercise.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationExerciseTests{
    private WebDriver driver;
    private HomePage homePage;
    private SignUpLoginPage signUpLoginPage;
    private SignUpInfoPage signUpInfoPage;
    private AccountCreatedPage accountCreatedPage;
    private DeleteAccountPage deleteAccountPage;

    /////Test Cases/////
    @Test
    public void verifySignUp(){
        homePage.verifyHomePageHeader();
        signUpLoginPage.navigateSignUpLogin();
        signUpLoginPage.assertNewUserSignUp("New User Signup!");
        signUpLoginPage.signUpAction("Nada", "nada.emad@GizaSystems.com");
        signUpInfoPage.assertEnterAccountInfo("ENTER ACCOUNT INFORMATION");
        signUpInfoPage.fillUserInfo("1234","25","8","2000");
        signUpInfoPage.fillAddressInfo("Nada","Emad","Giza Systems","New Cairo","Second sector",
                "India","Bihar","Bihar","123456","01005457869");
        signUpInfoPage.clickCreateAccount();

        accountCreatedPage.assertAccountCreated("ACCOUNT CREATED!");
        accountCreatedPage.clickContinue();
        homePage.assertLoggedInAsUsername("Nada");
        homePage.deleteAccount();
        deleteAccountPage.assertDeleteAccount("ACCOUNT DELETED!");
        deleteAccountPage.clickContinue();

    }

    ////Configurations////
    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
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

}
