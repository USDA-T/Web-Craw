package gov.sba.automation;

import static gov.sba.automation.CommonApplicationMethods.accept_Alert;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.navigationBarClick;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.non_Vendor_searchDuns_Number;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.LoginPageWithReference;

public class AssertionUtils {
  public static void verify_Element_Property(WebDriver webdriver, String locator_Yaml,
      String property_Yaml) throws Exception {
    Map locator = CommonApplicationMethods.getLocator(locator_Yaml);

    WebElement click_element =
        find_Element(webdriver, locator.get("Locator").toString(), locator.get("Value").toString());

    Map prop = CommonApplicationMethods.getLocator(property_Yaml);
    String prop_Name = prop.get("PropName").toString();
    String prop_Value = prop.get("PropValue").toString();

    switch (prop_Name.toLowerCase()) {
      case "enabled":
        assertTrue(click_element.isEnabled());
        break;
      case "disabled":
        assertFalse(click_element.isEnabled());
        break;
      case "visible":
        assertTrue(click_element.isDisplayed());
        break;
      case "notvisible":
      case "invisible":
      case "hidden":
        assertFalse(click_element.isDisplayed());
        break;
      default:
        assertEquals("Assertion Type is", "Incorrect");
        break;
    }
  }

  public static void delete_all_Drafts(WebDriver webDriver) throws Exception {

    Boolean FlagForAddEDWOSBNotPresent = true;
    CommonApplicationMethods.navigationMenuClick(webDriver, "DashBoard");
    List<WebElement> current_Row_Check_02 = webDriver
        .findElements(By.xpath("//table[@id='certifications']//td/a[contains(text(),'elete')] "));
    if (current_Row_Check_02.size() > 0) {

      for (int i = 0; i < current_Row_Check_02.size(); i++) {
        FlagForAddEDWOSBNotPresent = false;
        current_Row_Check_02.get(0).click();
        accept_Alert(webDriver, 8);
        Thread.sleep(1500); // Sleep Needed Deepa
        webDriver.navigate().refresh();
        current_Row_Check_02 = webDriver.findElements(
            By.xpath("//table[@id='certifications']//td/a[contains(text(),'elete')] "));
        i = 0;
        FlagForAddEDWOSBNotPresent = true;
      }
    }
    assertTrue(FlagForAddEDWOSBNotPresent);

  }

  public static void returnAll_App_To_Vendor(WebDriver webDriver, String duns_Number)
      throws Exception {

    Boolean FlagForReturn = true;
    List<WebElement> current_Row_Check_02 =
        CommonApplicationMethods.find_Elements(webDriver, "Vendor_Overview_Page_Rt_Vend_All");
    if (current_Row_Check_02.size() > 0) {
      for (int i = 0; i < current_Row_Check_02.size(); i++) {
        FlagForReturn = false;
        current_Row_Check_02.get(0).click();
        webDriver.navigate().refresh();
        current_Row_Check_02 =
            CommonApplicationMethods.find_Elements(webDriver, "Vendor_Overview_Page_Rt_Vend_All");
        i = 0;
        FlagForReturn = true;
      }
    }
    assertTrue(FlagForReturn);
  }

  public static void returnApplicationToVendorMethd(WebDriver webDriver,
      int which_Loginto_ReturnApp, String duns_Number, String type_Of_App, String status_Of_App,
      int which_Log_BackAgain) throws Exception {

    // Login provided should be Analyst
    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
    new LoginPageWithReference(webDriver, which_Loginto_ReturnApp).Login_With_Reference();
    non_Vendor_searchDuns_Number(webDriver, duns_Number);

    webDriver
        .findElement(By.xpath(
            "//*[@id='business_search']/div[h2[contains(text(),'Search Results')]]/div[1]/div/h4/a"))
        .click();
    switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
      case "edwosbactive":
        webDriver
            .findElement(By.xpath(
                "//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'EDWOSB') ]) and ( td[ position()=5 and contains(text(),'Active') ] ) ]/td[position()=7]/a[contains(text(),'Return to Vendor')]"))
            .click();
        accept_Alert(webDriver, 22);
        break;
      case "wosbactive":
        webDriver
            .findElement(By.xpath(
                "//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'WOSB') and not(contains(text(),'EDWOSB')) ]) and ( td[ position()=5 and contains(text(),'Active') ] ) ]/td[position()=7]/a[contains(text(),'Return to Vendor')]"))
            .click();
        accept_Alert(webDriver, 22);
        break;
      case "mppactive":
        webDriver
            .findElement(By.xpath(
                "//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'MPP') ]) and ( td[ position()=5 and contains(text(),'Active') ] ) ]/td[position()=7]/a[contains(text(),'Return to Vendor')]"))
            .click();
        accept_Alert(webDriver, 22);
        break;
    }
    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
    new LoginPageWithReference(webDriver, which_Log_BackAgain).Login_With_Reference();

  }

  public static void onlyReturnAppToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp,
      String duns_Number, String type_Of_App, String status_Of_App, int which_Log_BackAgain)
      throws Exception {
    // Login provided should be Analyst
    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
    login_Data.Login_With_Reference();

    CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
    WebElement current_Row_e =
        webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody"));

    List<WebElement> current_Row = current_Row_e.findElements(
        By.xpath("tr[ " + "td[position()=8 and contains(text(),'Under Review')]   and "
            + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
            + "td[position()=3 and contains(text(),'" + type_Of_App + "')]	" + "]"));

    if (current_Row.size() > 0) {
      WebElement a1 =
          current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
      a1.click();
      webDriver
          .findElement(
              By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]"))
          .click();
      webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
      webDriver.findElement(By.xpath("//input[contains(@value,'Save and commit')]")).click();
      webDriver.navigate().back();
    } else {
      current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
          + "td[position()=8 and contains(text(),'Submitted')]   and "
          + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
          + "td[position()=3 and contains(text()," + type_Of_App + ")]	" + "]"));
      if (current_Row.size() > 0) {
        current_Row.get(0).findElement(By.xpath("a[position()=2]")).click();
        current_Row = webDriver.findElements(By.xpath(
            "//*[@id='certifications']/tbody/tr[  (td[position()=4 and contains(text(),'tive')]) "
                + "and (td/a[position()=1 and contains(text(),'" + type_Of_App + "')]) ]"));
        current_Row.get(0).click();
      }
    }

    webDriver.navigate().back();
    webDriver.navigate().back();
    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
    login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
    login_Data.Login_With_Reference();

  }

  public static void returnAppToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp,
      String duns_Number, String type_Of_App, String status_Of_App, int which_Log_BackAgain)
      throws Exception {
    // Login provided should be Analyst
    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
    login_Data.Login_With_Reference();

    switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
      case "edwosbactive":
        navigationBarClick(webDriver, "Cases");
        WebElement current_Row_e = find_Element(webDriver, "SBA_Table_Search");

        List<WebElement> current_Row = current_Row_e.findElements(
            By.xpath("tr[ " + "td[position()=8 and contains(text(),'Under Review')]   and "
                + "td[position()=2 and contains(text(),'" + duns_Number + "')]	and "
                + "td[position()=3 and contains(text(),'" + type_Of_App + "')]	" + "]"));

        if (current_Row.size() > 0) {

          current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"))
              .click();
          click_Element(webDriver, "SBA_Question_Determinations_SideNav");
          click_Element(webDriver, "SBA_Review_Return_For_Mod");
          click_Element(webDriver, "SBA_Signature_Review_Save_Continue");
          webDriver.navigate().back();

        } else {
          current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
              + "td[position()=8 and contains(text(),'Submitted')]   and "
              + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
              + "td[position()=3 and contains(text()," + type_Of_App + ")]	" + "]"));
          current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"))
              .click();

          // webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));

          new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Reviewer_Id"))
              .selectByIndex(0);
          new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Owner_Id")).selectByIndex(1);
          new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Supervisor_Id"))
              .selectByIndex(1);

          click_Element(webDriver, "Case_Submit_Button");
          click_Element(webDriver, "Case_Submit_Button");

          accept_Alert(webDriver, 15);

          click_Element(webDriver, "SBA_Question_Determinations_SideNav");
          click_Element(webDriver, "SBA_Review_Return_For_Mod");
          click_Element(webDriver, "SBA_Signature_Review_Save_Continue");

          webDriver.navigate().back();
        }
        break;
      case "wosbactive":
        break;
      case "mppactive":
        break;
    }
    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
    login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
    login_Data.Login_With_Reference();

  }

  public static void return_All_Applications(WebDriver webDriver, int login_Id, String duns_Number)
      throws Exception {

    Logger logger =
        LogManager.getLogger(gov.sba.automation.CommonApplicationMethods.class.getName());
    new LoginPageWithReference(webDriver, login_Id).Login_With_Reference();
    non_Vendor_searchDuns_Number(webDriver, duns_Number);


    click_Element(webDriver, "SBA_Table_Business_Search_First_App");

    WebElement aa = find_Element(webDriver, "SBA_Table_Applications_Exist", true);
    if (!(aa == null)) {

      for (int i = 0; i < 11; i++) {

        try {
          click_Element(webDriver, "Vendor_Overview_Page_Rt_Vend_All");
        } catch (Exception e) {
          navigationBarClick(webDriver, "Logout");
          return;
        }

        accept_Alert(webDriver, 10);
        non_Vendor_searchDuns_Number(webDriver, duns_Number);
        click_Element(webDriver, "SBA_Table_Business_Search_First_App");
      }

    }

    navigationBarClick(webDriver, "Logout");
  }

  public static void delete_All_Application_Draft(WebDriver webDriver, int login_Id,
      String duns_Number) throws Exception {

    Logger logger =
        LogManager.getLogger(gov.sba.automation.CommonApplicationMethods.class.getName());
    new LoginPageWithReference(webDriver, login_Id).Login_With_Reference();

    for (int i = 0; i < 6; i++) {

      try {
        click_Element(webDriver, "SBA_Application_Any_Draft_Delete");
        accept_Alert(webDriver, 10);
      } catch (Exception e) {
        navigationMenuClick(webDriver, "Logout");
        return;
      }

    }
    navigationBarClick(webDriver, "Logout");
  }

  public static void delete_All_Application_Draft(WebDriver webDriver, String email_Id,
      String password, String duns_Number) throws Exception {

    Logger logger =
        LogManager.getLogger(gov.sba.automation.CommonApplicationMethods.class.getName());
    new LoginPageWithDetails(webDriver, email_Id, password).Login_With_Details();

    for (int i = 0; i < 6; i++) {

      try {
        click_Element(webDriver, "SBA_Application_Any_Draft_Delete");
        accept_Alert(webDriver, 10);
      } catch (Exception e) {
        navigationMenuClick(webDriver, "Logout");
        return;
      }

    }
    navigationBarClick(webDriver, "Logout");
  }

}
