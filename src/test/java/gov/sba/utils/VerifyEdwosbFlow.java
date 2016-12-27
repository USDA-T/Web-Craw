package gov.sba.utils;

import java.util.List;

import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.helpers.FixtureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.utils.helpers.LoginHelpers;
import junit.framework.TestCase;

public class VerifyEdwosbFlow extends TestCase {
    private static final Logger logger = LogManager.getLogger(ScorpQuestionsPage.class.getName());
    WebDriver webDriver;

    public void VerifyEDWOSBFlowSetDriver(WebDriver mydriver) {
        this.webDriver = mydriver;
    }

    public void VerifyEDWOSBFlowLogic() throws Exception {

        // logger.info(current_Row.GetParent());
        Boolean FlagForAddEDWOSBNotPresent = false;
        try {
            WebElement radio_Element = webDriver.findElement(By.linkText("Delete"));
            radio_Element.click();
            Thread.sleep(4000);
            webDriver.switchTo().alert().accept();
            FlagForAddEDWOSBNotPresent = true;
        } catch (Exception e) {
            logger.info("There are(is) no Radio button for EDWOSB");
            FlagForAddEDWOSBNotPresent = true;
        }
        assertTrue(FlagForAddEDWOSBNotPresent);
        // LinkForDelete.click();

        try {
            WebElement current_Row_Active = webDriver.findElement(By.xpath(
                        "//table[@id='certifications']/tbody/tr[ " +
                                    "(td[position()=4 and contains(text(),'ctive')]) " +
                                            " and " +
                                    "(td[position()=1]/a[contains(text(),'EDWOSB') and contains(text(),'elf') and contains(text(),'ertification')])" +
                                "]"
            ));
            logger.info("aaaaCame in");
            webDriver.findElement(By.xpath("//a[@href='/users/sign_out']")).click();
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 21);
            login_Data.Login_With_Reference();
            webDriver.findElement(By.xpath("//*[@id='query']"))
                    .sendKeys(LoginHelpers.getLoginDataWithIndex(9).getDunsNumber());
            webDriver.findElement(By.className("usa-search-submit-text")).click();
            webDriver.findElement(By.xpath("//a[contains(text(),'Legal Business Name')]")).click();
            WebElement current_Row_Draft1 = webDriver.findElement(By.xpath(
                    "//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'EDWOSB Self-Certification')]"));
            WebElement current_Row1 = current_Row_Draft1.findElement(By.xpath("..")).findElement(By.xpath(".."));
            logger.info(current_Row1.getText());
            List<WebElement> all_Cells1 = current_Row1.findElements(By.xpath("td"));
            logger.info(all_Cells1.size());
            all_Cells1.get(3).findElement(By.xpath("//a[contains(text(),'Return to Vendor')]")).click();
            Thread.sleep(4000);
            webDriver.switchTo().alert().accept();
            webDriver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            login_Data = new LoginPageWithReference(webDriver, 9);
            login_Data.Login_With_Reference();

        } catch (Exception e) {
            logger.info("There are(is) no certification Active on the dashboard, a new certification is being created");
            logger.info(e);
        }

        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();

        try {
            WebElement ElementEDWOSBRadio = webDriver.findElement(By.linkText("Delete"));
            ElementEDWOSBRadio.click();
        } catch (Exception e) {
            logger.info(
                    "There are(is) no certification in-progress on the dashboard, a new certification is being created");
        }

        // First Flow - Check For Drafts coming up correctly
        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
        logger.info("Certifications Deleted To start again");

        String myCertText = webDriver
                .findElement(
                        By.xpath("//article[@id='main-content']//h1[contains(text(),'My SBA Contracting Programs')]"))
                .getText();
        assertEquals(myCertText, "My SBA Contracting Programs");

        WebElement add_button = webDriver.findElement(By.id("add_certification"));
        logger.info(add_button.getAttribute("disabled"));
        assertEquals(add_button.getAttribute("disabled"), "true");
        try {
            webDriver.findElement(By.xpath("//div[@id='certificate_choice']//input[@id='certificate_type_edwosb']"))
                    .click();
        }
        catch (Exception e) {logger.info("Nothing here");}

        assertTrue(Boolean.toString(add_button.isEnabled()), true);
        Thread.sleep(2000);
        try {
            webDriver.findElement(By.id("add_certification")).click();
            webDriver.findElement(By.id("add_certification")).click();
        }
        catch (Exception e) {logger.info("Nothing here");}

        Thread.sleep(5000);
        webDriver.findElement(By.className("accept_button")).click();

        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
        WebElement current_Row_Draft = webDriver.findElement(
                By.xpath("//article[@id='main-content']//section/article/table/tbody/tr/td[contains(text(), 'Draft')]"));
        assertEquals(current_Row_Draft.getText(), "Draft");
        logger.info(current_Row_Draft.getText());
        WebElement current_Row = current_Row_Draft.findElement(By.xpath(".."));
        logger.info(current_Row.getText());
        List<WebElement> all_Cells = current_Row.findElements(By.xpath("td"));
        assertEquals(all_Cells.get(0).getText(), "EDWOSB Self-Certification");
        // assertEquals(all_Cells.get(1).getText(), "");
        assertEquals(all_Cells.get(3).getText(), "Draft");
        assertEquals(all_Cells.get(5).getText(), "Delete");
        all_Cells.get(4).findElement(By.xpath("//a[contains(@class,'delete-cert')][contains(@data-method,'delete')][contains(text(),'Delete')]")).click();
        Thread.sleep(4000);
        webDriver.switchTo().alert().accept();
        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();

        // First Flow - Check For Active
        webDriver.findElement(By.xpath("//div[@id='certificate_choice']//input[@id='certificate_type_edwosb']"))
                .click();
        webDriver.findElement(By.id("add_certification")).click();
        try {
            webDriver.findElement(By.id("add_certification")).click();
            webDriver.findElement(By.id("add_certification")).click();
        } catch (Exception e) {
            logger.info("Nothing here");
        }
        webDriver.findElement(By.className("accept_button")).click();
        String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

        logger.info(file_path_abs);
        fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
        fillApplCreatePages.finalSignatureSubmit(webDriver);

        // Check the section that its active and no delete in action is there
        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
        current_Row_Draft = webDriver.findElement(By.xpath("//article[@id='main-content']//section/article/table/tbody/tr/td[contains(text(),'Active')]"));
        assertEquals(current_Row_Draft.getText(), "Active");
        logger.info(current_Row_Draft.getText());
        current_Row = current_Row_Draft.findElement(By.xpath(".."));
        logger.info(current_Row.getText());

        all_Cells = current_Row.findElements(By.xpath("td"));
        assertEquals(all_Cells.get(0).getText(), "EDWOSB Self-Certification");
        assertEquals(all_Cells.get(3).getText(), "Active");
        assertEquals(all_Cells.get(5).getText(), "");
        all_Cells.get(0).findElement(By.xpath("a")).click();

        assertTrue(webDriver.getPageSource()
                .contains("Economically Disadvantaged Women-Owned Small Business Program Self-Certification Summary"));
        assertTrue(webDriver.getPageSource().contains(
                "By submitting this certification I, QA User, am an officer or owner of Entity 454 Legal Business Name authorized to represent it and electronically sign this certification on its behalf."));

    }
}
