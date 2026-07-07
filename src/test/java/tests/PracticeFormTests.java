package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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
 }

    @Test
    void successfulFillFormTest() {

        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Alex");
        $("[id=lastName]").setValue("Black");
        $("[id=userEmail]").setValue("alex@black.com");
        $("[id=userNumber]").setValue("1234567890");
        $("[id=gender-radio-1]").click();
        $("[id=dateOfBirthInput]").setValue("07 Jul 2026").pressEnter();
        $("[id=subjectsInput]").setValue("Maths").pressEnter();
        $("label[for='hobbies-checkbox-1']").click();
        $("#uploadPicture")
                .uploadFile(new File("src/test/resources/smailiki.png"));
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
        $("table").shouldHave(text("07 July,2026"));
        $("table").shouldHave(text("Maths"));
        $("table").shouldHave(text("Sports"));
        $("table").shouldHave(text("smailiki.png"));
        $("table").shouldHave(text("first address"));
        $("table").shouldHave(text("NCR Delhi"));
    }

    @Test
    public void successfulSubmitFormWithRequiredFieldsTest(){

        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Alex");
        $("[id=lastName]").setValue("Black");
        $("[id=gender-radio-1]").click();
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
        open("https://demoqa.com/automation-practice-form");
        $("[id=submit]").scrollIntoView(true).click();

        $("[id=example-modal-sizes-title-lg]")
                .shouldNotBe(visible);
    }

    @Test
    public void shouldNotSubmitFormWithInvalidEmail(){
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Alex");
        $("[id=lastName]").setValue("Black");
        $("[id=gender-radio-1]").click();
        $("[id=userEmail]").setValue("alexblack.com");
        $("[id=userNumber]").setValue("1234567890");
        $("[id=submit]").scrollIntoView(true).click();

        $("[id=example-modal-sizes-title-lg]")
                .shouldNotBe(visible);
    }

    @Test
    public void shouldNotSubmitFormWithShortUserNumber(){
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Alex");
        $("[id=lastName]").setValue("Black");
        $("[id=gender-radio-1]").click();
        $("[id=userNumber]").setValue("123");
        $("[id=submit]").scrollIntoView(true).click();

        $("[id=example-modal-sizes-title-lg]")
                .shouldNotBe(visible);
    }
}