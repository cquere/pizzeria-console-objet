package fr.pizzeria.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataBase {

	protected static final Logger LOGERROR = LoggerFactory.getLogger("ERROR");

	private String url;
	private String username;
	private String password;

	/**
	 * @throws ClassNotFoundException
	 * 
	 */
	public DataBase() throws ClassNotFoundException {
		ResourceBundle properties = ResourceBundle.getBundle("jdbc");
		Class.forName(properties.getString("jdbc.driver"));
		url = properties.getString("jdbc.url");
		username = properties.getString("jdbc.username");
		password = properties.getString("jdbc.password");
	}

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			LOGERROR.error(e.getMessage());
			throw new RuntimeException("Problème de connection a la base : Connection impossible");
		}
	}

	public void close(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			LOGERROR.error(e.getMessage());
			throw new RuntimeException("Problème de connection a la base : Action impossible");
		}
	}

}
