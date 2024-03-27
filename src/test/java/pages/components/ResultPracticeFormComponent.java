package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultPracticeFormComponent {
    private final SelenideElement tableRowToCheck = $(".table-responsive");

    public void checkPracticeFormResult(String key, String value) {
        tableRowToCheck.$(byText(key)).parent()
                .shouldHave(text(value));
    }
}
