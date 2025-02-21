import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_Select_4 {

	public static void main(String[] args) throws Exception {
		Connection con=DBConn.getConn();
		Statement stmt=con.createStatement();
		// int executeUpdate() and ResultSet executeQuery()
		ResultSet rs=stmt.executeQuery("select * from register");
		// if multiple records comes into ResultSet use while(rs.next())
		// if single record comes into ResultSet use if(rs.next())
		while(rs.next()) { // moves cursor from first - second record and so on
			// resultset points each record, fetch each column with index number or column name
			System.out.print(rs.getInt(1)+" ");
			System.out.print(rs.getString("fname")+" ");
			System.out.print(rs.getString(3)+" ");
			System.out.print(rs.getString(4)+" ");
			System.out.print(rs.getString(5)+" ");
			System.out.print(rs.getLong(6)+" ");
			System.out.println(rs.getString(7));
		}
		rs.close(); stmt.close(); con.close();
	}
}