package de.ilyas.qa;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest {

    private static final boolean HEADED = Boolean.getBoolean("headed");
    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(!HEADED)
                        .setSlowMo(HEADED ? 1000 : 0)
        );
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    @AfterAll
    static void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Test
    void loginSucceedsWithValidCredentials() {

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


    @Test
    void loginFailsWithInvalidPassword() {
        page.navigate("https://the-internet.herokuapp.com/login");

        Locator username = page.getByLabel("Username");
        Locator password = page.getByLabel("Password");

        username.fill("tomsmith");
        password.fill("WrongPassword!");

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        assertThat(page.getByText("Your password is invalid!")).isVisible();
        assertThat(page).hasURL("https://the-internet.herokuapp.com/login");
    }
}
