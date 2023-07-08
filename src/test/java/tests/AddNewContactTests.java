package tests;

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

@Test(invocationCount = 5)
    public void addNewContactPositive(){
    int i = (int)(System.currentTimeMillis()/1000)%3600;
    Contact contact = Contact.builder()
            .name("John_" + i)
            .lastName("Snow")
            .phone("01234578" + i)
            .email("john_" + i + "@mail.com")
            .address("Rehovot")
            .description("Best friend")
            .build();

    logger.info("Phone number is " + contact.getPhone());
    app.getHelperContact().openContactForm();
    app.getHelperContact().fillContactForm(contact);
    app.getHelperContact().submitContactForm();
    app.getHelperContact().pause(3000);
    Assert.assertTrue(app.getHelperContact().isContactCreated(contact));
}
    @Test
    public void addNewContactNegativeWrongPhone(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Contact contact = Contact.builder()
                .name("John_" + i)
                .lastName("Snow")
                .phone("0157")
                .email("john_" + i + "@mail.com")
                .address("Rehovot")
                .description("Best friend")
                .build();

        logger.info("Phone number is " + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();
        app.getHelperContact().pause(3000);
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
    @Test
    public void addNewContactNegativeWrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Contact contact = Contact.builder()
                .name("John_" + i)
                .lastName("Snow")
                .phone("0123457" + i)
                .email("john_" + i + "mail.com")
                .address("Rehovot")
                .description("Best friend")
                .build();

        logger.info("Phone number is " + contact.getPhone());
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
