package gov.sba.utils.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.sba.automation.utils.ConfigUtils;

public class DatabaseQuery {

    private static final Logger logger = LogManager.getLogger(DatabaseQuery.class.getName());
    
    
    public static void executeSQLScript(String sql_query) throws Exception {
        Connection dbConnection = null;
        PreparedStatement sqlStatement = null;

        try {
            dbConnection = getDatabaseConnection();
            sqlStatement = dbConnection.prepareStatement(sql_query);
            int rows = sqlStatement.executeUpdate();
            logger.info("Your query effects " + rows + " rows");
        } finally {
            if (sqlStatement != null) {
                sqlStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }            
        }
    }

    public static String[][] queryForData(String sqlQuery, int rowsNeeded, int colsNeeded) throws Exception {
        Connection dbConnection = null;
        ResultSet resultSet = null;

        try {

            dbConnection = getDatabaseConnection();
           
            Statement sqlStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            resultSet = sqlStatement.executeQuery(sqlQuery);
            
            if (!resultSet.first()) {
                throw new Exception("Problem fetching data using query: " + sqlQuery);
            }

            String[][] sqlData = new String[rowsNeeded][colsNeeded];

            for (int i = 0; i < rowsNeeded; i++) {
                for (int j = 1; j < colsNeeded + 1; j++) {
                    sqlData[i][j - 1] = resultSet.getString(j);
                }
            }

            return sqlData;
        } catch (Exception e) {
            logger.error("Unexpected problem getting data:" + e.toString());
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
    
    public static Connection  getDatabaseConnection() throws Exception {
        Properties props = ConfigUtils.loadDefaultProperties();        
        return DriverManager.getConnection(props.getProperty("db_url"),
                                    props.getProperty("db_username"), 
                                    props.getProperty("db_password"));
    }

}
