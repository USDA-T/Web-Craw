package gov.sba.utils;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.TestCase;

public class US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS3 extends TestCase {
  private static WebDriver mydriver;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    TestHelpers.initSystemConfig();
    mydriver = new FirefoxDriver();

    //mydriver.get("https://staging-certify.sba.gov/");
    mydriver.get("http://localhost:3000/");
    mydriver.manage().window().maximize();
  }

  @After
  public void tearDown() throws Exception {
    mydriver.quit();
  }

  @Test
  public void testUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS3_MainTest() throws Exception{
    System.out.println("User is NOT eligible for Any of the programs because user answer NO for Qs2: 8(a), WOSB, EDWOSB & Hob-zone");

    //Locate the Am I Eligible or the Find Out button on the Certify.SBA.Gov landing page and click on it.
    mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[3]/a")).click();
    Thread.sleep(4000);

    //Verify that user is navigated to the right page.
    String actual_error6= mydriver.findElement(By.xpath(".//*[@id='am-i']/h1")).getText();
    String expected_error6="Is there an SBA Small Business Program for me?";
    assertEquals(actual_error6, expected_error6);

    //Locate the first question and select Yes and verify the More Detail meaning of the question.
    String actual_Text2=mydriver.findElement(By.cssSelector("div.usa-width-one-half > p.lead-para")).getText();
    String expected_Text2="Are the qualifying individual(s) of the firm who are applying for SBA small business programs U.S. citizens?";
    assertEquals(actual_Text2, expected_Text2);

    // Validate the meaning for question one.
    String actual_Text3=mydriver.findElement(By.cssSelector("div.details.usa-width-one-half > p")).getText();
    String expected_Text3="A Citizen means a person born or naturalized in the United States. Resident ailens and holders of permanent visas are not considered to be citizens. 13 C.F.R. 127.102";
    assertEquals(actual_Text3, expected_Text3);

    // Verify and click on the Yes button.
    mydriver.findElement(By.xpath(".//*[@id='us_citizen']/div[1]/div[1]/div/button[1]")).click();
    Thread.sleep(5000);

    // Locate the 2nd question and select No and verify the More Detail meaning of the question.
    String actual_error3=mydriver.findElement(By.xpath(".//*[@id='unconditional_direct_51_percent']/div[1]/div[1]/p")).getText();
    String expected_error3="Is the 51% ownership of the firm unconditional and direct?";
    assertEquals(actual_error3, expected_error3);

    // Verify the detail meaning for the 2nd question.
    String actual_error=mydriver.findElement(By.xpath("//div[@id='unconditional_direct_51_percent']/div/div[2]/ul/li")).getText();
    String expected_error="Qualifying individual(s) mustunconditionally and directly own and controlat least 51% of the business.";
    assertEquals(actual_error, expected_error);

    String actual_error4=mydriver.findElement(By.xpath("//div[@id='unconditional_direct_51_percent']/div/div[2]/ul/li[2]")).getText();
    String expected_error4="In general, the 51%ownershipmay not be through another business entity.";
    assertEquals(actual_error4, expected_error4);

    String actual_error5=mydriver.findElement(By.xpath("//div[@id='unconditional_direct_51_percent']/div/div[2]/ul/li[3]")).getText();
    String expected_error5="Controlmeans that both the long-term decision making and the day-to-day management of the business are controlled by qualifying individual(s).";
    assertEquals(actual_error5, expected_error5);
    Thread.sleep(4000);

    //verify and click on the No button.
    mydriver.findElement(By.xpath(".//*[@id='unconditional_direct_51_percent']/div[1]/div[1]/div/button[2]")).click();

    //Verify searched results.
    String actual_error7=mydriver.findElement(By.cssSelector("span.message")).getText();
    String expected_error7="In order to participate in SBA small business programs, the owner or owners of the firm must be unconditional and direct.";
    assertEquals(actual_error7, expected_error7);

    //Click on the Exit button.
    mydriver.findElement(By.linkText("Exit")).click();
  }

}
