package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    void shouldLoginSuccessfullyWithValidCredentials() {
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    void loginWithWrongPassword() {
        loginPage.login("standard_user", "wrong_password");

        String error = driver.findElement(By.cssSelector("h3")).getText();

        assertTrue(error.contains("Username and password do not match"));
    }

    @Test
    void loginWithEmptyFields() {
        loginPage.login("", "");

        String error = driver.findElement(By.cssSelector("h3")).getText();

        assertTrue(error.contains("Username is required"));
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}