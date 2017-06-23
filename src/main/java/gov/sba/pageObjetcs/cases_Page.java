/**
 * Created by deepa on 5/15/2017.
 */
package gov.sba.pageObjetcs;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.getLocator;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class cases_Page {
    private static final Logger logger_Cases_Page = LogManager.getLogger(cases_Page.class.getName());

    public static void search_Duns_And_Verify(WebDriver webDriver, String which_Duns, String click_Duns_YesORNo,
            String verify_State, String delete_Draft) throws Exception {
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
                Boolean isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
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
}
