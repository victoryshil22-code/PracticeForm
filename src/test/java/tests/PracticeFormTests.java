package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

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
       open("/automation-practice-form");
        $("[id=firstName]").setValue("Alex");
        $("[id=lastName]").setValue("Black");
        $("[id=userEmail]").setValue("alex@black.com");
        $("[id=userNumber]").setValue("1234567890");
        $("#genterWrapper").$(byText("Male")).click();
        $("[id=dateOfBirthInput]").setValue("08 Jul 2026").pressEnter();
        $("[id=subjectsInput]").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture")
                .uploadFromClasspath(("smailiki.png"));
        $("[id=currentAddress]").setValue("first address");
        $("[id=react-select-3-input]").setValue("NCR").pressEnter();
        $("[id=react-select-4-input]").setValue("Delhi").pressEnter();
        $("[id=submit]").click();
        $("[id=example-modal-sizes-title-lg]")
                .shouldHave(text("Thanks for submitting the form"));
        $("[id=closeLargeModal]").click();

        $("[id=example-modal-sizes-title-lg]")
                .shouldBe(visible);
        $("table").shouldHave(text("Alex Black"));
        $("table").shouldHave(text("alex@black.com"));
        $("table").shouldHave(text("Male"));
        $("table").shouldHave(text("1234567890"));
        $("table").shouldHave(text("08 July,2026"));
        $("table").shouldHave(text("Maths"));
        $("table").shouldHave(text("Sports"));
        $("table").shouldHave(text("smailiki.png"));
        $("table").shouldHave(text("first address"));
        $("table").shouldHave(text("NCR Delhi"));
    }

    @Test
    public void successfulSubmitFormWithRequiredFieldsTest(){
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Alex");
        $("[id=lastName]").setValue("Black");
        $("#genterWrapper").$(byText("Male")).click();
        $("[id=userNumber]").setValue("1234567890");
        $("[id=submit]").scrollIntoView(true).click();

        $("[id=example-modal-sizes-title-lg]")
                .shouldBe(visible);
        $("table").shouldHave(text("Alex"));
        $("table").shouldHave(text("Black"));
        $("table").shouldHave(text("Male"));
        $("table").shouldHave(text("1234567890"));
    }

    @Test
    public  void emptyRegistrationForm(){
        open("/automation-practice-form");
        $("[id=submit]").scrollIntoView(true).click();

        $("[id=example-modal-sizes-title-lg]")
                .shouldNotBe(visible);
    }

    @Test
    public void shouldNotSubmitFormWithInvalidEmail(){
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Alex");
        $("[id=lastName]").setValue("Black");
        $("#genterWrapper").$(byText("Male")).click();
        $("[id=userEmail]").setValue("alexblack.com");
        $("[id=userNumber]").setValue("1234567890");
        $("[id=submit]").scrollIntoView(true).click();

        $("[id=example-modal-sizes-title-lg]")
                .shouldNotBe(visible);
    }

    @Test
    public void shouldNotSubmitFormWithShortUserNumber(){
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Alex");
        $("[id=lastName]").setValue("Black");
        $("#genterWrapper").$(byText("Male")).click();
        $("[id=userNumber]").setValue("123");
        $("[id=submit]").scrollIntoView(true).click();

        $("[id=example-modal-sizes-title-lg]")
                .shouldNotBe(visible);
    }
}