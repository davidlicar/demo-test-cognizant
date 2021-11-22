package com.cognizant.careers;

import com.cognizant.careers.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePageObject {

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By logo = By.cssSelector(".cog-logo");
    private By careers = By.xpath("//a[contains(@title,'Careers')]");
    private By contactUsCointainer = By.cssSelector("#contact-us");
    private By featureWorkContainer = By.xpath("//div[contains(@data-attribute,'featuredWork')]");
    private By industryExperienceContainer = By.xpath("//div[contains(@data-attribute,'industryExperience')]");

    public void ClickCareersOption() {
        click(careers);
    }

    public Boolean LogoDisplayed() {
        waitForVisibilityOf(featureWorkContainer, 5);
        return find(logo).isDisplayed();
    }

    public Boolean ContactUsCointainerDisplayed() {
        waitForVisibilityOf(contactUsCointainer, 5);
        return find(logo).isDisplayed();
    }

    public Boolean IndustryExperienceContainerDisplayed() {
        waitForVisibilityOf(industryExperienceContainer, 5);
        return find(logo).isDisplayed();
    }
}
