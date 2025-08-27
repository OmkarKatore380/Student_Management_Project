package in.sp.st;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import in.sp.db.ConnectionDb;
import java.sql.*;
@WebServlet("/enter_stud_data")
public class InsertData extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String Name=req.getParameter("name");
		String Roll_no=req.getParameter("roll");
		String Email_id=req.getParameter("email");
		String Contact=req.getParameter("con");
		String City=req.getParameter("city");
		String Death_of_Birth=req.getParameter("dob");
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		try {
			Connection con=ConnectionDb.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO student (name ,rollno,email,contact,city,dob)VALUES( ?,?,?,?,?,?)");
			ps.setString(1, Name);
			ps.setString(2, Roll_no);
			ps.setString(3,Email_id);
			ps.setString(4, Contact);
			ps.setString(5, City);
			ps.setString(6, Death_of_Birth);
			int i=ps.executeUpdate();
			if(i>0) {
				out.println("<h2 Style='color:green'> Data is successfully entered </h2>");
				RequestDispatcher rd=req.getRequestDispatcher("/stud.jsp");
				rd.include(req, resp);
			}else {
				out.println("<h2 Style='color:red'> Please re-enter the data!!!!!</h2>");
				RequestDispatcher rd=req.getRequestDispatcher("/stud.jsp");
				rd.include(req, resp);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			out.println("<h2 Style='color:red'> Please re-enter the data!!!!!"+ e.getMessage()+"</h2>");
			RequestDispatcher rd=req.getRequestDispatcher("/stud.jsp");
			rd.include(req, resp);
		}
	}

}
