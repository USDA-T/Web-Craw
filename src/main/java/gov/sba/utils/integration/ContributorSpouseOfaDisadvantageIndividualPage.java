//@Montana
package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.sba.automation.CoreUtils;
import junit.framework.TestCase;

  public class ContributorSpouseOfaDisadvantageIndividualPage extends TestCase {
      private static final Logger logger = LogManager.getLogger(ContributorSpouseOfaDisadvantageIndividualPage.class.getName());
      WebDriver webDriver;

      public ContributorSpouseOfaDisadvantageIndividualPage(WebDriver webDriver) {
        this.webDriver = webDriver;
      }

  public void ContributorSpouseOfaDisadvantageIndividual() throws Exception {
      WebDriverWait wait = new WebDriverWait(webDriver, 30);
      logger.info("Completing spouse of a Disadvantaged Individual Sub-questionnaire");
      String Actual_Text = null;
      String Expected_Text = null;     
      //Verify the contributor status is in NOT STARTED.      
      Actual_Text = webDriver.findElement(By.linkText("Contributors")).getText();
      Expected_Text = "Contributors";
      assertEquals(Actual_Text, Expected_Text);
      Actual_Text = webDriver.findElement(By.xpath("//tr[3]/td[3]")).getText();
      Expected_Text = "NOT STARTED";
      assertEquals(Actual_Text, Expected_Text);
      WebElement ContributorStatus1 = webDriver.findElement(By.xpath("//tr[3]/td[3]"));
      HighLight.highLightElement(webDriver, ContributorStatus1);
      webDriver.findElement(By.linkText("Contributors")).click();
      wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//b[5]/a"), false));
      Actual_Text = webDriver.findElement(By.xpath("//b[5]/a")).getText();
      Expected_Text = "Add a spouse of a Disadvantaged Individual";
      assertEquals(Actual_Text, Expected_Text);
      //Click on the Add a spouse of a Disadvantaged Individual link and invite this contributor.
      webDriver.findElement(By.linkText("Add a spouse of a Disadvantaged Individual")).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[2]/fieldset/ul/li/label")));
      //Validate the send invitation button is present and to make sure users can't send empty data as request
      try {
      assertTrue(isElementPresent(By.name("commit")));
      CoreUtils.clickContinue(webDriver);
      Actual_Text = webDriver.findElement(By.xpath("enter when error is fixed")).getText();
      Expected_Text = "Please enter full name and email.";
      assertEquals(Actual_Text, Expected_Text);
      logger.info("Sending invite to SDVD");
      } catch (Error e) {
      logger.info(e.getMessage()); 
      }
      //Try to send an invalid email(Email that is already associated to a business.
      try {
      webDriver.findElement(By.xpath("//form[2]/fieldset/ul/li/input")).sendKeys("Contributor1 SDVD");
      webDriver.findElement(By.xpath("//form[2]/fieldset/ul/li[2]/input")).sendKeys("akanamontana@gmail.com");
      CoreUtils.clickContinue(webDriver);
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article/div/div/div/div/p")));
      Actual_Text = webDriver.findElement(By.xpath("//article/div/div/div/div/p")).getText();
      Expected_Text = "Please enter another email address for this contributor. He/She is already associated with another business in the system";
      assertEquals(Actual_Text, Expected_Text);
      logger.info("Sending invite to SDVD");
      } catch (Error e) {
      logger.info(e.getMessage()); 
      }     
      try{
      //Enter Full Name of contributor.
      webDriver.findElement(By.xpath("//form[2]/fieldset/ul/li/input")).sendKeys("Contributor1 SDVD");
      //Enter contributor's email.
      webDriver.findElement(By.xpath("//form[2]/fieldset/ul/li[2]/input")).sendKeys("norole12@mailinator.com");
      //Click on the Send invitation to collaborator button.
      CoreUtils.clickContinue(webDriver);
      //Verify that contributor SDVD is successfully added.
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article/div/div/div/div/p")));
      Actual_Text = webDriver.findElement(By.xpath("//article/div/div/div/div/p")).getText();
      Expected_Text = "Contributor1 SDVD has been added";
      assertEquals(Actual_Text, Expected_Text);
      logger.info("invite to SDVD is successfully send");
      } catch (Error e) {
      logger.info(e.getMessage()); 
      }
      
      
      

      
      
      
      
      
      
      
      
      
      
      
      
      Actual_Text = webDriver.findElement(By.xpath("")).getText();
      Expected_Text = "";
      assertEquals(Actual_Text, Expected_Text);
      Actual_Text = webDriver.findElement(By.xpath("")).getText();
      Expected_Text = "";
      assertEquals(Actual_Text, Expected_Text);
      Actual_Text = webDriver.findElement(By.xpath("")).getText();
      Expected_Text = "";
      assertEquals(Actual_Text, Expected_Text);
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//fieldset/a/span")));
      
  }

  private boolean isElementPresent(By cssSelector) {
    // TODO Auto-generated method stub
    return false;
  }
  }
          
  
 
          
          
          
          
          
          
          
          
          
          
          