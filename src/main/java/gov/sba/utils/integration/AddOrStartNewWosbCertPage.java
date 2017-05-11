package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;

public class AddOrStartNewWosbCertPage extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(AddOrStartNewWosbCertPage.class.getName());
  WebDriver webDriver;

  public AddOrStartNewWosbCertPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void AddOrStartNewWosbbCert() throws Exception {
    String Actual_Text = null;
    String Expected_Text = null;
    // Verify for active certification on the dashboard.
    Thread.sleep(3000);
    if (webDriver.getPageSource().contains("Active")) {
      logger.info("There is (are) an active certification on the dashboard");
      // Click on the certification link.
      webDriver.findElement(By.linkText("Programs")).click();
      Actual_Text = webDriver.findElement(By.xpath("//h1[2]")).getText();
      Expected_Text = "Start a new certification";
      assertEquals(Actual_Text, Expected_Text);
      // Verify and Click on the EDWOSB Certification to select.
      Actual_Text = webDriver.findElement(By.xpath("//label[2]")).getText();
      Expected_Text =
          "Economically Disadvantaged Woman Owned Small Business (EDWOSB) Program (Review requirements)";
      assertEquals(Actual_Text, Expected_Text);
      webDriver.findElement(By.id("certificate_type_wosb")).click();
      // Click on the add new certification button.
      webDriver.findElement(By.id("add_certification")).click();
      // Verify and validate the terms for the certification.
      Actual_Text = webDriver.findElement(By.xpath("//form/div[2]")).getText();
      Expected_Text =
          "All small businesses that are interested in submitting an offer on a solicitation that has been set aside for Women-Owned Small Businesses (WOSB) and Economically Disadvantaged Women-Owned Small Businesses (EDWOSB) under the WOSB Program must complete this certification prior to submitting the offer. This includes checking all boxes presented and having an authorized officer of the woman-owned small business or economically disadvantaged woman-owned small business electronically sign and date the certification. You must upload all other required documents as indicated here-in to the WOSB Program Repository. For questions, please e-mail wosb@sba.gov.\nPlease read the following certification statements. The Federal government relies on the information in this form and any documents or supplemental information submitted to determine whether the business is eligible for a contract authorized under the WOSB Program. The definitions for the terms used in this certification are set forth in the Small Business Act, U.S. Small Business Administration (SBA) regulations (13 C.F.R. Part 127), and also any statutory and regulatory provision referenced in those authorities. In addition, please note that the SBA may request further clarification or supporting documentation in order to assist in the verification of any of the information provided and that each person signing this certification may be prosecuted if they have provided false information. Any action taken with respect to this certification does not affect the Government's right to pursue criminal, civil or administrative remedies for incorrect or incomplete information given, even if correct information has been included in other materials submitted to SBA.";
      assertEquals(Actual_Text, Expected_Text);
    } else {
      Actual_Text =
          webDriver.findElement(By.cssSelector("div.usa-width-one-whole > span")).getText();
      Expected_Text = "There are currently no certifications";
      assertEquals(Actual_Text, Expected_Text);
      Actual_Text = webDriver.findElement(By.cssSelector("div.usa-width-one-whole > p")).getText();
      Expected_Text =
          "Start a new Women-Owned Small Business (WOSB) or Economically Disadvantaged Women-Owned Small Business (EDWOSB) certification or update your current certification in our new system. If you have a current WOSB or EDWOSB certification, your company is required to add new documentation in our current system. If you have any questions, please email certify@sba.gov for more information.";
      assertEquals(Actual_Text, Expected_Text);
      // Click on the WOSB link.
      webDriver.findElement(By.linkText("WOSB")).click();
      // Verify and validate the terms for the certification.
      Actual_Text =
          webDriver.findElement(By.xpath("//form[@id='new_sba_application']/div[2]")).getText();
      Expected_Text =
          "All small businesses that are interested in submitting an offer on a solicitation that has been set aside for Women-Owned Small Businesses (WOSB) and Economically Disadvantaged Women-Owned Small Businesses (EDWOSB) under the WOSB Program must complete this certification prior to submitting the offer. This includes checking all boxes presented and having an authorized officer of the woman-owned small business or economically disadvantaged woman-owned small business electronically sign and date the certification. You must upload all other required documents as indicated here-in to the WOSB Program Repository. For questions, please e-mail wosb@sba.gov.\nPlease read the following certification statements. The Federal government relies on the information in this form and any documents or supplemental information submitted to determine whether the business is eligible for a contract authorized under the WOSB Program. The definitions for the terms used in this certification are set forth in the Small Business Act, U.S. Small Business Administration (SBA) regulations (13 C.F.R. Part 127), and also any statutory and regulatory provision referenced in those authorities. In addition, please note that the SBA may request further clarification or supporting documentation in order to assist in the verification of any of the information provided and that each person signing this certification may be prosecuted if they have provided false information. Any action taken with respect to this certification does not affect the Government's right to pursue criminal, civil or administrative remedies for incorrect or incomplete information given, even if correct information has been included in other materials submitted to SBA.";
      assertEquals(Actual_Text, Expected_Text);
    }
  }
}
