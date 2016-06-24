package all_production_test;

import org.openqa.selenium.WebDriver;

public class ProdbasePage {
	WebDriver mydriver;
	String MyUrl;

	public ProdbasePage(WebDriver driver) {
		this.mydriver = driver;
	}

	public void Prodbase() throws Exception {
		MyUrl = "https://certify.sba.gov/";
		mydriver.navigate().to(MyUrl);
		mydriver.manage().window().maximize();
	}
}
