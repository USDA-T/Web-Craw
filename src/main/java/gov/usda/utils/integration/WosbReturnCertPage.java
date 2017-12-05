package gov.usda.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import gov.sba.automation.CoreUtils;
import junit.framework.TestCase;

public class WosbReturnCertPage extends TestCase {
	private static final Logger logger = LogManager.getLogger(WosbReturnCertPage.class.getName());
	WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	public WosbReturnCertPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void WosbReturnCert() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		String Actual_Text;
		String Expected_Text;
		logger.debug("Returning submited certification back to vendor");
		// Click on the application link name.
		webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		Expected_Text = "Case Overview";
		assertEquals(Actual_Text, Expected_Text);
		// Click on the start button to start a review.
		jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.id("submit_button")));
		webDriver.findElement(By.id("submit_button")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		Expected_Text = "Question review";
		assertEquals(Actual_Text, Expected_Text);
		// Add a note.
		webDriver.findElement(By.xpath("//div[2]/div/div[2]/a")).click();
		webDriver.findElement(By.xpath("//textarea")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[2]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[2]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		jse.executeScript("arguments[0].scrollIntoView()",
				webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[3]")));
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[3]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[3]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[4]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[4]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		jse.executeScript("arguments[0].scrollIntoView()",
				webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[5]")));
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[5]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[5]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[6]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[6]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		jse.executeScript("arguments[0].scrollIntoView()",
				webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[7]")));
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[7]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[7]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[8]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[8]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		jse.executeScript("arguments[0].scrollIntoView()",
				webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[9]")));
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[9]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[9]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[10]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[10]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		jse.executeScript("arguments[0].scrollIntoView()",
				webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[11]")));
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[11]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[11]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[12]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[12]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		jse.executeScript("arguments[0].scrollIntoView()",
				webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[13]")));
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[13]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[13]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[14]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[14]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		jse.executeScript("arguments[0].scrollIntoView()",
				webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[15]")));
		webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[15]")).click();
		webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[15]")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		// Click on the save and continue button.
		jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.id("save_notes")));
		webDriver.findElement(By.id("save_notes")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		Expected_Text = "Signature review";
		assertEquals(Actual_Text, Expected_Text);
		// Add a note.
		webDriver.findElement(By.xpath("//select")).click();
		webDriver.findElement(By.xpath("//option[4]")).click();
		webDriver.findElement(By.xpath("//div/div[2]/div[2]/a")).click();
		webDriver.findElement(By.xpath("//textarea")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		// Click on the save and continue button.
		CoreUtils.clickContinue(webDriver);
		Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		Expected_Text = "Determination";
		assertEquals(Actual_Text, Expected_Text);
		// Select Return for Modification
		webDriver.findElement(By.xpath("//li[2]/label")).click();
		webDriver.findElement(By.xpath("//textarea")).sendKeys(
				"so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
		// Click on the save and continue button.
		CoreUtils.clickContinue(webDriver);
		Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		Expected_Text = "You can view the vendor's record but can not make edits";
		assertEquals(Actual_Text, Expected_Text);
		// Click on Vendor Overview link.
		webDriver.findElement(By.xpath("//a[contains(text(),'Vendor Overview')]")).click();
		if (webDriver.getCurrentUrl().contains("certify.qa")) {
			webDriver.findElement(By.id("profileid")).click();
			webDriver.findElement(By.linkText("Logout")).click();
		} else {
			if (webDriver.getCurrentUrl().contains("staging")) {
				webDriver.findElement(By.id("profileid")).click();
				webDriver.findElement(By.linkText("Logout")).click();
			} else {
				if (webDriver.getCurrentUrl().contains("newqa")) {
					webDriver.findElement(By.linkText("Logout")).click();
				} else {
					webDriver.findElement(By.id("profileid")).click();
					webDriver.findElement(By.id("profileid")).click();
					webDriver.findElement(By.linkText("Logout")).click();
				}
			}
		}
		logger.info("Success");
	}
}
