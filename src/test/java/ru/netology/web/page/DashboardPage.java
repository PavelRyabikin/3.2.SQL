package ru.netology.web.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public DashboardPage() {
        $("[data-test-id='dashboard']").shouldBe(Condition.visible);
    }

    public void shouldDashboardVisible() {
        $("[data-test-id='dashboard']").shouldBe(Condition.visible).shouldHave(Condition.text("Личный кабинет"));
    }
}