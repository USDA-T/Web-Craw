//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.pageObjetcs.master_Application_8A.*;
import static gov.sba.pageObjetcs.vendor_Admin_8a_Master_Application_Page.*;

public class delete {
    // Set The variabl.es/Define
    private static final Logger TestAnalystReview =
            LogManager.getLogger(delete.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        if (CommonApplicationMethods.get_Stop_Execution_Flag()) {
            return;
        }
        CommonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();

        get_The_Row_From_Login_Data = 43;

    }

    @Test
    public void testMainTest() throws Exception {
        try{
            TestAnalystReview.info("Test EDWOSB Sole-Proprietorship two partners on form413 with review");
            // Login to Dashboard.
            new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data).Login_With_Reference();

            // new programs_Page().select_MyCertifications_Table(webDriver, "Delete_8a_Initial_Draft");
            webDriver.navigate().to("https://certify.qa.sba-one.net/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initial&certificate_type_id=eight_a_initial");
            click_Element(webDriver, "Application_Common_Accept_Button");


            //masterApp_8a_Page_Click(webDriver,"page_basiceligibility");
            //Basic Eligibility Page
            BasicEligiblity_General_Assessment_Page(webDriver, "Yes", "Yes", "Yes", "Yes", "Yes");
            BasicEligiblity_Prior_8a_Involvement_Page(webDriver, "Yes", "Yes", "Yes");
            BasicEligiblity_Outside_Assistance_Page(webDriver, "Yes");
            BasicEligiblity_Business_Size_Page(webDriver, "Yes", "Yes");
            BasicEligiblity_Size_Determination_Page(webDriver);
            //Contributor Page- Vendor Admin application
            masterApp_8a_Page_Click(webDriver, "page_contributors_Start_Indv_Cont");
            masterApp_Gender_Info_Page(webDriver, "Male");
            masterApp_MaritalStatus_Page(webDriver, "Married");
            masterApp_SocialSecNum_Page(webDriver, "12345678");
            masterApp_ContactInfo_Page(webDriver, "12345678");
            masterApp_CuurentHomeAddress_Page(webDriver, "mclean", "mclean", "AL", "10002", "USA", "01/01/2019");
            masterApp_LengthofResidency_Page(webDriver, "yes");
            masterApp_DateandPlaceofBirth_Page(webDriver, "01/01/2019", "Mclean", "United States");
            masterApp_Us_Citizenship_Page(webDriver, "yes");
            masterApp_Appl_Firm_Ownership_Page(webDriver, "80", "Anything");
            masterApp_Bank_Acct_Access_Page(webDriver, "yes", "Anything");
            masterApp_Full_Time_Devotion_Page(webDriver, "yes");
            masterApp_Business_Affiliations_Page(webDriver, "yes", "yes", "something To Test");
            masterApp_8a_Prior_Involvement_Page(webDriver, "yes", "yes", "yes");
            masterApp_8a_Federal_Employment_Page(webDriver, "yes");
            masterApp_8a_Household_Federal_Employment_Page(webDriver, "yes");
            masterApp_8a_Financial_Page(webDriver, "yes", "yes", "yes", "yes");
            masterApp_8a_Criminal_History_Page(webDriver, "yes", "Anything", "yes", "yes", "yes");
            masterApp_criminal_Hist_Doc_Page(webDriver);
            masterApp_Basic_Of_Disadvantage_Page(webDriver, "Black American", "Anything");
            masterApp_Social_Narrative_Page(webDriver);
            masterApp_Transfer_Assets_Page(webDriver, "Yes", "Anything");
            masterApp_Tax_Returns_Page(webDriver);
            masterApp_FinancialData_AddPerson(webDriver, "deepa", "Mahesh", "Owner", "123456789", "Married", "d@gmail.com", "m", "1", "MN", "1", "US", "1", "1");
            masterApp_FinancialData_Legally_Separted(webDriver, "Yes");
            masterApp_financial_CashOnHand_Page(webDriver, "01/01/2019", "111", "111", "111");
            masterApp_financial_OtherSource_Page(webDriver, "111", "111", "Anything", "111", "111");
            masterApp_financial_Notes_Receivable_Page(webDriver, "Yes");
            masterApp_financial_Retirement_Account_Page(webDriver, "Yes", "Yes");
            masterApp_financial_RealEstate_Page(webDriver, "Yes", "Yes", "Yes", "Yes", "Yes", "Yes");
            masterApp_financial_RealEstateOther_Page(webDriver, "Yes", "Yes", "Yes", "Yes", "Yes", "Yes");
            masterApp_financial_Personal_Property_Page(webDriver, "Yes", "Yes");
            masterApp_financial_NotesPayableandOther_Page(webDriver, "Yes");
            masterApp_financial_Assessed_Taxes_Page(webDriver, "Yes");
            //Personal summary Report
            masterApp_financial_PersonalSummary_Page(webDriver);
            masterApp_financial_PrivacyStatements_Page(webDriver);
            masterApp_financial_Review_Page(webDriver);
        } catch (Exception e) {
            TestAnalystReview.info(e.toString());
            throw new Exception(e);
        }
    }

  @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}


