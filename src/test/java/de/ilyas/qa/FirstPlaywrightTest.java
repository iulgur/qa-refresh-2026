package de.ilyas.qa;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FirstPlaywrightTest {

    @Test
    void testTheTitlePageOfPlaywright() {

        try (Playwright playwright = Playwright.create()){
            try (Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(1000))){
                try (Page page = browser.newPage()){
                    page.navigate("https://playwright.dev");
                    assertThat(page).hasTitle(Pattern.compile("Playwright"));
                }
            }
        }
    }
}
