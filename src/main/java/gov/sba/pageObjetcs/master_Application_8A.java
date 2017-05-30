//Ts Created by Deepa patri
package gov.sba.pageObjetcs;

import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
public class master_Application_8A {

    public static void masterApp_8a_Page_Click(WebDriver webDriver, String link_To_Click) throws Exception {
        // Elements tag: @8a_Initial_Program
    if (!link_To_Click.equals(null) && !link_To_Click.equals("")) {
      switch (link_To_Click.toLowerCase()) {
        case "page_contributors":
            click_Element(webDriver, "8a_Master_Application_Page_Contributers_Link");
          break;
          case "page_contributors_start_indv_cont":
              click_Element(webDriver, "8a_Master_Application_Page_Contributers_Link");
              click_Element(webDriver, "8a_Master_Application_Page_Contributers_Link_Start_Indv");
              click_Element(webDriver, "Application_Common_Accept_Button");
          break;
          case "page_basiceligibility":
              click_Element(webDriver, "8a_Master_Application_Page_BasicEligibility_Link");
          break;
          case "page_business_ownership":
              click_Element(webDriver, "8a_Master_Application_Page_Business Ownership_Link");
          break;
          case "page_character_link":
              click_Element(webDriver, "8a_Master_Application_Page_Character_Link");
          break;
          case "page_control_link":
              click_Element(webDriver, "8a_Master_Application_Page_Control_Link");
              break;
          case "page_company_info":
              click_Element(webDriver, "8a_Master_Application_Page_Company Info_Link");
          break;
      }

    }


  }
}
