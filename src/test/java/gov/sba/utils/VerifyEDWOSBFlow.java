package gov.sba.utils;

//Assert
import static org.junit.Assert.assertEquals;import static org.junit.Assert.assertTrue;
//Java Util
import java.util.List; import java.util.Map;
//Loggers
import org.apache.logging.log4j.LogManager;import org.apache.logging.log4j.Logger;import org.openqa.selenium.By;import org.openqa.selenium.WebDriver;import org.openqa.selenium.WebElement;
import gov.sba.utils.helpers.SBAGlobal;import gov.sba.utils.VerifyEDWOSBFlow;
public class VerifyEDWOSBFlow {
    private static final Logger logger = LogManager.getLogger(ScorpQuestionsPage.class.getName());
    WebDriver webDriver;

    public void VerifyEDWOSBFlowSetDriver(WebDriver mydriver) {
        this.webDriver = mydriver;
    }
    public void VerifyEDWOSBFlowLogic() throws Exception {
        String Actual_Text = null;
        String Expected_Text = null;
        Map<?, ?> SBA_Elements = new SBAGlobal().Load_Given_Yaml_File("src/main/resources/Sba1_Elements.yaml");

        webDriver.findElement(By.xpath(SBA_Elements.get("xpath_Top_Panel_Certification").toString())).click();
        String myCertText = webDriver.findElement(By.xpath(SBA_Elements.get("xpath_CertPage_My_Certification").toString())).getText();
        assertEquals(myCertText, "My certifications");

        Actual_Text = webDriver.findElement(By.xpath(SBA_Elements.get("xpath_CertPage_StartNew_Certification").toString())).getText();
        //AddOrStartNewEdwosbCertPage;
        Expected_Text = "Start a new certification";
        assertEquals(Actual_Text, Expected_Text);
        //Acceptance criteria  7 -point 2  
        //Verify the Text under the start a New certification section.

        Expected_Text = "You may only have one active certification for each program at a time. If you need to make edits or changes to a submitted certification, please contact the help desk at certify@sba.gov to release your certification back to you.";
        //        String xpathNeeded = "//article[@id='main-content']//section/article/p[contains(text(),'" + Expected_Text + "')]";
        Actual_Text = webDriver.findElement(By.xpath(SBA_Elements.get("xpath_CertPage_Verify_One_Cert_Message").toString())).getText();
        assertEquals(Actual_Text, Expected_Text);

        try {
            WebElement ElementEDWOSBRadio = webDriver.findElement(By.xpath(SBA_Elements.get("xpath_CertPage_Delete_Cert").toString()));
            ElementEDWOSBRadio.click();
            //		webDriver.findElement(By.xpath("//a[@class='delete-cert' ][@data-method='delete'][text()='Delete']")).click();
        } catch (Exception e) {
            logger.info(
                "There are(is) no certification in-progress on the dashboard, a new certification is being created");
        }

        webDriver.findElement(By.xpath(SBA_Elements.get("xpath_Top_Panel_Certification").toString())).click();


        //Verify before selecting any certification the Add New certification button disabled
        WebElement radio_Element = webDriver.findElement(By.xpath(SBA_Elements.get("xpath_CertPage_Radio_EDWOSB").toString()));
        WebElement add_button = webDriver.findElement(By.id(SBA_Elements.get("id_CertPage_Add_Cert").toString()));
        logger.info(add_button.getAttribute("disabled"));
        assertEquals(add_button.getAttribute("disabled"), "true");
        //assertTrue(Boolean.toString(add_button.isEnabled()), false);
        radio_Element.click();
        assertTrue(Boolean.toString(add_button.isEnabled()), true);
        add_button.click();
        webDriver.findElement(By.className("accept_button")).click();
        webDriver.findElement(By.xpath(SBA_Elements.get("xpath_Top_Panel_Certification").toString())).click();
        WebElement current_Row_Draft = webDriver.findElement(By.xpath(SBA_Elements.get("xpath_CertPage_DraftRowCell").toString()));
        assertEquals(current_Row_Draft.getText(), "Draft");
        logger.info(current_Row_Draft.getText());
        WebElement current_Row = current_Row_Draft.findElement(By.xpath(".."));
        logger.info(current_Row.getText());

        List < WebElement > all_Cells = current_Row.findElements(By.xpath("td"));
        assertEquals(all_Cells.get(0).getText(), "EDWOSB Self-Certification");
        assertEquals(all_Cells.get(1).getText(), "");
        assertEquals(all_Cells.get(2).getText(), "Draft");
        assertEquals(all_Cells.get(3).getText(), "Delete");
       // WebElement LinkForCert = all_Cells.get(0).findElement(By.xpath("//a[text()='EDWOSB Self-Certification']"));
        WebElement LinkForDelete = all_Cells.get(3).findElement(By.xpath(SBA_Elements.get("xpath_CertPage_Delete_Cert").toString()));

        //logger.info(current_Row.GetParent());
        Boolean FlagForAddEDWOSBNotPresent = false;
        try {
            radio_Element = webDriver.findElement(By.xpath(SBA_Elements.get("xpath_CertPage_Radio_EDWOSB").toString()));
            //webDriver.findElement(By.xpath("//a[@class='delete-cert' ][@data-method='delete'][text()='Delete']")).click();
        } catch (Exception e) {
            logger.info("There are(is) no Radio button for EDWOSB");
            FlagForAddEDWOSBNotPresent = true;

        }
        assertTrue(FlagForAddEDWOSBNotPresent);
        LinkForDelete.click();
        logger.info("Certifications Deleted To start again");
        webDriver.findElement(By.xpath(SBA_Elements.get("xpath_Top_Panel_Certification").toString())).click();
        webDriver.findElement(By.xpath(SBA_Elements.get("xpath_CertPage_Radio_EDWOSB").toString())).click();
        webDriver.findElement(By.id(SBA_Elements.get("id_CertPage_Add_Cert").toString())).click();
        logger.info("Gling into Partnerships page");

        //			// partnership test for 1st person.
        //			PartnershipQuestionsPage partnershipquestions = new PartnershipQuestionsPage(webDriver);
        //			partnershipquestions.Partnershipquestions();
        //			// Financial section.
        //			FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
        //			financialsection.Financialsection();

        // Corp test for 1st person.
        ScorpQuestionsPage scorpQuestions = new ScorpQuestionsPage(webDriver);
        scorpQuestions.ScorpQuestions();
        // Financial section.
        FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
        financialsection.Financialsection();
    }
}