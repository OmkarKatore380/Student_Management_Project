package in.sp.st;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import in.sp.db.ConnectionDb;
@WebServlet("/Delete_Student_data")
public class Delete extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String Roll_no=req.getParameter("roll");
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		try {
			Connection con=ConnectionDb.getConnection();
			PreparedStatement ps=con.prepareStatement("DELETE FROM student WHERE rollno=?");
			ps.setString(1,Roll_no);
			int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("<h3 style='color:green'> Student with Roll No " + Roll_no + " deleted successfully.</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("/stud.jsp");
                rd.include(req, resp);
            } else {
                out.println("<h3 style='color:red'> No student found with Roll No " + Roll_no + ".</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("/DeleteStudent.jsp");
                rd.include(req, resp);
		}
		}catch(Exception e) {
			e.printStackTrace();
			out.println("<h3 style='color:red'>" +e.getMessage()+ "</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/DeleteStudent.jsp");
            rd.include(req, resp);
		}
	}
}
