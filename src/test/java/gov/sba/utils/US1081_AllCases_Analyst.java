package gov.sba.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import static org.junit.Assert.assertEquals;
import java.sql.*;


public class US1081_AllCases_Analyst {
				// Set The variabl.es/Define
					private static WebDriver webDriver;
					private static final Logger logger = LogManager.getLogger(US1081_AllCases_Analyst.class.getName());
					int get_The_Row_From_Login_Data;
					
			@Before
			public void setUp() throws Exception {


				webDriver = TestHelpers.getDefaultWebDriver();
				webDriver.get(TestHelpers.getBaseUrl());
				webDriver.manage().window().maximize();
				get_The_Row_From_Login_Data = 22;
				
			}
			@Test
			public void mainTest() throws Exception {
				// Login to dashboard.
				LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				login_Data.Login_With_Reference();
				Thread.sleep(3000);
			
			    // Need to submit the application in EDWosb, Wosb
				//	Log in As Super visor 	- validate as per the US1081 Acceptance criteria on Supervisor All cases page
				//	Log in As Analyst 			- validate as per the US1081 Acceptance criteria on Supervisor All cases page				
				try {
	
								WebElement Cases_Link = webDriver.findElement(By.cssSelector("a[href*='/sba_analyst/cases']"));
								Cases_Link.click();
								Thread.sleep(3000);
								logger.info("Cases link is on Main Navigator is Clicked");
								
								String Allcases_PageTitle = webDriver.findElement(By.xpath("//article[@id='main-content']//h1[contains(text(),'All cases')]")).getText();
								assertEquals(Allcases_PageTitle, "All cases");
						
						        String[] header_Names_Array = new String[]{"Business name","DUNS","Program","Review type","Submitted","Owner","Current reviewer","Status"};
								
								List<WebElement>    rows_Header = webDriver.findElements(By.xpath("//div[@id='table-search']/table/thead/tr/th"));  // Get the Table Header Cells
						        String[] header_Names_Array_Validate = new String[8];
								java.util.Iterator<WebElement> list_elements = rows_Header.iterator();
								int i = 0;
								while (list_elements.hasNext()) {
						            header_Names_Array_Validate[i] = list_elements.next().getText();
									i = i+1;
						        }

						        Assert.assertArrayEquals( header_Names_Array, header_Names_Array_Validate );
								String url = "jdbc:postgresql://sbaonedev.cypwvkg7qp3n.us-east-1.rds.amazonaws.com:5432/sbaone_qa";
								Properties props = new Properties();
								props.setProperty("user","app_etl");
								props.setProperty("password","etlpassworddev");
								Connection connection_SBA_One_Qa = DriverManager.getConnection(url, props);
								logger.info(connection_SBA_One_Qa);

								Statement statement_SQL = connection_SBA_One_Qa.createStatement();
								ResultSet result_Set = statement_SQL
													  .executeQuery(" SELECT F.legal_business_name AS legal_Name, " +
															  		"		 C.duns_number AS duns_No, " +
															  		"		 G.name AS cert_Name, " +
															  		"		 to_char(A.application_submitted_at, 'mm/dd/yyyy') AS sub_Date , " +
															  		"		 A.workflow_state AS sub_Status" +
																	" 	FROM " +
															  		"			sbaone.sba_applications A INNER JOIN sbaone.organizations C ON (A.organization_id = C.id), " +
															  		"			sbaone.certificate_types 		G , " +
															  		"			reference.sam_organizations 	F" +
																	" 	where(  A.workflow_state 		= 'submitted'				" +
																	"       AND A.progress 				->>'current' = 'signature')	" +
																	"       AND A.certificate_type_id 	= G.id						" +
																	"       AND C.duns_number 			= F.duns" +
															  		"		ORDER BY G.name ;					");

								List<ArrayList<String>> db_rows_array = new ArrayList<>();
								while (result_Set.next()) {
									ArrayList<String> db_rows_Cell	= new ArrayList<>(); 		// Add inside a second Dimension Array
									db_rows_Cell.add(result_Set.getString("legal_Name").toUpperCase( ));
									db_rows_Cell.add(result_Set.getString("duns_No"));
									db_rows_Cell.add(result_Set.getString("cert_Name").toUpperCase( ));
									db_rows_Cell.add(result_Set.getString("sub_Date"));
									db_rows_Cell.add(result_Set.getString("sub_Status").toUpperCase( ));
									// Add a Row
									db_rows_array.add(db_rows_Cell);
								}
								logger.info("db data :" + db_rows_array.toString());	
								Thread.sleep(50000);
								result_Set.close();


								//	Entire Table Verification for next Sprint
								List<ArrayList<String>> ui_rows_array = new ArrayList<>();
						  		List<WebElement> rows_Body = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr"));  // Get the Table rows //logger.info(rows_Body.size());
								for(int j=0;j < rows_Body.size(); j++){
									ArrayList<String> ui_rows_Cell	= new ArrayList<>();
									logger.info(rows_Body.get(j).getAttribute("innerHTML"));
									List<WebElement> rows_Body_Cells = rows_Body.get(j).findElements(By.xpath("td"));  // Get the Table Cells
						    		logger.info("+++++++" + rows_Body_Cells.size());
									ui_rows_Cell.add(rows_Body_Cells.get(0).getText().toUpperCase( ));
									ui_rows_Cell.add(rows_Body_Cells.get(1).getText());
									ui_rows_Cell.add(rows_Body_Cells.get(2).getText().toUpperCase( ));
									ui_rows_Cell.add(rows_Body_Cells.get(4).getText());
									ui_rows_Cell.add(rows_Body_Cells.get(7).getText().toUpperCase( ));
									ui_rows_array.add(ui_rows_Cell);
								}
								logger.info(ui_rows_array.toString());
								for(int j=0;j < ui_rows_array.size(); j++){
									for(int k=0;k < ui_rows_array.get(j).size(); k++){
										assertEquals(ui_rows_array.get(j).get(k), db_rows_array.get(j).get(k));
									
									}
								}




				}

				catch (Exception e) {
					logger.info("Cases link is on Main Navigator is not present" + e.toString());
				}
			
			}
			@After
					public void tearDown() throws Exception {
						webDriver.quit();
		}
}


