/**
 * Created by deepa on 5/15/2017.
 */
package gov.sba.pageObjetcs;

import gov.sba.utils.integration.newMppUploadDocumentPageDeepa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.automation.FixtureUtils.fixturesDir;


public class vendor_Admin_8a_Master_Application_Page {

    private static final Logger logger =
            LogManager.getLogger(vendor_Admin_8a_Master_Application_Page.class.getName());


    public static void doUpload_Action() throws Exception {
        StringSelection ss = new StringSelection(fixturesDir() + "Upload.pdf");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        logger.info("Uploading a new document - Clicked on Upload");

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(300);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(300);
        logger.info("Uploading a new document - Alt tab done");

        robot.keyPress(KeyEvent.VK_F4);
        Thread.sleep(300);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        Thread.sleep(300);
        logger.info("Uploading a new document - Clicked on F4");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(300);
        logger.info("Uploading a new document - Clicked on Tab");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(300);
        logger.info("Uploading a new document - Clicked on Tab2");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(300);
        logger.info("Uploading a new document - Clicked on Tab3");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(300);

        logger.info("Uploading a new document - Clicked on Tab4");
        robot.keyPress(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(300);
        logger.info("Uploading a new document - Clicked on Paste");
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        logger.info("Uploading a new document - Clicked on All Enters");
    }

    public static void masterApp_Gender_Info_Page(WebDriver webDriver, String which_Gender) throws Exception {
        try {
            //  Elements Tags: @vendor_Admin_8a_Master_Application_Page_Gender
            if (!which_Gender.equals(null) && !which_Gender.equals("")) {
                switch (which_Gender) {
                    case "Male":
                        new Select(find_Element(webDriver, "8a_MasterApp_Gender"))
                                .selectByIndex(1);
                        break;
                    case "Female":
                        new Select(find_Element(webDriver, "8a_MasterApp_Gender"))
                                .selectByIndex(2);
                        break;
                }
                click_Element(webDriver, "Application_Common_Continue_Button");
            }

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void masterApp_MaritalStatus_Page(WebDriver webDriver, String which_Status) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Master_Application_Page_martial_Status
        if (!which_Status.equals(null) && !which_Status.equals("")) {
            switch (which_Status) {
                case "UnMarried":
                    click_Element(webDriver, "8a_MasterApp_Status_Unmarried");
                    break;
                case "Married":
                    click_Element(webDriver, "8a_MasterApp_Status_Married");
                    break;
                case "LegallySeperated":
                    click_Element(webDriver, "8a_MasterApp_Status_LegallySeperated");
                    break;

            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }


    }

    public static void masterApp_SocialSecNum_Page(WebDriver webDriver, String ssntext) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Master_Application_Page_Social_Secuirty_Number Page
        setText_Element(webDriver, "8a_Master_Application_Page_Social_Secuirty_Number", ssntext);
        click_Element(webDriver, "Application_Common_Continue_Button");
    }

    public static void masterApp_ContactInfo_Page(WebDriver webDriver, String cotactinfo) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Master_Application_Page_ContactInfo Page
        setText_Element(webDriver, "8a_Master_Application_Page_ContactInfo", cotactinfo);
        click_Element(webDriver, "Application_Common_Continue_Button");
    }

    public static void masterApp_CuurentHomeAddress_Page(WebDriver webDriver, String StreetAdd, String CityAdd, String State, String Zip, String Contry, String Date) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Master_Application_Page_CurrentHomeAddress Page
        setText_Element(webDriver, "8a_Master_Application_Page_StreetAddress", StreetAdd);
        setText_Element(webDriver, "8a_Master_Application_Page_City", CityAdd);
        new Select(find_Element(webDriver, "8a_Master_Application_Page_State"))
                .selectByValue(State);
        setText_Element(webDriver, "8a_Master_Application_Page_Zip", Zip);
        new Select(find_Element(webDriver, "8a_Master_Application_Page_country"))
                .selectByValue(Contry);
        setText_Element(webDriver, "8a_Master_Application_Page_DateOfresidenecy", Date);

        click_Element(webDriver, "Application_Common_Continue_Button");
    }

    public static void masterApp_LengthofResidency_Page(WebDriver webDriver, String which_value) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Master_Application_Page_length of Residency
        if (!which_value.equals(null) && !which_value.equals("")) {
            switch (which_value) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_length_of_Residency_yes");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_length_of_Residency_no");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_DateandPlaceofBirth_Page(WebDriver webDriver, String DtofBirth, String Place, String Country) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Master_Application_Page_Date and palce of Birth page
        setText_Element(webDriver, "8a_MasterApp_DateofBirth", DtofBirth);
        setText_Element(webDriver, "8a_MasterApp_PlaceofBirth", Place);
        new Select(find_Element(webDriver, "8a_Master_Application_Page_country"))
                .selectByValue(Country);
        click_Element(webDriver, "Application_Common_Continue_Button");
    }

    public static void masterApp_Us_Citizenship_Page(WebDriver webDriver, String which_value) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Master_Application_US Citizenship Page
        if (!which_value.equals(null) && !which_value.equals("")) {
            switch (which_value) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_US_Citizenship_yes");
                    new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_US_citizenship_no");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_Appl_Firm_Ownership_Page(WebDriver webDriver, String firm_Name_Percent, String positons) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_MasterApp_Appl_Firm_Ownership Page
        setText_Element(webDriver, "8a_MasterApp_Firm_Owner_Percent", firm_Name_Percent);
        setText_Element(webDriver, "8a_MasterApp_Firm_All_Positions", positons);
        click_Element(webDriver, "Application_Common_Continue_Button");
    }

    public static void masterApp_Bank_Acct_Access_Page(WebDriver webDriver, String yesOrNO, String textVal) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Bank_Acct_Access Page
        if (!yesOrNO.equals(null) && !yesOrNO.equals("")) {
            switch (yesOrNO) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Bank_Acct_Access_Yes");
                    setText_Element(webDriver, "8a_MasterApp_Bank_Acct_Access_Yes_Text", textVal);
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Bank_Acct_Access_No");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_Full_Time_Devotion_Page(WebDriver webDriver, String which_value) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Full_time_Devotion Citizenship Page
        if (!which_value.equals(null) && !which_value.equals("")) {
            switch (which_value) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Full_time_Devotion_Yes");
                    new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Full_time_Devotion_No");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_Business_Affiliations_Page(WebDriver webDriver, String ans1, String ans2, String ans2Text) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Business_Affiliation Page
        if (!ans1.equals(null) && !ans1.equals("") && !ans2.equals(null) && !ans2.equals("")) {
            switch (ans1) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Business_Affiliation_AnyOther_Yes");
                    new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Business_Affiliation_AnyOther_No");
                    break;
            }
            switch (ans2) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Business_Affiliation_RelationOwnContract_Yes");
                    setText_Element(webDriver, "8a_MasterApp_Business_Affiliation_RelationOwnContract_Yes_Text", ans2Text);
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Business_Affiliation_RelationOwnContract_No");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_8a_Prior_Involvement_Page(WebDriver webDriver, String ans1, String ans2, String ans3) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Prior 8a Involvement Page Page
        if (!ans1.equals(null) && !ans1.equals("") && !ans2.equals(null) && !ans2.equals("") && !ans3.equals(null) && !ans3.equals("")) {
            switch (ans1) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_Involvement_Owned_Yes");
                    new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_Involvement_Owned_No");
                    break;
            }
            switch (ans2) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_Involvement_Owned_one-time-only_Yes");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_Involvement_Owned_one-time-only_No");
                    break;
            }
            switch (ans3) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Priro_8a_Involvement_Owned_ever_owned_Yes");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_Involvement_Owned_ever_owned_No");
                    new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_8a_Federal_Employment_Page(WebDriver webDriver, String which_value) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Federal Employment Page
        if (!which_value.equals(null) && !which_value.equals("")) {
            switch (which_value) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Federal_Employment_Yes");
                    new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Federal_Employment_No");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_8a_Household_Federal_Employment_Page(WebDriver webDriver, String which_value) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Household_Federal Employment Page
        if (!which_value.equals(null) && !which_value.equals("")) {
            switch (which_value) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Household_Federal_Employment_Yes");
                    new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Household_Federal_Employment_No");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_8a_Financial_Page(WebDriver webDriver, String ans1, String ans2, String ans3, String ans4) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Prior 8a Involvement Page Page
        if (!ans1.equals(null) && !ans1.equals("") && !ans2.equals(null) && !ans2.equals("") && !ans3.equals(null) && !ans3.equals("") && !ans3.equals(null) && !ans3.equals("")) {
            switch (ans1) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_personal bankruptcy_Yes");
                    new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_personal bankruptcy_No");
                    break;
            }
            switch (ans2) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_Previously_SBA loan_Yes");
                    new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_Previously_SBA loan_No");
                    break;
            }
            switch (ans3) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Priro_8a_pending_civil_lawsuit_Yes");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_pending_civil_lawsuit_No");
                    break;
            }
            switch (ans4) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Priro_8a_paying_Yes");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_paying_No");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_8a_Criminal_History_Page(WebDriver webDriver, String ans1, String ans1_Text, String ans2, String ans3, String ans4) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Prior 8a Involvement Page Page
        if (!ans1.equals(null) && !ans1.equals("") && !ans2.equals(null) && !ans2.equals("") && !ans3.equals(null) && !ans3.equals("") && !ans3.equals(null) && !ans3.equals("")) {
            switch (ans1) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_criminal_Hist_names_Yes");
                    setText_Element(webDriver, "8a_MasterApp_Prior_8a_criminal_Hist_names_Yes_text", ans1_Text);
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_criminal_Hist_names_No");
                    break;
            }
            switch (ans2) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_criminal_Charges_Yes");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_criminal_Charges_No");
                    break;
            }
            switch (ans3) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Priro_8a_criminal_Offense_Yes");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_criminal_Offense_No");
                    break;
            }
            switch (ans4) {
                case "yes":
                    click_Element(webDriver, "8a_MasterApp_Priro_8a_criminal_Violations_Yes");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Prior_8a_criminal_Violations_No");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        }
    }

    public static void masterApp_criminal_Hist_Doc_Page(WebDriver webDriver) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Crim_Hist_Doc
        try {
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up1_Add_Req");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up1_Add_Req_Upload");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up1_Add_Req_Choose");
            Thread.sleep(1500);
            doUpload_Action();

            click_Element(webDriver, "8a_Master_CrimHistDoc_Up2_Add_Req");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up2_Add_Req_Upload");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up2_Add_Req_Choose");
            Thread.sleep(1500);
            doUpload_Action();

            click_Element(webDriver, "8a_Master_CrimHistDoc_Up3_Add_Req");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up3_Add_Req_Upload");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up3_Add_Req_Choose");
            Thread.sleep(1500);
            doUpload_Action();
            click_Element(webDriver, "Application_Common_Continue_Button");

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void masterApp_Basic_Of_Disadvantage_Page(WebDriver webDriver, String Option, String Text) throws Exception {

        new Select(find_Element(webDriver, "8a_MasterApp_Basic_Of_DisAdv_Sel"))
                .selectByValue(Option);
        setText_Element(webDriver, "8a_MasterApp_Basic_Of_DisAdv_Text", Text);
        click_Element(webDriver, "Application_Common_Continue_Button");
    }

    public static void masterApp_Social_Narrative_Page(WebDriver webDriver) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Social_Narrative
        try {
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up1_Add_Req");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up1_Add_Req_Upload");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_CrimHistDoc_Up1_Add_Req_Choose");
            Thread.sleep(1500);
            doUpload_Action();
            click_Element(webDriver, "Application_Common_Continue_Button");

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void masterApp_Transfer_Assets_Page(WebDriver webDriver, String yesNo, String Text) throws Exception {
        try {
            //  Elements Tags: @vendor_Admin_8a_Transfer Assets Page
            switch (yesNo) {
                case "Yes":
                    click_Element(webDriver, "8a_MasterApp_Transfer Assets_Yes");
                    setText_Element(webDriver, "8a_MasterApp_Transfer Assets_Yes_Text", "Text");
                    break;
                case "no":
                    click_Element(webDriver, "8a_MasterApp_Transfer Assets_No");
                    break;
            }
            click_Element(webDriver, "Application_Common_Continue_Button");
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void masterApp_Tax_Returns_Page(WebDriver webDriver) throws Exception {
        //  Elements Tags: @vendor_Admin_8a_Social_Narrative
        try {
            click_Element(webDriver, "8a_Master_Tax_Returns_Up1_Add_Req");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_Tax_Returns_Up1_Add_Req_Upload");
            Thread.sleep(1500);
            click_Element(webDriver, "8a_Master_Tax_Returns_Up1_Add_Req_Choose");
            Thread.sleep(1500);
            doUpload_Action();
            click_Element(webDriver, "Application_Common_Continue_Button");

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void masterApp_FinancialData_AddPerson(WebDriver webDriver, String FName, String LName, String Title, String Ssn, String MartialSt, String Email, String City, String Address, String State, String Postal, String Co, String HPhone, String BPhone) throws Exception {

        //  Elements Tags: @vendor_Admin_8a_FinancialData Page - EDWOSB
        try {
            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Add_Person");
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_FirstName", FName);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_LastName", LName);
            new Select(find_Element(webDriver, "EDWOSB_Questionnaire_Page_title")).selectByValue(Title);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_SSN", Ssn);
            new Select(find_Element(webDriver, "EDWOSB_Questionnaire_Page_MaritalSt")).selectByValue(MartialSt);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Email", Email);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_City", City);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Address", Address);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_State", State);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Postal", Postal);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Co", Co);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_HPhone", HPhone);
            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Bphone", BPhone);
            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Done_Button");

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void masterApp_FinancialData_Legally_Separted(WebDriver webDriver, String YesNo) throws Exception {

        //  Elements Tags: @vendor_Admin_8a_FinancialData Page - EDWOSB
        try {
            if (!YesNo.equals(null) && !YesNo.equals("")) {
                switch (YesNo) {
                    case "Yes":
                        click_Element(webDriver, "8a_Master_financial_Legally_Separted_Yes");
                        click_Element(webDriver, "8a_Master_financial_Legally_Separted_Add_Req");
                        Thread.sleep(1500);
                        click_Element(webDriver, "8a_Master_financial_Legally_Separted_Upload");
                        Thread.sleep(1500);
                        click_Element(webDriver, "8a_Master_financial_Legally_Separted_Choose");
                        Thread.sleep(1500);
                        doUpload_Action();
                        break;
                    case "No":
                        click_Element(webDriver, "8a_Master_financial_Legally_Separted_No");
                        break;
                }
                click_Element(webDriver, "Application_Common_Continue_Button");
            }

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void masterApp_financial_CashOnHand_Page(WebDriver webDriver, String Date, String Currency, String SavingBal, String CheckingBal) throws Exception {
        try {
            //  Elements Tags: @vendor_Admin_8a_financial_CashOnHand Page
            setText_Element(webDriver, "8a_Master_financial_CashOnHand_Date", Date);
            setText_Element(webDriver, "8a_Master_financial_CashOnHand_Dollar", Currency);
            setText_Element(webDriver, "8a_Master_financial_CashOnHand_Saving", SavingBal);
            setText_Element(webDriver, "8a_Master_financial_CashOnHand_Checking", CheckingBal);
            click_Element(webDriver, "Application_Common_Continue_Button");
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void masterApp_financial_OtherSource_Page(WebDriver webDriver, String Salary, String OtherIncome, String OthIncText, String AppFirm, String EquityFirm) throws Exception {
        try {
            //  Elements Tags: @vendor_Admin_8a_financial_CashOnHand Page
            setText_Element(webDriver, "8a_Master_financial_OtherSource_Salary", Salary);
            setText_Element(webDriver, "8a_Master_financial_OtherSource_OtherIncome", OtherIncome);

            if (Integer.valueOf(OtherIncome) > 0) {
                setText_Element(webDriver, "8a_Master_financial_OtherSource_OtherIncome_Text", OthIncText);
            }
            setText_Element(webDriver, "8a_Master_financial_OtherSource_Appfirm", AppFirm);
            setText_Element(webDriver, "8a_Master_financial_OtherSource_EquityFirm", EquityFirm);
            click_Element(webDriver, "Application_Common_Continue_Button");
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void masterApp_financial_Notes_Receivable_Page(WebDriver webDriver, String YesNo) throws Exception {
        try {
            //  Elements Tags: @vendor_Admin_8a_Master_Application_Page_Gender
            if (!YesNo.equals(null) && !YesNo.equals("")) {
                switch (YesNo) {
                    case "Yes":
                        click_Element(webDriver, "8a_Master_financial_Notes Receivable_Yes");
                        click_Element(webDriver, "8a_Master_financial_Notes_Receivable_New_Button");
                        setText_Element(webDriver, "8a_Master_financial_Notes_Name_Of_Debtor", "ABC");
                        setText_Element(webDriver, "8a_Master_financial_Notes_Addr_Of_Debtor", "1234");
                        setText_Element(webDriver, "8a_Master_financial_Notes_Original_Balance", "100");
                        setText_Element(webDriver, "8a_Master_financial_Notes_Current_Balance", "100");
                        setText_Element(webDriver, "8a_Master_financial_Notes_Payment_Amount", "100");
                        setText_Element(webDriver, "8a_Master_financial_Notes_Type_Of_Collateral", "XYZ");
                        break;
                    case "No":
                        click_Element(webDriver, "8a_Master_financial_Notes Receivable_No");
                        break;
                }
                click_Element(webDriver, "Application_Common_Continue_Button");
            }

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }
}
