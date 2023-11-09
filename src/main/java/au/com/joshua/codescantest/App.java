package au.com.joshua.codescantest;

import java.sql.*;
import org.apache.log4j.Logger;

public class App {
	static final Logger LOGGER = Logger.getLogger(App.class);
    public static void main(String[] args) {
    	LOGGER.info("Class instantiated...");
        try {
            // This is a vulnerable query susceptible to SQL injection
            String userInput = args[0]; // Simulating user input
            String query = "SELECT * FROM Users WHERE username = '" + userInput + "'";

            // Establishing a connection (Assuming a connection is already set up)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", "username", "password");

            // Executing the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Processing the results
            while (resultSet.next()) {
            	LOGGER.info("User: " + resultSet.getString("username"));
            }

            // Closing resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        	LOGGER.error("An error occurred: " + e.getMessage());
        }
    }
}