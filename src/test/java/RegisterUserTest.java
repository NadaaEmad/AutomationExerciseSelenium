import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterUserTest {
    WebDriver driver;

    ///////Configurations/////////
    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void verifySignUp(){
        //Verify home page is visible
        driver.findElement(By.xpath("(//h1)[1]"));

        //Verify New User SignUp!
        driver.findElement(By.xpath("(//li)[4]")).click(); //click on signup/login
        String signupText = driver.findElement(By.xpath("(//h2)[3]")).getText();
        Assert.assertEquals(signupText, "New User Signup!");

        //Enter Credentials and click signup
        driver.findElement(By.name("name")).sendKeys("Nada");
        driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys("nada.emad@GizaSystems.com");
        driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();

        //Verify "ENTER ACCOUNT INFORMATION"
        String accountInfoText = driver.findElement(By.xpath("(//b)[1]")).getText();
        Assert.assertEquals(accountInfoText, "ENTER ACCOUNT INFORMATION");

        //Fill info
        driver.findElement(By.cssSelector("input[id='id_gender2']")).click();
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.id("days")).sendKeys("25");
        driver.findElement(By.id("months")).sendKeys("August");
        driver.findElement(By.id("years")).sendKeys("2000");
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();

        //Fill Address info
        String firstName = "Nada";
        driver.findElement(By.id("first_name")).sendKeys(firstName);
        driver.findElement(By.id("last_name")).sendKeys("Emad");
        driver.findElement(By.id("company")).sendKeys("Giza Systems");
        driver.findElement(By.id("address1")).sendKeys("New Cairo");
        driver.findElement(By.id("address2")).sendKeys("Second sector");
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("state")).sendKeys("Bihar");
        driver.findElement(By.id("city")).sendKeys("Bihar");
        driver.findElement(By.id("zipcode")).sendKeys("123456");
        driver.findElement(By.id("mobile_number")).sendKeys("01005457869");
        driver.findElement(By.cssSelector("button[data-qa='create-account']")).click();

        //Verify "ACCOUNT CREATED!"
        String accountCreated = driver.findElement(By.cssSelector("h2[data-qa='account-created']")).getText();
        Assert.assertEquals(accountCreated, "ACCOUNT CREATED!");

        //Verify "logged in as username"
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
        String loggedUser = driver.findElement(By.xpath("(//a)[11]")).getText();
        Assert.assertEquals(loggedUser, "Logged in as " + firstName);

        //delete account
        driver.findElement(By.cssSelector("a[href='/delete_account']")).click();
        String accountDeleted = driver.findElement(By.cssSelector("h2[data-qa='account-deleted']")).getText();

        //Verify "ACCOUNT DELETED!"
        Assert.assertEquals(accountDeleted, "ACCOUNT DELETED!");
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
    }


    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
