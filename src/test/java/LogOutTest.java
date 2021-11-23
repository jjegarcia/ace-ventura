import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogOutTest {
    WebDriver driver;
    Faker faker;
    @Before
    public void setup() {
        TestHelper helper = new TestHelper();
        helper.signUpAndIn();
        driver = helper.driver;
        faker = helper.faker;

    }

    @Test
    public void testLogOutButton(){
        driver.findElement(By.id("logout")).click();
        driver.findElement(By.xpath("//button[contains(text(), 'Log Out')]")).click();
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:8080/login?logout", expectedUrl);

    }

}
