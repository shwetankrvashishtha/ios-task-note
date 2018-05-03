package com.automation.framework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author shwetankvashishtha
 *
 */
public class DBConnectivity {

	Connection conn = null;
	Statement stmt = null;

	public String getDbResults(String query, String dbUrl, String dbUser, String dbPassword, int columnIndex)
			throws Exception {
		String result = null;
		ResultSet resultSet = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery(query);
		while (resultSet.next()) {
			result = resultSet.getString("password");
		}
		return result;
	}

	public void closedbConn() {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
