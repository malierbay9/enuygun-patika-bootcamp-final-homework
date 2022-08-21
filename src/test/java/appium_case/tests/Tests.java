package appium_case.tests;


import appium_case.utils.DP;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Tests extends BaseTest {

    @Test(priority = 0,description = "Checks the opened app's title for valdiation.")
    public void checksIfAppIsCorrect() {

        assertEquals(signInPage.getTitle(), "Appium Patika Tutorial");

    }

    @Test(priority = 1,description = "Checks if there is any error when user tries to login without mail.")
    public void checkErrorWithEmptyMail() {
        password = "123456";
        signInPage.setPassword(password)
                .signIn();

        assertEquals(signInPage.getErrorText(), "Email boş olamaz");
    }

    @Test(priority = 2,description = "Checks if there is any error when user tries to login without password.")
    public void checkErrorWithEmptyPassword() {
        mail = "testtest51@gmail.com";
        signInPage.setUserName(mail)
                .signIn();

        assertEquals(signInPage.getErrorText(), "Password cant be empty");

    }

    @Test(priority = 3,description = "Checks if there is any error when user tries to login with badly formatted email.")
    public void checkErrorWithBadFormattedMail() {
        mail = "noatatall";
        password = "123456";

        signInPage.setUserName(mail)
                .setPassword(password)
                .signIn();

        assertEquals(signInPage.getErrorText(), "The email address is badly formatted.");

    }

    @Test(priority = 4,description = "Checks if there is any error when user tries to login with non-registered account.")
    public void checkErrorWithNonRegisteredAccount() {
        mail = "wrongmailksbkgsx@gmail.com";
        password = "123456";

        signInPage.setUserName(mail)
                .setPassword(password)
                .signIn();

        assertEquals(signInPage.getErrorText(), "There is no user record corresponding to this identifier. The user may have been deleted.");

    }

    @Test(priority = 5,description = "Checks if there is any error when user tries to login with wrong password.")
    public void checkErrorWithWrongPassword() {
        mail = "testtest51@gmail.com";
        password = "wrongpass";

        signInPage.setUserName(mail)
                .setPassword(password)
                .signIn();

        assertEquals(signInPage.getErrorText(), "The password is invalid or the user does not have a password.");

    }

    @Test(priority = 6, dataProvider = "userData", dataProviderClass = DP.class,description = "Tests successfull login.")
    public void testSuccessfulLogin(String userName, String password) {

        signInPage.setUserName(userName)
                .setPassword(password)
                .signIn();

        assertTrue(welcomePage.isWelcomeTitleDisplayed());

    }

    @Test(priority = 7, dataProvider = "userData", dataProviderClass = DP.class,description = "Tests registering successfull customer with phone number.")
    public void checkIfCustomerAddedSuccessfully(String userName, String password) {

        signInPage.setUserName(userName)
                .setPassword(password)
                .signIn();

        welcomePage.addNewCustomer();

        addCustomerPage.setNumber(phoneNumber)
                .setName(name)
                .setAdress(address)
                .setDate(receiveDate)
                .saveCustomer();

        welcomePage.searchCustomer();
        assertTrue(customersPage.checkCustomerExist(phoneNumber));

    }

    @Test(priority = 8, dataProvider = "userData", dataProviderClass = DP.class,description = "Verifies registered customer name from customer info.")
    public void verifyCustomerNameRegisteredCorrectly(String userName, String password) {

        signInPage.setUserName(userName)
                .setPassword(password)
                .signIn();

        welcomePage.searchCustomer();

        customersPage.findCustomer(phoneNumber).isCustomerInfoContains(name);

    }



}
