// Ts Created by Deepa patri
package gov.sba.pageObjetcs;


import gov.sba.utils.integration.newMppUploadDocumentPageDeepa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.automation.FixtureUtils.fixturesDir;

public class master_Application_8A {

  private static final Logger logger = LogManager.getLogger(master_Application_8A.class.getName());

  public static void masterApp_8a_Page_Click(WebDriver webDriver, String link_To_Click)
      throws Exception {
    // Elements tag: @8a_Initial_Program
    if (!link_To_Click.equals(null) && !link_To_Click.equals("")) {
      switch (link_To_Click.toLowerCase()) {
        case "page_contributors":
          click_Element(webDriver, "8a_Application_Page_Contributers_Link");
          break;
        case "page_contributors_start_indv_cont":
          click_Element(webDriver, "8a_Application_Page_Contributers_Link");
          click_Element(webDriver, "8a_Application_Page_Contributers_Link_Start_Indv");
          click_Element(webDriver, "Application_Common_Accept_Button");
          break;
        case "page_basiceligibility":
          click_Element(webDriver, "8a_Application_Page_BasicEligibility_Link");
          break;
        case "page_business_ownership":
          click_Element(webDriver, "8a_Application_Page_Business_Ownership_Link");
          break;
        case "page_character_link":
          click_Element(webDriver, "8a_Application_Page_Character_Link");
          break;
        case "page_control_link":
          click_Element(webDriver, "8a_Application_Page_Control_Link");
          break;
      }

    }
  }

  public static void Contributor_Page(WebDriver webDriver, String link_To_Click) throws Exception {
    // Elements tag: @8a_Initial_Program- Contributor
    if (!link_To_Click.equals(null) && !link_To_Click.equals("")) {
      switch (link_To_Click.toLowerCase()) {
        case "8a_DisAdvInd_contributor":
          click_Element(webDriver, "Contributor_Add_DisAdv_Ind_Link");
          break;
        case "8a_AddSpouse_contributor":
          click_Element(webDriver, "Contributor_Add_Spouse_Ind_Link");
          break;
        case "8a_AddBusPart_contributor":
          click_Element(webDriver, "Contributor_Add_Business_Ind_Link");
          break;
      }
    }
  }

  public static void BasicEligiblity_General_Assessment_Page(WebDriver webDriver,
      String ProfileYesNo, String BrokenYesNo, String RevenueYesNo, String CitizenYesNo,
      String DBANameYesNo) throws Exception {
    try {
      // Elements tag: @8a_Initial_Program Basic Eligiblity -General Assessment
      if (!ProfileYesNo.equals(null) && !ProfileYesNo.equals("")) {
        switch (ProfileYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_General_Assessment_profit_business_Yes");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_General_Assessment_profit_business_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any button
      if (!BrokenYesNo.equals(null) && !BrokenYesNo.equals("")) {
        switch (BrokenYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_General_Assessment_a_broker_Yes");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_General_Assessment_a_broker_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any button
      if (!RevenueYesNo.equals(null) && !RevenueYesNo.equals("")) {
        switch (RevenueYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_General_Assessment_any_revenue_Yes");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_General_Assessment_any_revenue_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any buttonbutton
      if (!CitizenYesNo.equals(null) && !CitizenYesNo.equals("")) {
        switch (CitizenYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_General_Assessment_U.S._citizens_Yes");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_General_Assessment_U.S._citizens_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any buttonbutton
      if (!DBANameYesNo.equals(null) && !DBANameYesNo.equals("")) {
        switch (DBANameYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_General_Assessment_DBA_Name_Yes");
            setText_Element(webDriver, "BasicEligibility_General_Assessment_DBA_Name_Yes_Comment",
                "QA Testing");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_General_Assessment_DBA_Name_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any buttonbutton
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void BasicEligiblity_Prior_8a_Involvement_Page(WebDriver webDriver,
      String PartcipantYesNo, String SubmittedYesNo, String AssetYesNo) throws Exception {
    try {
      // Elements tag: @8a_Initial_Program Basic Eligiblity-Prior 8(a) Involvement
      if (!PartcipantYesNo.equals(null) && !PartcipantYesNo.equals("")) {
        switch (PartcipantYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver,
                "BasicEligibility_Prior_8a_Involvement_Program_Participant_Yes");
            break;
          case "no":
            click_Element(webDriver,
                "BasicEligibility_Prior_8a_Involvement_Program_Participant_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any buttonbutton
      if (!SubmittedYesNo.equals(null) && !SubmittedYesNo.equals("")) {
        switch (SubmittedYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_Prior_8a_Involvement_Submitted_Yes");
            setText_Element(webDriver, "BasicEligibility_Prior_8a_InvolvementSubmitted_Yes_Comment",
                "QA");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_Prior_8a_Involvement_Submitted_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any buttonbutton
      if (!AssetYesNo.equals(null) && !AssetYesNo.equals("")) {
        switch (AssetYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_Prior_8a_Involvement_firm_assets_Yes");
            new newMppUploadDocumentPageDeepa(webDriver)
                .deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_Prior_8a_Involvement_firm_assets_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any buttonbutton
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void BasicEligiblity_Outside_Assistance_Page(WebDriver webDriver, String YesNo)
      throws Exception {
    try {
      // Elements tag: @8a_Initial_Program Basic Eligiblity-Prior 8(a) Involvement
      if (!YesNo.equals(null) && !YesNo.equals("")) {
        switch (YesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_Outside Assistance_Outside_Consultant_Yes");
            new newMppUploadDocumentPageDeepa(webDriver)
                .deepaUploadMppDocument(fixturesDir() + "Upload.pdf");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_Outside Assistance_Outside_Consultant_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any buttonbutton
      click_Element(webDriver, "Application_Common_Submit_Button");
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }

  public static void BasicEligiblity_Business_Size_Page(WebDriver webDriver, String NAICSYesNo,
      String SizeYesNo) throws Exception {
    try {
      // Elements tag: @8a_Initial_Program Basic Eligiblity-Prior 8(a) Involvement
      if (!NAICSYesNo.equals(null) && !NAICSYesNo.equals("")) {
        switch (NAICSYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_Business_Size_NAICS_Yes");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_Business_Size_NAICS_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any buttonbutton
      if (!SizeYesNo.equals(null) && !SizeYesNo.equals("")) {
        switch (SizeYesNo.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "BasicEligibility_Business_Size_Determination_Yes");
            break;
          case "no":
            click_Element(webDriver, "BasicEligibility_Business_Size_Determination_No");
            break;
        }
      }
      Thread.sleep(1200);// Sleep Needed here as elements are dynamically moving by the time we
                         // click any buttonbutton
      click_Element(webDriver, "Application_Common_Submit_Button");

    } catch (Exception e) {
      logger.info(e.toString());
      logger.info(e.toString());
      throw e;
    }
  }

  public static void BasicEligiblity_Size_Determination_Page(WebDriver webDriver) throws Exception {
    try {
      // Elements tag: @8a_Initial_Program Basic Eligiblity-Prior 8(a) Involvement
      click_Element(webDriver, "BasicEligibility_Size_Up1_Add_Req");
      Thread.sleep(900);
      click_Element(webDriver, "Upload_doc_Upload_Button");
      Thread.sleep(900);
      click_Element(webDriver, "BasicEligibility_Size_Add_Req_Choose");
      Thread.sleep(900);
      contributor_8a_Disadvantaged_IndApp_Page.doUpload_Action();
      Thread.sleep(800);
      click_Element(webDriver, "Upload_Attach_Button");
      new Select(find_Element(webDriver, "BasicEligibility_SBA_Office")).selectByIndex(1);
      setText_Element(webDriver, "BasicEligibility_determination_Date", "05/31/2017");
      click_Element(webDriver, "Application_Common_Submit_Button");
      accept_Alert(webDriver, 2);
      Thread.sleep(2000);
      click_Element(webDriver, "Application_Common_Submit_Button");
      accept_Alert(webDriver, 8);
      Thread.sleep(2000);
    } catch (Exception e) {
      logger.info(e.toString());
      throw e;
    }
  }
}
