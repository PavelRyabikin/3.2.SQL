package ru.netology.web.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.utils.SQLHelper;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @Test
    void shouldLoginWithValidCodeFromDB() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var code = SQLHelper.getVerificationCode(authInfo.getLogin());
        var dashboardPage = verificationPage.validVerify(code);
        dashboardPage.shouldDashboardVisible();
    }

    @AfterAll
    static void tearDown() {
        SQLHelper.cleanDatabase();
    }
}