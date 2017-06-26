/** Created by deepa on 5/15/2017. */
package gov.sba.pageObjetcs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static gov.sba.automation.CommonApplicationMethods.*;
import static org.junit.Assert.assertNotNull;

public class analyst_Cases_Page {
  private static final Logger logger_Cases_Page =
      LogManager.getLogger(analyst_Cases_Page.class.getName());

  public static void search_Duns_And_Verify(WebDriver webDriver, String which_Duns,
      String click_Duns_YesORNo, String verify_State, String delete_Draft) throws Exception {
    // Elements Tags: @My_Contracting_Programs

    if (!which_Duns.equals(null) && !which_Duns.equals("")) {
      navigationMenuClick(webDriver, "Cases");
      setText_Element(webDriver, "Cases_Page_Search_Duns_Text", which_Duns);
      click_Element(webDriver, "Cases_Page_Search_Duns_Button");
    }

    if (!click_Duns_YesORNo.equals(null) && !click_Duns_YesORNo.equals("")) {
      if (click_Duns_YesORNo.toUpperCase().indexOf("Y") > 0) {
        Map locator = getLocator("Cases_Page_Search_Duns_Link");
        String a1 = locator.get("Locator").toString();
        String a2 = locator.get("Value").toString().replace("duns_Number_Replace", which_Duns);
        click_Element(webDriver, a1, a2);
      }
    }
    if (!verify_State.equals(null) && !verify_State.equals("")) {
      if (verify_State.toUpperCase().indexOf("PEND") > 0) {
        click_Element(webDriver, "Cases_Page_Verify_Duns_Pending");
      }
    }

    if (!delete_Draft.equals(null) && !delete_Draft.equals("")) {
      if (delete_Draft.toUpperCase().indexOf("Y") > 0) {
        Boolean isPresent =
            (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
        logger_Cases_Page.info(isPresent);
        while (isPresent) {
          webDriver.findElement(By.xpath("//a[@class='delete-cert']")).click();
          webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
          isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
          logger_Cases_Page.info(isPresent);
          isPresent = false;
        }
      }
    }
  }

  public static void return_DunsNo_Cases_Table(WebDriver webDriver, String which_Duns,
      String app_Type) throws Exception {
    navigationBarClick(webDriver, "Cases");
    search_Cases_Duns_Number_Table(webDriver, which_Duns);
    Map locator = getLocator("Cases_Page_Search_Duns_Link");
    String loc = locator.get("Locator").toString();
    String val = locator.get("Value").toString().replace("duns_Number_Replace", which_Duns);
    click_Element(webDriver, loc, val);

    assertNotNull(find_Element(webDriver, "Vendor_Overview_Page_Rt_Vend_All", true));
    // assertNotNull(find_Element(webDriver,"Vendor_Overview_Page_Download_Zip",true));
    click_Element(webDriver, "Vendor_Overview_Page_Rt_Vend_All");

  }
}
