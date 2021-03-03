package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataForRegistration;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ChangeDateOfCardDeliveryOrderTest {

    @Test
    void shouldSendCardDeliveryFormWhenCityIsValid () {
        DataForRegistration newUser = DataGenerator.Registration.generateByCard("ru");
        String validCity = DataGenerator.Registration.generateValidCity();
        open("http://localhost:9999");
        $("[data-test-id=city] [placeholder=Город]").setValue(validCity);
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(newUser.getDataOfMeeting());
        $("[data-test-id='name'] .input__control").setValue(newUser.getName());
        $("[data-test-id='phone'] .input__control").setValue(newUser.getPhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").
                shouldBe(Condition.visible, Duration.ofMillis(15000)).
                shouldHave(text("Встреча успешно запланирована на " + newUser.getDataOfMeeting()));

        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(newUser.getAnotherDataOfMeeting());
        $$("[type='button']").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification] .notification__content").
                shouldBe(Condition.visible, Duration.ofMillis(15000)).
                shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$(".button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").
                shouldBe(Condition.visible, Duration.ofMillis(15000)).
                shouldHave(text("Встреча успешно запланирована на " + newUser.getAnotherDataOfMeeting()));
    }

    @Test
    void shouldSendCardDeliveryFormWhenCityIsInvalid () {
        DataForRegistration newUser = DataGenerator.Registration.generateByCard("ru");
        String invalidCity = DataGenerator.Registration.generateInvalidCity();
        open("http://localhost:9999");
        $("[data-test-id=city] [placeholder=Город]").setValue(invalidCity);
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(newUser.getDataOfMeeting());
        $("[data-test-id='name'] .input__control").setValue(newUser.getName());
        $("[data-test-id='phone'] .input__control").setValue(newUser.getPhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(".input_invalid[data-test-id=city] .input__sub").
                shouldHave(exactText("Доставка в выбранный город недоступна"));
    }
}
