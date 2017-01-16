package gov.sba.utils.WorkflowPages;

import gov.sba.utils.LoginPageWithReference;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;
import gov.sba.utils.helpers.DatabaseQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class commonApplicationMethods {
    private static final Logger commonApplicationMethodsLogs = LogManager
            .getLogger(gov.sba.utils.WorkflowPages.commonApplicationMethods.class.getName());
    
    public static Boolean checkApplicationExists(WebDriver webDriver, String type_Of_App, String status_Of_App)
            throws Exception {
        // It should be in Vendor Dashboard
        switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
            case "edwosbactive":
                List<WebElement> listOfActiveEDWOSB = webDriver.findElements(By.xpath(
                        "//*[@id='certifications']/tbody/"
                        + "tr[  "
                        + "		(td[position()=4 and contains(text(),'ctive')]) "
                        + "and  (td[position()=1]/a[contains(text(),'EDWOSB')]) "
                        + "	]"));
                return listOfActiveEDWOSB.size() > 0;
            case "wosbactive":
                List<WebElement> listOfActiveWOSB = webDriver.findElements(By.xpath(
                        "//*[@id='certifications']/tbody/tr[  "
                        + "(td[position()=4 and contains(text(),'ctive')]) and "
                        + "(td[position()=1]/a[contains(text(),'WOSB') and not(contains(text(),'EDWOSB'))]) ]"));
                return listOfActiveWOSB.size() > 0;

            case "mpppending":
                List<WebElement> listOfActiveMpp = webDriver.findElements(By.xpath(
                        "//*[@id='certifications']/tbody/tr[  (td[position()=4 and contains(text(),'ending')]) and (td/a[position()=1 and contains(text(),'MPP')]) ]"));
                return listOfActiveMpp.size() > 0;

            default:
                return false;
        }
    }

    public static void clear_Env_Chrome(){
        File file_01 = new File("C:\\KillChromeLocalDesktop\\KillChrome.bat");
        if (file_01.exists()) {
            try {
                String[] command = {"cmd", "/C", "Start", "C:\\KillChromeLocalDesktop\\KillChrome.bat"};
                Process p = Runtime.getRuntime().exec(command);
                p.wait(); p.destroy();
            }
            catch (Exception ex) { }
        }
    }

    public static void deleteApplication(WebDriver webDriver, String type_Of_App, String status_Of_App)
            throws Exception {

        switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
            case "edwosbdraft":
                List<WebElement> deleteElem = webDriver.findElements(By.xpath(
                        "//*[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'EDWOSB')]) and ( td[ position()=4 and contains(text(),'Draft') ] )  ]/td[ position()=6 ]/a[ contains(text(),'Delete') ]  "));
                if (deleteElem.size() > 0) {
                    deleteElem.get(0).click();
                    Thread.sleep(1000);
                    webDriver.switchTo().alert().accept();
                }
                break;
            case "wosbdraft":
                List<WebElement> deleteElem_01 = webDriver.findElements(By.xpath(
                        "//*[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'WOSB') and not(contains(text(),'EDWOSB'))]) and ( td[ position()=4 and contains(text(),'Draft') ] )  ]/td[ position()=6 ]/a[ contains(text(),'Delete') ]  "));
                if (deleteElem_01.size() > 0) {
                    deleteElem_01.get(0).click();
                    webDriver.switchTo().alert().accept();
                }
                break;
            case "mppdraft":
                List<WebElement> deleteElem_02 = webDriver.findElements(By.xpath(
                        "//*[@id='certifications']/tbody/tr" +
                                "[  " +
                                " ( td[position()=1]/a[contains(text(),'MPP')]       )  and" +
                                " ( td[ position()=4 and contains(text(),'Draft')  ] )  and " +
                                " ( td[ position()=6 ]/a[ contains(text(),'Delete') ] )  " +
                                "]" +
                                "/td[position()=6]/a"
                ));
                if (deleteElem_02.size() > 0) {
                    deleteElem_02.get(0).click();
                    webDriver.switchTo().alert().accept();
                }
                break;
        }
    }
    
    public static void clickOnApplicationAllCasesPage(WebDriver webDriver, String type_Of_App)
            throws Exception {
        // It should be in Vendor Dashboard
        switch (type_Of_App.toLowerCase()) {
            case "wosb":
                webDriver.findElement(By.xpath(
                        "//*[@id='certifications']/tbody/tr" +
                                "[" +
                                    "td[position()=1]/a[contains(text(),'WOSB')]" +
                                "]" +
                                "/td[position()=1]/a")).click();
            case "edwosb":
                webDriver.findElement(By.xpath(
                        "//*[@id='certifications']/tbody/tr" +
                                "[" +
                                "td[position()=1]/a[contains(text(),'EDWOSB')]" +
                                "]" +
                                "/td[position()=1]/a")).click();
            case "mpp":
                webDriver.findElement(By.xpath(
                        "//*[@id='certifications']/tbody/tr" +
                                "[" +
                                "td[position()=1]/a[contains(text(),'MPP')]" +
                                "]" +
                                "/td[position()=1]/a")).click();
        }
    }
    
    public static String returnOrganization_Id(String duns_Number)
            throws Exception {
        String organization_Id;
        try{
            organization_Id =  DatabaseQuery.getDBData( "select id from sbaone.organizations where duns_number = '" + duns_Number + "'", 1, 1 )[0][0];
        }
        catch(Exception e) {
            commonApplicationMethodsLogs.info(e.toString() + ": The Duns number retreival has failed");
            throw e;
        }
        return organization_Id;
    };

    public static void deleteApplication_SetCert_Set_App_Tables(WebDriver webDriver, Integer certificate_ID, String duns_Number)
            throws Exception {
                String organization_Id =  returnOrganization_Id(duns_Number);

                DatabaseQuery.
                  executeSQLScript(
                    "update sbaone.certificates " +
                        "   set deleted_at = CURRENT_TIMESTAMP " +
                        "   where organization_id = " + organization_Id.toString()
                  );
                DatabaseQuery.
                  executeSQLScript(
                    "update sbaone.sba_applications " +
                    "       set deleted_at = CURRENT_TIMESTAMP " +
                            "   where organization_id = " + organization_Id.toString()
                  );
    };

    public static void deleteAllApplicationTypes(WebDriver webDriver, String duns_Number)
            throws Exception {
        // It should be in Vendor Dashboard
        deleteApplication_SetCert_Set_App_Tables(webDriver,1 , duns_Number);
        deleteApplication_SetCert_Set_App_Tables(webDriver,2 , duns_Number);
        deleteApplication_SetCert_Set_App_Tables(webDriver,3 , duns_Number);
        deleteApplication_SetCert_Set_App_Tables(webDriver,4 , duns_Number);

    };
    
    public static void returnApplicationToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp, String duns_Number, String type_Of_App, String status_Of_App, int which_Log_BackAgain) throws Exception {

        // Login provided should be Analyst
        commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
        login_Data.Login_With_Reference();
        webDriver.findElement(By.id("query")).sendKeys(duns_Number);
        webDriver.findElement(By.xpath("//*[@id='analyst-search']/div/button[ span[contains(text(),'Search')] ]"))
                .click();
        webDriver
                .findElement(By
                        .xpath("//*[@id='business_search']/div[h2[contains(text(),'Search Results')]]/div[1]/div/h4/a"))
                .click();
        switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
            case "edwosbactive":
                webDriver
                        .findElement(By
                                .xpath("//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'EDWOSB') ]) and ( td[ position()=4 and contains(text(),'Active') ] ) ]/td[position()=6]/a[contains(text(),'Return to Vendor')]"))
                        .click();
                webDriver.switchTo().alert().accept();
                break;
            case "wosbactive":
                webDriver
                        .findElement(By
                                .xpath("//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'WOSB') and not(contains(text(),'EDWOSB')) ]) and ( td[ position()=4 and contains(text(),'Active') ] ) ]/td[position()=6]/a[contains(text(),'Return to Vendor')]"))
                        .click();
                webDriver.switchTo().alert().accept();
                break;
            case "mppactive":
                webDriver
                        .findElement(By
                                .xpath("//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'MPP') ]) and ( td[ position()=4 and contains(text(),'Active') ] ) ]/td[position()=6]/a[contains(text(),'Return to Vendor')]"))
                        .click();
                webDriver.switchTo().alert().accept();
                break;
        }
        commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
        login_Data.Login_With_Reference();

    }

    public static void onlyReturnAppToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp, String duns_Number, String type_Of_App, String status_Of_App, int which_Log_BackAgain) throws Exception {
        // Login provided should be Analyst
        commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
        login_Data.Login_With_Reference();


 
                commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
                WebElement current_Row_e = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody"));

                List<WebElement> current_Row = current_Row_e.findElements(By.xpath("tr[ "
                        + "td[position()=8 and contains(text(),'Under Review')]   and "
                        + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
                        + "td[position()=3 and contains(text(),'" + type_Of_App + "')]	" + "]"));

                if (current_Row.size() > 0) {
                    WebElement a1 = current_Row.get(0)
                            .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
                    a1.click();
                    webDriver
                            .findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]"))
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
                        		 + "and (td/a[position()=1 and contains(text(),'"+ type_Of_App +"')]) ]"));    
                        current_Row.get(0).click();
                    }
                }

        webDriver.navigate().back();
        webDriver.navigate().back();
        commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
        login_Data.Login_With_Reference();

    }

    public static void returnAppToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp, String duns_Number,
                                              String type_Of_App, String status_Of_App, int which_Log_BackAgain) throws Exception {
        // Login provided should be Analyst
        commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
        login_Data.Login_With_Reference();

        switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
            case "edwosbactive":
                commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
                WebElement current_Row_e = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody"));

                List<WebElement> current_Row = current_Row_e.findElements(By.xpath("tr[ "
                        + "td[position()=8 and contains(text(),'Under Review')]   and "
                        + "td[position()=2 and contains(text(),'" + duns_Number + "')]	and "
                        + "td[position()=3 and contains(text(),'" + type_Of_App + "')]	" + "]"));

                if (current_Row.size() > 0) {
                    WebElement a1 = current_Row.get(0)
                            .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
                    a1.click();
                    webDriver
                            .findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]"))
                            .click();
                    webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
                    webDriver.findElement(By.xpath("//input[contains(@value,'Save and commit')]")).click();
                    webDriver.navigate().back();
                } else {
                    current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
                            + "td[position()=8 and contains(text(),'Submitted')]   and "
                            + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
                            + "td[position()=3 and contains(text()," + type_Of_App + ")]	" + "]"));
                    WebElement a1 = current_Row.get(0)
                            .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));

                    a1.click();
                    // webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));
                    Select dropdown1 = new Select(webDriver
                            .findElement(By.xpath("//select[@id='review_current_assignment_attributes_reviewer_id']")));
                    dropdown1.selectByIndex(0);
                    Select dropdown2 = new Select(webDriver
                            .findElement(By.xpath("//select[@id='review_current_assignment_attributes_owner_id']")));
                    dropdown2.selectByIndex(1);
                    Select dropdown3 = new Select(webDriver
                            .findElement(By.xpath("//select[@id='review_current_assignment_attributes_supervisor_id']")));
                    dropdown3.selectByIndex(1);
                    webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                    webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                    webDriver.switchTo().alert().accept();

                    webDriver.findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]")).click();
                    webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
                    webDriver.findElement(By.xpath("//input[contains(@value,'Save and commit')]")).click();
                    webDriver.navigate().back();
                }

                break;
            case "wosbactive":
                break;
            case "mppactive":
                break;
        }
        commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
        login_Data.Login_With_Reference();

    }

    public static void createApplication(WebDriver webDriver, String type_Of_App) throws Exception {
        webDriver.findElement(By.xpath("//*[@id='js-navigation-menu']/li/a[contains(text(),'Programs')]")).click();
        switch (type_Of_App.toUpperCase()) {
            case "EDWOSB":
                webDriver.findElement(By.id("certificate_type_edwosb")).click();
                break;
            case "WOSB":
                webDriver.findElement(By.id("certificate_type_wosb")).click();
                break;
            case "MPP":
                webDriver.findElement(By.id("certificate_type_mpp")).click();
                break;
            default:
                Assert.assertEquals("Edwosb or WOSB or MPP", "Not Found");
        }
        webDriver.findElement(By.id("add_certification")).click();
        webDriver.findElement(By.className("accept_button")).click();
    }

    public static void navigationMenuClick(WebDriver webDriver, String which_Button) throws Exception {
        String part_01 = "//nav[@role='navigation']/ul[@id='js-navigation-menu']/li/a[contains(text(),'";
        String part_03 = "')]";
        switch (which_Button.toUpperCase()) {
            case "LOGOUT":
                webDriver.findElement(By.xpath(part_01 + "Logout" + part_03)).click();
                break;
            case "HELP":
                webDriver.findElement(By.xpath(part_01 + "Help" + part_03)).click();
                break;
            case "CASES":
                webDriver.findElement(By.xpath(part_01 + "ases" + part_03)).click();
                break;
            case "PROGRAMS":
                webDriver.findElement(By.xpath(part_01 + "rograms" + part_03)).click();
                break;
            case "DASHBOARD":
                webDriver.findElement(By.xpath(part_01 + "Dashboard" + part_03)).click();
                break;
            case "BUSINESS":
                webDriver.findElement(By.xpath(part_01 + "Business" + part_03)).click();
                break;
            case "DOCUMENTS":
                webDriver.findElement(By.xpath(part_01 + "Documents" + part_03)).click();
                break;
            default:
                Assert.assertEquals("Navigation Menu Not correct", "among present Options");
        }
    }
}