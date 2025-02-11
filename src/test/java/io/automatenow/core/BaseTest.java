package io.automatenow.core;

import io.automatenow.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * @author Marco A. Cruz
 */
public class BaseTest extends BasePage {
    protected CalendarsPage calendarsPage;
    protected FormFieldsPage formFieldsPage;
    protected GesturesPage gesturesPage;
    protected HoverPage hoverPage;
    protected JavaScriptDelaysPage javaScriptDelaysPage;
    protected ModalsPage modalsPage;
    protected PopupsPage popupsPage;
    protected TablesPage tablesPage;
    protected NavigationBar navBar;
    protected HomePage homePage;
    protected SandboxPage sandboxPage;
    protected FormFieldsPage formFields;
    protected WindowOperationsPage windowOperationsPage;

    /**
     * This method runs before the suite starts, and it initializes all the page objects.
     */
    @BeforeSuite
    public void suiteSetup() {
        calendarsPage = new CalendarsPage();
        formFieldsPage = new FormFieldsPage();
        gesturesPage = new GesturesPage();
        hoverPage = new HoverPage();
        javaScriptDelaysPage = new JavaScriptDelaysPage();
        modalsPage = new ModalsPage();
        popupsPage = new PopupsPage();
        tablesPage = new TablesPage();
        navBar = new NavigationBar();
        homePage = new HomePage();
        sandboxPage = new SandboxPage();
        formFields = new FormFieldsPage();
        windowOperationsPage = new WindowOperationsPage();
    }

    /**
     * This method runs before every test
     */
    @BeforeTest
    public void setup() {
        Assert.assertTrue(goToHomepage(), "An error occurred while navigating to the the homepage");
    }

    /**
     * This method runs after every test
     */
    @AfterTest
    public void tearDown() {
        closeBrowser();
    }
}
