package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;

/**
 * @author Marco A. Cruz
 */
public class SearchBoxesPage extends BasePage {
    private final By searchBox = By.id("wp-block-search__input-1");
    private final By searchBtn = By.xpath("//button[text()='Search']");
    private final By noSearchResults = By.xpath("//h1[text()='Nothing Found']");

    public boolean search(String text) {
        setText(searchBox, text);
        click(searchBtn);

        if (driver.findElements(noSearchResults).size() > 0) {
            goBack();
            return false;
        }
        return true;
    }

}
