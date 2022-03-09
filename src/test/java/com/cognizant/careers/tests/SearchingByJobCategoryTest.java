package com.cognizant.careers.tests;

import com.cognizant.careers.base.Constants;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.cognizant.careers.CareersAtCognizantPage;
import com.cognizant.careers.base.BaseTest;
import com.cognizant.careers.base.TestUtilities;

public class SearchingByJobCategoryTest extends BaseTest {
	
	private TestUtilities util= new TestUtilities();

	private CareersAtCognizantPage searchPage;

	@Parameters({"category","location","expectedMessage"})
	@Test(priority = 1)
	public void testSearchingByJobCategory(String category,String location, String expectedMessage) {		
		searchPage = new CareersAtCognizantPage(driver, log);
		clearCookies();
		searchPage.openPage(properties.getProperty(Constants.URL_CAREERS_AT_COGNIZANT));
		log.info("testSearchingByJobAndLocationCategory: "+location+"-"+category);
		log.info("Parameters category: "+category+ ", location: "+location+", expectedMessage: "+expectedMessage);
		
		String currentMessage=searchPage.getSearchedJobsMessage();

		Assert.assertTrue(currentMessage.contains(expectedMessage), "Message doesn't contain expected text.");
		
		searchPage.searchByCategoryAndLocation(category,location);
		
		util.sleep(5000);		
		
		log.info("Searching was made");
		
		int result=searchPage.getNumberListJobs();
		log.info("number of jobs: "+result);				

		Assert.assertTrue(result > 0, "Not Found any result");
		
		searchPage.clickOnFirstSearchedJob();

		searchPage.scrollBottom();

	}

	@BeforeTest()
	public void beforeTest() {
		System.out.println("Before Test");
	}

	@AfterTest()
	public void afterTest() {
		System.out.println("After Test");
	}

}
