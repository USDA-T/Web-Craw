package gov.sba.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import gov.sba.utils.VerifyEdwosbFlow;
import gov.sba.utils.helpers.LoginHelpers;

public class VerifyEdwosbFlow {
	private static final Logger logger = LogManager.getLogger(ScorpQuestionsPage.class.getName());
	WebDriver webDriver;

	public void VerifyEDWOSBFlowSetDriver(WebDriver mydriver) {
		this.webDriver = mydriver;
	}

	public void VerifyEDWOSBFlowLogic() throws Exception {
		String Actual_Text = null;
		String Expected_Text = null;

		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
		String myCertText = webDriver
				.findElement(By.xpath("//article[@id='main-content']//h1[contains(text(),'My certifications')]"))
				.getText();
		assertEquals(myCertText, "My certifications");

		Actual_Text = webDriver
				.findElement(By
						.xpath("//article[@id='main-content']//section/article/h1[contains(text(),'Start a new certification')]"))
				.getText();
		// AddOrStartNewEdwosbCertPage;
		Expected_Text = "Start a new certification";
		assertEquals(Actual_Text, Expected_Text);
		// Acceptance criteria 7 -point 2
		// Verify the Text under the start a New certification section.

		Expected_Text = "You may only have one active certification for each program at a time. If you need to make edits or changes to a submitted certification, please contact the help desk at certify@sba.gov to release your certification back to you.";
		// String xpathNeeded =
		// "//article[@id='main-content']//section/article/p[contains(text(),'"
		// + Expected_Text + "')]";
		Actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
		assertEquals(Actual_Text, Expected_Text);
		try {
			webDriver.findElement(By.xpath("//a[@href='/users/sign_out']")).click();
			LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 19);
			login_Data.Login_With_Reference();
			webDriver.findElement(By.xpath("//*[@id='query']"))
					.sendKeys(LoginHelpers.getLoginDataWithIndex(19).getDunsNumber());

			webDriver.findElement(By.className("usa-search-submit-text")).click();
			webDriver.findElement(By.xpath("//a[contains(text(),'Legal Business Name')]")).click();
			WebElement current_Row_Draft1 = webDriver.findElement(By.xpath(
					"//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'EDWOSB Self-Certification')]"));
			WebElement current_Row1 = current_Row_Draft1.findElement(By.xpath("..")).findElement(By.xpath(".."));
			logger.info(current_Row1.getText());

			List<WebElement> all_Cells1 = current_Row1.findElements(By.xpath("td"));
			logger.info(all_Cells1.size());
			all_Cells1.get(3).findElement(By.xpath("//a[contains(text(),'Return to Vendor')]")).click();
			logger.info(webDriver.switchTo().alert().getText());
			webDriver.switchTo().alert().accept();
			webDriver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

			login_Data = new LoginPageWithReference(webDriver, 8);
			login_Data.Login_With_Reference();

		} catch (Exception e) {
			logger.info("There are(is) no certification Active on the dashboard, a new certification is being created");
		}

		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();

		try {
			WebElement ElementEDWOSBRadio = webDriver
					.findElement(By.xpath("//a[@class='delete-cert'][@data-method='delete'][text()='Delete']"));
			ElementEDWOSBRadio.click();
			// webDriver.findElement(By.xpath("//a[@class='delete-cert'
			// ][@data-method='delete'][text()='Delete']")).click();
		} catch (Exception e) {
			logger.info(
					"There are(is) no certification in-progress on the dashboard, a new certification is being created");
		}

		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();

		// Verify before selecting any certification the Add New certification
		// button disabled
		WebElement radio_Element = webDriver
				.findElement(By.xpath("//div[@id='certificate_choice']/input[@id='certificate_type_edwosb']"));
		WebElement add_button = webDriver.findElement(By.id("add_certification"));
		logger.info(add_button.getAttribute("disabled"));
		assertEquals(add_button.getAttribute("disabled"), "true");
		// assertTrue(Boolean.toString(add_button.isEnabled()), false);
		radio_Element.click();
		assertTrue(Boolean.toString(add_button.isEnabled()), true);
		add_button.click();
		webDriver.findElement(By.className("accept_button")).click();
		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
		WebElement current_Row_Draft = webDriver.findElement(
				By.xpath("//article[@id='main-content']//section/article/table/tbody/tr/td[text()='Draft']"));
		assertEquals(current_Row_Draft.getText(), "Draft");
		logger.info(current_Row_Draft.getText());
		WebElement current_Row = current_Row_Draft.findElement(By.xpath(".."));
		logger.info(current_Row.getText());

		List<WebElement> all_Cells = current_Row.findElements(By.xpath("td"));
		assertEquals(all_Cells.get(0).getText(), "EDWOSB Self-Certification");
		assertEquals(all_Cells.get(1).getText(), "");
		assertEquals(all_Cells.get(2).getText(), "Draft");
		assertEquals(all_Cells.get(3).getText(), "Delete");
		// WebElement LinkForCert =
		// all_Cells.get(0).findElement(By.xpath("//a[text()='EDWOSB
		// Self-Certification']"));
		// WebElement LinkForDelete =
		// all_Cells.get(3).findElement(By.xpath("//a[@class='delete-cert'][@data-method='delete'][text()='Delete']"));

		// logger.info(current_Row.GetParent());
		Boolean FlagForAddEDWOSBNotPresent = false;
		try {
			radio_Element = webDriver
					.findElement(By.xpath("//a[@class='delete-cert'][@data-method='delete'][text()='Delete']"));
			radio_Element.click();
			FlagForAddEDWOSBNotPresent = true;
			// webDriver.findElement(By.xpath("//a[@class='delete-cert'
			// ][@data-method='delete'][text()='Delete']")).click();
		} catch (Exception e) {
			logger.info("There are(is) no Radio button for EDWOSB");
			FlagForAddEDWOSBNotPresent = true;

		}
		assertTrue(FlagForAddEDWOSBNotPresent);
		// LinkForDelete.click();

		logger.info("Certifications Deleted To start again");
		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
		webDriver.findElement(By.xpath("//div[@id='certificate_choice']/input[@id='certificate_type_edwosb']")).click();
		webDriver.findElement(By.id("add_certification")).click();
		logger.info("Going into Partnerships page");

		// Corp test for 1st person.
		ScorpQuestionsPage scorpQuestions = new ScorpQuestionsPage(webDriver);
		scorpQuestions.ScorpQuestions();
		// Financial section.
		FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
		financialsection.Financialsection();

		// Check the section that its active and no delete revoke is there
		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
		current_Row_Draft = webDriver.findElement(
				By.xpath("//article[@id='main-content']//section/article/table/tbody/tr/td[text()='Active']"));
		assertEquals(current_Row_Draft.getText(), "Active");
		logger.info(current_Row_Draft.getText());
		current_Row = current_Row_Draft.findElement(By.xpath(".."));
		logger.info(current_Row.getText());

		all_Cells = current_Row.findElements(By.xpath("td"));
		assertEquals(all_Cells.get(0).getText(), "EDWOSB Self-Certification");
		// assertEquals(all_Cells.get(1).getText(), "");
		assertEquals(all_Cells.get(2).getText(), "Active");
		assertEquals(all_Cells.get(3).getText(), "");
		all_Cells.get(0).findElement(By.xpath("//a")).click();

		assertTrue(webDriver.getPageSource()
				.contains("Economically Disadvantaged Women-Owned Small Business Program Self-Certification Summary"));
		assertTrue(webDriver.getPageSource().contains(
				"   By submitting this certification I, QA User, am an officer or owner of Entity 454 Legal Business Name authorized to represent it and electronically sign this certification on its behalf."));

	}
}

