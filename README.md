### README.md

Let's improve QA Automation and make it possible for all team members in Dev/QA to use it effectively.

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

If you are using Windows, you can use this [excellent guide](http://www.mkyong.com/maven/how-to-install-maven-in-windows/)

- Add [Google Web Driver][] or [Firefox Web Driver][] to your path

For Windows you can

- Copy the example property file [development/src/main/resources/default-example.properties](development/src/main/resources/default-example.properties)

To use point to your current appropriate driver e.g.

```sh
# Copy the example configuration from the template
cp src/main/resources/default-example.properties.examples src/main/resources/default.properties
```
For me I have the following setup in my `src/main/resources/default.properties`

```properties
## Default browser one of 'Firefox' or 'Chrome' (for now IE will be added shortly)
browser=Chrome
#browser=Firefox

## Setting specific to Google Chrome (EDIT TO YOUR CORRECT PATH)
webdriver.chrome.driver=/usr/sbin/chromedriver

## Setting specific to Firefox (EDIT TO YOUR CORRECT PATH)
webdriver.firefox.bin=/home/bchoomnuan/apps/firefox/firefox
webdriver.firefox.port=7046

## URL under test for different environment
base_url_development=http://localhost:3000/
base_url_qa=https://certify.qa.sba-one.net/
base_url_staging=https://staging-certify.sba.gov/
```

### Setup and run the project

- To run the test against the server running locally (for local development)

You will need to edit the following line in `src/test/java/gov/sba/utils/Test*.java`

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

- You may also like to add the `M2_REPO` variables to point to `~/.m2/repository/`

  See [How to add M2_REPO classpath variable to Eclipse IDE](http://www.mkyong.com/maven/how-to-configure-m2_repo-variable-in-eclipse-ide/) for detail.

### Sample sessions

If you have everything setup correctly you should see something like the following:

The result of `./run-test-development`

```
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building SBA Automation Modules 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ sba_automation ---
[INFO] Deleting /home/bchoomnuan/codes/sba/sba-automation/target
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.244 s
[INFO] Finished at: 2016-06-27T13:15:00-04:00
[INFO] Final Memory: 8M/303M
[INFO] ------------------------------------------------------------------------
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building SBA Automation Modules 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ sba_automation ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.0:compile (default-compile) @ sba_automation ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ sba_automation ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.0:testCompile (default-testCompile) @ sba_automation ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 3 source files to /home/bchoomnuan/codes/sba/sba-automation/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ sba_automation ---
[INFO] Surefire report directory: /home/bchoomnuan/codes/sba/sba-automation/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running TestSuite
Configuring TestNG with: org.apache.maven.surefire.testng.conf.TestNGMapConfigurator@7ab2bfe1
Configuring TestNG with: org.apache.maven.surefire.testng.conf.TestNGMapConfigurator@3f0ee7cb
Your system under test :development
FYI: your test URL:http://localhost:3000/
FYI: update system property: webdriver.chrome.driver->/usr/sbin/chromedriver
User is NOT eligible for Any of the programs because user answer NO for Qs2: 8(a), WOSB, EDWOSB & Hob-zone
Your system under test :development
FYI: your test URL:http://localhost:3000/
FYI: update system property: webdriver.chrome.driver->/usr/sbin/chromedriver
User is NOT eligible(due to NO for Qs1) for Any of the programs 8(a), WOSB, EDWOSB & Hob-zone
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 20.961 sec

Results :

Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 23.777 s
[INFO] Finished at: 2016-06-27T13:15:25-04:00
[INFO] Final Memory: 20M/271M
[INFO] ------------------------------------------------------------------------
```

### Recommendation

- Avoid using `System.out.println()` use `logger.info()`, `logger.debug()` instead (see `TestHelpers.java` for example)
- Use appropriate logger e.g. `logger.info()` vs `logger.debug()`
- Avoid using the long name for class but instead rename the class to some context that make sense (Java standard)
- Test should be small and make use of proper assertion method e.g. `assertEqual()`, `assertTrue()`, etc
- Proper use of Java package name e.g. `gov.sba.utils`, `gov.sba.automation`, `gov.sba.automation.wosb` etc
- No hard-coded values for a particular system (development, qa, staging) must be removed. Make use of property file for this task.

### Roadmaps & Todos

- [X] Move the test url to the property file
- [X] Use just one project structure (no need to have DEV/QA/Staging) and deploy from one code but using different configuration
- [X] Add the code to make it work with Chrome
- [X] Add the code to make it work with Firefox
- [X] Add the code to make it work with Safari
- [X] Add the code to make it work with PhantomJS
- [ ] Add the code to make it work with Internet Explorer
- [X] Add proper logging framework avoid the need for `System.out.println()`
- [X] Remove all hard-coded constants that are not portable and error-proned to failures
- [X] Struct the shared code properly (e.g. `src/main/java/*` vs `src/test/java/*`)
- [ ] Many other improvement like proper code style guide (PMD), bugs analysist (FindBugs), etc

### Notes

* For Linux the test will work properly for older version of Firefox e.g. 35.0.1 e.g. [Firefox 35.0.1][] (tested)

[Google Web Driver]: https://sites.google.com/a/chromium.org/chromedriver/downloads
([IE Web Web Driver]: http://selenium-release.storage.googleapis.com/index.html)
[Firefox Web Driver]: https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver
[Apache Maven]: https://maven.apache.org/
[Firefox 35.0.1]: https://ftp.mozilla.org/pub/firefox/releases/35.0.1/linux-x86_64/en-US/

### Useful Links

#### Safari Driver

- [Safari Driver - main page](https://github.com/SeleniumHQ/selenium/wiki/SafariDriver)
- [Safari Driver - properties](https://github.com/SeleniumHQ/selenium/wiki/DesiredCapabilities#safari-specific)

#### Chrome Driver

- [Google Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/)

#### Firefox Driver

- If you are on Linux system, you may like to install Firefox version 35.0.1 instead of the lastest version

#### PhantomJS Driver

- [PhantomJS](http://phantomjs.org/) the headless browser

### Additional Tips for Windows based environment

- [How to install Maven on Windows](http://www.mkyong.com/maven/how-to-install-maven-in-windows/)
- [Git for Windows](https://git-for-windows.github.io/)

#### How to properly raise pull request

As this is the shared repository and we trying to remove any duplication as much as possible I propose the
follow guide line to make it easy for everyone going forward.

- Please create your branch of the `develop` branch of the repository
- Once you complete your story/implementation the please raise your pull request (PR) against `develop` branch not `master`
- Make sure that your PR can run against `development`, `qa` and `staging`
  * e.g. development means running against localhost (e.g. have the Rails server running locally, if you can) if not the code must
  be able to run and pass on both [QA](https://cerfify.qa.sba-one.net) or [Staging](https://staging-certify.sba.net)

  * To run test against the two servers (QA/Staging) just change the `default.properties` file to point to use the right URL, or use
  the correct script (e.g. `./run-test-staging` , or `./run-test-qa`)

- Try to keep the Git history clean by squash your commit together when it make sense don't publish your `work-in-progress` commits to
  the shared remote repository. This will make it easy to review/rebase if any conflict arise.

### Guideline on how to write a better test

This is just a general rules that I learnt as a Java/JEE developer for sometime in the past
this is by no mean hard/fast rules but it is quite common Java's practices that I gained
with experience working in the field. Hopefully we could use some of this as a guide in improving
our QA automation project.

#### Naming things in Java (class, variables, etc)

##### The common style guide for naming things in Java are using snake case

Please use

```java
// Good : naming using camelCase
public class TestUs1157 {
  // ...
}
```

Instead of

```java
// Bad: not using camelCase
public class Test_Us1157_Something {
  // ...
}

// Bad: not properly use camelCase
public class EdithpasswordPage {
}

// Good:
public class EditPasswordPage {

}
```
##### The JUnit test class shourd start with `TestSomething` like `TestSearchPage`

The helper class that can be named without the `Test*` keyword, like `LoginHelper.java` etc.

##### The same rules should be used when naming the variable but the first letter must be lower case

```java
// Bad : not using the camelCase
String expected_Text4 = "some text";

// Bad : not start with lower case
String ExpectedText4 = "some other text";

// Good: camelCase started with lower case
String expectedText = "some other text";
```

##### Keep the variable/class name short avoid writing long class name

Most of the time we refer to the story by the number in Rally system, we might as well
use this to name the class to reflect the story under test. (Open to discussion)

```java
// Good: short and no extra stuffs needed
public TestUs1157 {

}

// Bad: the name is too long and does not use camelCase
public class US1157_IAM_Configure_Vendor_Admin_Role_and_Permissions {
}
```

The benefit from using the shorter variable is that when we use `logger.info()` or `logger.debug()`
we can see the result nicely in the console. So my advise is to keep this short.

#### How to test for something that could potentially failed

```java
// If we run this test then this should pass as the code should raise exception
// thus the `expected` will be validated
@Test(expected=IndexOutOfBoundException.class)
public void testSomethingThatCouldFailed() {
   List emptyList = new ArrayList();
   Object o = emptyList.get(0);
}

// If we are not using annotation in Java then
//
@Test
public void testSomethingThatCouldFailed() {
   try {
     // Your tested code
     List emptyList = new ArrayList();
     Object o = emptyList.get(0);
     fail("My method didn't thrown when I expected it to");
   } catch (Exception e) {
     // expected so do nothing
   }
}
```

#### Avoid putting the hard-coded values in the test/setup

e.g. Avoid writing something like

```java
// Avoid writing
@Before
public void setup() throws Exception {
  // Common code re-use patterns as example
  webDriver = TestHelpers.getDefaultWebDriver();
  webDriver.manage().window().maximize();

  // Note: hard-coded value, we will not be able to use this code easily between
  // different environment
  myUrl = "https://max.gov/"
  userName = "SomeUserName";
  password = "SomPassword";
}
```

Instead try to capture the to the shared/reusable class/modules like

```java
@Before
public void setup() throws Exception {
  // Common code re-use patterns as example
  webDriver = TestHelpers.getDefaultWebDriver();
  webDriver.manage().window().maximize();

  // Note: hard-coded value, we will not be able to use this code easily between
  // different environment  e.g. `environment = "DEV" or "QA", "Staging", etc
  loginHelpers = LoginHelpers.loginCredentials(environment)

  myUrl = loginHelpers.getTestUrl();
  userName = loginHelpers.getTestUserName();
  password = loginHelpers.getPassword();
}
```

- Properly expanded the import statements when possible (good to have)

```java
// Use
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

// instead of just
import static org.junit.Assert.*
```

The existing codes are now properly expanded, please keep expanding the import statement when bringing in
new dependency.

#### JUnit - use the proper `setUp()`, `tearDown()`, `setUpBeforeClass()`, `tearDownAfterClass()` appropriately

For example see [src/test/java/gov/sba/utils/TestSearchPage.java](src/test/java/gov/sba/utils/TestSearchPage.java)

```java
// In JUnit the following are common practices when create the tests
// It is so common that if you create the new JUnit test file, this code will
// be generated for your from your IDE (Eclipse/IntelliJ)
// See: the example in

@BeforeClass
public static void setUpBeforeClass() throws Exception {
  // Add code here to setup something that need to be done before all of the tests
}

@AfterClass
public static void tearDownAfterClass() throws Exception {
  // Add code here to properly cleanup after all of the tests has been ran
}

@Before
public void setUp() throws Exception {
  // This is the code that run before each test
  webDriver = TestHelpers.getDefaultWebDriver();
  // more code here
}

@After
public void tearDown() throws Exception {
  // This is the code that should cleanup after each test
  webDriver.quit();
}
```

#### JUnit - break your test into small logical unit when possible

When writing your test, it is better to only write keep your test small to
validate the simplest possible path (happy/unhappy), don't just write one big test
for everything.

```java
// ...
@Test
public void testHappyPath01() throws Exception {
   // Your normal test code here
}

@Test
public void testHappyPath02() throws Exception {
   // Your normal test code here
}

@Test
public void testUnhappyPath01() throws Exception {
  // Your other tests
}

@Test
public void testUnhappyPath02() throws Exception {
  // Your other tests
}
```

#### Do things the Java-ways please

Please don't name your class like

```java
public class Analyst_search_View_vendors_Record_Regression_1 {
  //...
}
```

but instead name your class like

```java
public class AnalystSearchViewVendorsRecordRegression1 {
  //..
}
```

#### Name your JUnit test class start with `Test*` e.g.

```
public class TestAnalystSearchViewVendorsRecordRegression1 {
```

- Please don't swallow Java exception e.g.

```java
try {
   // some code that could fail
} catch (SomeException e) {
  // don't just printStrackTrace()
  // re-raise exception or log the errors properly!
}
```

#### In Java all of the `TODO` must be addressed or remove they are [Technical Debts](https://en.wikipedia.org/wiki/Technical_debt)

```java
// Some where I found some code like this which is not accessible anywhere
@Rule
public ErrorCollector errorCollector = new ErrorCollector();


// see: AnalystLoginPage.java
private void assertEquals(String actualText, String expectedText) {
   //TODO: Auto-generated method stub
}
```

#### Don't use `if-then-else` in your test, use assertion library directly

e.g. Dont' do this
```java
if (webDriver.getPageSource().contains("Signed in successfully")) {
   // avoid the side-effect in the test!
    webDriver.findElement(By.xpath(".//*[@id='labelid']")).click();
} else {
    System.out.println("Successful sign in alert message not present");
}
```

Instead try using assertion directly

```java
assertTrue(webDriver.getPageSource().contains("Signed in successfully"));
```

If the test fail, then we will know based on the above assertion

#### Remove unused comments or extra blank lines

Please don't add extra empty lines to the code, try to keep it clean.

#### Try to keep the logger to the minimum and use appropriate level of logging

- Use `logger.info()` only for things that you really need to show all the time.
- Use `logger.debug()` or remove your `logger.info()` when you work out the details of the problem.

#### More Stuffs will goes here

- [JUnit Categories](http://maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html#)

