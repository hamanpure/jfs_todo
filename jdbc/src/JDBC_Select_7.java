import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_Select_7 {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Email");
		String email=sc.nextLine();
		System.out.println("Enter Pass");
		String pass=sc.nextLine();
		Connection con=DBConn.getConn();
		Statement stmt=con.createStatement();
		/*
		int regId=0;
		ResultSet rs=stmt.executeQuery("select regid from register where email='"+email+"' and pass='"+pass+"'");
		if(rs.next()) {
			regId=rs.getInt(1);
		}

		rs=stmt.executeQuery("select * from tasks where regid="+regId);
		while(rs.next()) {
			System.out.print(rs.getInt(1)+" ");
			System.out.print(rs.getString(2)+" ");
			System.out.print(rs.getString(3)+" ");
			System.out.println(rs.getInt(4));
		}
		*/
		
		int regId=0;
		ResultSet rs=stmt.executeQuery("select * from tasks where regid=(select regid from register where email='"+email+"' and pass='"+pass+"')");
		while(rs.next()) {
			System.out.print(rs.getInt(1)+" ");
			System.out.print(rs.getString(2)+" ");
			System.out.print(rs.getString(3)+" ");
			System.out.println(rs.getInt(4));
		}
		
		rs.close(); stmt.close(); con.close();
	}
}
