package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TextBoxTests {
    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterAll
    static void tearDown() {
        Selenide.closeWebDriver();
    }


    @Test
    void successfulFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue("Alex Black");
        $("[id=userEmail]").setValue("alex@black.com");
        $("[id=currentAddress]").setValue("first address 1");
        $("[id=permanentAddress]").setValue("second address 2");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Alex Black"));
        $("[id=output] [id=email]").shouldHave(text("alex@black.com"));
        $("[id=output] [id=currentAddress]").shouldHave(text("first address 1"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("second address 2"));
    }

    @Test
    void successfulShortFillFormTest(){
        open("/text-box");
        $("[id=userName]").setValue("Alex Black");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Alex Black"));
    }



    @Test
    void wrongEmailShortFillFormTest(){
        open("/text-box");
        $("[id=userEmail]").setValue("1");
        $("[id=submit]").click();

        $("#userEmail").shouldHave(cssClass("field-error"));
    }
}
