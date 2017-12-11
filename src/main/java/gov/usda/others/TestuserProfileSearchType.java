package gov.usda.others;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

public class TestuserProfileSearchType extends TestCase {

  public static void TestuserProfileSearch(WebDriver webDriver, String last_name,
      String xpathSearch, String Expected_Result) throws Exception {

    // Select Government User Radio button
    click_Element(webDriver, xpathSearch);
    setText_Element(webDriver, "OppSup_Dashboard_User_Search_Text", last_name);
    click_Element(webDriver, "OppSup_Dashboard_User_Search_Button");
    // User Search results
    click_Element(webDriver, "Opp_Support_Page_Test_Search");
    Assert.assertEquals(
        find_Element(webDriver, "Opp_Support_Page_Test_Search_User_Profile").getText(),
        Expected_Result);
  }
}
