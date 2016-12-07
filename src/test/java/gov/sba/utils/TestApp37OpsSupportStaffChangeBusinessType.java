package gov.sba.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit. * ;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;
import org.openqa.selenium.support.ui.Select;

public class TestApp37OpsSupportStaffChangeBusinessType extends TestCase {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp77CancelReviewLink = LogManager.getLogger(TestApp77CancelReviewLink.class.getName());
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 9;
    }@Test
    public void testMainTest() throws Exception {
        try {
            // Login to dashboard.
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
            Thread.sleep(2000);

            if (commonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active")) {
                String duns_Number = webDriver.findElement(By.xpath("//*[@id='main-content']/section/article//p[ (b[contains(text(),'DUNS:')]) ]")).getText().replaceAll("DUNS:", "").trim();
                commonApplicationMethods.returnAppToVendorMethd(webDriver, 11, duns_Number, "EDWOSB", "Active", get_The_Row_From_Login_Data);
                commonApplicationMethods.deleteApplication(webDriver, "EDWOSB", "Draft");

                commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                login_Data = new LoginPageWithReference(webDriver, 27);
                login_Data.Login_With_Reference();
                Thread.sleep(2000);
                commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                webDriver.findElement(By.id("query")).sendKeys(duns_Number);
                webDriver.findElement(By.xpath("//*[@id='analyst-search']/div/button[ span[contains(text(),'Search')] ]")).click();
                webDriver.findElement(By.xpath("//*[@id='business_search']/div[h2[contains(text(),'Search Results')]]/div[1]/div/h4/a")).click();
                webDriver.findElement(By.xpath("//div[@class='vendor-support-link']/a[contains(text(),'Vendor Support')]")).click();
                webDriver.findElement(By.id("change_business_type_link")).click();
                Select dropdown1 = new Select(webDriver.findElement(By.id("business_type")));
                dropdown1.selectByIndex(1);
                webDriver.findElement(By.id("save")).click();
                commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                login_Data = new LoginPageWithReference(webDriver, 9);
                login_Data.Login_With_Reference();
                Thread.sleep(2000);
            }

            commonApplicationMethods.deleteApplication(webDriver, "EDWOSB", "Draft");
            //          commonApplicationMethods.createApplication(webDriver, "EDWOSB");
            webDriver.findElement(By.xpath("//*[@id='js-navigation-menu']/li/a[contains(text(),'Programs')]")).click();
            webDriver.findElement(By.id("certificate_type_edwosb")).click();
            webDriver.findElement(By.id("add_certification")).click();
            PartnershipQuestionsPage newApp = new PartnershipQuestionsPage(webDriver);
            newApp.Partnershipquestions();
            FinancialSectionPage fp = new FinancialSectionPage(webDriver);
            fp.Financialsection();
            fillApplCreatePages.finalSignatureSubmit(webDriver);

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