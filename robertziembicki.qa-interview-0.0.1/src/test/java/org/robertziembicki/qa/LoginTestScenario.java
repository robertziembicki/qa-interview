package org.robertziembicki.qa;

import static org.testng.AssertJUnit.assertTrue;
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
 * 
 * @author Robert Ziembicki
 *
 */
public class LoginTestScenario {

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
	public void loginTestScenarioCorrectCredentials() throws InterruptedException {
		
		webDriver.navigate().to("http://qm-homework.wikia.com");
		assertEquals(webDriver.getCurrentUrl(),"http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia");
		
		Thread.sleep(5000);
		
		Actions actions = new Actions(webDriver);
		WebElement signInLabel = webDriver.findElement(By.id("AccountNavigation"));
		actions.moveToElement(signInLabel).build().perform();
		
		Thread.sleep(10000);
		
		webDriver.findElement(By.id("usernameInput")).sendKeys("robertziembicki");
	    webDriver.findElement(By.id("passwordInput")).sendKeys("interview");  
	    Thread.sleep(4000);
	    webDriver.findElement(By.className("login-button")).click();
	    
	    Thread.sleep(10000);
	    assertFalse(webDriver.getCurrentUrl().endsWith("Special:UserLogin"));
	}
	
	@Test
	public void loginTestScenarioIncorrectCredentials() throws InterruptedException {

		webDriver.navigate().to("http://qm-homework.wikia.com");
		assertEquals(webDriver.getCurrentUrl(),"http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia");
		
		Thread.sleep(5000);
		
		Actions actions = new Actions(webDriver);
		WebElement signInLabel = webDriver.findElement(By.id("AccountNavigation"));
		actions.moveToElement(signInLabel).build().perform();
		
		Thread.sleep(10000);
		
		webDriver.findElement(By.id("usernameInput")).sendKeys("robertziembicki");
	    webDriver.findElement(By.id("passwordInput")).sendKeys("wrongPassword");     
	    webDriver.findElement(By.className("login-button")).click();
	    
	    Thread.sleep(10000);
	    /*assertEquals(webDriver.findElement(By.name("username")).getAttribute("value"),"robertziembicki".toString());
	    assertEquals(webDriver.findElement(By.name("password")).getAttribute("value"),"wrongPassword".toString());
	    */
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
