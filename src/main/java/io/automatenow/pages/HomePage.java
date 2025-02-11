package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

/**
 * @author Marco A. Cruz
 */
public class HomePage extends BasePage {
    private final By welcomeMsg = By.xpath("//*[normalize-space()='Welcome to your software automation practice website!']");

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getWelcomeMessage() {
        return driver.findElement(welcomeMsg).getText();
    }
}
