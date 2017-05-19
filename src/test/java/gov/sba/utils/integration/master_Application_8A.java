//Ts Created by Deepa patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.find_Element_Loc;
import static gov.sba.automation.CommonApplicationMethods.getLocator;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.SbaOne_Pages;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class master_Application_8A {


  public static void page_Click(WebDriver webDriver, String link_To_Click) throws Exception {

    if (!link_To_Click.equals(null) && !link_To_Click.equals("")) {
      switch (link_To_Click.toLowerCase()) {
        case "page_contributers":
          CommonApplicationMethods.click_Element(webDriver, "8a_Master_Application_Page_Contributers_Link");
          break;
        case "Page_BasicEligibility":
          CommonApplicationMethods.click_Element(webDriver, "8a_Master_Application_Page_BasicEligibility_Link");
          break;
        case "page_Business Ownership":
          CommonApplicationMethods.click_Element(webDriver, "8a_Master_Application_Page_Business Ownership_Link");
          break;
        case "page_Character_Link":
          CommonApplicationMethods.click_Element(webDriver, "8a_Master_Application_Page_Character_Link");
          break;
        case "page_Control_Link":
          CommonApplicationMethods.click_Element(webDriver, "8a_Master_Application_Page_Control_Link");
          break;
        case "page_Company Info":
          CommonApplicationMethods.click_Element(webDriver, "8a_Master_Application_Page_Company Info_Link");
          break;
      }

    }


  }
}
