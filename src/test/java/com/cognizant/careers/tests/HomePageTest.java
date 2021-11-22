package com.cognizant.careers.tests;

import com.cognizant.careers.HomePage;
import com.cognizant.careers.base.BaseTest;
import com.cognizant.careers.base.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @Test
    public void TC01_VerifyLogoIsDisplayed() {
        clearCookies();
        homePage = new HomePage(driver, log);
        homePage.openPage(properties.getProperty(Constants.URL_HOME_PAGE_COGNIZANT));
        Assert.assertTrue(homePage.LogoDisplayed(), "The element is not present");
    }

    @Test
    public void TC01_VerifyContactUsContainerIsDisplayed() {
        clearCookies();
        homePage = new HomePage(driver, log);
        homePage.openPage(properties.getProperty(Constants.URL_HOME_PAGE_COGNIZANT));
        Assert.assertTrue(homePage.ContactUsCointainerDisplayed(), "The element is not present");
    }

    @Test
    public void TC01_VerifyIndustryExperienceContainerDisplayed() {
        clearCookies();
        homePage = new HomePage(driver, log);
        homePage.openPage(properties.getProperty(Constants.URL_HOME_PAGE_COGNIZANT));
        Assert.assertTrue(homePage.IndustryExperienceContainerDisplayed(), "The element is not present");
    }

}
