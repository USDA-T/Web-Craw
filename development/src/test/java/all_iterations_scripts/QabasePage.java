package all_iterations_scripts;
import org.openqa.selenium.WebDriver;
public class QabasePage {
	WebDriver mydriver;
	String MyUrl;

	public QabasePage(WebDriver driver) {
		this.mydriver = driver;
	}

	public void Qabase() throws Exception {
		MyUrl = "https://certify.qa.sba-one.net";
		mydriver.navigate().to(MyUrl);
		mydriver.manage().window().maximize();
	}
}
