package io.automatenow.core;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Set;

/**
 * @author Marco A. Cruz
 */
public class BasePage {
    protected static WebDriver driver;
    protected static Logger log = LogManager.getLogger();

    public String browser;
    public String baseUrl;
    public Properties properties;

    private void loadProperties() {
        FileInputStream fis = null;

        try {
            properties = new Properties();
            fis = new FileInputStream("src\\main\\java\\io\\automatenow\\config\\config.properties");
            properties.load(fis);

            browser = properties.getProperty("browser");
            baseUrl = properties.getProperty("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openBrowser() {
            if (browser.equals("chrome")) {
                // Use this to debug ChromeDriver
//                System.setProperty("webdriver.chrome.verboseLogging", "true");

                // Run in headless mode
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless");  // or: options.setHeadless(true);
//                options.addArguments("--window-size=1920,1080");
//                driver = new ChromeDriver(options);

                // Change download default directory
//                ChromeOptions options = new ChromeOptions();
//                Map<String, Object> prefs = new HashMap<String, Object>();
//                prefs.put("download.default_directory", "<directory such as C:\\MyFolder\\");
//                options.setExperimentalOption("prefs", prefs);
//                driver = new ChromeDriver(options);

                // Disable message 'Chrome is being controlled by automated test software'
//                ChromeOptions options = new ChromeOptions();
//                options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
//                driver = new ChromeDriver(options);

                driver = new ChromeDriver();
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
    }

    /**
     * Closes all the windows associated with the WebDriver instance and also ends the WebDriver session
     */
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * Closes the current window on which the WebDriver instance is focused
     */
    public void closeWindow() {
        driver.close();
    }

    public Boolean goToHomepage() {
        try {
            loadProperties();
            openBrowser();
            driver.get(baseUrl);
        } catch (Exception e) {
            System.out.println("Unable to navigate to the homepage");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void setText(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        tab(locator);
    }

    public void tab(By locator) {
        driver.findElement(locator).sendKeys(Keys.TAB);
    }

    public String getText(By locator) {
        String displayedText = driver.findElement(locator).getText();
        if (displayedText.isEmpty()) {
            return driver.findElement(locator).getDomAttribute("value");
        } else {
            return displayedText;
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void goBack() {
        driver.navigate().back();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public int getNumberOfOpenWindows() {
        return driver.getWindowHandles().size();
    }

    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean waitForPageTitle(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.titleContains(title));
    }

    /**
     * Performs a drag-n-drop operation on a given element by a given x,y offset.
     *
     * @param locator The element to be interacted with
     * @param x x-coordinate
     * @param y Y-coordinate
     */
    public void dragAndDropByOffset(By locator, int x, int y) {
        WebElement element = driver.findElement(locator);
        new Actions(driver).dragAndDropBy(element, x, y).perform();
    }

    public void dismissPopup() {
        driver.switchTo().alert().dismiss();
    }

    public void acceptPopup() {
        driver.switchTo().alert().accept();
    }

    public void setAlertText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public void waitForElementText(By locator, String text) {
        // This is an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(locator, text));

        // This is a FluentWait. It does the same as the above wait, but it is more customizable
//        Wait<WebDriver> wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(3))
//                .pollingEvery(Duration.ofMillis(250))
//                .ignoring(NoSuchElementException.class);
//        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    public void waitForElementValue(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    public void hoverElement(By locator) {
        WebElement element = driver.findElement(locator);
        new Actions(driver).moveToElement(element).perform();
    }

    /**
     * Scroll the specified element into the center of the viewport
     * @param locator The element to scroll into view
     */
    public void scrollElementIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView({block: \"center\"});", element);
        pause(1);
    }

    /**
     * Pauses the test execution for the specified number of seconds.
     *
     * @param seconds The number of seconds to pause.
     */
    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scrolls the document by the specified number of pixels.
     *
     * @param x How many pixels to scroll by, along the x-axis (horizontal).
     * @param y How many pixels to scroll by, along the y-axis (vertical).
     */
    public void scrollPage(int x, int y) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    /**
     * Takes screenshot of whole page and uses the current date/time as the file name
     */
    public void takeScreenshot() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss-SSS");
        LocalDateTime dateTime = LocalDateTime.now();
        takeScreenshot(dateTime.format(formatter));
    }

    /**
     * Takes screenshot of whole page and allows you to name the screenshot
     *
     * @param screenshotName The screenshot file name
     */
    public void takeScreenshot(String screenshotName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./screenshots/" + screenshotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes screenshot of single WebElement
     */
    public void takeElementScreenshot(By locator) {
        WebElement element = driver.findElement(locator);
        File file = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./screenshots/element_screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param frame The index of the frame to switch to (first frame has index 0)
     */
    public void switchFrames(int frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    public void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        driver.manage().addCookie(cookie);
        refresh();
    }

    public Cookie getCookie(String name) {
        return driver.manage().getCookieNamed(name);
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
        refresh();
    }

    public boolean cookiesCleared() {
        return (driver.manage().getCookies()).isEmpty();
    }

    private void refresh() {
        driver.navigate().refresh();
    }
}
