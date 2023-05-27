package JDBCCrud_Operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class Crud_Operation {

	private final Scanner sc;
	private final Connection con ;
	static char ch;
	
	public Crud_Operation() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/amol_rec";
		String username = "root";
		String password = "MySQL";
		con = DriverManager.getConnection(url, username, password);
		sc = new Scanner(System.in);
	}
	
	public void insert() throws SQLException {
		System.out.println("Enter ID");
		int id=sc.nextInt();
		
		System.out.println("Enter name");
		String name = sc.next();
		
		System.out.println("Enter City");
		String city = sc.next();

		System.out.println("Enter email");
		String email = sc.next();
		
		String query="insert into emp13(id,name,city,email)values(?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setString(3, city);
		ps.setString(4, email);
		
		int status=ps.executeUpdate();
		if(status>0) {
			System.out.println("Data Inserted Successfully");
		}else {
			System.out.println("No rows were inserted");
		}
	}
	
	public void update() throws SQLException{
		System.out.println("Enter ID you want to update");
		int id=sc.nextInt();
		
		System.out.println("Enter name");
		String name = sc.next();
		
		System.out.println("Enter City");
		String city = sc.next();

		System.out.println("Enter email");
		String email = sc.next();
		
		String query="update emp13 set id=?,name=?,email=? where id=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,name);
		ps.setString(2,email);
		ps.setInt(3,id);
		
		int rowsUpdated=ps.executeUpdate();
		if(rowsUpdated>0) {
			System.out.println("Your data has been successfully updated. Thanks!");
		}else {
			System.out.println("No rows were updated.");
		}	
	}
	
	public void search() throws SQLException{
		System.out.println("Enter Id");
		int id=sc.nextInt();
		
		String query="select * from emp13 where id=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
		}
	}

	public void delete() throws SQLException {
		System.out.println("Enter id you want to delete?");
		int id = sc.nextInt();

		String sql ="DELETE FROM emp13 WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int rowsDeleted = ps.executeUpdate();
		if(rowsDeleted>0) {
			System.out.println("Your data has been successfully deleted. Thanks!");
		}else {
			System.out.println("No rows were deleted.");
		}
	}
	
	public void close() throws SQLException {
		con.close();
	}
	public static void main(String[] args) throws SQLException{
         
		try {	
			Scanner sc=new Scanner(System.in);
			Crud_Operation jk=new Crud_Operation();
			
		  do {
			  System.out.println("Enter Choice :\n1.Insert\n2.Update\n3.Search\n4.Delete");
			  int choice=sc.nextInt();
			  switch(choice) {
			  
			  case 1:
				  jk.insert();
			  break;
			  
			  case 2:
				  jk.update();
			  break;
			  
			  case 3:
				  jk.search();
			  break;
			  
			  case 4:
				  jk.delete();
			  break;
			  
			  case 0:
				  System.out.println("Exiting program...");
			  break;
			  
			  default:
				  System.out.println("Invalid choice. Please try again.");
			  }
			  System.out.println("If U Want To Cont Then Press 'Y'");
			  char ch=sc.next().charAt(0);
		  }while(ch=='Y');
		      jk.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
