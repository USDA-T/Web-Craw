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

### Roadmaps & Todos

- [X] Move the test url to the property file
- [X] Use just one project structure (no need to have DEV/QA/Staging) and deploy from one code but using different configuration
- [X] Add the code to make it work with Chrome
- [X] Add the code to make it work with Firefox
- [ ] Add the code to make it work with Internet Explorer
- [ ] Add the code to make it work with Safari
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
