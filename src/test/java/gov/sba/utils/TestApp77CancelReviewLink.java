package gov.sba.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit. * ;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import junit.framework.TestCase;

public class TestApp77CancelReviewLink extends TestCase {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp77CancelReviewLink = LogManager.getLogger(TestApp77CancelReviewLink.class.getName());
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 11;
    }@Test
    public void testMainTest() throws Exception {
        try {
            // Login to dashboard.
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
            Thread.sleep(2000);
            //Click on Case Link on main navigator
            commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            //pass teh dun'sno,type of app
            AnalystReviewPage TestReviewProcess = new AnalystReviewPage();
            // Give Duns Number
            TestReviewProcess.TestReviewDriver(webDriver, "159165917", "WOSB", "Initial Review", "Analyst2 X", "Analyst3 X", "Analyst4 X");
            TestReviewProcess.testSubmitted();
            fillApplCreatePages.pageQuestionReviewFillup(webDriver);
            fillApplCreatePages.pageSignatureReviewFillup(webDriver);
            webDriver.findElement(By.xpath("//a[contains(text(),'Case overview')]")).click();
            List < WebElement > cancelLInk = webDriver.findElements(By.xpath("//a[contains(text(),'Cancel review')]"));
            assertEquals(cancelLInk.size(), 0);
            //webDriver.navigate().back();
        }
        catch(Exception e) {
            TestApp77CancelReviewLink.info(e.toString());
            throw e;
        }
    }@After
    public void tearDown() throws Exception {
        //webDriver.quit();
    }
}