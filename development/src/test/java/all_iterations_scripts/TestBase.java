package all_iterations_scripts;

import java.util.Hashtable;

public class TestBase {

	static Hashtable<String, String> environemntVariables;

	public void initializeEnvironment() {
		// Step 1 - Read config file
		// Step 2 initialize all the required values in environemntVariables
	}

	public String Url() {
		return environemntVariables.get("url");
	}
}
