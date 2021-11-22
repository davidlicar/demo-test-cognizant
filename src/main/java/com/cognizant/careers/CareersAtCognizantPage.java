package com.cognizant.careers;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cognizant.careers.base.TestUtilities;
import com.cognizant.careers.pages.BasePageObject;

public class CareersAtCognizantPage extends BasePageObject{



	private TestUtilities util= new TestUtilities();
	
	/*
	private By usernameLocator = By.id("username");
	private By passwordLocator = By.name("password");
	private By logInButtonLocator = By.tagName("button");
	private By errorMessageLocator = By.id("flash");*/
	
	private By searchedJobsMessage = By.xpath("/html/body/div[2]/div[2]/div/div/section/div/h4/ppc-content");
	private By jobCategoryText = By.name("phsKeywords");
	private By jobLocationText = By.id("locationInput");
	private By searchButton = By.cssSelector(".au-target.btn.primary-button.btn-lg.phs-search-submit");
	private By fisrtLinkJob = By.xpath("(//a[@ref='linkEle' and @ph-tevent='job_click'])[1]");
	private By listLinkJobs = By.xpath("//a[@ref='linkEle' and @ph-tevent='job_click']");
	
	
	public CareersAtCognizantPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	public void searchByCategoryAndLocation(String category,String location) {
		type(category, jobCategoryText);
		type(location, jobLocationText);
		util.sleep(5000);
		click(searchButton);
	}
	
	public void clickOnFirstSearchedJob() {
		click(fisrtLinkJob);
	}
	
	public int getNumberListJobs() {
		return finds(listLinkJobs).size();
	}
	
	public String getSearchedJobsMessage() {
		return find(searchedJobsMessage).getText();
	}

}
