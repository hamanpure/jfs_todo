import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_Select_5 {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Email");
		String email=sc.nextLine();
		System.out.println("Enter Pass");
		String pass=sc.nextLine();
		Connection con=DBConn.getConn();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from register where email='"+email+"' and pass='"+pass+"'");
		if(rs.next()) {
			System.out.println("Login Success");
		} else {
			System.out.println("Login Failed");
		}
		rs.close(); stmt.close(); con.close();
	}
}
