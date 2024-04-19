package properties;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.stream.StreamSupport;

import static java.lang.String.format;

public class SystemPropertiesTests {
    /*@Test
    void systemProperties3Test() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser", "mozilla");

        System.out.println(browser); // chrome
    }*/

    @Test
    @Tag("property")
    void systemPropertiesTest() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser", "mozilla");

        System.out.println(browser); // chrome

        // gradle property_test
        // mozilla

        // gradle property_test -Dbrowser=opera
        // opera
    }
}
