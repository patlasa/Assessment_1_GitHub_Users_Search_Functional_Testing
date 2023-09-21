package com.assessment1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRepositoriesPage {
    WebDriver driver;

    @FindBy(css = ".p-name")
    WebElement urpName;

    @FindBy(css = ".p-note")
    WebElement urpDescription;

    @FindBy(css = ".p-org")
    WebElement urpOrg;

    @FindBy(css = ".p-label")
    WebElement urpLoc;

    @FindBy(xpath = "//li[@itemprop='email']/a")
    WebElement urpEmail;

    @FindBy(xpath = "//li[@itemprop='url']/a")
    WebElement urpURL;

    public UserRepositoriesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void udpGetDetails(){
        urpName.getText();
        urpDescription.getText();
        urpEmail.getText();
        urpLoc.getText();
        urpOrg.getText();
        urpURL.getText();
    }


    
}
