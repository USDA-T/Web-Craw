/* Created BY Deepa Patri */
package gov.sba.pageObjetcs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.pageObjetcs.ProgramsPage.generic_file_Upld;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WosbCorpScorpQuestionnairePage {
  private static final Logger logger =
      LogManager.getLogger(WosbCorpScorpQuestionnairePage.class.getName());

  public static void wosb_Questionnaire_8a_Page(WebDriver webDriver, String yesno)
      throws Exception {
    /* Elements tag: 8a_Questionnaire */
    try {
      if (!yesno.equals(null) && !yesno.equals("")) {
        switch (yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_Yes");
            generic_file_Upld(webDriver);
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_No");
              click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
            break;
            case "assert_no":
                assertFalse(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_No").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_No");
                click_Element(webDriver, "Application_Common_Submit_Button");
                break;
            case "assert_yes":
                assertFalse(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_Yes").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_Yes");
                generic_file_Upld(webDriver);
                click_Element(webDriver, "Application_Common_Submit_Button");
                break;
        }
      }
        } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void wosb_Questionnaire_ThridParty_Page(WebDriver webDriver, String yesno)
      throws Exception {
    /* Elements tag: Third Party Certification */
    try {
      if (!yesno.equals(null) && !yesno.equals("")) {
        switch (yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_Yes");
            generic_file_Upld(webDriver);
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_No");
            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
            break;
            case "assert_no":
                assertFalse(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_No").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_No");
                click_Element(webDriver, "Application_Common_Submit_Button");
                break;
            case "assert_yes":
                assertFalse(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_Yes").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_Yes");
                generic_file_Upld(webDriver);
                click_Element(webDriver, "Application_Common_Submit_Button");
                break;
        }
      }
     } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void wosb_Questionnaire_ChangeEligiblity_Page(WebDriver webDriver, String yesno)
      throws Exception {
    /* Elements tag: Changes in Eligiblity */
    try {
      if (!yesno.equals(null) && !yesno.equals("")) {
        switch (yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_N");
            break;
            case "assert_no":
                assertFalse(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_N");
                break;
            case "assert_yes":
                assertFalse(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_Y");
                break;
        }
      }
      click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  // Verify the more detail for the Non-qualification question
  public static void wosb_Questionnaire_NonQualification_Page(WebDriver webDriver, String yesno)
      throws Exception {
    /* Elements tag: Non-Qualification */
    try {
      if (!yesno.equals(null) && !yesno.equals("")) {
        switch (yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_191_Y");
            generic_file_Upld(webDriver);
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_191_Y");
            break;
        }
      }
      click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void WOSB_Questionnaire_Cooperation_Scorp_Page(WebDriver webDriver,
      String stockYesNo, String votingyesno, String voting51yesno, String unexercisedyesno,
      String unexercised1yesno, String unexercised2yesno) throws Exception {
    try {
      // Elements tag: @Scorp questions beings
      if (!stockYesNo.equals(null) && !stockYesNo.equals("")) {
        switch (stockYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_192_Y");
            generic_file_Upld(webDriver);
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_192_N");
            setText_Element(webDriver, "WOSB_Questionnaire_Page_Ans_192_Comment", "QA testing");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_192_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_192_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!votingyesno.equals(null) && !votingyesno.equals("")) {
        switch (votingyesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_193_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_193_N");
            break;
            case "assert_no":
              assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_193_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_193_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!voting51yesno.equals(null) && !voting51yesno.equals("")) {
        switch (voting51yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_194_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_194_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_194_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_194_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!unexercisedyesno.equals(null) && !unexercisedyesno.equals("")) {
        switch (unexercisedyesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_195_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_195_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_195_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_195_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }

      if (!unexercised1yesno.equals(null) && !unexercised1yesno.equals("")) {
        switch (unexercised1yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_196_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_196_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_196_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_196_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!unexercised2yesno.equals(null) && !unexercised2yesno.equals("")) {
        switch (unexercised2yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_197_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_197_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_197_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_197_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!unexercised2yesno.equals(null) && !unexercised2yesno.equals("")) {
        switch (unexercised2yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_198_Y");
            generic_file_Upld(webDriver,"WOSB_Questionnaire_Page_Ans_Attachment_All");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_198_N");
            setText_Element(webDriver, "WOSB_Questionnaire_Page_Ans_198_Comment", "QA testing");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_198_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_198_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      click_Element(webDriver, "Application_Common_Submit_Button");
      /* Scorp questions ends */
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void wosb_Questionnaire_CitizenShip_Page(WebDriver webDriver, String yesno)
      throws Exception {
    /* Elements tag: // CitizenShip */
    try {
      if (!yesno.equals(null) && !yesno.equals("")) {
        switch (yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_203_Y");
            generic_file_Upld(webDriver);
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_203_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_203_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_203_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void WOSB_Questionnaire_cooperation_Ownership_Page(WebDriver webDriver,
      String ownershipbenfitYesNo, String businessentityYesNo, String ownershipbeneficiaryYesNoNA)
      throws Exception {
    try {
      // Elements tag: @--// OwnerShip
      if (!ownershipbenfitYesNo.equals(null) && !ownershipbenfitYesNo.equals("")) {
        switch (ownershipbenfitYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_204_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_204_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_204_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_204_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!businessentityYesNo.equals(null) && !businessentityYesNo.equals("")) {
        switch (businessentityYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_205_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_205_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_205_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_205_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!ownershipbeneficiaryYesNoNA.equals(null) && !ownershipbeneficiaryYesNoNA.equals("")) {
        switch (ownershipbeneficiaryYesNoNA.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_206_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_206_N");
            setText_Element(webDriver, "WOSB_financial_Page_Ans_206_setText", "QA testing");
            break;
          case "na":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_206_NA");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_206_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_206_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  // Management
  public static void WOSB_Questionnaire_cooperation_Management_Page(WebDriver webDriver,
      String managementYesNo, String businessYesNo, String managerialYesNo, String fulltimeYesNo,
      String controlYesNo, String LongtermYesNo) throws Exception {
    try {
      // Elements tag: @--// Management
      if (!managementYesNo.equals(null) && !managementYesNo.equals("")) {
        switch (managementYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_207_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_207_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_207_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_207_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!businessYesNo.equals(null) && !businessYesNo.equals("")) {
        switch (businessYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_208_Y");
            generic_file_Upld(webDriver);
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_208_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_208_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_208_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!managerialYesNo.equals(null) && !managerialYesNo.equals("")) {
        switch (managerialYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_209_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_209_N");
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_209_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_209_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!fulltimeYesNo.equals(null) && !fulltimeYesNo.equals("")) {
        switch (fulltimeYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_210_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_210_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_210_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_210_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!controlYesNo.equals(null) && !controlYesNo.equals("")) {
        switch (controlYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_211_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_211_N");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_211_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_211_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      if (!LongtermYesNo.equals(null) && !LongtermYesNo.equals("")) {
        switch (LongtermYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_212_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_212_N");
            setText_Element(webDriver, "WOSB_financial_Page_Ans_212_setText", "QA testing");
            break;
            case "assert_no":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_212_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
            case "assert_yes":
                assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_212_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                break;
        }
      }
      click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void wosb_Questionnaire_SBAExam_Page(WebDriver webDriver, String yesno)
      throws Exception {
    /* Elements tag: /* SBA EXAM */
    try {
      if (!yesno.equals(null) && !yesno.equals("")) {
        switch (yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_Y");
            break;
          case "no":
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_N");
            break;
            case "assert_no":
                assertFalse(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_N").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_N");
                break;
            case "assert_yes":
                assertFalse(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_Y").getAttribute("outerHTML").toLowerCase()
                    .contains("checked"));
                click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_Y");
                break;
        }
      }
      click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }
    public static void wosb_Review_Page(WebDriver webDriver) throws Exception {
        try {
            // Elements Tags: Application_Common_Submit_Button
            accept_Alert(webDriver, 30);
            click_Element(webDriver, "Application_Common_Submit_Button");
            accept_Alert(webDriver, 4);
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }

    }

  }

