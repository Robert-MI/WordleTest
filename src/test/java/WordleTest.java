import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.Keys;
import java.util.List;

public class WordleTest {
    public static final String baseURL = "https://wordlegame.org/";
    public static final String webdriver = "webdriver.geco.driver";
    public static final String path = "src/drivers/gecodriver";
    WebDriver driver;

    @BeforeSuite
    public void setUp(){
        System.setProperty(webdriver, path);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @org.testng.annotations.Test
    public void statsButtonTest(){
        driver.get(baseURL);
        WebElement statsButton = driver.findElement(By.cssSelector("button.mini_modal_link"));
        statsButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(driver.findElement(By.id("modal_stats")).isDisplayed());
    }

    @org.testng.annotations.Test
    public void keyboardTest(){
        driver.get(baseURL);
        List<WebElement> keyboardKeys = driver.findElements(By.className("Game-keyboard-button"));
        for (WebElement key : keyboardKeys) {
            System.out.println("Text of keyboard key: " + key.getText());
        }
        assert !keyboardKeys.isEmpty();
    }

    @org.testng.annotations.Test
    public void GeneratorTest(){
        driver.get(baseURL);
        WebElement generatorButton = driver.findElement(By.className("generator"));
        generatorButton.click();
        WebElement inputField = driver.findElement(By.className("input"));
        inputField.sendKeys("tests");
        String inputValue = inputField.getAttribute("value");
        Assert.assertEquals(inputValue, "tests");
        inputField.sendKeys(Keys.ENTER);
    }

    @AfterTest
    public void refresh(){
        driver.navigate().refresh();
    }

    @AfterSuite
    public void close(){
        driver.quit();
    }
}