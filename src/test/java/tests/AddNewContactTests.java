package tests;

import manager.ProviderData;
import model.Contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

    Logger logger = LoggerFactory.getLogger(AddNewContactTests.class);

@BeforeMethod
    public void precondition(){
    if(!app.getUser().isLogged()){
        String email = "billabo@gmail.com", password = "$Abcdef12345";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitLogin();
    }
}

@Test(dataProvider = "addContactPositiveDto", dataProviderClass = ProviderData.class)
    public void addNewContactPositive(Contact contact){

    logger.info("Phone number is " + contact.getPhone());
    logger.info("Email is " + contact.getEmail());
    app.getHelperContact().openContactForm();
    app.getHelperContact().fillContactForm(contact);
    app.getHelperContact().submitContactForm();
    app.getHelperContact().pause(3000);
    Assert.assertTrue(app.getHelperContact().isContactCreated(contact));
}
    @Test(dataProvider = "addContactWrongPhoneDto", dataProviderClass = ProviderData.class)
    public void addNewContactNegativeWrongPhone(Contact contact){

        logger.info("Phone number is " + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();
        app.getHelperContact().pause(3000);
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
    @Test(dataProvider = "addContactWrongEmailDto", dataProviderClass = ProviderData.class)
    public void addNewContactNegativeWrongEmail(Contact contact){

        logger.info("Email is " + contact.getEmail());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();
        app.getHelperContact().pause(3000);
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }


@AfterMethod
    public void postcodition(){
    app.getUser().logout();
}

}
