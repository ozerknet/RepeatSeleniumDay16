package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.WebDriverFactory;

import java.util.List;
import java.util.Set;
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
    public void radioButtons() {
        driver.get("https://practice.cydeo.com/radio_buttons");
        WebElement hockeyRadioBtn = driver.findElement(By.xpath("//input[@id='hockey']"));
        hockeyRadioBtn.click();
        if (hockeyRadioBtn.isSelected()) {
            System.out.println("Button is selected. Verification PASSED!");
        } else {
            System.out.println("Button is not selected. Verification FAILED!!!");
        }
    }

/*
    @Test
    public void radioButtonsWithMethod() {
        driver.get("https://practice.cydeo.com/radio_buttons");

        //Locate name='sports' radio buttons and store them in a List of Web Element

        clickAndVerifyRadioButton(driver, "sport", "hockey");

        clickAndVerifyRadioButton(driver, "sport", "football");

        clickAndVerifyRadioButton(driver, "color", "yellow");

        private static void clickAndVerifyRadioButton (WebDriver driver, String nameAttribute, String idValue){

            List<WebElement> radioButtons = driver.findElements(By.name(nameAttribute));

            //Loop through the List of WebElement and select matching result "hockey"
            for (WebElement each : radioButtons) {
                String eachId = each.getAttribute("id");

                if (eachId.equals(idValue)) {

                    each.click();
                    System.out.println(eachId + " is selected : " + each.isSelected());
                    break;
                }
            }

        }
    }
*/ // radioButton With Method ???

    @Test
    public void dropDown() {
        driver.get("https://practice.cydeo.com/dropdown");

        Select simpleDropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown']")));
        WebElement currentlySelectedOption = simpleDropdown.getFirstSelectedOption();
        String actualSimpleDropdownText = currentlySelectedOption.getText();
        String expectedSimpleDropdownText = "Please select an option";
        Assert.assertEquals(actualSimpleDropdownText, expectedSimpleDropdownText);


        Select stateDropdown = new Select(driver.findElement(By.xpath("//select[@id='state']")));
        String expectedStateDropdownText = "Select a State";
        String actualStateDropdownText = stateDropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(actualStateDropdownText, expectedStateDropdownText);

    }


    @Test
    public void dropDownMorePractice() {
        driver.get("https://practice.cydeo.com/dropdown");
        Select yearDropdown = new Select(driver.findElement(By.xpath("//select[@id='year']")));
        Select monthDropdown = new Select(driver.findElement(By.xpath("//select[@id='month']")));
        Select dayDropdown = new Select(driver.findElement(By.xpath("//select[@id='day']")));

        yearDropdown.selectByVisibleText("1980");
        monthDropdown.selectByVisibleText("April");
        dayDropdown.selectByIndex(3);

        String expectedYear = "1980";
        String expectedMonth = "April";
        String expectedDay = "4";

        String actualYear = yearDropdown.getFirstSelectedOption().getText();
        String actualMonth = monthDropdown.getFirstSelectedOption().getText();
        String actualDay = dayDropdown.getFirstSelectedOption().getText();

        Assert.assertTrue(actualYear.equals(expectedYear));
        Assert.assertEquals(actualMonth, expectedMonth);
        Assert.assertEquals(actualDay, expectedDay);


    }

    @Test
    public void Alerts() {
        driver.get("https://practice.cydeo.com/javascript_alerts");
        WebElement informationAlertButton = driver.findElement(By.xpath("//button[.='Click for JS Alert']"));

        informationAlertButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement resultText = driver.findElement(By.xpath("//p[@id='result']"));
        Assert.assertTrue(resultText.isDisplayed(), "Result text is NOT displayed.");
        String expectedText = "You successfully clicked an alert";
        String actualText = resultText.getText();
        Assert.assertEquals(actualText, expectedText, "Actual result text is not as expected!!!");

    }


    @Test
    public void iFrames() {
        driver.get("https://practice.cydeo.com/iframe");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']")));
        WebElement yourContentGoesHereText = driver.findElement(By.xpath("//p"));

        Assert.assertTrue(yourContentGoesHereText.isDisplayed());

        driver.switchTo().parentFrame();
        WebElement headerText = driver.findElement(By.xpath("//h3"));
        Assert.assertTrue(headerText.isDisplayed());

    }

    @Test
    public void windows() {
        driver.get("https://practice.cydeo.com/windows");
        String mainHandle = driver.getWindowHandle();
        System.out.println("mainHandle = " + mainHandle);

        String expectedTitle = "Windows";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("Title before click: " + actualTitle);

        WebElement clickHereLink = driver.findElement(By.linkText("Click Here"));
        clickHereLink.click();

        Set<String> allWindowHandles = driver.getWindowHandles();
        for(String each: allWindowHandles){

            driver.switchTo().window(each);
            System.out.println("Current title while switching windows: " + driver.getTitle());

        }

        String expectedTitleAfterClick = "New Window";
        actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitleAfterClick);
        System.out.println("Title after click: " + actualTitle);


    }

    @Test
    public void xx() {
        driver.get("https://practice.cydeo.com/");

    }

    @Test
    public void xxx() {
        driver.get("https://practice.cydeo.com/");

    }

    @Test
    public void xxxx() {
        driver.get("https://practice.cydeo.com/");

    }

    @Test
    public void xxxxx() {
        driver.get("https://practice.cydeo.com/");

    }

    @Test
    public void xx1() {
        driver.get("https://practice.cydeo.com/");

    }

    @Test
    public void xxx1() {
        driver.get("https://practice.cydeo.com/");

    }

    @Test
    public void xxxx1() {
        driver.get("https://practice.cydeo.com/");

    }

    @Test
    public void xxxxx1() {
        driver.get("https://practice.cydeo.com/");

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

