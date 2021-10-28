package com.cognizant.careers.base;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;
	protected Logger log;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);
		
		BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
		driver = factory.createDriver();
		
		// This sleep here is for instructor only. Students don't need this here
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		log.info("Close driver");
		// Close browser
		driver.quit();
	}

	public void clearCookies() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			wait.until((WebDriver) -> {
				Set<Cookie> cookies = driver.manage().getCookies();
				if (!cookies.isEmpty()) {
					driver.manage().deleteAllCookies();
					log.info("Browser Cookies deleted successfully");
					return false;
				}
				return true;
			});

		} catch (TimeoutException ignored) {
			log.error("Timeout exception while deleting the browser cookies");
		} catch (WebDriverException ignored) {
			log.error("Webdriver exception while deleting the browser cookies");
		}
	}
}
