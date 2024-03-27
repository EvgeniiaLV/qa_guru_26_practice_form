package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultTextBoxComponent {
    public void checkTextBoxResult(String key, String value) {
        $("#output #" + key).shouldHave(text(value));
    }
}
