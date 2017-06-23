// TS created by Deepa Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.FixtureUtils;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.createApplication;
import static gov.sba.automation.CommonApplicationMethods.find_Element;

public class VerifyEdwosbFlow extends TestCase {
  private static final Logger logger = LogManager.getLogger(VerifyEdwosbFlow.class.getName());
  WebDriver webDriver;

  public void VerifyEDWOSBFlowSetDriver(WebDriver mydriver) {
    this.webDriver = mydriver;
  }

  public void VerifyEDWOSBFlowLogic() throws Exception {

    CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");

    // First Flow - Check For Drafts coming up correctly
    CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
    logger.info("Certifications Deleted To start again");

    assertEquals(find_Element(webDriver, "SBA_My_App_Contracting_Programs").getText(), "My SBA Contracting Programs");

    assertEquals(find_Element(webDriver, "JoinNewPgm_Add_Cert").getAttribute("disabled"), "true");
    createApplication(webDriver, "EdWOSB");

    click_Element(webDriver, "SBA_Vendor_My_Certifications");

    WebElement current_Row_Draft = find_Element(webDriver, "SBA_Draft_Table_EDWOSB");
    assertEquals(current_Row_Draft.getText(), "Draft");
    logger.info(current_Row_Draft.getText());
    WebElement current_Row = current_Row_Draft.findElement(By.xpath(".."));
    logger.info(current_Row.getText());
    List<WebElement> all_Cells = current_Row.findElements(By.xpath("td"));
    assertEquals(all_Cells.get(0).getText(), "EDWOSB Self-Certification");
    // assertEquals(all_Cells.get(1).getText(), "");
    assertEquals(all_Cells.get(4).getText(), "Draft");
    assertEquals(all_Cells.get(6).getText(), "Delete");

    all_Cells.get(4).findElement(By.xpath("//a[contains(@class,'delete-cert')][contains(@data-method,'delete')][contains(text(),'Delete')]")).click();
    CommonApplicationMethods.accept_Alert(webDriver, 4);

    click_Element(webDriver, "SBA_Vendor_My_Certifications");

    // First Flow - Check For Active
    createApplication(webDriver, "EdWOSB");

    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

    logger.info(file_path_abs);
    fillApplCreatePages.page8aFillUp(webDriver, "Yes");
    fillApplCreatePages.finalSignatureSubmit(webDriver);

    // Check the section that its active and no delete in action is there
    click_Element(webDriver, "SBA_Vendor_My_Certifications");

    current_Row_Draft = webDriver.findElement(By.xpath(
        "//article[@id='main-content']//section/article/table/tbody/tr/td[contains(text(),'Active')]"));

    assertEquals(current_Row_Draft.getText(), "Active");
    logger.info(current_Row_Draft.getText());
    current_Row = current_Row_Draft.findElement(By.xpath(".."));
    logger.info(current_Row.getText());

    all_Cells = current_Row.findElements(By.xpath("td"));
    assertEquals(all_Cells.get(0).getText(), "EDWOSB Self-Certification");
    assertEquals(all_Cells.get(4).getText(), "Active");
    assertEquals(all_Cells.get(6).getText(), "");
    all_Cells.get(0).findElement(By.xpath("a")).click();
    Thread.sleep(1000);

    assertTrue(webDriver.getPageSource().contains(
        "Economically Disadvantaged Women-Owned Small Business Program Self-Certification Summary"));

  }

}
