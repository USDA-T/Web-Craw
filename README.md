### README.md

Let's improve QA Automation and make it possible for all team members (Dev/QA)

### Installation

#### Prerequisite

You will need to install and download one of the following before it can be run locally
for your local platform.

- [Google Web Driver](https://sites.google.com/a/chromium.org/chromedriver/downloads)
- [IE Web Web Driver](http://selenium-release.storage.googleapis.com/index.html)
- [Firefox Web Driver](https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver)
- [Apache Maven](https://maven.apache.org/)

For those who using MacOS you should be able to `brew install` these packages.

#### Basic Setup

- Add [Apache Maven][] to your path e.g. `mvn` command
- Add [Google Web Driver][] or [Firefox Web Driver][] to your path

- Edit the content of the file [development/src/main/resources/default.properties](development/src/main/resources/default.properties)
- Update the the content of the file [development/src/test/resources/default.properties](development/src/test/resources/default.properties)

To use point to your current appropriate driver e.g.
For me I have the following setup

```properties
## Default browser one of 'Firefox' or 'Chrome' (for now IE will be added shortly)
browser=Chrome

## Setting specific to Google Chrome (EDIT TO YOUR CORRECT PATH)
webdriver.chrome.driver=/home/bchoomnuan/bin/chromedriver

## Setting specific to Firefox (EDIT TO YOUR CORRECT PATH)
webdriver.firefox.bin=/home/bchoomnuan/apps/firefox/firefox
webdriver.firefox.port=7046

## URL under test
base_url_development=http://localhost:3000/
base_url_qa=https://certify.qa.sba-one.net/
base_url_staging=https://staging-certify.sba.gov/
```

### Setup and run the project

- To run the test against the server running locally (for local development)

You will need to edit the following line in `src/test/java/gov/sba/utils/TestUS801_*.java`

- To execute the test from command line

```sh
# Running default test for development
# Make sure that you have the local development running e.g. `rails s`
mvn clean test

# Running the test against QA
TEST_ENV=qa mvn clean test

# Running the test against Staging
TEST_ENV=staging mvn clean test
```

- If you like to run the test from within Eclipse IDE then you can optionally

```
# Then get the dependencies
mvn dependency:sources

# Generate the .project file for use in Eclipse
mvn eclipse:eclipse
```

Then import the project to your Eclipse IDE

- Currently the sample tests codes are in `src/test/java/gov/sba/utils/*.java`

- If you have any problems please let me know via HipChat or just call me

### Sample sessions

If you have everything setup correctly you should see something like the following:


```
$mvn test
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO]
[INFO] SBA Automation Modules
[INFO] Production
[INFO] Development Automation
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building SBA Automation Modules 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ sba_automation ---
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Production 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ production ---
[INFO] Deleting /home/bchoomnuan/codes/sba/sba-automation/production/target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ production ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/bchoomnuan/codes/sba/sba-automation/production/src/main/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.0:compile (default-compile) @ production ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ production ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/bchoomnuan/codes/sba/sba-automation/production/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.0:testCompile (default-testCompile) @ production ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 31 source files to /home/bchoomnuan/codes/sba/sba-automation/production/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ production ---
[INFO] Surefire report directory: /home/bchoomnuan/codes/sba/sba-automation/production/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running TestSuite
Configuring TestNG with: org.apache.maven.surefire.testng.conf.TestNGMapConfigurator@7506e922
Tests run: 0, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.182 sec

Results :

Tests run: 0, Failures: 0, Errors: 0, Skipped: 0

[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Development Automation 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ development ---
[INFO] Deleting /home/bchoomnuan/codes/sba/sba-automation/development/target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ development ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.0:compile (default-compile) @ development ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ development ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.0:testCompile (default-testCompile) @ development ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 3 source files to /home/bchoomnuan/codes/sba/sba-automation/development/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ development ---
[INFO] Surefire report directory: /home/bchoomnuan/codes/sba/sba-automation/development/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running TestSuite
Configuring TestNG with: org.apache.maven.surefire.testng.conf.TestNGMapConfigurator@7ab2bfe1
Configuring TestNG with: org.apache.maven.surefire.testng.conf.TestNGMapConfigurator@3f0ee7cb
FYI: your test URL:http://localhost:3000/
FYI: update system property: webdriver.chrome.driver->/home/bchoomnuan/bin/chromedriver
User is NOT eligible for Any of the programs because user answer NO for Qs2: 8(a), WOSB, EDWOSB & Hob-zone
FYI: your test URL:http://localhost:3000/
FYI: update system property: webdriver.chrome.driver->/home/bchoomnuan/bin/chromedriver
User is NOT eligible(due to NO for Qs1) for Any of the programs 8(a), WOSB, EDWOSB & Hob-zone
Tests run: 2, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 21.105 sec <<< FAILURE!
testUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS3_MainTest(gov.sba.utils.TestUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS3)  Time elapsed: 16.719 sec  <<< FAILURE!
org.openqa.selenium.ElementNotVisibleException: element not visible
  (Session info: chrome=51.0.2704.103)
  (Driver info: chromedriver=2.22.397932 (282ed7cf89cf0053b6542e0d0f039d4123bbb6ad),platform=Linux 4.4.13-1-lts x86_64) (WARNING: The server did not provide any stacktrace information)
Command duration or timeout: 18 milliseconds
Build info: version: '2.53.0', revision: '35ae25b1534ae328c771e0856c93e187490ca824', time: '2016-03-15 10:43:46'
System info: host: 'sbadev', ip: '127.0.0.1', os.name: 'Linux', os.arch: 'amd64', os.version: '4.4.13-1-lts', java.version: '1.8.0_92'
Driver info: org.openqa.selenium.chrome.ChromeDriver
Capabilities [{applicationCacheEnabled=false, rotatable=false, mobileEmulationEnabled=false, chrome={chromedriverVersion=2.22.397932 (282ed7cf89cf0053b6542e0d0f039d4123bbb6ad), userDataDir=/tmp/.com.google.Chrome.tp63Wp}, takesHeapSnapshot=true, databaseEnabled=false, handlesAlerts=true, hasTouchScreen=false, version=51.0.2704.103, platform=LINUX, browserConnectionEnabled=false, nativeEvents=true, acceptSslCerts=true, locationContextEnabled=true, webStorageEnabled=true, browserName=chrome, takesScreenshot=true, javascriptEnabled=true, cssSelectorsEnabled=true}]
Session ID: aef3c11068def763849a1fef2ca0c1c4
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:206)
	at org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:158)
	at org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:678)
	at org.openqa.selenium.remote.RemoteWebElement.execute(RemoteWebElement.java:327)
	at org.openqa.selenium.remote.RemoteWebElement.click(RemoteWebElement.java:85)
	at gov.sba.utils.TestUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS3.testUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS3_MainTest(TestUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS3.java:94)


Results :

Failed tests:   testUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS3_MainTest(gov.sba.utils.TestUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS3): element not visible(..)

Tests run: 2, Failures: 1, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] SBA Automation Modules ............................. SUCCESS [  0.158 s]
[INFO] Production ......................................... SUCCESS [  3.538 s]
[INFO] Development Automation ............................. FAILURE [ 21.589 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 25.397 s
[INFO] Finished at: 2016-06-24T16:49:52-04:00
[INFO] Final Memory: 21M/273M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test (default-test) on project development: There are test failures.
[ERROR]
[ERROR] Please refer to /home/bchoomnuan/codes/sba/sba-automation/development/target/surefire-reports for the individual test results.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
[ERROR]
[ERROR] After correcting the problems, you can resume the build with the command
[ERROR]   mvn <goals> -rf :development
```

### Some useful links

```
http://certify.qa.sba-one.net/
http://staging.qa.sba-one.net/
http://demo.qa.sba-one.net/
```

### Roadmaps & Todos

- [ ] Add the code to make it work with IE
- [ ] Move the test url to the property file
- [ ] Improve the documentation
- [ ] Make the code structure work with IE/Chrome/Firefox
- [ ] Use just one project structure (no need to have DEV/QA/Staging) and deploy from one code but using different configuration
- [ ] Struct the shared code properly (e.g. `src/main/java/*` vs `src/test/java/*`)
- [ ] Remove all hard-coded constants that are not portable and error-proned to failures
- [ ] Many other improvement like proper code style guide (PMD), bugs analysist (FindBugs), etc

### Notes

* For Linux the test will work properly for older version of Firefox e.g. 35.0.1 e.g. [Firefox 35.0.1][] (tested)

[Google Web Driver]: https://sites.google.com/a/chromium.org/chromedriver/downloads
[IE Web Web Driver]: http://selenium-release.storage.googleapis.com/index.html
[Firefox Web Driver]: https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver
[Apache Maven]: https://maven.apache.org/
[Firefox 35.0.1]: https://ftp.mozilla.org/pub/firefox/releases/35.0.1/linux-x86_64/en-US/
