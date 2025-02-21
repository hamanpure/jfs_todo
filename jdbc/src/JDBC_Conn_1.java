import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Conn_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// loading JDBC driver
//						Class.forName("oracle.jdbc.driver.OracleDriver");
						 Class.forName("com.mysql.jdbc.Driver");
						
						// establish Connection to DB
//						Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jfs", "jfs");
						 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jfs", "root", "");
						
						System.out.println(con);

			
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e3) {
			e3.printStackTrace();
		}
	}

}
