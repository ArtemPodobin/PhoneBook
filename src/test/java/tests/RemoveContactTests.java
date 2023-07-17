package tests;


import model.Contact;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(!app.getUser().isLogged()){
            String email = "billabo@gmail.com", password = "$Abcdef12345";
            app.getUser().openLoginForm();
            app.getUser().fillLoginForm(email, password);
            app.getUser().submitLogin();
            app.getHelperContact().openContactForm();
            app.getHelperContact().fillContactForm(Contact.builder()
                           .name("RON")
                           .lastName("Jonson")
                           .email("roton@gmail.com")
                           .phone("0542259547")
                           .address("123 Main St")
                           .description("Seller")
                   .build());
           app.getHelperContact().submitContactForm();
        }
    }

    @Test
    public void removeOneContactPositive(){
        int res = app.getHelperContact().removeOneContact();
        Assert.assertEquals(-1, res);
    }

//    @Test
//    public void removeAllContactsPositive(){
//        app.getHelperContact().removeAllContacts();
//        Assert.assertTrue(app.getHelperContact().isNoContacts());
//    }
    @AfterMethod
    public void postcodition(){
        app.getUser().logout();
    }

}

