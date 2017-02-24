package gov.sba.utils.integration;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonApplicationMethods {
    private static final Logger commonApplicationMethodsLogs = LogManager
            .getLogger(gov.sba.utils.integration.CommonApplicationMethods.class.getName());

    public static Boolean checkApplicationExists(WebDriver webDriver, String type_Of_App, String status_Of_App)
            throws Exception {
        // It should be in Vendor Dashboard
        switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
        case "edwosbactive":
            List<WebElement> listOfActiveEDWOSB = webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/"
                    + "tr[  " + "		(td[position()=4 and contains(text(),'ctive')]) "
                    + "and  (td[position()=1]/a[contains(text(),'EDWOSB')]) " + "	]"));
            return listOfActiveEDWOSB.size() > 0;
        case "wosbactive":
            List<WebElement> listOfActiveWOSB = webDriver.findElements(By.xpath(
                    "//*[@id='certifications']/tbody/tr[  " + "(td[position()=4 and contains(text(),'ctive')]) and "
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
    public static WebElement find_Element(WebDriver webdriver, String type_Locator, String value_Locator ) throws Exception {

        String flag = "";
        WebElement element_01 = null;
        switch (type_Locator.toLowerCase()) {
            case "xpath":
                    for (int i=0;i<15;i++){
                        try{//commonApplicationMethodsLogs.info(Calendar.getInstance().get(Calendar.MILLISECOND));
                            element_01=  webdriver.findElement(By.xpath(value_Locator));
                            flag = "Y";
                            break;
                        }catch (Exception e){
                            commonApplicationMethodsLogs.info("Trying to find xpath:" + value_Locator );
                            Thread.sleep(300);
                        }
                    }
                    break;
            case "id":
                for (int i=0;i<15;i++){
                    try{ //commonApplicationMethodsLogs.info(Calendar.getInstance().get(Calendar.MILLISECOND));
                        element_01=  webdriver.findElement(By.id(value_Locator));
                        flag = "Y";
                        break;
                    }catch (Exception e){
                        commonApplicationMethodsLogs.info("Trying to find id:" + value_Locator );
                        Thread.sleep(300);
                    }
                }
                break;
            case "classname":
                for (int i=0;i<15;i++){
                    try{ //commonApplicationMethodsLogs.info(Calendar.getInstance().get(Calendar.MILLISECOND));
                      element_01=  webdriver.findElement(By.className(value_Locator));
                        flag = "Y";
                        break;
                    }catch (Exception e){
                        commonApplicationMethodsLogs.info("Trying to find classname:" + value_Locator );
                        Thread.sleep(300);
                    }
                }
                break;
            case "name":
                for (int i=0;i<15;i++){
                    try{ //commonApplicationMethodsLogs.info(Calendar.getInstance().get(Calendar.MILLISECOND));
                      element_01=  webdriver.findElement(By.name(value_Locator));
                        flag = "Y";
                        break;
                    }catch (Exception e){
                        commonApplicationMethodsLogs.info("Trying to find name:" + value_Locator );
                        Thread.sleep(300);
                    }
                }
                break;
            case "cssselector":
                for (int i=0;i<15;i++){
                    try{ //commonApplicationMethodsLogs.info(Calendar.getInstance().get(Calendar.MILLISECOND));
                      element_01=  webdriver.findElement(By.cssSelector(value_Locator));
                        flag = "Y";
                        break;
                    }catch (Exception e){
                        commonApplicationMethodsLogs.info("Trying to find cssSelector:" + value_Locator );
                        Thread.sleep(300);
                    }
                }
                break;
            case "linktext":
                for (int i=0;i<15;i++){
                    try{ //commonApplicationMethodsLogs.info(Calendar.getInstance().get(Calendar.MILLISECOND));
                      element_01=  webdriver.findElement(By.linkText(value_Locator));
                        flag = "Y";
                        break;
                    }catch (Exception e){
                        commonApplicationMethodsLogs.info("Trying to find linktext:" + value_Locator );
                        Thread.sleep(300);
                    }
                }
                break;
        }

        if (flag == "Y"){
            return element_01;
        } else{
            throw new Exception();
        }
    }
    public static void click_Element(WebDriver webdriver, String type_Locator, String value_Locator ) throws Exception {
        WebElement click_element = find_Element(webdriver, type_Locator, value_Locator);
        click_element.click();
    }

    public static void setText_Element(WebDriver webdriver, String type_Locator, String value_Locator, String textVal ) throws Exception {
        WebElement click_element = find_Element(webdriver, type_Locator, value_Locator);
        click_element.sendKeys(textVal);
    }

    public static void focus_window() throws AWTException, InterruptedException {
        final Robot robot = new Robot();
        robot.mouseMove(300, 300);
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        Thread.sleep(700);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        Thread.sleep(700);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        Thread.sleep(700);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        Thread.sleep(700);
    }

    public static void return_all_Applications(WebDriver webDriver, int login_Id, String duns_Number) throws Exception {
        Logger commonApplicationMethodsLogs = LogManager
                .getLogger(gov.sba.utils.integration.CommonApplicationMethods.class.getName());

        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, login_Id);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);

        searchDuns_Number(webDriver, duns_Number);
        webDriver.findElement(By.xpath("//div[@id='business_search']/div[2]/div[1]/div[1]/h4/a")).click();
        String paS = webDriver.getPageSource().toLowerCase();
        WebElement current_Row_Check_01;

        try {
            current_Row_Check_01 = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody"));
            List<WebElement> current_Row_Check = current_Row_Check_01
                    .findElements(By.xpath("tr/td/a[contains(text(),'Return to Vendor')]"));
            if (current_Row_Check.size() > 0) {
                for (int i = 0; i < current_Row_Check.size(); i++) {
                    current_Row_Check.get(0).click();
                    Thread.sleep(3000);
                    webDriver.switchTo().alert().accept();
                }
            }

        } catch (Exception ex) {
            commonApplicationMethodsLogs.info(ex);
        }

        try {
            if ((paS.contains("return to vendor") || paS.contains("active"))
                    && (paS.contains("wosb") || paS.contains("mpp"))) {
                current_Row_Check_01 = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody"));
                List<WebElement> current_Row_Check_02 = current_Row_Check_01.findElements(
                        By.xpath("tr[" + "td[position()=1]/a[contains(text(),'WOSB') or contains(text(),'MPP')] and "
                                + "td[(position()=4) and" + "               ( "
                                + "                   (contains(text(),'ctive')) or "
                                + "                   (contains(text(),'ending')) " + "               )" + "   ]"
                                + "]/td[position()=1]/a"));
                if (current_Row_Check_02.size() > 0) {
                    for (int i = 0; i < current_Row_Check_02.size(); i++) {
                        current_Row_Check_02.get(0).click();
                        Thread.sleep(3000);
                        Thread.sleep(3000);
                        webDriver
                                .findElement(By
                                        .xpath("//ul[contains(@class, 'sidenav-list')]/li/a[contains(text(),'etermination')]"))
                                .click();
                        Thread.sleep(1500);
                        webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
                        Thread.sleep(1500);
                        webDriver.findElement(By.xpath("//input[@type='submit' and contains(@value,'commit')]"))
                                .click();
                        Thread.sleep(1500);
                        webDriver.navigate().back();
                        webDriver.navigate().back();
                        webDriver.navigate().back();
                        webDriver.navigate().refresh();
                        try {
                            current_Row_Check_01 = webDriver
                                    .findElement(By.xpath("//table[@id='certifications']/tbody"));
                        } catch (Exception ex) {
                            return;
                        }

                        current_Row_Check_02 = current_Row_Check_01.findElements(By.xpath("tr["
                                + "td[position()=1]/a[contains(text(),'WOSB') or contains(text(),'MPP')] and "
                                + "td[(position()=4) and (contains(text(),'ctive'))]" + "]/td[position()=1]/a"));

                        i = 0;
                    }
                }
            }
        } catch (Exception ex1) {
            commonApplicationMethodsLogs.info(ex1);
        }

        CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
    }

    public static void delete_all_Drafts(WebDriver webDriver) throws Exception {

        Boolean FlagForAddEDWOSBNotPresent = true;
        navigationMenuClick(webDriver, "DashBoard");
        List<WebElement> current_Row_Check_02 = webDriver.findElements(
                By.xpath("//table[@id='certifications']/tbody/tr/td[position()=6]/a[contains(text(),'elete')] "));
        if (current_Row_Check_02.size() > 0) {

            for (int i = 0; i < current_Row_Check_02.size(); i++) {
                FlagForAddEDWOSBNotPresent = false;
                current_Row_Check_02.get(0).click();
                Thread.sleep(2000);
                webDriver.switchTo().alert().accept();
                Thread.sleep(1500);
                webDriver.navigate().refresh();
                current_Row_Check_02 = webDriver.findElements(By
                        .xpath("//table[@id='certifications']/tbody/tr/td[position()=6]/a[contains(text(),'elete')] "));
                i = 0;
                FlagForAddEDWOSBNotPresent = true;
            }
        }
        Assert.assertTrue(FlagForAddEDWOSBNotPresent);

    }

    public static void clear_Env_Chrome() throws InterruptedException, IOException {
        if (System.getProperty("os.name").startsWith("Windows")) {
            Runtime rt = Runtime.getRuntime();
            rt.exec("Taskkill /IM chrome.exe /F");
            rt.exec("Taskkill /IM firefox.exe /F");
            Thread.sleep(1000);
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
            List<WebElement> deleteElem_02 = webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr"
                    + "[  " + " ( td[position()=1]/a[contains(text(),'MPP')]       )  and"
                    + " ( td[ position()=4 and contains(text(),'Draft')  ] )  and "
                    + " ( td[ position()=6 ]/a[ contains(text(),'Delete') ] )  " + "]" + "/td[position()=6]/a"));
            if (deleteElem_02.size() > 0) {
                deleteElem_02.get(0).click();
                webDriver.switchTo().alert().accept();
            }
            break;
        }
    }

    public static void clickOnApplicationAllCasesPage(WebDriver webDriver, String type_Of_App) throws Exception {
        // It should be in Vendor Dashboard
        switch (type_Of_App.toLowerCase()) {
        case "wosb":
            webDriver.findElement(By.xpath("//*[@id='certifications']/tbody/tr" + "["
                    + "td[position()=1]/a[contains(text(),'WOSB')]" + "]" + "/td[position()=1]/a")).click();
        case "edwosb":
            webDriver.findElement(By.xpath("//*[@id='certifications']/tbody/tr" + "["
                    + "td[position()=1]/a[contains(text(),'EDWOSB')]" + "]" + "/td[position()=1]/a")).click();
        case "mpp":
            webDriver.findElement(By.xpath("//*[@id='certifications']/tbody/tr" + "["
                    + "td[position()=1]/a[contains(text(),'MPP')]" + "]" + "/td[position()=1]/a")).click();
        }
    }

    public static String returnOrganization_Id(String duns_Number) throws Exception {
        String organization_Id;
        try {
            organization_Id = DatabaseQuery.getDBData(
                    "select id from sbaone.organizations where duns_number = '" + duns_Number + "'", 1, 1)[0][0];
        } catch (Exception e) {
            commonApplicationMethodsLogs.info(e.toString() + ": The Duns number retreival has failed");
            throw e;
        }
        return organization_Id;
    };
    public static String return_Url_check() throws Exception {
        String file_path = FixtureUtils.fixturesDir_Duns() + "default.properties";
        Scanner in = new Scanner(new FileReader(file_path));
        while(in.hasNextLine()){
            String nline = in.nextLine();
            if (nline.indexOf("base_url_development") == 0)
            {
                return nline.split("base_url_development=")[1].trim();
            }
        }
        throw new Exception("Not Good Environment");
    }

    public static String return_Db_URL() throws Exception {
        String url_Check = return_Url_check();
        if (url_Check.contains("elb.maint")) {
                return "jdbc:postgresql://db.qa.sba-one.net:5432/sbaone_dev";
        } else {
            if (url_Check.contains("certify.qa")) {
                return "jdbc:postgresql://sbaonedev.cypwvkg7qp3n.us-east-1.rds.amazonaws.com:5432/sbaone_qa";
            }
            else {
                    throw new Exception(new NoSuchFieldException("Connection incorrect - Neither QA/ELB"));
            }
        }
    }

    public static String[] return_Good_Duns_no() throws Exception {
        String[] duns_Number = new String[] { "" };
        try {

            String url_Check = "";
            String url = "";
            String file_path_abs = "";
            String file_path = FixtureUtils.fixturesDir_Duns() + "default.properties";
            Scanner in = new Scanner(new FileReader(file_path));
            while(in.hasNextLine()){
                String nline = in.nextLine();
                if (nline.indexOf("base_url_development") == 0)
                {
                    url_Check= nline.split("base_url_development=")[1].trim();
                    break;
                }
            }
            if (url_Check.contains("elb.maint")) {
                file_path_abs = FixtureUtils.fixturesDir_Duns() + "fixtures_duns_List_Elb.csv";

            } else {
                if (url_Check.contains("certify.qa")) {
                    file_path_abs = FixtureUtils.fixturesDir_Duns() + "fixtures_duns_List_QA.csv";
                } else {
                    throw new Exception(new NoSuchFieldException("Connection incorrect - Neither QA/ELB"));
                }
            }


            FileInputStream fstream = new FileInputStream(file_path_abs);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                if (!strLine.contains("Email,password,duns_number")) {
                    String[] details = strLine.split(",");
                    String sql_Q_01 = "select count(*) from sbaone.certificates where organization_id in (select id from sbaone.organizations where duns_number = '"
                            + details[2] + "')";

                    DatabaseQuery dbcall = new DatabaseQuery();
                    String[][] returned_Count_01 = dbcall.getDBData(sql_Q_01, 1, 1);

                    String sql_Q_02 = "select count(*) from sbaone.sba_applications where organization_id in (select id from sbaone.organizations where duns_number = '"
                            + details[2] + "')";
                    DatabaseQuery dbcall_02 = new DatabaseQuery();
                    String[][] returned_Count_02 = dbcall_02.getDBData(sql_Q_02, 1, 1);

                    int counter = Integer.parseInt(returned_Count_01[0][0].toString())
                            + Integer.parseInt(returned_Count_02[0][0].toString());
                    if (counter <= 0) { 
                        commonApplicationMethodsLogs.info("Details Passed: " + Arrays.toString(details) + ":Url:- " + url_Check);
                        return details;
                    }
                }
            }
            br.close();

        } catch (Exception e) {
            commonApplicationMethodsLogs.info(e.toString() + ": The Duns number retreival has failed");
            throw e;
        }
        return duns_Number;
    };

    public static void deleteApplication_SetCert_Set_App_Tables(WebDriver webDriver, Integer certificate_ID,
            String duns_Number) throws Exception {
        String organization_Id = returnOrganization_Id(duns_Number);

        DatabaseQuery.executeSQLScript("update sbaone.certificates " + "   set deleted_at = CURRENT_TIMESTAMP "
                + "   where organization_id = " + organization_Id.toString());
        DatabaseQuery.executeSQLScript("update sbaone.sba_applications " + "       set deleted_at = CURRENT_TIMESTAMP "
                + "   where organization_id = " + organization_Id.toString());
    };

    public static void deleteAllApplicationTypes(WebDriver webDriver, String duns_Number) throws Exception {
        // It should be in Vendor Dashboard
        deleteApplication_SetCert_Set_App_Tables(webDriver, 1, duns_Number);
        deleteApplication_SetCert_Set_App_Tables(webDriver, 2, duns_Number);
        deleteApplication_SetCert_Set_App_Tables(webDriver, 3, duns_Number);
        deleteApplication_SetCert_Set_App_Tables(webDriver, 4, duns_Number);

    };

    public static void returnApplicationToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp,
            String duns_Number, String type_Of_App, String status_Of_App, int which_Log_BackAgain) throws Exception {

        // Login provided should be Analyst
        CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
        login_Data.Login_With_Reference();
        searchDuns_Number(webDriver, duns_Number);
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
        CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
        login_Data.Login_With_Reference();

    }

    public static void onlyReturnAppToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp, String duns_Number,
            String type_Of_App, String status_Of_App, int which_Log_BackAgain) throws Exception {
        // Login provided should be Analyst
        CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
        login_Data.Login_With_Reference();

        CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
        WebElement current_Row_e = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody"));

        List<WebElement> current_Row = current_Row_e
                .findElements(By.xpath("tr[ " + "td[position()=8 and contains(text(),'Under Review')]   and "
                        + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
                        + "td[position()=3 and contains(text(),'" + type_Of_App + "')]	" + "]"));

        if (current_Row.size() > 0) {
            WebElement a1 = current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
            a1.click();
            webDriver.findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]"))
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
                current_Row = webDriver.findElements(
                        By.xpath("//*[@id='certifications']/tbody/tr[  (td[position()=4 and contains(text(),'tive')]) "
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

    public static void returnAppToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp, String duns_Number,
            String type_Of_App, String status_Of_App, int which_Log_BackAgain) throws Exception {
        // Login provided should be Analyst
        CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
        login_Data.Login_With_Reference();

        switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
        case "edwosbactive":
            CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            WebElement current_Row_e = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody"));

            List<WebElement> current_Row = current_Row_e
                    .findElements(By.xpath("tr[ " + "td[position()=8 and contains(text(),'Under Review')]   and "
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

                webDriver
                        .findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]"))
                        .click();
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
        CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
        login_Data.Login_With_Reference();

    }

    public static void createApplication(WebDriver webDriver, String type_Of_App) throws Exception {
        navigationMenuClick(webDriver, "Programs");
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
        case "8A":
            webDriver.findElement(By.id("certificate_type_eight_a")).click();
            break;
        default:
            Assert.assertEquals("Edwosb or WOSB or MPP or 8a", "Not Found");
        }
        webDriver.findElement(By.id("add_certification")).click();
        webDriver.findElement(By.className("accept_button")).click();
    }

    public static void searchDuns_Number(WebDriver webDriver, String search_Text) throws Exception {
        webDriver.findElement(By.id("searchtext")).click();
        Thread.sleep(500);
        webDriver.findElement(By.id("query")).sendKeys(search_Text);
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//div[@role='search']/button[@type='submit']")).click();
        Thread.sleep(500);
    }

    public static void navigationMenuClick(WebDriver webDriver, String which_Button) throws Exception {
        String part_01 = "//nav[@role='navigation']/div/ul/li/a/span[contains(text(),'";
        String part_03 = "')]";
        switch (which_Button.toUpperCase()) {
        case "LOGOUT":
            webDriver.findElement(By.xpath("//a[ contains(@class, 'ogout' ) and contains(text(), 'ogout') ]")).click();
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
        case "HOME":
            webDriver.findElement(By.xpath(part_01 + "Home" + part_03)).click();
            break;
        default:
            Assert.assertEquals("Navigation Menu Not correct", "among present Options");
        }
    }
}