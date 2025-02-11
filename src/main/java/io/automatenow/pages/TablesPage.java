package io.automatenow.pages;

import io.automatenow.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author Marco A. Cruz
 */
public class TablesPage extends BasePage {
    private final By countrySort = By.cssSelector("[aria-label='Country: Activate to sort']");
    private final By noNextBtn = By.cssSelector(".paginate_button.next.disabled");
    private final By nextBtn = By.cssSelector("[aria-label='Next']");

    public String getItemPrice(String item) {
        return driver.findElement(By.xpath("//td[text()='" + item + "']/following-sibling::td")).getText();
    }

    public TablesPage sortByCountry() {
        click(countrySort);
        return this;
    }

    /**
     * Gets a country's population
     *
     * @param country The name of the country
     * @return Population in millions when country is found; -1 otherwise.
     */
    public String getPopulation(String country) {
        boolean foundCountry = false;

        while (!foundCountry) {
            List<WebElement> countryListedOnCurrentPage = driver.findElements(By.xpath("//table[@id='tablepress-1']//td[normalize-space()='"+ country +"']"));
            List<WebElement> disabledNextBtn = driver.findElements(noNextBtn);

            if (!countryListedOnCurrentPage.isEmpty()) {
                foundCountry = true;
            } else if (disabledNextBtn.isEmpty()) {
                scrollElementIntoView(nextBtn);  // This is sometimes needed!
                click(nextBtn);
            } else {
                return "-1";
            }
        }

        // Return the country's population
        return getText(By.xpath("//td[normalize-space()='" + country + "']/following-sibling::td"));
    }
}
