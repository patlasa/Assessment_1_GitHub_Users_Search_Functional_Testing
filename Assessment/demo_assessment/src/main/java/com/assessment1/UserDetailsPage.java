package com.assessment1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDetailsPage{

    WebDriver driver;

    @FindBy(css = ".p-name")
    WebElement udpName;

    @FindBy(css = ".p-note")
    WebElement udpDescription;

    @FindBy(css = ".p-org")
    WebElement udpOrg;

    @FindBy(css = ".p-label")
    WebElement udpLoc;

    @FindBy(xpath = "//li[@itemprop='email']/a")
    WebElement udpEmail;

    @FindBy(xpath = "//li[@itemprop='url']/a")
    WebElement udpURL;

    @FindBy(css = "main > div.mt-4 a:nth-of-type(2)")
    WebElement udpPage;

    public void geturpPage(){
        udpPage.click();
    }

    public UserDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void udpGetDetails(){
        udpName.getText();
        udpDescription.getText();
        udpEmail.getText();
        udpLoc.getText();
        udpOrg.getText();
        udpURL.getText();
    }

}
