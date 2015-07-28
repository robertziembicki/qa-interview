package org.robertziembicki.qa;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;

/**
 * Selenium test for test scenario 1 - Login in to wikia.com portal.
 * 
 * @author Robert Ziembicki
 *
 */
public class LoginTestScenario {

	/*
	 * WebDriver object used in test.
	 */
	private WebDriver webDriver;

	@Parameters({ "browserName", "version", "platform" })
	@BeforeMethod
	public void beforeMethod(String browserName, String version, String platform) throws MalformedURLException {

		/*
		 * DesiredCapabilities for FireFox or Chrome browser.
		 */
		DesiredCapabilities desiredCapabilities;

		/*
		 * DesiredCapabilities values set for chosen browser (chrome or
		 * firefox).
		 */
		if (browserName.equals("firefox")) {
			desiredCapabilities = DesiredCapabilities.firefox();
		} else if (browserName.equals("chrome")) {
			desiredCapabilities = DesiredCapabilities.chrome();
		} else {
			desiredCapabilities = DesiredCapabilities.firefox();
		}
		/*
		 * Platform parameter value set for the browser.
		 */
		desiredCapabilities.setCapability("platform", platform);
		/*
		 * Version of the browser set.
		 */
		desiredCapabilities.setCapability("version", version);

		webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
	}

	@Test
	public void loginTestScenarioCorrectCredentials() throws InterruptedException {

		/*
		 * User navigates to http://qm-homework.wikia.com web page.
		 */
		webDriver.navigate().to("http://qm-homework.wikia.com");
		assertEquals(webDriver.getCurrentUrl(), "http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia");

		Thread.sleep(5000);

		/*
		 * Mouse hovers over "Sign In" label (global navigation toolbar). Login
		 * panel appears in the user screen.
		 */
		Actions actions = new Actions(webDriver);
		WebElement signInLabel = webDriver.findElement(By.id("AccountNavigation"));
		actions.moveToElement(signInLabel).build().perform();

		Thread.sleep(10000);

		/*
		 * "Username" and "Password" fields are populated with correct login
		 * credentials.
		 * 
		 * Login credentials: username = robertziembicki; password = interview.
		 */
		webDriver.findElement(By.id("usernameInput")).sendKeys("robertziembicki");
		webDriver.findElement(By.id("passwordInput")).sendKeys("interview");
		Thread.sleep(4000);

		/*
		 * "Log In" button is clicked to start login process to wikia.com portal
		 * using provided credentials.
		 */
		webDriver.findElement(By.className("login-button")).click();

		Thread.sleep(10000);
		assertFalse(webDriver.getCurrentUrl().endsWith("Special:UserLogin"));
	}

	@Test
	public void loginTestScenarioIncorrectCredentials() throws InterruptedException {

		/*
		 * User navigates to http://qm-homework.wikia.com web page.
		 */
		webDriver.navigate().to("http://qm-homework.wikia.com");
		assertEquals(webDriver.getCurrentUrl(), "http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia");

		Thread.sleep(5000);

		/*
		 * Mouse hovers over "Sign In" label (global navigation toolbar). Login
		 * panel appears in the user screen.
		 */
		Actions actions = new Actions(webDriver);
		WebElement signInLabel = webDriver.findElement(By.id("AccountNavigation"));
		actions.moveToElement(signInLabel).build().perform();

		Thread.sleep(10000);

		/*
		 * "Username" and "Password" fields are populated with incorrect login
		 * credentials.
		 * 
		 * Login credentials: username = robertziembicki; password =
		 * wrongPassword.
		 */
		webDriver.findElement(By.id("usernameInput")).sendKeys("robertziembicki");
		webDriver.findElement(By.id("passwordInput")).sendKeys("wrongPassword");
		Thread.sleep(4000);

		/*
		 * "Log In" button is clicked to start login process to wikia.com portal
		 * using incorrect login credentials.
		 */
		webDriver.findElement(By.className("login-button")).click();

		Thread.sleep(10000);

	}

	@AfterMethod
	public void afterMethod() {

		/*
		 * Closing selenium webDriver object.
		 */
		try {
			webDriver.close();
		} catch (Exception exception) {
			Logger.getLogger("Exception: " + exception);
		}
		try {
			webDriver.quit();
		} catch (Exception exception) {
			Logger.getLogger("Exception: " + exception);
		}
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
