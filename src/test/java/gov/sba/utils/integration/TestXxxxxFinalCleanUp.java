// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.FixtureUtils;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.PrintWriter;

import static gov.sba.automation.CommonApplicationMethods.delete_Any_File_To_Indicate_Currently_Running_In_Headless;

@Category({gov.sba.utils.integration.StableTests.class})
public class TestXxxxxFinalCleanUp extends TestCase {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testXxxxxFinalCleanUp() throws Exception {
    PrintWriter writer = new PrintWriter(FixtureUtils.rootDirExecutionFile(), "UTF-8");
    writer.println("Should_Execution_Stop: \"False\"");
    writer.close();
    delete_Any_File_To_Indicate_Currently_Running_In_Headless();
  }

  @After
  public void tearDown() throws Exception {

  }
}
