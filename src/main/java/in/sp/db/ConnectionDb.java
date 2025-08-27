package in.sp.db;
import java.sql.DriverManager;
import java.sql.Connection;
public class ConnectionDb {
	
		public static Connection getConnection() {
			Connection con=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/student_data","root","Omkar_sql1");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return con;
		}
	}


