package io.automatenow.tests;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.automatenow.core.BaseTest;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * @author Marco A. Cruz
 */
//@Listeners(TestListener.class)
@Listeners({ExtentITestListenerClassAdapter.class})
public class HomepageTests extends BaseTest {

//    @Test(description = "Verify page title")
//    @Test(groups = { "tagName", "tag:another-tagName", "author:authorName", "device:deviceName" })
    @Test(groups = { "tagName", "t:another-tagName", "a:authorName", "d:deviceName" })
    public void testPageTile() {
        String pageTitle = homePage.getPageTitle();
        assertEquals(pageTitle, "Learn and Practice Automation | automateNow", "The page title did not match!");
    }

    @Test(description = "Verify welcome message")
    public void testGreeting() {
        String greeting = homePage.getWelcomeMessage();
        assertTrue(greeting.contains("Welcome"), "Welcome message did not match");
    }
}