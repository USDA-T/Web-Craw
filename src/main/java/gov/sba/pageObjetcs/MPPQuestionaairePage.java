package gov.sba.pageObjetcs;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.pageObjetcs.ProgramsPage.generic_file_Upld;
import static org.junit.Assert.assertFalse;

public class MPPQuestionaairePage {
  private static final Logger logger = LogManager.getLogger(MPPQuestionaairePage.class.getName());

  public static void answers_8a_Questioannaire(WebDriver webDriver, String YesNo) throws Exception {
    // Elements Tags:
    if (!YesNo.equals(null) && !YesNo.equals("")) {
      switch (YesNo.toLowerCase()) {
        case "yes":
          click_Element(webDriver, "MPP_8a_Yes");
          generic_file_Upld(webDriver);
          break;
        case "no":
          click_Element(webDriver, "MPP_8a_No");
          click_Element(webDriver, "Application_Common_Submit_Button");
          break;
        case "assert_no":
          assertFalse(find_Element(webDriver, "MPP_8a_No").getAttribute("outerHTML").toLowerCase()
              .contains("checked"));
          click_Element(webDriver, "MPP_8a_No");
          click_Element(webDriver, "Application_Common_Submit_Button");
          break;
        case "assert_yes":
          assertFalse(find_Element(webDriver, "MPP_8a_yes").getAttribute("outerHTML").toLowerCase()
              .contains("checked"));
          click_Element(webDriver, "MPP_8a_Yes");
          click_Element(webDriver, "Application_Common_Submit_Button");
          break;
      }
    }
  }

  public static void eligibilityPage(WebDriver webDriver, String EYesNO, String MPYesNO,
      String DAYesNo, String MEYesNo) throws Exception {
    //
    try {
      if (!EYesNO.equals(null) && !EYesNO.equals("")) {
        switch (EYesNO.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Eligibility_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Eligibility_No");
            break;
          case "assert_no":
            assertFalse(find_Element(webDriver, "MPP_Eligibility_No").getAttribute("outerHTML")
                .toLowerCase().contains("checked"));
            click_Element(webDriver, "MPP_Eligibility_No");
            break;
          case "assert_yes":
            assertFalse(find_Element(webDriver, "MPP_Eligibility_Yes").getAttribute("outerHTML")
                .toLowerCase().contains("checked"));
            click_Element(webDriver, "MPP_Eligibility_Yes");
            break;
        }
      }
      if (!MPYesNO.equals(null) && !MPYesNO.equals("")) {
        switch (MPYesNO.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_MentorProfit_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_MentorProfit_No");
            break;
          case "assert_yes":
            assertFalse(find_Element(webDriver, "MPP_MentorProfit_Yes").getAttribute("outerHTML")
                .toLowerCase().contains("checked"));
            click_Element(webDriver, "MPP_MentorProfit_Yes");
            break;
          case "assert_no":
            assertFalse(find_Element(webDriver, "MPP_MentorProfit_No").getAttribute("outerHTML")
                .toLowerCase().contains("checked"));
            click_Element(webDriver, "MPP_MentorProfit_No");
            break;
        }
      }
      if (!DAYesNo.equals(null) && !DAYesNo.equals("")) {
        switch (DAYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Determination_Affiliation_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Determination_Affiliation_No");
            break;
          case "assert_yes":
            assertFalse(find_Element(webDriver, "MPP_Determination_Affiliation_Yes")
                .getAttribute("outerHTML").toLowerCase().contains("checked"));
            click_Element(webDriver, "MPP_Determination_Affiliation_Yes");
            break;
          case "assert_no":
            assertFalse(find_Element(webDriver, "MPP_Determination_Affiliation_No")
                .getAttribute("outerHTML").toLowerCase().contains("checked"));
            click_Element(webDriver, "MPP_Determination_Affiliation_No");
            break;
        }
      }
      if (!MEYesNo.equals(null) && !MEYesNo.equals("")) {
        switch (MEYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Mentor_Equityinterest_yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Mentor_Equityinterest_No");
            break;
          case "assert_yes":
            assertFalse(find_Element(webDriver, "MPP_Mentor_Equityinterest_yes")
                .getAttribute("outerHTML").toLowerCase().contains("checked"));
            click_Element(webDriver, "MPP_Mentor_Equityinterest_yes");
            break;
          case "assert_no":
            assertFalse(find_Element(webDriver, "MPP_Mentor_Equityinterest_No")
                .getAttribute("outerHTML").toLowerCase().contains("checked"));
            click_Element(webDriver, "MPP_Mentor_Equityinterest_No");
            break;

        }
      }
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void select_NALCS_Code(WebDriver webDriver, String workNaicsYesNo,
      String smallNaicsYesNo) throws Exception {
    try {

      // "assert_NotEquals_1"

      // Elements Tags:
      Select aa1 = new Select(find_Element(webDriver, "MPP_NAICS_Code"));
      WebElement aa = find_Element(webDriver, "MPP_small_NAICSCode_Error_Message", true);
      if (!(aa == null) || (aa1.getOptions().get(0).getText() == "")) {
        throw new Exception(" NAICS Code not found - pls set up one");
      }
      aa1.selectByIndex(1);

      if (!workNaicsYesNo.equals(null) && !workNaicsYesNo.equals("")) {
        switch (workNaicsYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_work_NAICSCode_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_work_NAICSCode_No");
            break;

        }
      }
      if (!smallNaicsYesNo.equals(null) && !smallNaicsYesNo.equals("")) {
        switch (smallNaicsYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_small_NAICSCode_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_small_NAICSCode_No");

            break;
        }
      }
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void size_Determination(WebDriver webDriver, String sizeDeterminationYesNo)
      throws Exception {
    try {
      // Elements Tags:
      if (!sizeDeterminationYesNo.equals(null) && !sizeDeterminationYesNo.equals("")) {
        switch (sizeDeterminationYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Size_Determination_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Size_Determination_No");
            break;
        }
      }
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void size_ReDetermination(WebDriver webDriver, String sizeReDeterminationYesNo)
      throws Exception {
    try {
      // Elements Tags:
      if (!sizeReDeterminationYesNo.equals(null) && !sizeReDeterminationYesNo.equals("")) {
        switch (sizeReDeterminationYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Size_Re_Determination_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Size_Re_Determination_No");
            break;
        }
      }
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void reDetermination_Info(WebDriver webDriver) throws Exception {
    try {
      // Elements Tags:
      generic_file_Upld(webDriver);
      setText_Element(webDriver, "MPP_SBA_Area", "SBAOne");
      setText_Element(webDriver, "MPP_Determination_Date", "09/09/2001");
      click_Element(webDriver, "Application_Common_Submit_Button");
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void training(WebDriver webDriver) throws Exception {
    try {
      // Elements Tags:
      generic_file_Upld(webDriver);
    } catch (Exception e) {
      throw e;
    }
  }

  public static void plan_Agreement(WebDriver webDriver, String ActivementorYesNo)
      throws Exception {
    try {
      // Elements Tags:
      generic_file_Upld(webDriver);
      if (!ActivementorYesNo.equals(null) && !ActivementorYesNo.equals("")) {
        switch (ActivementorYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Active_MentorProtege_Agrement_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Active_MentorProtege_Agrement_No");
            break;
        }
      }
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void activeAgreement(WebDriver webDriver) throws Exception {
    try {
      // Elements Tags:
      click_Element(webDriver, "MPP_Active_Agrement");
      new Select(find_Element(webDriver, "MPP_Role_Agrement")).selectByIndex(1);
      new Select(find_Element(webDriver, "MPP_Agency_Agrement")).selectByIndex(1);
      setText_Element(webDriver, "MPP_Date_Agrement", "08/07/1998");
      setText_Element(webDriver, "MPP_OtherBusiness_Agrement", "sbaautomation");
      new Select(find_Element(webDriver, "MPP_OtherNAICSCode_Agrement")).selectByIndex(1);
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void activeAgreementDcoument(WebDriver webDriver) throws Exception {
    try {
      // Elements Tags:
      generic_file_Upld(webDriver);
    } catch (Exception e) {
      throw e;
    }
  }

  public static void mppAgreementDcoument(WebDriver webDriver) throws Exception {
    try {
      // Elements Tags:
      generic_file_Upld(webDriver);
    } catch (Exception e) {
      throw e;
    }
  }

  public static void determination_Needs_Page(WebDriver webDriver, String MTYesNo, String FAYesNo,
      String CAYes, String INTYesNo, String BDYesNo, String GAYesNo) throws Exception {
    try {
      // Elements Tags:
      if (!MTYesNo.equals(null) && !MTYesNo.equals("")) {
        switch (MTYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Management_and_Technical_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Management_and_Technical_No");
            break;
        }
      }
      if (!FAYesNo.equals(null) && !FAYesNo.equals("")) {
        switch (FAYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Financial_Assistance_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Financial_Assitance_No");
            break;
        }
      }
      if (!CAYes.equals(null) && !CAYes.equals("")) {
        switch (CAYes.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Contracting_Assitance_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Contracting_Assitance_No");
            break;
        }
      }
      if (!INTYesNo.equals(null) && !INTYesNo.equals("")) {
        switch (INTYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_International_Trade_Education_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_International_Trade_Education_No");
            break;
        }
      }
      if (!BDYesNo.equals(null) && !BDYesNo.equals("")) {
        switch (BDYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_Business_Development_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_Business_Development_No");
            break;
        }
      }
      if (!GAYesNo.equals(null) && !GAYesNo.equals("")) {
        switch (GAYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "MPP_General_and_Administrative_Yes");
            break;
          case "no":
            click_Element(webDriver, "MPP_General_and_Administrative_No");
            break;
        }
      }
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void Management_and_Technical_Needs(WebDriver webDriver, String Text)
      throws Exception {
    try {
      // Elements Tags:
      setText_Element(webDriver, "MPP_Management_and_Technical_Needs_Textbox1", Text);
      setText_Element(webDriver, "MPP_Management_and_Technical_Needs_Textbox2", Text);
      setText_Element(webDriver, "MPP_Management_and_Technical_Needs_Textbox3", Text);
      setText_Element(webDriver, "MPP_Management_and_Technical_Needs_Textbox4", Text);
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void Financial_Needs(WebDriver webDriver, String Text) throws Exception {
    try {
      // Elements Tags:
      setText_Element(webDriver, "MPP_Financial_Needs_Textbox1", Text);
      setText_Element(webDriver, "MPP_Financial_Needs_Textbox2", Text);
      setText_Element(webDriver, "MPP_Financial_Needs_Textbox3", Text);
      setText_Element(webDriver, "MPP_Financial_Needs_Textbox4", Text);
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void Contracting_Needs(WebDriver webDriver, String Text) throws Exception {
    try {
      // Elements Tags:
      setText_Element(webDriver, "MPP_Contracting_Needs_Textbox1", Text);
      setText_Element(webDriver, "MPP_Contracting_Needs_Textbox2", Text);
      setText_Element(webDriver, "MPP_Contracting_Needs_Textbox3", Text);
      setText_Element(webDriver, "MPP_Contracting_Needs_Textbox4", Text);
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void Intl_Trade_Needs(WebDriver webDriver, String Text) throws Exception {
    try {
      // Elements Tags:
      setText_Element(webDriver, "MPP_Intl_Trade_Needs_Textbox1", Text);
      setText_Element(webDriver, "MPP_Intl_Trade_Needs_Textbox2", Text);
      setText_Element(webDriver, "MPP_Intl_Trade_Needs_Textbox3", Text);
      setText_Element(webDriver, "MPP_Intl_Trade_Needs_Textbox4", Text);
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void Business_Development_Needs(WebDriver webDriver, String Text) throws Exception {
    try {
      // Elements Tags:
      setText_Element(webDriver, "MPP_Business_Development_Needs_Textbox1", Text);
      setText_Element(webDriver, "MPP_Business_Development_Needs_Textbox2", Text);
      setText_Element(webDriver, "MPP_Business_Development_Needs_Textbox3", Text);
      setText_Element(webDriver, "MPP_Business_Development_Needs_Textbox4", Text);
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void General_and_Administrative_Needs(WebDriver webDriver, String Text)
      throws Exception {
    try {
      // Elements Tags:
      setText_Element(webDriver, "MPP_General_and_Administrative_Needs_Textbox1", Text);
      setText_Element(webDriver, "MPP_General_and_Administrative_Needs_Textbox2", Text);
      setText_Element(webDriver, "MPP_General_and_Administrative_Needs_Textbox3", Text);
      setText_Element(webDriver, "MPP_General_and_Administrative_Needs_Textbox4", Text);
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      throw e;
    }
  }

  public static void mpp_training(WebDriver webDriver) throws Exception {
    try {
      // Elements Tags:
      generic_file_Upld(webDriver);
    } catch (Exception e) {
      throw e;
    }
  }

  public static void mpp_BusinessInfo(WebDriver webDriver, String MentorDunsNO) throws Exception {
    try {
      // Elements Tags:
      setText_Element(webDriver, "SBA_8a_Duns_Confrm_Text", MentorDunsNO);
      click_Element(webDriver, "Search_Duns_No");
      accept_Alert(webDriver, 10);
      for (int i = 0; i < 2; i++) {
        WebElement aa = find_Element(webDriver, "SBA_8a_Duns_Confrm_Text_Error", true);
        if (aa != null) {
          click_Element(webDriver, "Search_Duns_No");
          accept_Alert(webDriver, 10);
          accept_Alert(webDriver, 10);
          i = 9999;
        }
      }
      click_Element(webDriver, "Application_Common_Submit_Button");

    } catch (Exception e) {
      throw e;
    }
  }

}
