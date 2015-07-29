package org.robertziembicki.qa;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.AssertJUnit.assertEquals;

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
 * 
 * Selenium test for test scenario 2 - Add video file to user account on
 * wikia.com portal.
 * 
 * @author Robert Ziembicki
 *
 */
public class AddVideoTestScenario {

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
	public void addVideoTestScenario() throws InterruptedException {

		/*
		 * User navigates to http://qm-homework.wikia.com web page.
		 */
		webDriver.navigate().to("http://qm-homework.wikia.com");
		assertEquals(webDriver.getCurrentUrl(), "http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia");

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
		Thread.sleep(4000);

		/*
		 * Click "Contribute" dropdown button. List of available options is
		 * displayed.
		 */
		webDriver.findElement(By.className("drop")).click();
		Thread.sleep(4000);

		/*
		 * Click "Add a video" option from the dropdown list. User is redirected
		 * to the WikiaVideoAdd page
		 * http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd.
		 */
		webDriver.findElement(By.xpath("//*[@id='WikiHeader']/div[1]/nav/ul/li[2]/a")).click();
		Thread.sleep(4000);

		/*
		 * Add the following youtube link to the "Video URL" field:
		 * "http://www.youtube.com/watch?v=h9tRIZyTXTI".
		 */
		webDriver.findElement(By.id("wpWikiaVideoAddUrl")).sendKeys("http://www.youtube.com/watch?v=h9tRIZyTXTI");
		
		/*
		 * Click "Add" button below "Video URL" field.
		 */
		webDriver.findElement(By.xpath("//*[@id='mw-content-text']/form/div/input")).click();
		Thread.sleep(4000);

		assertEquals(webDriver.findElement(By.xpath("//*[@id='WikiaPage']/div[2]/div/div/div")).getText(),
				"Video page File:The Best Classical Music In The World was successfully added.");

		/*
		 * Click on the link to the file on the flash message.
		 */
		webDriver.findElement(By.partialLinkText("File:The Best Classical Music In The World")).click();
		assertEquals(webDriver.getCurrentUrl(),
				"http://qm-homework.wikia.com/wiki/File:The_Best_Classical_Music_In_The_World");

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
