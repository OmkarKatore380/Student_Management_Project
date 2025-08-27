package in.sp.st;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import in.sp.db.ConnectionDb;
@WebServlet("/update_Student_data")
public class Update extends HttpServlet{

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
			PreparedStatement ps=con.prepareStatement
					("UPDATE student SET name=?,rollno=?,email=?,contact=?,city=?,dob=? WHERE rollno=? OR contact=?");
			ps.setString(1, Name);
			ps.setString(2, Roll_no);
			ps.setString(3, Email_id);
			ps.setString(4, Contact);
			ps.setString(5, City);
			ps.setString(6, Death_of_Birth);
			ps.setString(7, Roll_no);
			ps.setString(8, Contact);
			int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("<h3 style='color:green'>Student data updated successfully!</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("/stud.jsp");
                rd.include(req, resp);
            } else {
                out.println("<h3 style='color:red'>No student found with Roll No: " + Roll_no + "</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("/update.jsp");
                rd.include(req, resp);
            }

            con.close();
		}catch(Exception e) {
			e.printStackTrace();
			out.println("<h3 style='color:red'>" + e.getMessage() + "</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/update.jsp");
            rd.include(req, resp);
		}
	}

}
