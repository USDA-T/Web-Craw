package gov.sba.utils.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseQuery {
	
	public String[][] getDBData(String sql_query, int rows_Needed, int cols_Needed) throws Exception {
		// Connect SBAONE QA DB -to get data from DB
		String url = "jdbc:postgresql://sbaonedev.cypwvkg7qp3n.us-east-1.rds.amazonaws.com:5432/sbaone_qa";
		Properties props = new Properties();
		props.setProperty("user", "app_etl");
		props.setProperty("password", "etlpassworddev");
		Connection connection_SBA_One_Qa = DriverManager.getConnection(url, props);
		//query execution
		Statement statement_SQL = connection_SBA_One_Qa.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);
		ResultSet result_Set = statement_SQL
				.executeQuery(sql_query);
		
		if (!result_Set.first()) {    
		    System.out.println("No data");
		    return null;
		} 
		
		String[][] sql_Data = new String[rows_Needed][cols_Needed];
		for(int i = 0; i < rows_Needed; i++)
		{			
			for (int j = 1; j<cols_Needed+1; j++)
			{
//					String max_first_name = result_Set.getString(column_Names[0]);	
//				    String max_last_name = result_Set.getString(column_Names[1]);	
				sql_Data[i][j -1] = result_Set.getString(j);
				//System.out.println(sql_Data[i][j-1].toString());
			}

		};
		result_Set.close();
		return sql_Data;
	}
	

}
