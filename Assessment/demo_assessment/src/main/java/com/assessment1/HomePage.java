package com.assessment1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    
    WebDriver driver;
    String siteURL;

    @FindBy (xpath = "//div/input")
    WebElement searchKey;

    @FindBy (xpath = "//a[contains(text(), 'follow')]")
    WebElement follow;

    @FindBy (css = "div input")
    WebElement searchedName;

    @FindBy (css = "header h4")
    WebElement userName;

    @FindBy (css = ".bio")
    WebElement description;

    @FindBy (css = ".links p:nth-child(1)")
    WebElement company;

    @FindBy (css = ".links p:nth-child(2)")
    WebElement location;

    @FindBy (css = ".links a")
    WebElement site;

    @FindBy (xpath = "//h3[@data-testid]")
    WebElement resultElement;


    public HomePage(WebDriver driver, String siteURL){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.siteURL = "https://gh-users-search.netlify.app/";
    }

    public String getURL(){
        return siteURL;
    }

    public void setURL(String siteURL){
        this.siteURL = siteURL;
    }

    public void typeOnSearchBar(String user){
        searchKey.sendKeys(user);
    }

    public void follow(){
        follow.click();
    }

    public void userDetails(){
        searchedName.getText();
        userName.getText();
        description.getText();
        company.getText();
        location.getText();
        site.getText();
    }
    
    public List<WebElement> getElements(){
        return driver.findElements(By.cssSelector(".sc-dkrFOg"));
    }

    public void pause(long milseconds){
        try {
            Thread.sleep(milseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
