package tests;

import manager.ProviderData;
import manager.TestNgListener;
import model.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)
public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }
    @Test(dataProvider = "userRegPositiveDto", dataProviderClass = ProviderData.class)
    public void registrationPositive(User user){
        logger.info("Phone number is " + user.getEmail());
        logger.info("Email is " + user.getPassword());

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test(dataProvider = "userRegWrongEmailDto", dataProviderClass = ProviderData.class)
    public void registrationNegativeWrongEmail(User user){
        logger.info("Phone number is " + user.getEmail());
        logger.info("Email is " + user.getPassword());

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
    @Test(dataProvider = "userRegWrongPasswordDto", dataProviderClass = ProviderData.class)
    public void registrationNegativeWrongPassword(User user){

        logger.info("Phone number is " + user.getEmail());
        logger.info("Email is " + user.getPassword());

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}
