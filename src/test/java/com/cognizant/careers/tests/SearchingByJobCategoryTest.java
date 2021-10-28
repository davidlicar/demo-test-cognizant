package com.cognizant.careers.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.cognizant.careers.CareersAtCognizantPage;
import com.cognizant.careers.base.BaseTest;
import com.cognizant.careers.base.TestUtilities;

public class SearchingByJobCategoryTest extends BaseTest {
	
	private TestUtilities util= new TestUtilities();
	
	@Parameters({"category","location","expectedMessage"})
	@Test(priority = 1)
	public void testSearchingByJobCategory(String category,String location, String expectedMessage) {		
		CareersAtCognizantPage searchPage = new CareersAtCognizantPage(driver, log);		
		clearCookies();
		searchPage.openPage();		
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
		
		
	}

}
