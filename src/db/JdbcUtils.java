package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtils {
	Connection conn = null;
	
	public Connection getConnection() {
		//
		
		return null;
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		
	}
	
	public void commit(Connection conn) {
		//
	}
	
	public void rollback(Connection conn) {
		//
	}
}
