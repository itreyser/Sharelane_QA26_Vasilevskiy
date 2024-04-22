import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShareLaneTest {

    Faker faker = new Faker();

    @Test
    public void zipCodePositiveTest() {

        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.navigate().to("https://www.sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        boolean findInputRegister = driver.findElement(By.cssSelector("input[value=Register]")).isDisplayed();

        Assert.assertTrue(findInputRegister);

        driver.quit();
    }


    @Test
    public void zipCodeIsEmpty() {

        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.navigate().to("https://www.sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        boolean findErrorMessage = driver.findElement(By.className("error_message")).isDisplayed();

        Assert.assertTrue(findErrorMessage);

        driver.quit();
    }

    @Test
    public void zipCodeEnter4digit() {

        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.navigate().to("https://www.sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("1234");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        boolean findErrorMessage = driver.findElement(By.className("error_message")).isDisplayed();

        Assert.assertTrue(findErrorMessage);

        driver.quit();
    }

    @Test
    public void signUpPositiveTest() {

        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.navigate().to("https://www.sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();

        //заполнение формы валидными данными
        driver.findElement(By.name("first_name")).sendKeys(faker.name().firstName());
        driver.findElement(By.name("last_name")).sendKeys(faker.name().lastName());
        driver.findElement(By.name("email")).sendKeys("mail@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123qwe");
        driver.findElement(By.name("password2")).sendKeys("123qwe");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        boolean message = driver.findElement(By.className("confirmation_message")).isDisplayed();

        Assert.assertTrue(message);


        driver.quit();
    }

    @Test
    public void signUpWithoutConfirmPassword() {

        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.navigate().to("https://www.sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();

        //заполнение формы без подтверждения пароля
        driver.findElement(By.name("first_name")).sendKeys(faker.name().firstName());
        driver.findElement(By.name("last_name")).sendKeys(faker.name().lastName());
        driver.findElement(By.name("email")).sendKeys("mail@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123qwe");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        boolean errorMessage = driver.findElement(By.className("error_message")).isDisplayed();

        Assert.assertTrue(errorMessage);


        driver.quit();
    }

    @Test
    public void signUpWithoutDomainMail() {

        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.navigate().to("https://www.sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();

        //заполнение формы c невалидной почтой
        driver.findElement(By.name("first_name")).sendKeys(faker.name().firstName());
        driver.findElement(By.name("last_name")).sendKeys(faker.name().lastName());
        driver.findElement(By.name("email")).sendKeys("mail");
        driver.findElement(By.name("password1")).sendKeys("123qwe");
        driver.findElement(By.name("password2")).sendKeys("123qwe");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        boolean errorMessage = driver.findElement(By.className("error_message")).isDisplayed();

        Assert.assertTrue(errorMessage);


        driver.quit();
    }


}
