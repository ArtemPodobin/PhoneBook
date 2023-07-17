package tests;

import manager.ProviderData;
import model.User;
import org.openqa.selenium.By;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    Logger logger = LoggerFactory.getLogger(AddNewContactTests.class);
    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }
    @Test(dataProvider = "userLogPositiveDto", dataProviderClass = ProviderData.class)
    public void loginPositiveTestBase(User user){
        logger.info("Phone number is " + user.getEmail());
        logger.info("Email is " + user.getPassword());

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test(dataProvider = "userLogWrongPassDto", dataProviderClass = ProviderData.class)
    public void loginNegativeWrongPassword(User user){
        logger.info("Phone number is " + user.getEmail());
        logger.info("Email is " + user.getPassword());
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }


    @Test(dataProvider = "userLogWrongEmailDto", dataProviderClass = ProviderData.class)
    public void loginNegativeWrongEmail(User user){
        logger.info("Phone number is " + user.getEmail());
        logger.info("Email is " + user.getPassword());

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }


}
