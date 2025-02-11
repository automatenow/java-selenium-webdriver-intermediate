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
    protected SearchBoxesPage searchBoxesPage;
    protected TablesPage tablesPage;
    protected NavigationBar navBar;
    protected HomePage homePage;
    protected SandboxPage sandboxPage;
    protected FormFieldsPage formFields;
    protected WindowOperationsPage windowOperationsPage;

    @BeforeSuite
    public void suiteSetup() {
        calendarsPage = new CalendarsPage();
        formFieldsPage = new FormFieldsPage();
        gesturesPage = new GesturesPage();
        hoverPage = new HoverPage();
        javaScriptDelaysPage = new JavaScriptDelaysPage();
        modalsPage = new ModalsPage();
        popupsPage = new PopupsPage();
        searchBoxesPage = new SearchBoxesPage();
        tablesPage = new TablesPage();
        navBar = new NavigationBar();
        homePage = new HomePage();
        sandboxPage = new SandboxPage();
        formFields = new FormFieldsPage();
        windowOperationsPage = new WindowOperationsPage();
    }

    @BeforeTest
    public void setup() {
        Assert.assertTrue(goToHomepage(), "An error occurred while navigating to the the homepage");
    }

    @AfterTest
    public void tearDown() {
        closeBrowser();
    }
}
