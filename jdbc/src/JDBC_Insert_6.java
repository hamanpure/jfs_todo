import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_Insert_6 {

	public static void main(String[] args) throws Exception {
		
		Connection con=DBConn.getConn();
		Statement stmt=con.createStatement();
		// accept taskName, taskDate, taskStatus and regId from user
		// retrieve taskId from taskid_pks table using regId
		// if user task is a first task, then no record exist in taskid_pks
		// hence start taskId=0
		// if already exist take the taskId from taskid_pks
		// taskId++
		// with the incremented taskId, insert record into task table
		// insert / update taskId with regId in taskid_pks
		PreparedStatement pstmt1=con.prepareStatement("insert into tasks values (?,?,?,?,?)");
		PreparedStatement pstmt2=con.prepareStatement("insert into taskid_pks values (?,?)");
		PreparedStatement pstmt3=con.prepareStatement("update taskid_pks set taskid=? where regid=?");
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Task Name");
		String taskName=sc.nextLine();
		System.out.println("Enter Task Date");
		String taskDate=sc.nextLine();
		System.out.println("Enter Task Status");
		int taskStatus=sc.nextInt(); sc.nextLine();
		System.out.println("Enter RegID");
		int regId=sc.nextInt(); sc.nextLine();
		
		ResultSet rs=stmt.executeQuery("select taskId from taskid_pks where regid="+regId);
		int taskId=0;
		int i,j=0;
		boolean isNew=true;
		if(rs.next()) {
			isNew=false;
			taskId=rs.getInt(1);
		}
		taskId++;
		
		con.setAutoCommit(false);
		
		pstmt1.setInt(1, taskId);
		pstmt1.setString(2, taskName);
		pstmt1.setString(3, taskDate);
		pstmt1.setInt(4, taskStatus);
		pstmt1.setInt(5, regId);
		i=pstmt1.executeUpdate();
		
		if(isNew) {
			pstmt2.setInt(1,regId);
			pstmt2.setInt(2,taskId);
			j=pstmt2.executeUpdate();
		} else {
			pstmt3.setInt(1, taskId);
			pstmt3.setInt(2, regId);
			j=pstmt3.executeUpdate();
		}
		
		if(i==1 && j==1) {
			con.commit();
			System.out.println("TX Success, Task Added");
		} else {			
			con.rollback();
			System.out.println("TX Failed");
		}
		
		rs.close(); pstmt1.close();  pstmt2.close(); pstmt3.close(); stmt.close(); con.close();
	}
}
