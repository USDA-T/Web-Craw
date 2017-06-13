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
import gov.sba.automation.FixtureUtils;
import junit.framework.TestCase;

public class ContributorSpouseOfaDisadvantageIndividualPage extends TestCase {
  private static final Logger logger = LogManager.getLogger(ContributorSpouseOfaDisadvantageIndividualPage.class.getName());
  WebDriver webDriver;
  int get_The_Row_From_Login_Data;
  String SDvDFN;
  String SDvDEmail1;
  String SDvDEmail2;
  String SDvDEmail3;
  String SDvDEmail4;

  public ContributorSpouseOfaDisadvantageIndividualPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    SDvDFN = "Contributor1 SDVD";
    SDvDEmail1 = "norole22@mailinator.com";
    SDvDEmail1 = "norole12@mailinator.com";
    SDvDEmail1 = "norole13@mailinator.com";
    SDvDEmail1 = "akanamontana@gmail.com";

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
  //Validate the send invitation button is present and to make sure users can't send empty data as request// open when de fix.
  //try {
  //assertTrue(isElementPresent(By.name("commit")));
  //CoreUtils.clickContinue(webDriver);
  //Actual_Text = webDriver.findElement(By.xpath("enter when error is fixed")).getText();
  //Expected_Text = "Please enter full name and email.";
  //assertEquals(Actual_Text, Expected_Text);
  //logger.info("Sending invite to SDVD");
  //} catch (Error e) {
  //logger.info(e.getMessage()); 
  //}
  //Try to send an invalid email(Email that is already associated to a business.
  //try {
  //webDriver.findElement(By.xpath("//form[2]/fieldset/ul/li/input")).sendKeys("Contributor1 SDVD");
  //webDriver.findElement(By.xpath("//form[2]/fieldset/ul/li[2]/input")).sendKeys("norole22@mailinator.com");
  //CoreUtils.clickContinue(webDriver);
  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article/div/div/div/div/p")));
  //Actual_Text = webDriver.findElement(By.xpath("//article/div/div/div/div/p")).getText();
  //Expected_Text = "Contributor1 SDVD has been added";
  //assertEquals(Actual_Text, Expected_Text);
  //logger.info("Invite was sent to SDVD");
  //} catch (Error e) {
  //logger.info(e.getMessage()); 
  //}     
  try{
  //Enter Full Name of contributor.
  webDriver.findElement(By.xpath("//form[2]/fieldset/ul/li/input")).sendKeys("Contributor1 SDVD");
  //Enter contributor's email.
  webDriver.findElement(By.xpath("//form[2]/fieldset/ul/li[2]/input")).sendKeys("norole17@mailinator.com");
  //Click on the Send invitation to collaborator button.
  CoreUtils.clickContinue(webDriver);
  webDriver.navigate().back();
  webDriver.findElement(By.linkText("Add a spouse of a Disadvantaged Individual")).click();
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[2]/fieldset/ul/li/label")));
  webDriver.findElement(By.xpath("//form[2]/fieldset/input")).click();
  //Verify that contributor SDVD is successfully added.
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article/div/div/div/div/p")));
  Actual_Text = webDriver.findElement(By.xpath("//article/div/div/div/div/p")).getText();
  Expected_Text = "Contributor1 SDVD has been added";
  assertEquals(Actual_Text, Expected_Text);
  logger.info("invite to SDVD is successfully send");
  } catch (Error e) {
  logger.info(e.getMessage()); 
  }
  //Logout and login with the invited contributor.
  webDriver.findElement(By.linkText("Logout")).click();
  get_The_Row_From_Login_Data = 59;
  LoginPageWithReference login_Data =new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
  login_Data.Login_With_Reference();
  
  
  //Verify intro page.
  
  
  CoreUtils.clickContinue(webDriver);
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
  Expected_Text = "Gender";
  assertEquals(Actual_Text, Expected_Text);
  CoreUtils.clickContinue(webDriver);
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2"))); 
  Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
  Expected_Text = "Marital Status";
  assertEquals(Actual_Text, Expected_Text);
  webDriver.findElement(By.xpath("//label")).click();
  CoreUtils.clickContinue(webDriver);
  //Verify page.
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2"))); 
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);
  Actual_Text = webDriver.findElement(By.xpath("//div/span")).getText();
  Expected_Text = "Please answer this question";
  assertEquals(Actual_Text, Expected_Text);      
  //Enter SSN
  webDriver.findElement(By.id("input-type-text")).sendKeys("123456789");
  CoreUtils.clickContinue(webDriver);
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);      
  Actual_Text = webDriver.findElement(By.xpath("//div/span")).getText();
  Expected_Text = "Please answer this question";
  assertEquals(Actual_Text, Expected_Text);       
  webDriver.findElement(By.id("input-type-text")).sendKeys("123456789");
  //click on continue.
  CoreUtils.clickContinue(webDriver);      
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
  //Verify section is required.
  CoreUtils.clickContinue(webDriver); 
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset/span")));
  Actual_Text = webDriver.findElement(By.xpath("//fieldset/span")).getText();
  Expected_Text = "This field is required.";
  assertEquals(Actual_Text, Expected_Text);            
  //enter address.
  webDriver.findElement(By.xpath("//fieldset/div/fieldset/input")).sendKeys("7865 testing dr country");
  webDriver.findElement(By.xpath("//div/input")).sendKeys("Washington");
  Actual_Text = webDriver.findElement(By.xpath("//select")).getText();
  Expected_Text = "Select a state\nAlabama\nAlaska\nArizona\nArkansas\nCalifornia\nColorado\nConnecticut\nDelaware\nFlorida\nGeorgia\nHawaii\nIdaho\nIllinois\nIndiana\nIowa\nKansas\nKentucky\nLouisiana\nMaine\nMaryland\nMassachusetts\nMichigan\nMinnesota\nMississippi\nMissouri\nMontana\nNebraska\nNevada\nNew Hampshire\nNew Jersey\nNew Mexico\nNew York\nNorth Carolina\nNorth Dakota\nOhio\nOklahoma\nOregon\nPennsylvania\nRhode Island\nSouth Carolina\nSouth Dakota\nTennessee\nTexas\nUtah\nVermont\nVirginia\nWashington\nWest Virginia\nWisconsin\nWyoming\nDistrict of Columbia\nPuerto Rico\nGuam\nAmerican Samoa\nU.S. Virgin Islands\nNorthern Mariana Islands";
  assertEquals(Actual_Text, Expected_Text);
  webDriver.findElement(By.xpath("//select")).click();
  webDriver.findElement(By.xpath("//option[48]")).click();
  webDriver.findElement(By.xpath("//div[2]/input")).sendKeys("20987");
  webDriver.findElement(By.id("date-643")).sendKeys("09/12/1990");
  //click on continue.
  CoreUtils.clickContinue(webDriver);
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
  assertEquals("Role in Applicant Firm", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);       
  Actual_Text = webDriver.findElement(By.xpath("//div/span")).getText();
  Expected_Text = "Please answer this question";
  assertEquals(Actual_Text, Expected_Text);       
  webDriver.findElement(By.xpath("//div/input")).click();
  //click on continue.
  CoreUtils.clickContinue(webDriver);      
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Length of residency", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);       
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div/span")).getText());
  webDriver.findElement(By.xpath("//div/input")).click();
  //click on continue.
  CoreUtils.clickContinue(webDriver);      
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Date and Place of Birth", webDriver.findElement(By.cssSelector("h2")).getText());
  webDriver.findElement(By.id("date-649")).sendKeys("04/23/1978");
  webDriver.findElement(By.id("input-type-text")).sendKeys("Washington");
  //click on continue.
  CoreUtils.clickContinue(webDriver);       
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("US Citizenship", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);          
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div/span")).getText());
  webDriver.findElement(By.xpath("//div/input")).click();
  //click on continue.
  CoreUtils.clickContinue(webDriver);      
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Upload Resume", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);        
  assertEquals("Attachment is required", webDriver.findElement(By.xpath("//div/span")).getText());     
  //Upload a document.
  String file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  MontanaUploadDocumentPage MontanaUploadDocument = new MontanaUploadDocumentPage(webDriver);
  MontanaUploadDocument.MontanaUploadDocument(file_path_abs);
  //click on continue.
  CoreUtils.clickContinue(webDriver);       
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Applicant Firm Ownership", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);      
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div[2]/span")).getText());
  webDriver.findElement(By.xpath("//div[2]/input")).sendKeys("76");
  webDriver.findElement(By.id("input-type-textarea")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
  //click on continue.
  CoreUtils.clickContinue(webDriver);      
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Bank Account Access", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);        
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div/span")).getText());
  webDriver.findElement(By.xpath("//div/input")).click();
  webDriver.findElement(By.xpath("//textarea")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
  //click on continue.
  CoreUtils.clickContinue(webDriver);      
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Prior Ownership", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);       
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div/span")).getText());
  webDriver.findElement(By.xpath("//div/input")).click();
  webDriver.findElement(By.xpath("//textarea")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
  webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();     
  //Upload a document.
  String file_path_abs1 = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  MontanaUploadDocumentPage MontanaUploadDocument1 = new MontanaUploadDocumentPage(webDriver);
  MontanaUploadDocument1.MontanaUploadDocument(file_path_abs1);
  //click on continue.
  CoreUtils.clickContinue(webDriver);        
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Business Affiliations", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);      
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div/span")).getText());
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div[2]/fieldset/div/span")).getText());
  webDriver.findElement(By.xpath("//div/input")).click();     
  //Upload a document.
  String file_path_abs2 = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  MontanaUploadDocumentPage MontanaUploadDocument2 = new MontanaUploadDocumentPage(webDriver);
  MontanaUploadDocument2.MontanaUploadDocument(file_path_abs2);
  webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();
  webDriver.findElement(By.xpath("//textarea")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
  //click on continue.
  CoreUtils.clickContinue(webDriver);       
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Prior 8a Involvement", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);       
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div/span")).getText());
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div[2]/fieldset/div/span")).getText());
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div[3]/fieldset/div/span")).getText());
  webDriver.findElement(By.xpath("//div/input")).click();      
  //Upload a document.
  String file_path_abs3 = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  MontanaUploadDocumentPage MontanaUploadDocument3 = new MontanaUploadDocumentPage(webDriver);
  MontanaUploadDocument3.MontanaUploadDocument(file_path_abs3);      
  webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();
  webDriver.findElement(By.xpath("//div[3]/fieldset/div/input")).click();      
  //Upload a document.
  file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  Upload2pdfOnSamePage upload2pdfOnSame = new Upload2pdfOnSamePage(webDriver);
  upload2pdfOnSame.Upload2pdfOnSame(file_path_abs);     
  //click on continue.
  CoreUtils.clickContinue(webDriver);       
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Federal Employment", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);         
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div/span")).getText());
  webDriver.findElement(By.xpath("//div/input")).click();      
  //Upload a document.
  String file_path_abs4 = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  MontanaUploadDocumentPage MontanaUploadDocument4 = new MontanaUploadDocumentPage(webDriver);
  MontanaUploadDocument4.MontanaUploadDocument(file_path_abs4);     
  //click on continue.
  CoreUtils.clickContinue(webDriver);     
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Financial", webDriver.findElement(By.cssSelector("h2")).getText());
  webDriver.findElement(By.xpath("//div/input")).click();      
  //Upload a document.
  String file_path_abs5 = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  MontanaUploadDocumentPage MontanaUploadDocument5 = new MontanaUploadDocumentPage(webDriver);
  MontanaUploadDocument5.MontanaUploadDocument(file_path_abs5);
  //Select yes and upload documents.
  webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();     
  //Upload a document.
  file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  Upload2pdfOnSamePage upload2pdfOnSame1 = new Upload2pdfOnSamePage(webDriver);
  upload2pdfOnSame1.Upload2pdfOnSame(file_path_abs);      
  webDriver.findElement(By.xpath("//div[3]/fieldset/div/input")).click();     
  //Upload a document.
  file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  Upload3pdfOnSamePage upload3pdfOnSame1 = new Upload3pdfOnSamePage(webDriver);
  upload3pdfOnSame1.Upload3pdfOnSame(file_path_abs);      
  webDriver.findElement(By.xpath("//div[4]/fieldset/div/input")).click();     
  //Upload a document.
  file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  Upload4pdfOnSamePage upload4pdfOnSame2 = new Upload4pdfOnSamePage(webDriver);
  upload4pdfOnSame2.Upload4pdfOnSame(file_path_abs);
  CoreUtils.clickContinue(webDriver);
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Criminal History", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);       
  assertEquals("Please answer this question", webDriver.findElement(By.xpath("//div[2]/fieldset/div/span")).getText());
  webDriver.findElement(By.xpath("//div/input")).click();
  webDriver.findElement(By.xpath("//textarea")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
  webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();
  webDriver.findElement(By.xpath("//div[3]/fieldset/div/input")).click();
  webDriver.findElement(By.xpath("//div[3]/fieldset/div/label")).click();
  webDriver.findElement(By.xpath("//div[4]/fieldset/div/input")).click();
  //click on continue.
  CoreUtils.clickContinue(webDriver); 
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  //Verify section is required.
  CoreUtils.clickContinue(webDriver); 
  assertEquals("Attachment is required", webDriver.findElement(By.xpath("//div[2]/fieldset/div/div/span")).getText());      
  //Upload a document.
  file_path_abs4 = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  MontanaUploadDocumentPage MontanaUploadDocument6 = new MontanaUploadDocumentPage(webDriver);
  MontanaUploadDocument6.MontanaUploadDocument(file_path_abs4);      
  //Upload a document.
  file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  Upload2pdfOnSamePage upload2pdfOnSame4 = new Upload2pdfOnSamePage(webDriver);
  upload2pdfOnSame4.Upload2pdfOnSame(file_path_abs);      
  //Upload a document.
  file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  Upload3pdfOnSamePage upload3pdfOnSame5 = new Upload3pdfOnSamePage(webDriver);
  upload3pdfOnSame5.Upload3pdfOnSame(file_path_abs);  
  //click on continue.
  CoreUtils.clickContinue(webDriver);       
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  assertEquals("Tax Returns", webDriver.findElement(By.cssSelector("h2")).getText());
  //Verify section is required.
  CoreUtils.clickContinue(webDriver);       
  assertEquals("Attachment is required", webDriver.findElement(By.xpath("//div/span")).getText());      
  //Upload a document.
  file_path_abs4 = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
  MontanaUploadDocumentPage MontanaUploadDocument7 = new MontanaUploadDocumentPage(webDriver);
  MontanaUploadDocument7.MontanaUploadDocument(file_path_abs4);
  webDriver.findElement(By.id("section_submit_button")).click();
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));      
  //Complete the SDvD financial section.
  ContributorsFinancialSectionPage contributorsFinancialSection = new ContributorsFinancialSectionPage(webDriver);
  contributorsFinancialSection.ContributorsFinancialSection();      

  
  
  
  
  
  
  
  
  
  
  
       
  
}
}
      

          
          
          
          
          
          
          
          
          
          
          