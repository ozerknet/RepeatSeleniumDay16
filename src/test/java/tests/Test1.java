package tests;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.WebDriverFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test1 {
    WebDriver driver;

    @BeforeClass
    public void main() {

    }


    @BeforeMethod
    public void setupMethod() {
        //1. Open browser
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void urlOpen() {
        driver.get("https://practice.cydeo.com/");
    }

    @Test
    public void getTitle() {
        driver.get("https://practice.cydeo.com/");
        String currentTitle = driver.getTitle();
        System.out.println("currentTitle = " + currentTitle);
    }

    @Test
    public void asserted() {
        driver.get("https://practice.cydeo.com/");
        String currentTitle = driver.getTitle();
        System.out.println("currentTitle = " + currentTitle);
        String expectedtitle = "Practice";
        Assert.assertTrue(expectedtitle.equalsIgnoreCase(currentTitle));
    }

    @Test
    public void getCurrentUrl() {
        driver.get("https://practice.cydeo.com/");
        String currentURL = driver.getCurrentUrl();
        System.out.println("currentURL = " + currentURL);
    }

    @Test
    public void navigate() throws InterruptedException {
        driver.get("https://practice.cydeo.com/");
        Thread.sleep(3000);
        driver.get("https://www.amazon.com/");
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();

    }

    @Test
    public void navigateTo() throws InterruptedException {
        driver.navigate().to("https://practice.cydeo.com");
        driver.navigate().refresh();
    }

    @Test
    public void locator_linkText() {
        driver.get("https://practice.cydeo.com");
        WebElement abTestLink = driver.findElement(By.linkText("A/B Testing"));
        abTestLink.click();
    }

    @Test
    public void keys_Enter() {
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//*[.='I agree']")).click();
        WebElement googleSearchBox = driver.findElement(By.name("q")); // xpath => //input[@name='q']
        googleSearchBox.sendKeys("apple" + Keys.ENTER);
    }

    @Test
    public void LoginTest() {
        driver.get("https://login1.nextbasecrm.com/");
        WebElement inputUsername = driver.findElement(By.xpath("//input[@placeholder='Login']"));
        inputUsername.sendKeys("incorrect");
        WebElement inputPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.sendKeys("incorrect");

        WebElement loginButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='errortext']"));

        String expectedErrorMessage = "Incorrect login or password";
        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(expectedErrorMessage.equalsIgnoreCase(actualErrorMessage));

    }

    @Test
    public void isDisplayed() {
        driver.get("https://practice.cydeo.com/forgot_password");

        WebElement retrievePasswordBtn = driver.findElement(By.xpath("//button[@id='form_submit']"));
        retrievePasswordBtn.isDisplayed();

        WebElement forgetPasswordTitle = driver.findElement(By.xpath("//h2"));
        forgetPasswordTitle.isDisplayed();

    }

    @Test
    public void findElements() {
        driver.get("https://practice.cydeo.com/");
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        for (WebElement each : allLinks) {

            System.out.println("Text of link: " + each.getText());
            System.out.println("HREF Values: " + each.getAttribute("href"));

        }

    }

    @Test
    public void checkBox() {
        driver.get("https://practice.cydeo.com/checkboxes");
        WebElement checkbox1 = driver.findElement(By.xpath("//input[@name='checkbox1']"));
        WebElement checkbox2 = driver.findElement(By.xpath("//input[@name='checkbox2']"));

        System.out.println(checkbox1.isSelected());
        System.out.println(checkbox2.isSelected());

        if (checkbox2.isSelected()) {
            checkbox2.click();
        }
    }

    @Test
    public void StaleElementReferenceException() { // Add Remove Test
        driver.get("https://practice.cydeo.com/add_remove_elements/");

        driver.findElement(By.xpath("//button[@onclick]")).click();
        WebElement deleteButton = driver.findElement(By.xpath("//button[@onclick='deleteElement()']"));
        deleteButton.isDisplayed();
        deleteButton.click();

        try {

            System.out.println("deleteButton.isDisplayed() = " + deleteButton.isDisplayed());

        } catch (StaleElementReferenceException e) {

            System.out.println("-->StaleElementReferenceException exception is thrown");
            System.out.println("-->This means the web element is completely deleted from the page");
            System.out.println("deleteButton.isDisplayed() = false");

        }

    }

    @Test
    public void radioButton() throws InterruptedException {
        driver.get("https://practice.cydeo.com/radio_buttons");
        WebElement hockeyRadioBtn = driver.findElement(By.xpath("//input[@id='hockey']"));
        Thread.sleep(2000);
        hockeyRadioBtn.click();

    }

    @Test
    public void a(){

    }

    /*
    Radio Button
    Drop Down

    Alert
    IFrame
    Window
     */

    @AfterMethod
    public void teardownMethod() {
        // driver.close();
        // driver.quit();
    }


}
