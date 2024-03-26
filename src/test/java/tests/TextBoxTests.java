package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests extends TestBase {
    private final String userName = "Evgeniia",
            email = "evlv@mail.ru",
            currentAddress = "SPb, Nevskyi street 123/4/5",
            permanentAddress = "Somewhere in Puerto Vallarta";

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void successfulFillFormTest() {
        textBoxPage.openPage().
                setUserName(userName).
                setUserEmail(email).
                setCurrentAddress(currentAddress).
                setPermanentAddress(permanentAddress).
                clickSubmit();

        textBoxPage.checkResult("name", userName).
                checkResult("email", email).
                checkResult("currentAddress", currentAddress).
                checkResult("permanentAddress", permanentAddress);
    }
}