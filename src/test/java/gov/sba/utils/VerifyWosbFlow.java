package gov.sba.utils;

import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.helpers.FixtureUtils;
import gov.sba.utils.helpers.LoginHelpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VerifyWosbFlow {
    private static final Logger logger = LogManager.getLogger(VerifyWosbFlow.class.getName());
    WebDriver webDriver;

    public void VerifyWOSBFlowSetDriver(WebDriver mydriver) {
        this.webDriver = mydriver;
    }

    public void VerifyWOSBFlowLogic() throws Exception {
        // logger.info(current_Row.GetParent());
        Boolean FlagForAddEDWOSBNotPresent = false;

        try {
            List<WebElement> current_Row_Draft = webDriver.findElements(By.xpath(
                    "//table[@id='certifications']/tbody/tr[ " +
                            "(td[position()=4 and contains(text(),'raft')]) " +
                            " and " +
                            "(td[position()=1]/a[contains(text(),'WOSB') and contains(text(),'elf') and contains(text(),'ertification')])" +
                            "]"
            ));
            if (current_Row_Draft.size() > 0) {
                current_Row_Draft.get(0).findElement(By.linkText("Delete")).click();
                FlagForAddEDWOSBNotPresent = true;
                webDriver.switchTo().alert().accept();
            }
            FlagForAddEDWOSBNotPresent = true;
        } catch (Exception e) {
            logger.info("There are(is) no Radio button for WOSB");
            FlagForAddEDWOSBNotPresent = true;
        }
        assertTrue(FlagForAddEDWOSBNotPresent);

        try {
            List<WebElement> current_Row_Active = webDriver.findElements(By.xpath(
                    "//table[@id='certifications']/tbody/tr[ " +
                            "(td[position()=4 and contains(text(),'ctive')]) " +
                            " and " +
                            "(td[position()=1]/a[contains(text(),'WOSB') and not(contains(text(),'EDW'))])" +
                            "]"
            ));

            if (current_Row_Active.size() > 0){
                logger.info(current_Row_Active.get(0).getText());
                commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 21);
                login_Data.Login_With_Reference();

                String Duns_Number = LoginHelpers.getLoginDataWithIndex(10).getDunsNumber();
                webDriver.findElement(By.xpath("//*[@id='query']")).sendKeys(Duns_Number);
                webDriver.findElement(By.xpath("//button[@type='submit']/span[contains(text(),'earch')]")).click();

                webDriver.findElement(By.xpath("//div[@id='business_search']/div[2]/div[1]/div[1]/h4/a")).click();
                List<WebElement> current_Row_Check = webDriver.findElements(By.xpath(
                        "//table[@id='certifications']/tbody/tr/td/a[contains(text(),'Return to Vendor')]"
                ));

                if (current_Row_Check.size() >0 ) {
                    //  webDriver.findElement(By.className("usa-search-submit-text")).click();
                    current_Row_Check.get(0).click();
                    Thread.sleep(3000);
                    webDriver.switchTo().alert().accept();
                    commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                    login_Data = new LoginPageWithReference(webDriver, 10);
                    login_Data.Login_With_Reference();
                } else {
                    webDriver.findElement(By.xpath(
                            "//table[@id='certifications']/tbody/tr/td[position()=1]/a" +
                                    "[" +
                                    "contains(text(),'WOSB') and" +
                                    "not(contains(text(),'EDWOSB'))" +
                                    "]"
                    )).click();
                    webDriver.findElement(By.xpath(
                            "//ul[contains(@class, 'sidenav-list')]/li/a[contains(text(),'etermination')]"
                    )).click();
                    webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
                    webDriver.findElement(By.xpath("//input[@type='submit' and contains(@value,'commit')]")).click();

                    commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                    login_Data = new LoginPageWithReference(webDriver, 10);
                    login_Data.Login_With_Reference();
                }
            }




        } catch (Exception e) {
            logger.info(e.toString());
            logger.info("There are(is) no certification Active on the dashboard, a new certification is being created");
            logger.info(e);
        }



        commonApplicationMethods.navigationMenuClick(webDriver, "Programs");
        try {
            WebElement ElementWOSBRadio = webDriver.findElement(By.linkText("Delete"));
            ElementWOSBRadio.click();
            webDriver.switchTo().alert().accept();
        } catch (Exception e) {
            logger.info(
                    "There are(is) no certification in-progress on the dashboard, a new certification is being created");
        }

        // First Flow - Check For Drafts coming up correctly
        commonApplicationMethods.navigationMenuClick(webDriver, "Programs");
        logger.info("Certifications Deleted To start again");

        String myCertText = webDriver
                .findElement(
                        By.xpath("//article[@id='main-content']//h1[contains(text(),'My SBA Contracting Programs')]"))
                .getText();
        assertEquals(myCertText, "My SBA Contracting Programs");
        WebElement radio_Element = webDriver
                .findElement(By.xpath("//div[@id='certificate_choice']//input[@id='certificate_type_wosb']"));

        WebElement add_button = webDriver.findElement(By.id("add_certification"));
        logger.info(add_button.getAttribute("disabled"));
        assertEquals(add_button.getAttribute("disabled"), "true");
        radio_Element.click();
        try {
            webDriver.findElement(By.xpath("//div[@id='certificate_choice']//input[@id='certificate_type_wosb']"))
                    .click();
        } catch (Exception e) {
        }
        assertTrue(Boolean.toString(add_button.isEnabled()), true);
        Thread.sleep(2000);
        try {
            webDriver.findElement(By.id("add_certification")).click();
            webDriver.findElement(By.id("add_certification")).click();
        } catch (Exception e) {
        }

        Thread.sleep(5000);
        webDriver.findElement(By.className("accept_button")).click();

        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
        WebElement current_Row_Draft = webDriver.findElement(
                By.xpath("//article[@id='main-content']//section/article/table/tbody/tr/td[contains(text(),'Draft')]"));
        assertEquals(current_Row_Draft.getText(), "Draft");
        logger.info(current_Row_Draft.getText());
        WebElement current_Row = current_Row_Draft.findElement(By.xpath(".."));
        logger.info(current_Row.getText());
        List<WebElement> all_Cells = current_Row.findElements(By.xpath("td"));
        assertEquals(all_Cells.get(0).getText(), "WOSB Self-Certification");
        // assertEquals(all_Cells.get(1).getText(), "");
        assertEquals(all_Cells.get(3).getText(), "Draft");
        assertEquals(all_Cells.get(5).getText(), "Delete");
        all_Cells.get(4).findElement(By.xpath("//a[contains(@class,'delete-cert')][contains(@data-method,'delete')][contains(text(),'Delete')]")).click();
        Thread.sleep(3000);
        webDriver.switchTo().alert().accept();
        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
        // First Flow - Check For Active
        webDriver.findElement(By.xpath("//div[@id='certificate_choice']//input[@id='certificate_type_wosb']")).click();
        try {
            webDriver.findElement(By.id("add_certification")).click();
        } catch (Exception e) {
            logger.info("Nothing here");
        }

        logger.info("Going into Upload  - create new Doc page");
        webDriver.findElement(By.className("accept_button")).click();
        String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

        logger.info(file_path_abs);
        fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
        fillApplCreatePages.finalSignatureSubmit(webDriver);


        // Check the section that its active and no delete in action is there
        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
        current_Row_Draft = webDriver.findElement(
                By.xpath("//article[@id='main-content']//section/article/table/tbody/tr/td[contains(text(),'ctive')]"));
        assertEquals(current_Row_Draft.getText(), "Active");
        logger.info(current_Row_Draft.getText());
        current_Row = current_Row_Draft.findElement(By.xpath(".."));
        logger.info(current_Row.getText());

        all_Cells = current_Row.findElements(By.xpath("td"));
        assertEquals(all_Cells.get(0).getText(), "WOSB Self-Certification");
        // assertEquals(all_Cells.get(1).getText(), "");
        assertEquals(all_Cells.get(3).getText(), "Active");
        assertEquals(all_Cells.get(5).getText(), "");
        all_Cells.get(0).findElement(By.xpath("a")).click();
        logger.info(webDriver.getPageSource());
        assertTrue(webDriver.getPageSource().contains("Women-Owned Small Business Program Self-Certification Summary"));
//        assertTrue(webDriver.getPageSource().contains("By submitting this certification I"));
//        assertTrue(webDriver.getPageSource().contains("QA User, am an officer or owner of Entity 454 Legal Business Name authorized to represent it and electronically sign this certification on its behalf."));

    }

}
