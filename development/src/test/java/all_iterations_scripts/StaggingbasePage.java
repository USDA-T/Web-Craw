package all_iterations_scripts;
import org.openqa.selenium.WebDriver;
public class StaggingbasePage {
	WebDriver mydriver;

	String MyUrl;

	public StaggingbasePage(WebDriver driver) {
		this.mydriver = driver;
	}

	public void Staggingbase() throws Exception {
		MyUrl = "https://staging-certify.sba.gov/";
		mydriver.navigate().to(MyUrl);
		mydriver.manage().window().maximize();
	}
}
