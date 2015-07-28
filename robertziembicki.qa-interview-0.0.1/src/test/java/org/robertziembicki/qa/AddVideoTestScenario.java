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

	@Parameters({"browserName","version","platform"})
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
		assertEquals(webDriver.getCurrentUrl(),"http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia");
		
		Thread.sleep(5000);
		
		webDriver.findElement(By.className("wikia-menu-button contribute secondary combined")).click();
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
