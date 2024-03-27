package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultPracticeFormComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            mobileNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbyInput = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("div#state input"),
            cityInput = $("div#city input"),
            submitButton = $("#submit"),
            modalWindowSubmit = $(".modal-content");


    CalendarComponent calendarComponent = new CalendarComponent();
    ResultPracticeFormComponent resultPracticeFormComponent = new ResultPracticeFormComponent();

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Practice Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public PracticeFormPage setGender(String value) {
        genderInput.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage setMobileNumber(String value) {
        mobileNumberInput.setValue(value);

        return this;
    }

    public PracticeFormPage setDateOfBirth(String year, String month, String day) {
        calendarInput.click();
        calendarComponent.setDate(year, month, day);

        return this;
    }

    public PracticeFormPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();

        return this;
    }

    public PracticeFormPage setHobby(String value) {
        hobbyInput.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage uploadPicture(String value) {
        pictureUpload.uploadFromClasspath(value);

        return this;
    }

    public PracticeFormPage setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public PracticeFormPage setState(String value) {
        stateInput.setValue(value).pressEnter();

        return this;
    }

    public PracticeFormPage setCity(String value) {
        cityInput.setValue(value).pressEnter();

        return this;
    }

    public PracticeFormPage clickSubmit() {
        submitButton.click();

        return this;
    }

    public PracticeFormPage checkResult(String key, String value) {
        resultPracticeFormComponent.checkPracticeFormResult(key, value);

        return this;
    }

    public PracticeFormPage checkEmptyMobileNumber() {
        modalWindowSubmit.shouldNot(exist);
        mobileNumberInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        mobileNumberInput.shouldBe(empty);

        return this;
    }

}
