package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({gov.sba.utils.integration.UnstableTests.class})

public class TestApp8aInitialMaterApplication extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestApp8aInitialMaterApplication.class.getName());
  private static WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    webDriver.manage().window().maximize();
    get_The_Row_From_Login_Data = 1;

  }

  @Test
  public void testMainTest() throws Exception {
    // try{
    logger.info("Test for 8a initial master Flow");
    // Login to dashboard.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();
    // Verify if there is an existing certification on the dashboard and
    // delete to start a new certification.
    DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
    deleteDraftCert.DeleteDraftCert();
    // delete second draft if any.
    DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
    deleteDraftCert1.DeleteDraftCert();
    // Complete the basic eligibility section.
    BasicEligibilityMasterAppPage basicEligibilityMasterApp =
        new BasicEligibilityMasterAppPage(webDriver);
    basicEligibilityMasterApp.BasicEligibilityMasterApp();
    // Complete the SDvD section.
    ContributorSpouseOfaDisadvantageIndividualPage contributorSpouseOfaDisadvantageIndividual =
        new ContributorSpouseOfaDisadvantageIndividualPage(webDriver);
    contributorSpouseOfaDisadvantageIndividual.ContributorSpouseOfaDisadvantageIndividual();
    // Complete the Business Ownership section.
    BusinessOwnershipSubsectionMasterPage businessOwnershipSubsectionMaster =
        new BusinessOwnershipSubsectionMasterPage(webDriver);
    businessOwnershipSubsectionMaster.BusinessOwnershipSubsectionMaster();
    // Complete the character section.
    CharacterMasterAppSectionPage characterMasterAppSection =
        new CharacterMasterAppSectionPage(webDriver);
    characterMasterAppSection.CharacterMasterAppSection();
    // Complete the Control section.
    ControlMasterAppSubsectionPage controlMasterAppSubsection =
        new ControlMasterAppSubsectionPage(webDriver);
    controlMasterAppSubsection.ControlMasterAppSubsection();
    // Complete the SDvD section.
    // ContributorSpouseOfaDisadvantageIndividualPage contributorSpouseOfaDisadvantageIndividual =
    // new ContributorSpouseOfaDisadvantageIndividualPage(webDriver);
    // contributorSpouseOfaDisadvantageIndividual.ContributorSpouseOfaDisadvantageIndividual();
    // Complete the Other Individuals section.
    ContributorOtherIndividualsQuestionPage contributorOtherIndividualsQuestion =
        new ContributorOtherIndividualsQuestionPage(webDriver);
    contributorOtherIndividualsQuestion.ContributorOtherIndividualsQuestion();
    // webDriver.findElement(By.linkText("Logout")).click();
    // } catch (Exception e) {
    // ScreenShotPage screenShot = new ScreenShotPage(webDriver);
    // screenShot.ScreenShot();
    // logger.info(e.getMessage());
    // Assert.fail();
    // }
    logger.info("Success");
  }

  @After
  public void tearDown() throws Exception {
    webDriver.close();
  }
}
