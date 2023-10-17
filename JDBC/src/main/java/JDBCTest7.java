import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCTest7 {

	public static void main(String[] args) {

		
		
		// 2023-10-17
		try {				//com.mysql.cj.jdbc.Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("load Ok!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("load Fail!");
		}
		
		try(
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?serviceTimezone=UTC","root","1234");
			Statement stmt = conn.createStatement();			
				) {
			System.out.println("OKK");
		
		
		Scanner sc = new Scanner(System.in,"euc-kr");
		String name = null , title = null, contents = null , sql = null;
		int result = 0;
		int no =0;
		int rCnt = 0;
		Date wTime;
		
		while(true) {
			System.out.println("1. insert");
			System.out.println("2. select");
			System.out.println("3. modify");
			System.out.println("4. delete");
			System.out.println("5. create table");
			System.out.println("6. drop table");
			System.out.println("7. down program!!");
			System.out.println("=====================");
			System.out.println("choice number : ");
			System.out.println("=====================");
			
			int menu =  Integer.parseInt(sc.nextLine());
			
			switch (menu) {
			case 1: 
				System.out.print("name :");
				name = sc.nextLine();
				
				System.out.print("title : ");
				title = sc.nextLine();
				
				System.out.print("contents");
				contents = sc.nextLine();
				
				sql = "insert into board (name , title , contents ) values ('"+name+"', '"+title+"','"+contents+"')";
				
				result = stmt.executeUpdate(sql);
				if(result > 0) System.out.println("insert OK!!");
				
				break;
			
			case 2: 	
				
				sql = "select * from board";
				try (ResultSet rs = stmt.executeQuery(sql);) 
				{	
					while(rs.next()) {
						  no = rs.getInt("no");
						  name = rs.getString("name");
						  title = rs.getString("title");
						  contents = rs.getString("contents");
						  rCnt = rs.getInt("rCnt");
						  wTime = rs.getDate("wTime");
						  
						  System.out.println("no  :" + no);
						  System.out.println("name : " + name);
						  System.out.println("title : "+ title);
						  System.out.println("contents : "+contents);
						  System.out.println("rCnt : " +rCnt);
						  System.out.println("wTime :" + wTime);
						  System.out.println();
					}
				} catch (Exception e2) {
					System.out.println("select fail");
				}
				break;
				
			case 3 :
				
				System.out.println("choice no : ");
				no = Integer.parseInt(sc.nextLine());
				
				System.out.println("modify contents : ");
				contents = sc.nextLine();
				
				sql = "update board set contents ='"+contents+"' where no='"+no+"' ";
				result  = stmt.executeUpdate(sql);
				if(result > 0) System.out.println("update OK!");
			
			case 4:
				System.out.println("choice number");
				no = Integer.parseInt(sc.nextLine());
				sql = "delete from board where no = '"+no+"'";
				
				result = stmt.executeUpdate(sql);
				if(result > 0) System.out.println("delete OK!");
				
				
				
			
		} //switch
		
		} //while
		
		
		
	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NOO");
		}// load END
	
			
		
		
		
		}//main
}//class
