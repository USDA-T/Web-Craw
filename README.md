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
# I am using Linux system, so this is my path to the current driver
webdriver.chrome.driver=/home/bchoomnuan/chromedriver
webdriver.firefox.bin=/home/bchoomnuan/apps/firefox/firefox

# This is optional, but for me the default port '7045' is not working so I have
# to setup this explicitly on my local system
webdriver.firefox.port=7046
```

### Setup and run the project

- To run the test against the server running locally (for local development)

You will need to edit the following line in `src/test/java/gov/sba/utils/US801_*.java`

```java
  @Before
  public void setUp() throws Exception {
    TestHelpers.initSystemConfig();
    mydriver = new FirefoxDriver();

    // If you want to test against staging
    //mydriver.get("https://staging-certify.sba.gov/");

    // If you want to test with your server running locally (default)
    mydriver.get("http://localhost:3000/");

    mydriver.manage().window().maximize();
  }
```

e.g. for local test you will need to run the rails server locally e.g. `rails s`

- To execute the test from command line

```sh
mvn clean test
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
[INFO] ------------------------------------------------------------------------
[INFO] Building Production 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ production ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/bchoomnuan/codes/sba/sba-automation/production/src/main/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ production ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ production ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/bchoomnuan/codes/sba/sba-automation/production/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ production ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ production ---
[INFO] Surefire report directory: /home/bchoomnuan/codes/sba/sba-automation/production/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running TestSuite
Configuring TestNG with: org.apache.maven.surefire.testng.conf.TestNGMapConfigurator@7506e922
Tests run: 0, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.194 sec

Results :

Tests run: 0, Failures: 0, Errors: 0, Skipped: 0

[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Development Automation 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ development ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ development ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ development ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ development ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 32 source files to /home/bchoomnuan/codes/sba/sba-automation/development/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ development ---
[INFO] Surefire report directory: /home/bchoomnuan/codes/sba/sba-automation/development/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running TestSuite
Configuring TestNG with: org.apache.maven.surefire.testng.conf.TestNGMapConfigurator@7ab2bfe1
Configuring TestNG with: org.apache.maven.surefire.testng.conf.TestNGMapConfigurator@11531931
Starting ChromeDriver 2.22.397932 (282ed7cf89cf0053b6542e0d0f039d4123bbb6ad) on port 1076
Only local connections are allowed.
testGoogleSearch()
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.795 sec

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] SBA Automation Modules ............................. SUCCESS [  0.003 s]
[INFO] Production ......................................... SUCCESS [  2.268 s]
[INFO] Development Automation ............................. SUCCESS [  4.783 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 7.164 s
[INFO] Finished at: 2016-06-24T09:26:48-04:00
[INFO] Final Memory: 23M/250M
[INFO] ------------------------------------------------------------------------
```

### Some useful links

```
http://certify.qa.sba-one.net/
http://staging.qa.sba-one.net/
http://demo.qa.sba-one.net/
```

### TODOs

- [ ] Add the code to make it work with IE
- [ ] Move the test url to the property file
- [ ] Improve the documentation

### Notes

* For Linux the test will work properly for older version of Firefox e.g. 35.0.1 e.g. [Firefox 35.0.1][] (tested)

[Google Web Driver]: https://sites.google.com/a/chromium.org/chromedriver/downloads
[IE Web Web Driver]: http://selenium-release.storage.googleapis.com/index.html
[Firefox Web Driver]: https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver
[Apache Maven]: https://maven.apache.org/
[Firefox 35.0.1]: https://ftp.mozilla.org/pub/firefox/releases/35.0.1/linux-x86_64/en-US/
