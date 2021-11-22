package com.cognizant.careers.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class BaseTest {

	protected WebDriver driver;
	protected Logger log;
	protected static Properties properties;

	private static final String fileProperties="config.properties";

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);
		BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
		driver = factory.createDriver();
		initialProperties();
		
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

	@AfterSuite(alwaysRun = true)
	public void tearDownMain() {
		long id = Thread.currentThread().getId();
		log.info("After test-class. Thread id is: " + id);
	}

	private void initialProperties() {
		try {
			properties = new Properties();
			properties.load(getFileAsIOStream(fileProperties));
			log.info("File properties loaded: "+fileProperties);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
	}

	private InputStream getFileAsIOStream(final String fileName)
	{
		InputStream ioStream = this.getClass()
				.getClassLoader()
				.getResourceAsStream(fileName);

		if (ioStream == null) {
			throw new IllegalArgumentException(fileName + " is not found");
		}
		return ioStream;
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
