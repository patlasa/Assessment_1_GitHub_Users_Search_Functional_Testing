package com.assessment1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GitHubUsersSearchTest {
    WebDriver driver;
    HomePage varObj;
    UserDetailsPage udp;
    UserRepositoriesPage urp;

    @BeforeTest
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "This test validates the url link")
    public void HomePage(){
        varObj = new HomePage(driver, null);
        varObj.driver.get(varObj.siteURL);
        Assert.assertEquals(driver.getTitle(), "Github User - Starter");
        varObj.pause(2000);
    }


    @Test(description = "Test case to verify if the number of request is available")
    public void noOfRequest_click(){

        String resultText = varObj.resultElement.getText();
        String[] textArray = resultText.split("[\\s/]+");
        int middleIndex = textArray.length / 2;

            if (middleIndex <= 0) {
                System.out.println("Number of request has been depleted please try again later");
                driver.quit();
            } 
    }

    @Test(description = "Test case to verify that an error message is displayed when searching for a non-existing user", dependsOnMethods = "noOfRequest_click")
    public void Non_ExistingUser(){
        WebElement inv_user =  driver.findElement(By.xpath("//div/input"));
        inv_user.sendKeys("invalid user not valid" + Keys.ENTER);

        varObj.pause(2000);

        if(varObj.userName.isDisplayed()){
            System.out.println("Search not proceed due to Github User Name is non-existing user. Please try again with Github existing user");
            varObj.pause(2000);
            inv_user.clear();

            varObj.pause(2000);
        } 
    }


    @Test(description = "This test validates the UserDetails functionality on HomePage", dataProvider = "Details", dependsOnMethods = "Non_ExistingUser")
    public void UserDetailsPage_HomePage(WebElement userDetails){

        varObj.typeOnSearchBar("Giko" + Keys.ENTER);
        varObj.pause(3000);

        WebElement searchedName = varObj.searchedName;
        WebElement userName = varObj.userName;
        WebElement description = varObj.description;
        WebElement company = varObj.company;
        WebElement location = varObj.location;
        WebElement site = varObj.site;

        ArrayList<WebElement> list = new ArrayList<>();

        list.add(userDetails);

        for (WebElement userE: list){
            Assert.assertTrue(userE.getText().contains(searchedName.getText()));
            Assert.assertTrue(userE.getText().contains(userName.getText()));
            Assert.assertTrue(userE.getText().contains(description.getText()));
            Assert.assertTrue(userE.getText().contains(company.getText()));
            Assert.assertTrue(userE.getText().contains(location.getText()));
            Assert.assertTrue(userE.getText().contains(site.getText()));
        }

        varObj.pause(2000);
    }

    

    @Test(description = "This test validates the UserDetails functionality on GitHub page", dependsOnMethods = "UserDetailsPage_HomePage")
    public void UserDetailsPage_Github(){
        udp = new UserDetailsPage(driver);

        WebElement userName = varObj.userName;
        WebElement description = varObj.description;
        WebElement company = varObj.company;
        WebElement location = varObj.location;
        WebElement site = varObj.site;

        String captureduserName = userName.getText();
        String captureddescription = description.getText();
        String capturedcompany = company.getText();
        String capturedlocation = location.getText();
        String capturedsite = site.getText();

        varObj.follow();
        varObj.pause(2000);

        Assert.assertEquals(driver.getTitle(), "giko (Nikita Chudakov) · GitHub");
        
        WebElement name = udp.udpName;
        WebElement udpDescription = udp.udpDescription;
        WebElement udpLoc = udp.udpLoc;
        WebElement udpOrg = udp.udpOrg;
        WebElement udpURL = udp.udpURL;
        
        String actualName = name.getText();
        String actualDescription = udpDescription.getText();
        String actualLoc = udpLoc.getText();
        String actualOrg = udpOrg.getText();
        String actualURL = udpURL.getText();

        Assert.assertEquals(actualName, captureduserName);
        Assert.assertEquals(actualDescription, captureddescription);
        Assert.assertEquals(actualLoc, capturedlocation);
        Assert.assertEquals(actualOrg, capturedcompany);
        Assert.assertEquals(actualURL, capturedsite);

        varObj.pause(2000);
    }

    
    @DataProvider (name = "Details")
    public Object[] Details(){
        List<WebElement> userDetails = varObj.getElements();
        Object[] data = new Object[userDetails.size()];
        
        for (int i = 0; i < userDetails.size(); i++) {
            data[i] = userDetails.get(i);
        }
        return data;
    }

    @Test(description = "This test validates the UserRepositories functionality on GitHub page", dependsOnMethods = "UserDetailsPage_HomePage")
    public void UserRepositoriesPage(){
        urp = new UserRepositoriesPage(driver);

        varObj.pause(3000);

        WebElement udpname = udp.udpName;
        WebElement udpDescription = udp.udpDescription;
        WebElement udpLoc = udp.udpLoc;
        WebElement udpOrg = udp.udpOrg;
        WebElement udpURL = udp.udpURL;
        
        String captureduserName = udpname.getText();
        String captureddescription = udpDescription.getText();
        String capturedlocation = udpLoc.getText();
        String capturedcompany = udpOrg.getText();
        String capturedsite = udpURL.getText();


        udp.geturpPage();
        varObj.pause(3000);

        Assert.assertEquals(driver.getTitle(), "giko (giko) / Repositories · GitHub");

        WebElement urpname = urp.urpName;
        WebElement urpDescription = urp.urpDescription;
        WebElement urpLoc = urp.urpLoc;
        WebElement urpOrg = urp.urpOrg;
        WebElement urpURL = urp.urpURL;
        
        String actualName = urpname.getText();
        String actualDescription = urpDescription.getText();
        String actualLoc = urpLoc.getText();
        String actualOrg = urpOrg.getText();
        String actualURL = urpURL.getText();

        Assert.assertEquals(actualName, captureduserName);
        Assert.assertEquals(actualDescription, captureddescription);
        Assert.assertEquals(actualLoc, capturedlocation);
        Assert.assertEquals(actualOrg, capturedcompany);
        Assert.assertEquals(actualURL, capturedsite);

        varObj.pause(2000);
    }


    @AfterTest
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
