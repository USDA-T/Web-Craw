package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.TestCase;

public class ReturnActiveCert2Page extends TestCase {
  private static final Logger logger = LogManager.getLogger(ReturnActiveCert2Page.class.getName());
  WebDriver webDriver;
  int get_The_Row_From_Login_Data;
  String DunsNumber;

  public ReturnActiveCert2Page(WebDriver webDriver) {
    this.webDriver = webDriver;
    DunsNumber = null;
  }

  public void ReturnActiveCert2() throws Exception {
    WebDriverWait wait = new WebDriverWait(webDriver, 40);
    wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//p[2]/span"), false));
    DunsNumber = webDriver.findElement(By.xpath("//p[2]/span")).getText();

    if (webDriver.getPageSource().contains("Self Certified")) {
      // Logout.
      webDriver.findElement(By.linkText("Logout")).click();
      // Return the Active Cert.
      logger.info("Returning active cert");
      get_The_Row_From_Login_Data = 0;
      LoginPageWithReference login_Data71 =
          new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data71.Login_With_Reference();
      if (webDriver.getCurrentUrl().contains("certify.qa")) {
        webDriver.findElement(By.id("query")).sendKeys(DunsNumber);
        webDriver.findElement(By.xpath("//form/div/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4/a")));
        webDriver.findElement(By.xpath("//h4/a")).click();
        if (webDriver.getPageSource().contains("Return to Vendor")) {
          webDriver.findElement(By.linkText("Return to Vendor")).click();
          // webDriver.switchTo().alert().accept();
          webDriver.findElement(By.id("profileid")).click();
          webDriver.findElement(By.linkText("Logout")).click();
          get_The_Row_From_Login_Data = 1;
          LoginPageWithReference login_Data =
              new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
          login_Data.Login_With_Reference();
        } else {
          logger.info("Return to Vendor Link is missing please verify why.");
          if (assertTrue(webDriver.getPageSource().concat("EDWOSB Self-Certification"))) {
            webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
          } else {
            logger.info("NO EDWOSB, All good");
          }
          if (assertTrue(webDriver.getPageSource().concat("WOSB Self-Certification"))) {
            webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
          } else {
            logger.info("NO WOSB, All good");
          }
          webDriver.findElement(By.id("submit_button")).click();
          webDriver.findElement(By.linkText("Determination")).click();
          webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
          webDriver.findElement(By.xpath("//form[@id='new_determination']/input[5]")).click();
          webDriver.findElement(By.linkText("Vendor Overview")).click();
          webDriver.findElement(By.id("profileid")).click();
          webDriver.findElement(By.linkText("Logout")).click();
          Thread.sleep(2000);
          get_The_Row_From_Login_Data = 1;
          LoginPageWithReference login_Data =
              new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
          login_Data.Login_With_Reference();
        }
      } else {
        if (webDriver.getCurrentUrl().contains("staging")) {
          webDriver.findElement(By.id("query")).sendKeys(DunsNumber);
          webDriver.findElement(By.xpath("//form/div/button")).click();
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4/a")));
          webDriver.findElement(By.xpath("//h4/a")).click();
          if (webDriver.getPageSource().contains("Return to Vendor")) {
            webDriver.findElement(By.linkText("Return to Vendor")).click();
            // webDriver.switchTo().alert().accept();
            webDriver.findElement(By.id("profileid")).click();
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 1;
            LoginPageWithReference login_Data =
                new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
          } else {
            logger.info("Return to Vendor Link is missing please verify why.");
            if (assertTrue(webDriver.getPageSource().concat("EDWOSB Self-Certification"))) {
              webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
            } else {
              logger.info("NO EDWOSB, All good");
            }
            if (assertTrue(webDriver.getPageSource().concat("WOSB Self-Certification"))) {
              webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
            } else {
              logger.info("NO WOSB, All good");
            }
            webDriver.findElement(By.id("submit_button")).click();
            webDriver.findElement(By.linkText("Determination")).click();
            webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
            webDriver.findElement(By.xpath("//form[@id='new_determination']/input[5]")).click();
            webDriver.findElement(By.linkText("Vendor Overview")).click();
            webDriver.findElement(By.id("profileid")).click();
            webDriver.findElement(By.linkText("Logout")).click();
            Thread.sleep(2000);
            get_The_Row_From_Login_Data = 1;
            LoginPageWithReference login_Data =
                new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
          }
        } else {
          if (webDriver.getCurrentUrl().contains("newqa")) {
            webDriver.findElement(By.xpath("//button[@id='searchtext']")).click();
            webDriver.findElement(By.id("query")).sendKeys(DunsNumber);
            webDriver.findElement(By.xpath("//form/div/button")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4/a")));
            webDriver.findElement(By.xpath("//h4/a")).click();
            if (webDriver.getPageSource().contains("Return to Vendor")) {
              webDriver.findElement(By.linkText("Return to Vendor")).click();
              // webDriver.switchTo().alert().accept();
              webDriver.findElement(By.id("profileid")).click();
              webDriver.findElement(By.linkText("Logout")).click();
              get_The_Row_From_Login_Data = 1;
              LoginPageWithReference login_Data =
                  new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
              login_Data.Login_With_Reference();
            } else {
              logger.info("Return to Vendor Link is missing please verify why.");
              if (assertTrue(webDriver.getPageSource().concat("EDWOSB Self-Certification"))) {
                webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
              } else {
                logger.info("NO EDWOSB, All good");
              }
              if (assertTrue(webDriver.getPageSource().concat("WOSB Self-Certification"))) {
                webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
              } else {
                logger.info("NO WOSB, All good");
              }
              webDriver.findElement(By.id("submit_button")).click();
              webDriver.findElement(By.linkText("Determination")).click();
              webDriver.findElement(By.id("review_workflow_state_returned_for_modification"))
                  .click();
              webDriver.findElement(By.xpath("//form[@id='new_determination']/input[5]")).click();
              webDriver.findElement(By.linkText("Vendor Overview")).click();
              webDriver.findElement(By.id("profileid")).click();
              webDriver.findElement(By.linkText("Logout")).click();
              get_The_Row_From_Login_Data = 1;
              LoginPageWithReference login_Data =
                  new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
              login_Data.Login_With_Reference();
            }
          } else {
            if (webDriver.getCurrentUrl().contains("localhost")) {
              webDriver.findElement(By.id("query")).sendKeys(DunsNumber);
              webDriver.findElement(By.xpath("//form/div/button")).click();
              wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4/a")));
              webDriver.findElement(By.xpath("//h4/a")).click();
              if (webDriver.getPageSource().contains("Return to Vendor")) {
                webDriver.findElement(By.linkText("Return to Vendor")).click();
                // webDriver.switchTo().alert().accept();
                webDriver.findElement(By.id("profileid")).click();
                webDriver.findElement(By.linkText("Logout")).click();
                get_The_Row_From_Login_Data = 1;
                LoginPageWithReference login_Data =
                    new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
                login_Data.Login_With_Reference();
              } else {
                logger.info("Return to Vendor Link is missing please verify why.");
                if (assertTrue(webDriver.getPageSource().concat("EDWOSB Self-Certification"))) {
                  webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
                } else {
                  logger.info("NO EDWOSB, All good");
                }
                if (assertTrue(webDriver.getPageSource().concat("WOSB Self-Certification"))) {
                  webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
                } else {
                  logger.info("NO WOSB, All good");
                }
                webDriver.findElement(By.id("submit_button")).click();
                webDriver.findElement(By.linkText("Determination")).click();
                webDriver.findElement(By.id("review_workflow_state_returned_for_modification"))
                    .click();
                webDriver.findElement(By.xpath("//form[@id='new_determination']/input[5]")).click();
                webDriver.findElement(By.linkText("Vendor Overview")).click();
                webDriver.findElement(By.id("profileid")).click();
                webDriver.findElement(By.linkText("Logout")).click();
                get_The_Row_From_Login_Data = 1;
                LoginPageWithReference login_Data =
                    new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
                login_Data.Login_With_Reference();
              }
            } else {
              logger.info("This Vendor has no Active cert, we are good.");
            }
          }
        }
      }
    }
  }

  private boolean assertTrue(String concat) {
    // TODO Auto-generated method stub
    return false;
  }
}


