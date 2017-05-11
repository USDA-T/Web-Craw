//Created By Deepa patri
package gov.sba.automation;

import static gov.sba.automation.ConfigUtils.isUnix;
import static gov.sba.automation.ConfigUtils.systemType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.google.common.base.Function;

import gov.sba.utils.integration.LoginPageWithReference;

public class CommonApplicationMethods {

	public static void display(String smeThng) throws Exception {
		LogManager.getLogger(gov.sba.automation.CommonApplicationMethods.class.getName()).info(smeThng);
	}

	public static Map getLocator(String locatorName) throws YamlException, FileNotFoundException {
		YamlReader reader = new YamlReader(new FileReader(FixtureUtils.fixturesDir() + "Locators.yaml"));
		Object object = reader.read(); // System.out.println(object);
		Map map = (Map) object; // System.out.println(map.get(locatorName));
		return (Map) map.get(locatorName);
	};

	public static void take_ScreenShot_TestCaseName(WebDriver webDriver, String[] stringValueArray) throws Exception {
		File src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		String time = Integer.toString((int) (new Date().getTime() / 1000));
		display(time);

		try {
			// now copy the screenshot to the screenshot folder.
			if (stringValueArray.length == 2) {
				FileUtils.copyFile(src, new File(
						FixtureUtils.get_SS_Dir() + stringValueArray[0] + stringValueArray[1] + time + ".png"));
			} else {
				FileUtils.copyFile(src,
						new File(FixtureUtils.get_SS_Dir() + stringValueArray[0] + "Exception" + ".png"));
			}
		}

		catch (IOException e) {
			throw e;
		}

	};

	public static void take_Desktop_SShot_TestCaseName(String[] stringValueArray) throws Exception {
		Robot robot = new Robot();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
		Calendar now = Calendar.getInstance();
		BufferedImage screenShot = robot
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		if (stringValueArray.length == 2) {
			ImageIO.write(screenShot, "JPG",
					new File(FixtureUtils.get_SS_Dir() + stringValueArray[0] + stringValueArray[1] + ".jpg"));
		} else {
			ImageIO.write(screenShot, "JPG",
					new File(FixtureUtils.get_SS_Dir() + stringValueArray[0] + "_Exception" + ".jpg"));
		}

	};

	public static Boolean checkApplicationExists(WebDriver webDriver, String type_Of_App, String status_Of_App)
			throws Exception {
		// It should be in Vendor Dashboard
		switch (type_Of_App.toLowerCase((Locale.ENGLISH)) + status_Of_App.toLowerCase((Locale.ENGLISH))) {
		case "edwosbactive":
			List<WebElement> listOfActiveEDWOSB = webDriver.findElements(By.xpath("//table[@id='certifications']/tbody/"
					+ "tr[  " + "		(td[position()=5 and contains(text(),'ctive')]) "
					+ "and  (td[position()=1]/a[contains(text(),'EDWOSB')]) " + "	]"));
			return listOfActiveEDWOSB.size() > 0;
		case "wosbactive":
			List<WebElement> listOfActiveWOSB = webDriver.findElements(By.xpath(
					"//table[@id='certifications']/tbody/tr[  " + "(td[position()=5 and contains(text(),'ctive')]) and "
							+ "(td[position()=1]/a[contains(text(),'WOSB') and not(contains(text(),'EDWOSB'))]) ]"));
			return listOfActiveWOSB.size() > 0;
		case "mpppending":
			List<WebElement> listOfActiveMpp = webDriver.findElements(By.xpath(
					"//table[@id='certifications']/tbody/tr[  (td[position()=5 and contains(text(),'ending')]) and (td/a[position()=1 and contains(text(),'MPP')]) ]"));
			return listOfActiveMpp.size() > 0;

		default:
			return false;
		}
	}

	public static List<WebElement> find_Elements_Loc_InPrg(WebDriver webdriver, String type_Locator,
			String value_Locator) throws Exception {
		List<WebElement> element_01 = null;
		for (int i = 0; i < 10; i++) {
			try {
				switch (type_Locator.toLowerCase()) {
				case "xpath":
					element_01 = webdriver.findElements(By.xpath(value_Locator));
				case "id":
					element_01 = webdriver.findElements(By.id(value_Locator));
				case "classname":
					element_01 = webdriver.findElements(By.className(value_Locator));
				case "name":
					element_01 = webdriver.findElements(By.name(value_Locator));
				case "cssselector":
					element_01 = webdriver.findElements(By.cssSelector(value_Locator));
				case "linktext":
					element_01 = webdriver.findElements(By.linkText(value_Locator));
				}

				if (element_01.size() > 0) {
					break;
				}

			} catch (Exception e) {
				display("Trying to find BY " + type_Locator + ":" + value_Locator);
				Thread.sleep(100); // DEEPA: is needed here since we are
									// Repeatedly Finding
			}
		}
		return element_01;
	};

	public static List<WebElement> find_Elements(WebDriver webdriver, String locator_Yaml) throws Exception {
		Map locator = getLocator(locator_Yaml);
		return find_Elements_Loc_InPrg(webdriver, locator.get("Locator").toString(), locator.get("Value").toString());
	}

	public static WebElement find_Element_Loc(WebDriver webdriver, String type_Locator, String value_Locator)
			throws Exception {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(webdriver).withTimeout(7, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		try {
			switch (type_Locator.toLowerCase()) {
			case "xpath":
				WebElement element_01 = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriver) {
						return webDriver.findElement(By.xpath(value_Locator));
					}
				});
				display("Hey this came in Xpa");
				return element_01;
			case "id":
				element_01 = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriver) {
						return webDriver.findElement(By.id(value_Locator));
					}
				});
				display("Hey this came in ID");
				return element_01;
			case "classname":
				element_01 = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriver) {
						return webdriver.findElement(By.className(value_Locator));
					}
				});
				display("Hey this came in CName");
				return element_01;
			case "name":
				element_01 = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriver) {
						return webdriver.findElement(By.name(value_Locator));
					}
				});
				display("Hey this came in Name");
				return element_01;
			case "cssselector":
				element_01 = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriver) {
						return webdriver.findElement(By.cssSelector(value_Locator));
					}
				});
				display("Hey this came in Css");
				return element_01;
			case "linktext":
				element_01 = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriver) {
						return webdriver.findElement(By.linkText(value_Locator));
					}
				});
				display("Hey this came in Lt");
				return element_01;
			}
		} catch (Exception e) {
			display("Trying to find BY " + type_Locator + ":" + value_Locator);
			throw new Exception("Tried to find BY " + type_Locator + ":" + value_Locator);
		}
		return null;
	};

	public static WebElement find_Element(WebDriver webdriver, String locator_Yaml) throws Exception {
		Map locator = getLocator(locator_Yaml);
		return find_Element_Loc(webdriver, locator.get("Locator").toString(), locator.get("Value").toString());
	}

	public static void click_Element_Loc(WebDriver webdriver, String type_Locator, String value_Locator)
			throws Exception {
		find_Element_Loc(webdriver, type_Locator, value_Locator).click();
	}

	public static void accept_Alert(WebDriver webDriver) throws Exception {
		for (int i = 0; i < 15; i++) {
			try {
				webDriver.switchTo().alert().accept();
				return;
			} catch (Exception e) {
				if (i == 14) {
					throw new Exception("Alert Not found");
				} else {
					display("Trying to Accept Alert");
					Thread.sleep(300);
				}
			}
		}
	}

	public static void accept_Optional_Alert(WebDriver webDriver, int counter) throws Exception {
		for (int i = 0; i < counter; i++) {
			try {
				webDriver.switchTo().alert().accept();
				return;
			} catch (Exception e) {
				display("Trying to Accept Alert");
				Thread.sleep(300);
			}
		}
	}

	public static void click_Element(WebDriver webdriver, String locator_Yaml) throws Exception {
		Map locator = getLocator(locator_Yaml);
		find_Element_Loc(webdriver, locator.get("Locator").toString(), locator.get("Value").toString()).click();
	}

	public static void setText_Element(WebDriver webdriver, String locator_Yaml, String textVal) throws Exception {
		Map locator = getLocator(locator_Yaml);
		WebElement click_element = find_Element_Loc(webdriver, locator.get("Locator").toString(),
				locator.get("Value").toString());
		click_element.click();
		click_element.clear();
		click_element.sendKeys(textVal);
	}

	public static void verify_Element_Attribute(WebDriver webdriver, String locator_Yaml, String property_Yaml)
			throws Exception {
		Map locator = getLocator(locator_Yaml);

		WebElement click_element = find_Element_Loc(webdriver, locator.get("Locator").toString(),
				locator.get("Value").toString());

		Map prop = getLocator(property_Yaml);
		String prop_Name = prop.get("PropName").toString();
		String prop_Value = prop.get("PropValue").toString();

		Assert.assertEquals(click_element.getAttribute(prop_Name), prop_Value);

	}

	public static void verify_Element_Property(WebDriver webdriver, String locator_Yaml, String property_Yaml)
			throws Exception {
		Map locator = getLocator(locator_Yaml);

		WebElement click_element = find_Element_Loc(webdriver, locator.get("Locator").toString(),
				locator.get("Value").toString());

		Map prop = getLocator(property_Yaml);
		String prop_Name = prop.get("PropName").toString();
		String prop_Value = prop.get("PropValue").toString();

		switch (prop_Name.toLowerCase()) {
		case "enabled":
			assertTrue(click_element.isEnabled());
			break;
		case "disabled":
			assertFalse(click_element.isEnabled());
			break;
		case "visible":
			assertTrue(click_element.isDisplayed());
			break;
		case "notvisible":
		case "invisible":
		case "hidden":
			assertFalse(click_element.isDisplayed());
			break;
		default:
			assertEquals("Assertion Type is", "Incorrect");
			break;
		}

	}

	public static void sendKeys_Element(WebDriver webdriver, String locator_Yaml, String textVal) throws Exception {
		Map locator = getLocator(locator_Yaml);
		WebElement click_element = find_Element_Loc(webdriver, locator.get("Locator").toString(),
				locator.get("Value").toString());
		click_element.sendKeys(textVal);
	}

	public static void focus_window() throws AWTException, InterruptedException {
		final Robot robot = new Robot();
		robot.mouseMove(300, 300);
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		Thread.sleep(700);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		Thread.sleep(700);
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(700);
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(700);
	}

	public static void return_all_Applications(WebDriver webDriver, int login_Id, String duns_Number) throws Exception {
		Logger commonApplicationMethodsLogs = LogManager
				.getLogger(gov.sba.automation.CommonApplicationMethods.class.getName());

		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, login_Id);
		login_Data.Login_With_Reference();

		searchDuns_Number(webDriver, duns_Number);
		webDriver.findElement(By.xpath("//div[@id='business_search']/div[2]/div[1]/div[1]/h4/a")).click();
		String paS = webDriver.getPageSource().toLowerCase();
		WebElement current_Row_Check_01;

		try {
			current_Row_Check_01 = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody"));
			List<WebElement> current_Row_Check = current_Row_Check_01
					.findElements(By.xpath("tr/td/a[contains(text(),'Return to Vendor')]"));
			if (current_Row_Check.size() > 0) {
				for (int i = 0; i < current_Row_Check.size(); i++) {
					current_Row_Check.get(0).click();
					accept_Optional_Alert(webDriver, 30);
				}
			}

		} catch (Exception ex) {
			commonApplicationMethodsLogs.info(ex);
		}

		try {
			if ((paS.contains("return to vendor") || paS.contains("active"))
					&& (paS.contains("wosb") || paS.contains("mpp"))) {
				current_Row_Check_01 = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody"));
				List<WebElement> current_Row_Check_02 = current_Row_Check_01.findElements(
						By.xpath("tr[" + "td[position()=1]/a[contains(text(),'WOSB') or contains(text(),'MPP')] and "
								+ "td[(position()=4) and" + "               ( "
								+ "                   (contains(text(),'ctive')) or "
								+ "                   (contains(text(),'ending')) " + "               )" + "   ]"
								+ "]/td[position()=1]/a"));
				if (current_Row_Check_02.size() > 0) {
					for (int i = 0; i < current_Row_Check_02.size(); i++) {
						current_Row_Check_02.get(0).click();
						click_Element(webDriver, "Analyst_Review_Determ_Page_Link");
						click_Element(webDriver, "Analyst_Review_Determ_Page_Return_Mod_Link");
						click_Element(webDriver, "Application_Common_Submit_Button");
						Thread.sleep(1500); // Deepa - Sleep needed here to
											// navigate
						webDriver.navigate().back();
						webDriver.navigate().back();
						webDriver.navigate().back();
						webDriver.navigate().refresh();
						try {
							current_Row_Check_01 = webDriver
									.findElement(By.xpath("//table[@id='certifications']/tbody"));
						} catch (Exception ex) {
							return;
						}

						current_Row_Check_02 = current_Row_Check_01.findElements(By.xpath("tr["
								+ "td[position()=1]/a[contains(text(),'WOSB') or contains(text(),'MPP')] and "
								+ "td[(position()=4) and (contains(text(),'ctive'))]" + "]/td[position()=1]/a"));

						i = 0;
					}
				}
			}
		} catch (Exception ex1) {
			commonApplicationMethodsLogs.info(ex1);
		}

		CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
	}

	public static void delete_all_Drafts(WebDriver webDriver) throws Exception {

		Boolean FlagForAddEDWOSBNotPresent = true;
		navigationMenuClick(webDriver, "DashBoard");
		List<WebElement> current_Row_Check_02 = webDriver
				.findElements(By.xpath("//table[@id='certifications']//td/a[contains(text(),'elete')] "));
		if (current_Row_Check_02.size() > 0) {

			for (int i = 0; i < current_Row_Check_02.size(); i++) {
				FlagForAddEDWOSBNotPresent = false;
				current_Row_Check_02.get(0).click();
				accept_Optional_Alert(webDriver, 8);
				Thread.sleep(1500); // Sleep Needed Deepa
				webDriver.navigate().refresh();
				current_Row_Check_02 = webDriver
						.findElements(By.xpath("//table[@id='certifications']//td/a[contains(text(),'elete')] "));
				i = 0;
				FlagForAddEDWOSBNotPresent = true;
			}
		}
		assertTrue(FlagForAddEDWOSBNotPresent);

	}

	public static void clear_Env_Chrome() throws InterruptedException, IOException {
		if (System.getProperty("os.name").startsWith("Windows")) {
			Runtime rt = Runtime.getRuntime();
			rt.exec("Taskkill /IM chrome.exe /F");
			rt.exec("Taskkill /IM firefox.exe /F");
			Thread.sleep(1000); // Deepa Sleep needed here.
		}
		if (isUnix(systemType())) {
			Runtime rt = Runtime.getRuntime();
			rt.exec("ps aux | grep chrome | awk ' { print $2 } ' | xargs kill -9");
			rt.exec("ps aux | grep firefox | awk ' { print $2 } ' | xargs kill -9");
			Thread.sleep(1000); // Deepa Sleep needed here
		}
	}

	public static void deleteApplication(WebDriver webDriver, String type_Of_App, String status_Of_App)
			throws Exception {

		switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
		case "edwosbdraft":
			List<WebElement> deleteElem = webDriver.findElements(By.xpath(
					"//*[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'EDWOSB')]) and ( td[ position()=5 and contains(text(),'Draft') ] )  ]/td[ position()=7 ]/a[ contains(text(),'Delete') ]  "));
			if (deleteElem.size() > 0) {
				deleteElem.get(0).click();
				accept_Optional_Alert(webDriver, 8);
			}
			break;
		case "wosbdraft":
			List<WebElement> deleteElem_01 = webDriver.findElements(By.xpath(
					"//*[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'WOSB') and not(contains(text(),'EDWOSB'))]) and ( td[ position()=5 and contains(text(),'Draft') ] )  ]/td[ position()=7 ]/a[ contains(text(),'Delete') ]  "));
			if (deleteElem_01.size() > 0) {
				deleteElem_01.get(0).click();
				webDriver.switchTo().alert().accept();
			}
			break;
		case "mppdraft":
			List<WebElement> deleteElem_02 = webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr"
					+ "[  " + " ( td[position()=1]/a[contains(text(),'MPP')]       )  and"
					+ " ( td[ position()=5 and contains(text(),'Draft')  ] )  and "
					+ " ( td[ position()=7 ]/a[ contains(text(),'Delete') ] )  " + "]" + "/td[position()=7]/a"));
			if (deleteElem_02.size() > 0) {
				deleteElem_02.get(0).click();
				webDriver.switchTo().alert().accept();
			}
			break;
		}

	}

	public static void clickOnApplicationAllCasesPage(WebDriver webDriver, String type_Of_App) throws Exception {
		// It should be in Vendor Dashboard
		switch (type_Of_App.toLowerCase()) {
		case "wosb":
			webDriver.findElement(By.xpath("//*[@id='certifications']/tbody/tr" + "["
					+ "td[position()=1]/a[contains(text(),'WOSB')]" + "]" + "/td[position()=1]/a")).click();
		case "edwosb":
			webDriver.findElement(By.xpath("//*[@id='certifications']/tbody/tr" + "["
					+ "td[position()=1]/a[contains(text(),'EDWOSB')]" + "]" + "/td[position()=1]/a")).click();
		case "mpp":
			webDriver.findElement(By.xpath("//*[@id='certifications']/tbody/tr" + "["
					+ "td[position()=1]/a[contains(text(),'MPP')]" + "]" + "/td[position()=1]/a")).click();
		}
	}

	public static String returnOrganization_Id(String duns_Number) throws Exception {
		String organization_Id;
		try {
			// See below Start: Need Sleep
			Thread.sleep(3000); // DEEPA: Sleep is needed here since we are
								// querying SQL, and its too fast
			// See below Start

			organization_Id = DatabaseUtils.queryForData(
					"select id from sbaone.organizations where duns_number = '" + duns_Number + "'", 1, 1)[0][0];
		} catch (Exception e) {
			display(e.toString() + ": The Duns number retreival has failed");
			throw e;
		}
		return organization_Id;
	};

	public static void returnApplicationToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp,
			String duns_Number, String type_Of_App, String status_Of_App, int which_Log_BackAgain) throws Exception {

		// Login provided should be Analyst
		CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
		login_Data.Login_With_Reference();
		searchDuns_Number(webDriver, duns_Number);
		webDriver
				.findElement(By
						.xpath("//*[@id='business_search']/div[h2[contains(text(),'Search Results')]]/div[1]/div/h4/a"))
				.click();
		switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
		case "edwosbactive":
			webDriver
					.findElement(By
							.xpath("//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'EDWOSB') ]) and ( td[ position()=5 and contains(text(),'Active') ] ) ]/td[position()=7]/a[contains(text(),'Return to Vendor')]"))
					.click();
			webDriver.switchTo().alert().accept();
			break;
		case "wosbactive":
			webDriver
					.findElement(By
							.xpath("//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'WOSB') and not(contains(text(),'EDWOSB')) ]) and ( td[ position()=5 and contains(text(),'Active') ] ) ]/td[position()=7]/a[contains(text(),'Return to Vendor')]"))
					.click();
			webDriver.switchTo().alert().accept();
			break;
		case "mppactive":
			webDriver
					.findElement(By
							.xpath("//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'MPP') ]) and ( td[ position()=5 and contains(text(),'Active') ] ) ]/td[position()=7]/a[contains(text(),'Return to Vendor')]"))
					.click();
			webDriver.switchTo().alert().accept();
			break;
		}
		CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
		login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
		login_Data.Login_With_Reference();

	}

	public static void returnAll_App_To_Vendor(WebDriver webDriver, String duns_Number) throws Exception {

		Boolean FlagForReturn = true;
		List<WebElement> current_Row_Check_02 = find_Elements(webDriver, "Vendor_Overview_Page_Rt_Vend_All");
		if (current_Row_Check_02.size() > 0) {
			for (int i = 0; i < current_Row_Check_02.size(); i++) {
				FlagForReturn = false;
				current_Row_Check_02.get(0).click();
				webDriver.navigate().refresh();
				current_Row_Check_02 = find_Elements(webDriver, "Vendor_Overview_Page_Rt_Vend_All");
				i = 0;
				FlagForReturn = true;
			}
		}
		assertTrue(FlagForReturn);
	}

	public static void onlyReturnAppToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp, String duns_Number,
			String type_Of_App, String status_Of_App, int which_Log_BackAgain) throws Exception {
		// Login provided should be Analyst
		CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
		login_Data.Login_With_Reference();

		CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
		WebElement current_Row_e = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody"));

		List<WebElement> current_Row = current_Row_e
				.findElements(By.xpath("tr[ " + "td[position()=8 and contains(text(),'Under Review')]   and "
						+ "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
						+ "td[position()=3 and contains(text(),'" + type_Of_App + "')]	" + "]"));

		if (current_Row.size() > 0) {
			WebElement a1 = current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
			a1.click();
			webDriver.findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]"))
					.click();
			webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
			webDriver.findElement(By.xpath("//input[contains(@value,'Save and commit')]")).click();
			webDriver.navigate().back();
		} else {
			current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
					+ "td[position()=8 and contains(text(),'Submitted')]   and "
					+ "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
					+ "td[position()=3 and contains(text()," + type_Of_App + ")]	" + "]"));
			if (current_Row.size() > 0) {
				current_Row.get(0).findElement(By.xpath("a[position()=2]")).click();
				current_Row = webDriver.findElements(
						By.xpath("//*[@id='certifications']/tbody/tr[  (td[position()=4 and contains(text(),'tive')]) "
								+ "and (td/a[position()=1 and contains(text(),'" + type_Of_App + "')]) ]"));
				current_Row.get(0).click();
			}
		}

		webDriver.navigate().back();
		webDriver.navigate().back();
		CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
		login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
		login_Data.Login_With_Reference();

	}

	public static void returnAppToVendorMethd(WebDriver webDriver, int which_Loginto_ReturnApp, String duns_Number,
			String type_Of_App, String status_Of_App, int which_Log_BackAgain) throws Exception {
		// Login provided should be Analyst
		CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
		login_Data.Login_With_Reference();

		switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
		case "edwosbactive":
			CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
			WebElement current_Row_e = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody"));

			List<WebElement> current_Row = current_Row_e
					.findElements(By.xpath("tr[ " + "td[position()=8 and contains(text(),'Under Review')]   and "
							+ "td[position()=2 and contains(text(),'" + duns_Number + "')]	and "
							+ "td[position()=3 and contains(text(),'" + type_Of_App + "')]	" + "]"));

			if (current_Row.size() > 0) {
				WebElement a1 = current_Row.get(0)
						.findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
				a1.click();
				webDriver
						.findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]"))
						.click();
				webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
				webDriver.findElement(By.xpath("//input[contains(@value,'Save and commit')]")).click();
				webDriver.navigate().back();
			} else {
				current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
						+ "td[position()=8 and contains(text(),'Submitted')]   and "
						+ "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
						+ "td[position()=3 and contains(text()," + type_Of_App + ")]	" + "]"));
				WebElement a1 = current_Row.get(0)
						.findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));

				a1.click();
				// webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));
				Select dropdown1 = new Select(webDriver
						.findElement(By.xpath("//select[@id='review_current_assignment_attributes_reviewer_id']")));
				dropdown1.selectByIndex(0);
				Select dropdown2 = new Select(webDriver
						.findElement(By.xpath("//select[@id='review_current_assignment_attributes_owner_id']")));
				dropdown2.selectByIndex(1);
				Select dropdown3 = new Select(webDriver
						.findElement(By.xpath("//select[@id='review_current_assignment_attributes_supervisor_id']")));
				dropdown3.selectByIndex(1);
				webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
				webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
				webDriver.switchTo().alert().accept();

				webDriver
						.findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]"))
						.click();
				webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
				webDriver.findElement(By.xpath("//input[contains(@value,'Save and commit')]")).click();
				webDriver.navigate().back();
			}

			break;
		case "wosbactive":
			break;
		case "mppactive":
			break;
		}
		CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
		login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
		login_Data.Login_With_Reference();

	}

	public static void createApplication(WebDriver webDriver, String type_Of_App) throws Exception {
		navigationMenuClick(webDriver, "Programs");
		switch (type_Of_App.toUpperCase()) {
		case "EDWOSB":
			click_Element(webDriver, "JoinNewPgm_Create_App_EDWOSB");
			break;
		case "WOSB":
			click_Element(webDriver, "JoinNewPgm_Create_App_WOSB");
			break;
		case "MPP":
			click_Element(webDriver, "JoinNewPgm_Create_App_MPP");
			break;
		case "8A":
			click_Element(webDriver, "JoinNewPgm_Create_App_8A");
			break;
		default:
			Assert.assertEquals("Edwosb or WOSB or MPP or 8a", "Not Found");
		}
		click_Element(webDriver, "JoinNewPgm_Add_Cert");
		click_Element(webDriver, "Application_Common_Accept_Button");

	}

	public static void searchDuns_Number(WebDriver webDriver, String search_Text) throws Exception {
		click_Element(webDriver, "Search_Duns_Search_Text");
		setText_Element(webDriver, "Search_Duns_Search_Query", search_Text);
		click_Element(webDriver, "Search_Duns_Search_Submit");
	}

	public static void search_Cases_Duns_Number_Table(WebDriver webDriver, String search_Text) throws Exception {
		CommonApplicationMethods.setText_Element(webDriver, "Search_Duns_Cases_Test", search_Text);
		CommonApplicationMethods.click_Element(webDriver, "Search_Duns_Cases_Submit");
	}

	public static void navigationMenuClick(WebDriver webDriver, String which_Button) throws Exception {
		String part_01 = "//nav[@role='navigation']/div/ul/li/a/span[contains(text(),'";
		String part_03 = "')]";
		switch (which_Button.toUpperCase()) {
		case "LOGOUT":
			click_Element(webDriver, "Navigation_Logout");
			break;
		case "HELP":
			click_Element(webDriver, "Navigation_Help");
			break;
		case "CASES":
			click_Element(webDriver, "Navigation_Cases");
			break;
		case "PROGRAMS":
			click_Element(webDriver, "Navigation_Programs");
			break;
		case "DASHBOARD":
			click_Element(webDriver, "Navigation_Dashboard");
			break;
		case "BUSINESS":
			click_Element(webDriver, "Navigation_Business");
			break;
		case "DOCUMENTS":
			click_Element(webDriver, "Navigation_Documents");
			break;
		case "HOME":
			click_Element(webDriver, "Navigation_Home");
			break;
		default:
			Assert.assertEquals("Navigation Menu Not correct", "among present Options");
		}
	}

	public static String getflagvalue() throws Exception {
		String flagforRunfile = FixtureUtils.fixturesDir() + "flagforRunEmailNotification.config";

		BufferedReader bufferedReader = new BufferedReader(new FileReader(flagforRunfile));

		String detailFlag = bufferedReader.readLine();

		return detailFlag;
	}

	public static void casesPageSearch(WebDriver webDriver, String searchValue) throws Exception {
		Map locator = getLocator("Apllication_Case_Search_Text");
		CommonApplicationMethods.setText_Element(webDriver, "Apllication_Case_Search_Text", searchValue);
		locator = getLocator("Apllication_Case_Search_Button");
		CommonApplicationMethods.click_Element(webDriver, "Apllication_Case_Search_Button");
	}
}
