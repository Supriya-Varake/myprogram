package com.studentsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class StudentManagementSystem {
	
	private static Connection conn = null;
		private ResultSet rs;

		public StudentManagementSystem() throws SQLException {
			String url = "jdbc:mysql://localhost/studentdb";
	        String uname = "root";
	        String pwd = "java@9881";
	        conn = DriverManager.getConnection(url,uname,pwd);  

		}
		public void showRecords() throws SQLException
	    {
	          PreparedStatement stmt = conn.prepareStatement("Select * from Students");

	            rs = stmt.executeQuery("select * from Student");
	    		while (rs.next())
	    			System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

	            stmt.executeUpdate();

	            stmt.close();

	    }
		public void searchRecord(int srno) throws SQLException
		{
			    PreparedStatement stmt = conn.prepareStatement("Select *from Student where Srno=" + srno +" ");

	             rs = stmt.executeQuery();
	            while (rs.next())
	            {
	                System.out.println(rs.getInt("srno") + " \t" + rs.getString("sname") + "\t " +rs.getString("saddress"));

	            }

		}

		 public int saveStudent(Student std) throws SQLException
		    {
		    	        int affectedRow = 0;

		            PreparedStatement stmt = conn.prepareStatement("insert into Student (srno,sname,saddress) values(?,?,?)");

		            stmt.setInt(1,std.getSrno());
		            stmt.setString(2, std.getSname());
		            stmt.setString(3, std.getSaddress());

		            affectedRow = stmt.executeUpdate();

		            stmt.close();

		        return affectedRow;

		    }
		 public int updateStudent(Student std) throws SQLException
		    {
			        int affectedRow = 0;
		             PreparedStatement stmt=conn.prepareStatement("update Student set(srno,sname,saddress) values(?,?,?) where srno ="+ std.getSrno());
		             stmt.executeUpdate();


		            stmt.setInt(1,std.getSrno());
		            stmt.setString(2, std.getSname());
		            stmt.setString(3, std.getSaddress());

		            affectedRow = stmt.executeUpdate();

		            stmt.close();

		        return affectedRow;
		    }

		 public int deleteStudent(int srno) throws SQLException
		    { 
			 int affectedRow = 0;

			 PreparedStatement stmt=conn.prepareStatement("delete from Student where srno="+srno);
			 affectedRow = stmt.executeUpdate();

			 stmt.close();
	         return affectedRow;
		    }

		 public static void main(String[] args) throws SQLException {

			 try{
				 StudentManagementSystem obj = new StudentManagementSystem();
				    Scanner sc=new Scanner(System.in);
					System.out.println("1. Display Records");
					System.out.println("2. Insert Record");
					System.out.println("3. Update Record");
					System.out.println("4. Search Record");
					System.out.println("5. Delete Record");
					System.out.println("6. Exit");
					System.out.println("Enter Your Choice:"); 

					int num=sc.nextInt();

					switch(num){    
					    case 1: obj.showRecords();  
					        break;  

					    case 2: System.out.println("Enter Srno:");
								int srno =sc.nextInt();
								System.out.println("Enter Sname:");
								String sname = sc.next();
								System.out.println("Enter Saddress:");	
								String saddress = sc.next();
								Student std=new Student(srno, sname, saddress);
								int res= obj.saveStudent(std);
								if (res != 0)
					                System.out.println("record Deleted");
					            else
					                System.out.println("record not Deleted");

								break;  
					    case 3:
						    	System.out.println("Enter Srno:");
								int srno1 =sc.nextInt();
								System.out.println("Enter Sname:");
								String sname1 = sc.next();
								System.out.println("Enter Saddress:");	
								String saddress1 = sc.next();
								Student std1=new Student(srno1, sname1, saddress1);
								int res1= obj.updateStudent(std1);
								if (res1 != 0)
					                System.out.println("record Updated");
					            else
					                System.out.println("record not Updated");

					    	break;  
					    case 4:  
					    	System.out.println("Enter Srno:"); 
					    		int srno2=sc.nextInt();
					    	    obj.searchRecord(srno2);
					        break;  
					    case 5:
						    	System.out.println("Enter Srno:"); 
					    		int srno3=sc.nextInt();
					    		int res2=obj.deleteStudent(srno3);

					            if (res2 != 0)
					                System.out.println("record Deleted");
					            else
					                System.out.println("record not Deleted");

					    	break;
					    case 6:  System.exit(0);
					    	break;  

					    default:System.out.println("Not in 1, 2, 3, 4 or 5");  
				    }  
					sc.close();
				}
			 catch(Exception e){
			 }

			finally {
					conn.close();
					System.out.println("Database Closed....");
			 	 }
	  }
	}

	


