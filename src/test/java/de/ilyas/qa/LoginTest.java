package de.ilyas.qa;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest {

    private static final boolean HEADED = Boolean.getBoolean("headed");

    @Test
    void loginSucceedsWithValidCredentials() {
        try (Playwright playwright = Playwright.create()) {
            try (Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(!HEADED)
                            .setSlowMo(HEADED ? 1000 : 0))) {
                try (Page page = browser.newPage()) {
                    page.navigate("https://the-internet.herokuapp.com/login");

                    Locator username = page.getByLabel("Username");
                    Locator password = page.getByLabel("Password");

                    username.fill("tomsmith");
                    password.fill("SuperSecretPassword!");

                    Locator submit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
                    submit.click();

                    assertThat(page.getByText("You logged into a secure area!")).isVisible();
                    assertThat(page).hasURL("https://the-internet.herokuapp.com/secure");

                }
            }
        }
    }

    @Test
    void loginFailsWithInvalidPassword() {
        try (Playwright playwright = Playwright.create()) {
            try (Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(!HEADED)
                            .setSlowMo(HEADED ? 1000 : 0))) {
                try (Page page = browser.newPage()) {

                    page.navigate("https://the-internet.herokuapp.com/login");
                    page.getByLabel("Username").fill("tomsmith");
                    page.getByLabel("Password").fill("WrongPassword!");

                    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

                    assertThat(page.getByText("Your password is invalid!")).isVisible();
                    assertThat(page).hasURL("https://the-internet.herokuapp.com/login");
                }
            }
        }
    }
}
