package gov.sba.utils;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import gov.sba.utils.VerifyEdwosbFlow;
import gov.sba.utils.helpers.LoginHelpers;
import junit.framework.TestCase;

public class VerifyEdwosbFlow extends TestCase {
	private static final Logger logger = LogManager.getLogger(ScorpQuestionsPage.class.getName());
	WebDriver webDriver;

	public void VerifyEDWOSBFlowSetDriver(WebDriver mydriver) {
		this.webDriver = mydriver;
	}

	public void VerifyEDWOSBFlowLogic() throws Exception {
		
		// logger.info(current_Row.GetParent());
		Boolean FlagForAddEDWOSBNotPresent = false;
		try {
			WebElement radio_Element = webDriver.findElement(By.linkText("Delete"));
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
		
		try {
			@SuppressWarnings("unused")
			WebElement current_Row_Active = webDriver.findElement(By.xpath("//article[@id='main-content']//section/article/table/tbody/tr/td[text()='Active']"));						
			webDriver.findElement(By.xpath("//a[@href='/users/sign_out']")).click();
			LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 21);
			login_Data.Login_With_Reference();
			webDriver.findElement(By.xpath("//*[@id='query']")).sendKeys(LoginHelpers.getLoginDataWithIndex(20).getDunsNumber());
			webDriver.findElement(By.className("usa-search-submit-text")).click();
			webDriver.findElement(By.xpath("//a[contains(text(),'Legal Business Name')]")).click();
			WebElement current_Row_Draft1 = webDriver.findElement(By.xpath("//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'EDWOSB Self-Certification')]"));
			WebElement current_Row1 = current_Row_Draft1.findElement(By.xpath("..")).findElement(By.xpath(".."));
			logger.info(current_Row1.getText());
			List<WebElement> all_Cells1 = current_Row1.findElements(By.xpath("td"));
			logger.info(all_Cells1.size());
			all_Cells1.get(3).findElement(By.xpath("//a[contains(text(),'Return to Vendor')]")).click();
			logger.info(webDriver.switchTo().alert().getText());
			webDriver.switchTo().alert().accept();
			webDriver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

			login_Data = new LoginPageWithReference(webDriver, 9);
			login_Data.Login_With_Reference();

		} catch (Exception e) {
			logger.info("There are(is) no certification Active on the dashboard, a new certification is being created");
		}

		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();

		try {
			WebElement ElementEDWOSBRadio = webDriver.findElement(By.linkText("Delete"));
			ElementEDWOSBRadio.click();
		} catch (Exception e) {
			logger.info(
					"There are(is) no certification in-progress on the dashboard, a new certification is being created");
		}

		
		//        First Flow - Check For Drafts coming up correctly
		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
    	logger.info("Certifications Deleted To start again");

		String myCertText = webDriver.findElement(By.xpath("//article[@id='main-content']//h1[contains(text(),'My certifications')]")).getText();
		assertEquals(myCertText, "My certifications");
		
	   //condition to check no certificate in my certification table
		//Expected_Text = "There are currently no certifications";
		//Actual_Text   = webDriver.findElement(By.xpath("//article[@id='main-content']//span[contains(text(),'There are currently no certifications')]")).getText();
		//assertEquals(Actual_Text, Expected_Text);
		//Start New certification-Verify before selecting any certification the Add New certification button disabled

		WebElement radio_Element = webDriver.findElement(By.xpath("//div[@id='certificate_choice']/input[@id='certificate_type_edwosb']"));
		WebElement add_button = webDriver.findElement(By.id("add_certification"));
		logger.info(add_button.getAttribute("disabled"));
		assertEquals(add_button.getAttribute("disabled"), "true");
		radio_Element.click();
		try {webDriver.findElement(By.xpath("//div[@id='certificate_choice']/input[@id='certificate_type_edwosb']")).click(); 
		webDriver.findElement(By.xpath("//div[@id='certificate_choice']/input[@id='certificate_type_edwosb']")).click();} catch (Exception e){};
		
		assertTrue(Boolean.toString(add_button.isEnabled()), true);
		Thread.sleep(2000);
		webDriver.findElement(By.id("add_certification")).click();
		try {webDriver.findElement(By.id("add_certification")).click();
		webDriver.findElement(By.id("add_certification")).click();
		} catch (Exception e){}
		
		Thread.sleep(5000);
		webDriver.findElement(By.className("accept_button")).click();
		
		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
		WebElement current_Row_Draft = webDriver.findElement(By.xpath("//article[@id='main-content']//section/article/table/tbody/tr/td[text()='Draft']"));
		assertEquals(current_Row_Draft.getText(), "Draft");
		logger.info(current_Row_Draft.getText());
		WebElement current_Row = current_Row_Draft.findElement(By.xpath(".."));
		logger.info(current_Row.getText());
		List<WebElement> all_Cells = current_Row.findElements(By.xpath("td"));
		assertEquals(all_Cells.get(0).getText(), "EDWOSB Self-Certification");
		assertEquals(all_Cells.get(1).getText(), "");
		assertEquals(all_Cells.get(2).getText(), "Draft");
		assertEquals(all_Cells.get(3).getText(), "Delete");
		all_Cells.get(3).findElement(By.xpath("//a[@class='delete-cert'][@data-method='delete'][text()='Delete']")).click();
		Thread.sleep(3000);
		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
		
		//        First Flow - Check For Active 		
		webDriver.findElement(By.xpath("//div[@id='certificate_choice']/input[@id='certificate_type_edwosb']")).click();
		webDriver.findElement(By.id("add_certification")).click();
		try {webDriver.findElement(By.id("add_certification")).click();
		webDriver.findElement(By.id("add_certification")).click();
		} catch (Exception e){}
		
		logger.info("Going into Partnerships page");
		//webDriver.findElement(By.className("accept_button")).click();
		//try{webDriver.findElement(By.className("accept_button")).click();
		//webDriver.findElement(By.className("accept_button")).click();}catch (Exception e){}
		

		// Corp test for 1st person.
		ScorpQuestionsPage scorpQuestions = new ScorpQuestionsPage(webDriver);
		scorpQuestions.ScorpQuestions();
		// Financial section.
		FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
		financialsection.Financialsection();

		// Check the section that its active and no delete in action is there
		webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
		current_Row_Draft = webDriver.findElement(By.xpath("//article[@id='main-content']//section/article/table/tbody/tr/td[text()='Active']"));
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

		assertTrue(webDriver.getPageSource().contains("Economically Disadvantaged Women-Owned Small Business Program Self-Certification Summary"));
		assertTrue(webDriver.getPageSource().contains("   By submitting this certification I, QA User, am an officer or owner of Entity 454 Legal Business Name authorized to represent it and electronically sign this certification on its behalf."));

	}
}