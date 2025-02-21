import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_Insert_3 {

	public static void main(String[] args) throws Exception {
		Connection con=DBConn.getConn();
		Statement stmt=con.createStatement();
		PreparedStatement pstmt=con.prepareStatement("insert into register values (?,?,?,?,?,?,?)");
		// Statement, PreparedStatement, CallableStatement
		// Statement - to execute diff SQL statements
		// PreparedStatement - to execute same SQL statement for many times
		// CallableStatement - to call functions/procedures (stored procs)

		// accept registration details from the user
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter FName");
		String fname=sc.nextLine();
		System.out.println("Enter LName");
		String lname=sc.nextLine();
		System.out.println("Enter Email");
		String email=sc.nextLine();
		System.out.println("Enter Pass");
		String pass=sc.nextLine();
		System.out.println("Enter Mobile");
		long mobile=sc.nextLong();
		sc.nextLine();
		System.out.println("Enter Address");
		String address=sc.nextLine();
		
		// user enters all the details except regid
		// hence generate regid pk
		int regid=0;
		
		// Statement interface is having 2 methods 
			// int executeUpdate(String dml)
			// ResultSet executeQuery(String drl)
		ResultSet rs=stmt.executeQuery("select max(regid) from register");
		if(rs.next()) {
			regid=rs.getInt(1);
		}
		regid++;
		
		// record insertion
		pstmt.setInt(1, regid);
		pstmt.setString(2, fname);
		pstmt.setString(3, lname);
		pstmt.setString(4, email);
		pstmt.setString(5, pass);
		pstmt.setLong(6, mobile);
		pstmt.setString(7, address);
		int i=pstmt.executeUpdate();
		
		if(i==1)
			System.out.println("Record inserted");
		rs.close(); stmt.close(); con.close();
	}
}
