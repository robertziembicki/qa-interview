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
 * @author Robert Ziembicki
 *
 */
public class AddVideoTestScenario {

	private WebDriver webDriver;

	@Parameters({ "browserName", "version", "platform" })
	@BeforeMethod
	public void beforeMethod(String browserName, String version, String platform) throws MalformedURLException {
		DesiredCapabilities desiredCapabilities;
		if (browserName.equals("firefox")) {
			desiredCapabilities = DesiredCapabilities.firefox();
		} else if (browserName.equals("chrome")) {
			desiredCapabilities = DesiredCapabilities.chrome();
		} else {
			desiredCapabilities = DesiredCapabilities.firefox();
		}
		desiredCapabilities.setCapability("platform", platform);
		desiredCapabilities.setCapability("version", version);

		webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
	}

	@Test
	public void addVideoTestScenario() throws InterruptedException {

		webDriver.navigate().to("http://qm-homework.wikia.com");
		assertEquals(webDriver.getCurrentUrl(), "http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia");

		Actions actions = new Actions(webDriver);
		WebElement signInLabel = webDriver.findElement(By.id("AccountNavigation"));
		actions.moveToElement(signInLabel).build().perform();

		Thread.sleep(10000);

		webDriver.findElement(By.id("usernameInput")).sendKeys("robertziembicki");
		webDriver.findElement(By.id("passwordInput")).sendKeys("interview");
		Thread.sleep(4000);
		webDriver.findElement(By.className("login-button")).click();

		Thread.sleep(4000);

		webDriver.findElement(By.className("drop")).click();

		Thread.sleep(4000);

		webDriver.findElement(By.xpath("//*[@id='WikiHeader']/div[1]/nav/ul/li[2]/a")).click();

		Thread.sleep(4000);

		webDriver.findElement(By.id("wpWikiaVideoAddUrl")).sendKeys("http://www.youtube.com/watch?v=h9tRIZyTXTI");
		webDriver.findElement(By.xpath("//*[@id='mw-content-text']/form/div/input")).click();

		Thread.sleep(4000);

		assertEquals(webDriver.findElement(By.xpath("//*[@id='WikiaPage']/div[2]/div/div/div")).getText(),
				"Video page File:The Best Classical Music In The World was successfully added.");

		webDriver.findElement(By.partialLinkText("File:The Best Classical Music In The World")).click();
		assertEquals(webDriver.getCurrentUrl(),
				"http://qm-homework.wikia.com/wiki/File:The_Best_Classical_Music_In_The_World");

		Thread.sleep(10000);
	}

	@AfterMethod
	public void afterMethod() {

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
