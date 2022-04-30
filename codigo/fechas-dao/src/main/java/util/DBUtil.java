package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	public static Connection getConexion() throws NamingException, SQLException {
		DataSource datasource = (DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/postgres");
		return datasource.getConnection();
	}
}
