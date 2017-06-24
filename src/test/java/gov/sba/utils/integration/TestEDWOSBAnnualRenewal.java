//Ts Created By Deepa Patri
package gov.sba.utils.integration;

import gov.sba.automation.*;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.automation.CommonApplicationMethods.deleteApplication;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.FillApplCreatePages.page8aFillUp;
@Category({gov.sba.utils.integration.StableTests.class})
public class TestEDWOSBAnnualRenewal extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestEDWOSBAnnualRenewal.class.getName());
    private static WebDriver webDriver;
    String duns_Number, email, password;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        CommonApplicationMethods.get_Stop_Execution_Flag();
        CommonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        //CommonApplicationMethods.focus_window();
        get_The_Row_From_Login_Data = 49;
        String duns_Number          = "246235962";
    }

    @Test
    public void testMainTest() throws Exception {

        // Login to dashboard.
            LoginPageWithReference login_Data =  new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);

        try {
            // Return the Applicatiom if exist:
            if (CommonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active")) {
                navigationMenuClick(webDriver, "LOGOUT");
                AssertionUtils.return_All_Applications(webDriver, 11, duns_Number);
                login_Data = new LoginPageWithReference(webDriver, 49);
                login_Data.Login_With_Reference();
            }
            deleteApplication(webDriver, "Edwosb", "Draft");
            deleteApplication(webDriver, "Wosb", "Draft");
            // Create application Edwosb
            programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
            // Create New Edwosb Application with select No to all questions
            // with one person's 413 form
            NewScorpQuestionPageDeepa scorpQuestionsPage = new NewScorpQuestionPageDeepa(webDriver);
            scorpQuestionsPage.NewScorpQuestionPageDeepa();
            new NewFinancialSectionQuestionDeepa(webDriver).NewFinancialQuestion();
            FillApplCreatePages.finalSignatureSubmit(webDriver);
            //Check Application is submitted sucessfully
            List<WebElement> count_Active = find_Elements(webDriver,"xpath","//table[@id='certifications']/tbody/tr[ td[1]/a[ contains(text(),'EDWOSB') ] and td[5][ contains(text(),'Active') ]]");
            assertEquals(count_Active.size(), 1);
            logger.info("Doc has been uploaded.-Edwosb application submitted sucessfully");
            // update the expiry date to current date to make thecertificate to expire
            // --after the midnight cron job runs check the status of the certificate to - Expired -- APP387
            String sql_Q_01 = "update sbaone.certificates set expiry_date = CURRENT_TIMESTAMP where organization_id = (select id from sbaone.organizations where duns_number = '" + duns_Number + "')";
            new DatabaseUtils().executeSQLScript(sql_Q_01);
            // check the status of the certificate to - Expired -  verify the Renewal link - submit new renew application
            webDriver.navigate().refresh();
            List<WebElement> count_ReNew = find_Elements(webDriver,"xpath","//table[@id='certifications']/tbody/tr[ td[1]/a[ contains(text(),'EDWOSB') ] and td[7]/a[ contains(text(),'Renew')] ]");
            assertEquals(count_ReNew.size(), 1);
            // Create new renew application - submit
            count_ReNew.get(0).findElement(By.xpath("td[7]/a")).click();
            click_Element(webDriver,"Application_Common_Accept_Button");
            page8aFillUp(webDriver, "Yes");
            finalSignatureSubmit(webDriver);
            // Verify the old application's status - Expired The renewed Application's status - Active
            List<WebElement> count_Edwosb_Active = find_Elements(webDriver,"xpath","//table[@id='certifications']/tbody/tr[ td[1]/a[ contains(text(),'EDWOSB Self-Certification') ] and td[5][ contains(text(),'Active') ]]");
            assertTrue(count_Edwosb_Active.size() > 0);
            List<WebElement> count_Edwosb_Expire = find_Elements(webDriver,"xpath","//*[@id='certifications']/tbody/tr" + "["
                    + "td[position()=1]/a[contains(text(),'EDWOSB')]" + " and "
                    + "td[position()=5 and (contains(text(),'Expired'))]" + "]");
            assertEquals(count_Edwosb_Expire.size(), 1);
        }
        catch (Exception e) {
            logger.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{TestEDWOSBAnnualRenewal.class.getName(), "Exception"});
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
