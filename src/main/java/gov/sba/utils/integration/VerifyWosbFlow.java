// TS created by Deepa Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.FixtureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static gov.sba.automation.CommonApplicationMethods.accept_Alert;
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
    CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
    logger.info("Certifications Deleted To start again");

    String myCertText = webDriver
        .findElement(By.xpath(
            "//article[@id='main-content']//h1[contains(text(),'My SBA Contracting Programs')]"))
        .getText();
    assertEquals(myCertText, "My SBA Contracting Programs");
    WebElement radio_Element = webDriver.findElement(
        By.xpath("//div[@id='certificate_choice']//input[@id='certificate_type_wosb']"));

    WebElement add_button = webDriver.findElement(By.id("add_certification"));
    logger.info(add_button.getAttribute("disabled"));
    assertEquals(add_button.getAttribute("disabled"), "true");
    radio_Element.click();
    try {
      webDriver
          .findElement(
              By.xpath("//div[@id='certificate_choice']//input[@id='certificate_type_wosb']"))
          .click();
    } catch (Exception e) {
      logger.info("No Buttons available");
    }
    assertTrue(Boolean.toString(add_button.isEnabled()), true);
    Thread.sleep(2000);
    try {
      webDriver.findElement(By.id("add_certification")).click();
      webDriver.findElement(By.id("add_certification")).click();
    } catch (Exception e) {
      logger.info("No Buttons available");
    }

    Thread.sleep(5000);
    webDriver.findElement(By.className("accept_button")).click();

    webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
    WebElement current_Row_Draft = webDriver.findElement(By.xpath(
        "//article[@id='main-content']//section/article/table/tbody/tr/td[contains(text(),'Draft')]"));
    assertEquals(current_Row_Draft.getText(), "Draft");
    logger.info(current_Row_Draft.getText());
    WebElement current_Row = current_Row_Draft.findElement(By.xpath(".."));
    logger.info(current_Row.getText());
    List<WebElement> all_Cells = current_Row.findElements(By.xpath("td"));
    assertEquals(all_Cells.get(0).getText(), "WOSB Self-Certification");
    // assertEquals(all_Cells.get(1).getText(), "");
    assertEquals(all_Cells.get(4).getText(), "Draft");
    assertEquals(all_Cells.get(6).getText(), "Delete");
    all_Cells.get(4)
        .findElement(By.xpath(
            "//a[contains(@class,'delete-cert')][contains(@data-method,'delete')][contains(text(),'Delete')]"))
        .click();
      accept_Alert(webDriver, 15);
    webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
    // First Flow - Check For Active
    webDriver
        .findElement(
            By.xpath("//div[@id='certificate_choice']//input[@id='certificate_type_wosb']"))
        .click();
    try {
      webDriver.findElement(By.id("add_certification")).click();
    } catch (Exception e) {
      logger.info("Nothing here");
    }

    logger.info("Going into Upload  - create new Doc page");
    webDriver.findElement(By.className("accept_button")).click();
    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

    logger.info(file_path_abs);
    fillApplCreatePages.page8aFillUp(webDriver, "Yes");
    fillApplCreatePages.finalSignatureSubmit(webDriver);

    // Check the section that its active and no delete in action is there
    webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
    current_Row_Draft = webDriver.findElement(By.xpath(
        "//article[@id='main-content']//section/article/table/tbody/tr/td[contains(text(),'ctive')]"));
    assertEquals(current_Row_Draft.getText(), "Active");
    logger.info(current_Row_Draft.getText());
    current_Row = current_Row_Draft.findElement(By.xpath(".."));
    logger.info(current_Row.getText());

    all_Cells = current_Row.findElements(By.xpath("td"));
    assertEquals(all_Cells.get(0).getText(), "WOSB Self-Certification");
    // assertEquals(all_Cells.get(1).getText(), "");
    assertEquals(all_Cells.get(4).getText(), "Active");
    assertEquals(all_Cells.get(6).getText(), "");
    all_Cells.get(0).findElement(By.xpath("a")).click();
    logger.info(webDriver.getPageSource());
    assertTrue(webDriver.getPageSource()
        .contains("Women-Owned Small Business Program Self-Certification Summary"));
    // assertTrue(webDriver.getPageSource().contains("By submitting this
    // certification I"));
    // assertTrue(webDriver.getPageSource().contains("QA User, am an officer
    // or owner of Entity 454 Legal Business Name authorized to represent it
    // and electronically sign this certification on its behalf."));

  }

}
