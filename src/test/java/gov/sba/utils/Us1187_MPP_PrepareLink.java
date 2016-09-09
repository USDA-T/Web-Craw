package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Us1187_MPP_PrepareLink {
	// Set The variables/Define
	private static WebDriver webDriver;
	private static final Logger logger = LogManager.getLogger(Us1187_MPP_PrepareLink.class.getName());

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
	}

	@Test
	public void mainTest() throws Exception {
		String Actual_Text = null;
		String Expected_Text = null;
		try {
			WebElement Prepare_Link = webDriver.findElement(By.cssSelector("a[href*='/prepare']"));
			Prepare_Link.click();
			Prepare_Link.click();
			Thread.sleep(3000);
			logger.info("link is Clicked");

		} catch (Exception e) {
			logger.info("Link is not present + e.toString())");
		}

		try {
			// Check the Top part of the text for MPP Link
			List<WebElement> all_link = webDriver
					.findElement(By
							.xpath("//div[contains(@class,'usa-width-one-whole') and contains(@class,'usa-content')]"))
					.findElements(By.tagName("a"));
			WebElement mpp_link = all_link.get(4);
			Actual_Text = mpp_link.getText();
			logger.info(Actual_Text);
			Expected_Text = "All Small Mentor-Protégé Program Preparation Checklist";
			assertEquals(Expected_Text, Actual_Text);
			Thread.sleep(3000);
			mpp_link.click();
			// Check the bottom part of the text for MPP Link
			WebElement copy_Content = webDriver
					.findElement(By.xpath("//h3[contains(@id,'mpp-anc') and contains(text(),'All Small Mentor')]"));
			Actual_Text = copy_Content.getText();
			logger.info(Actual_Text);
			// Below to Assert the new Mpp Text added
			WebElement copy_Content_Parent = copy_Content.findElement(By.xpath("./.."));
			String total_Text = copy_Content_Parent.getText();
			logger.info(total_Text);
			String text_All_MppCopy = "All Small Mentor-Protégé Program Preparation Checklist\nQualifying individual(s) include all businesses that meet the size standard for small in the NAICS code in which they are seeking business development assistance\nActive registration in the System for Award Management for the firm, available at SAM.gov (Note: The firm’s DUNS number and EIN, and MPIN must exactly match SAM registration)\nElectronic versions (in PDF format) of the following documents:\nCertificates of completion for viewing the All Small MPP training module (mandatory viewing for both the Protégé and the Mentor)\nYour completed business plan\nAny active Mentor-Protégé Agreements you have with either the SBA or another federal agency\nAn SBA size redetermination letter if the SBA has ever found you to be “other than small” in the NAICS code in which you’re requesting business development assistance";
			assertEquals(text_All_MppCopy, total_Text);

		} catch (Exception e) {
			logger.info("Link is not present" + e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}

}
