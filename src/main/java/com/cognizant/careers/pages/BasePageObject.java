package com.cognizant.careers.pages;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {

	protected WebDriver driver;
	protected Logger log;

	public BasePageObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	/** Open page with given URL */
	protected void openUrl(String url) {
		driver.get(url);
	}

	/** Find element using given locator */
	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}
	
	/** Find list of elements using given locator */
	protected List<WebElement> finds(By locator) {
		return driver.findElements(locator);
	}	

	/** Click on element with given locator when its visible */
	protected void click(By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).click();
	}

	/** Type given text into element with given locator */
	protected void type(String text, By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).sendKeys(text);
	}

	/** Get URL of current page from browser */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Wait for specific ExpectedCondition for the given amount of time in seconds
	 */
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/**
	 * Wait for given number of seconds for element with given locator to be visible
	 * on the page
	 */
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
				log.error(e.getMessage(),e);
			}
			attempts++;
		}
	}

	/** Open Page with it's url */
	public void openPage(String url) {
		log.info("Opening page: " + url);
		openUrl(url);
		log.info("Page opened!");
	}

	public void scrollToBottom() {
		log.info("Scrolling to the bottom of the page");
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}


}
