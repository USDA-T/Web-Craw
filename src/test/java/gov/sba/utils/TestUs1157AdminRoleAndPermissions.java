package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

public class TestUs1157AdminRoleAndPermissions extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestUs1157AdminRoleAndPermissions.class.getName());
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
        // open firefox, chrome or IE and navigate to certify.sba login page.
        // Login to Vendor Admin Dashboard.
        Thread.sleep(4000);
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        try {
            assertTrue(webDriver.getPageSource().contains("Draft"));
            webDriver.findElement(By.linkText("Delete")).click();
            webDriver.switchTo().alert().accept();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webDriver.navigate().refresh();
        } catch (Error e) {
            logger.info(
                    "There are(is) no certification in-progress on the dashboard, a new certification is beinng created");
        }
        Thread.sleep(7000);

        // Locate the Certifications on the dashboard, click on it and select
        // EDWOSB to continue.
        assertElementpresent(webDriver.findElement(By.linkText("Programs")));
        webDriver.findElement(By.linkText("Programs")).click();
        webDriver.findElement(By.linkText("EDWOSB")).click();
        // Verify that vendor admin start a certification.
        String actual_Text0 = webDriver.findElement(By.xpath("//form[@id='new_sba_application']/div/div[3]")).getText();
        String expected_Text0 = "All small businesses that are interested in submitting an offer on a solicitation that has been set aside for Women-Owned Small Businesses (WOSB) and Economically Disadvantaged Women-Owned Small Businesses (EDWOSB) under the WOSB Program must complete this certification prior to submitting the offer. This includes checking all boxes presented and having an authorized officer of the woman-owned small business or economically disadvantaged woman-owned small business electronically sign and date the certification. You must upload all other required documents as indicated here-in to the WOSB Program Repository. For questions, please e-mail wosb@sba.gov.\nPlease read the following certification statements. The Federal government relies on the information in this form and any documents or supplemental information submitted to determine whether the business is eligible for a contract authorized under the WOSB Program. The definitions for the terms used in this certification are set forth in the Small Business Act, U.S. Small Business Administration (SBA) regulations (13 C.F.R. Part 127), and also any statutory and regulatory provision referenced in those authorities. In addition, please note that the SBA may request further clarification or supporting documentation in order to assist in the verification of any of the information provided and that each person signing this certification may be prosecuted if they have provided false information. Any action taken with respect to this certification does not affect the Government's right to pursue criminal, civil or administrative remedies for incorrect or incomplete information given, even if correct information has been included in other materials submitted to SBA.";
        assertEquals(actual_Text0, expected_Text0);
        // verify the accept button and click on it.
        assertElementpresent(webDriver.findElement(By.xpath("//input[@name='commit']")));
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        // click the dashboard link.
        webDriver.findElement(By.linkText("Dashboard")).click();
        Thread.sleep(4000);
        // verify that the Business Profile,Document Library and Certifications
        // tables are present.
        // Business Profile.
        assertElementpresent(webDriver.findElement(By.cssSelector("div.usa-width-one-whole.connect")));
        String actual_Text = webDriver.findElement(By.cssSelector("div.usa-width-one-whole.connect")).getText();
        String expected_Text = "Business Profile";
        assertEquals(actual_Text, expected_Text);
        // Documents Library.
        assertElementpresent(webDriver.findElement(By.cssSelector("div.usa-width-one-half > div.connect")));
        String actual_Text1 = webDriver.findElement(By.cssSelector("div.usa-width-one-half > div.connect")).getText();
        String expected_Text1 = "Document Library";
        assertEquals(actual_Text1, expected_Text1);
        // Certifications.
        assertElementpresent(
                webDriver.findElement(By.cssSelector("div.usa-width-one-half.right_padding > div.connect")));
        String actual_Text2 = webDriver
                .findElement(By.cssSelector("div.usa-width-one-half.right_padding > div.connect")).getText();
        String expected_Text2 = "Certifications";
        assertEquals(actual_Text2, expected_Text2);
        // Click on the View More+ and verify.
        assertElementpresent(
                webDriver.findElement(By.cssSelector("a.expander-view-more-trigger.expander-view-more-hidden")));
        webDriver.findElement(By.cssSelector("a.expander-view-more-trigger.expander-view-more-hidden")).click();
        // Verify collapse page.
        String actual_Text3 = webDriver
                .findElement(By.xpath("//article[@id='dashboard-index']/div[3]/div/div/div/p[4]/span")).getText();
        String expected_Text3 = "123456789";
        assertEquals(actual_Text3, expected_Text3);
        // Click on view less-.
        webDriver.findElement(By.cssSelector("a.expander-view-more-trigger")).click();
        // Locate the My Profile link, verify and click on it.
        assertElementpresent(webDriver.findElement(By.linkText("My Profile")));
        webDriver.findElement(By.linkText("My Profile")).click();
        // Verify and validate the vendor admin profile.
        assertElementpresent(webDriver.findElement(By.cssSelector("div.usa-width-one-whole.connect")));
        String actual_Text4 = webDriver.findElement(By.cssSelector("div.usa-width-one-whole.connect")).getText();
        String expected_Text4 = "My Profile";
        assertEquals(actual_Text4, expected_Text4);
        // Verify the Associated Business section.
        assertElementpresent(
                webDriver.findElement(By.cssSelector("div.usa-width-one-whole > div.usa-width-one-whole.connect")));
        String actual_Text5 = webDriver
                .findElement(By.cssSelector("div.usa-width-one-whole > div.usa-width-one-whole.connect")).getText();
        String expected_Text5 = "Associated Business";
        assertEquals(actual_Text5, expected_Text5);
        // Business Address.
        assertElementpresent(
                webDriver.findElement(By.cssSelector("div.usa-width-one-whole.business-bg > h4.usa-heading")));
        // Duns
        assertElementpresent(
                webDriver.findElement(By.xpath("//article[@id='dashboard-index']/div[3]/div[2]/div/p[2]/span")));
        // ***add view more view less .
        // Verify that Vendor Admin is able to edit passphrase.
        assertElementpresent(webDriver.findElement(By.linkText("Edit Passphrase")));
        String actual_Text6 = webDriver.findElement(By.linkText("Edit Passphrase")).getText();
        String expected_Text6 = "Edit Passphrase";
        assertEquals(actual_Text6, expected_Text6);
        webDriver.findElement(By.linkText("Edit Passphrase")).click();
        Thread.sleep(5000);
        // Verify page.
        assertElementpresent(webDriver.findElement(By.cssSelector("h2.usa-heading")));
        String actual_Text7 = webDriver.findElement(By.cssSelector("h2.usa-heading")).getText();
        String expected_Text7 = "Edit Passphrase";
        assertEquals(actual_Text7, expected_Text7);
        // Logout.
        webDriver.findElement(By.linkText("Logout")).click();
    }

    private void assertElementpresent(WebElement findElement) {
        // TODO Auto-generated method stub
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}