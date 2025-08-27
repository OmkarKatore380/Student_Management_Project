package in.sp.st;
import javax.servlet.annotation.*;
import java.io.*;
import javax.servlet.http.*;
import java.sql.Connection;
import javax.servlet.*;
import in.sp.db.ConnectionDb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.*;
@WebServlet("/login")
public class Form extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String aname=req.getParameter("name");
		String acon=req.getParameter("con");
		String apass=req.getParameter("pass");
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		try {
		Connection con=ConnectionDb.getConnection() ;
		PreparedStatement ps=con.prepareStatement("SELECT * FROM admin WHERE name=? AND contact=? AND password=?");
		ps.setString(1, aname);
		ps.setString(2, acon);
		ps.setString(3, apass);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			out.println("<h3 Style='color:green'> Welcome "+aname+"</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("/stud.jsp");
			rd.forward(req, resp);
		}else {
			out.println("<h3 Style='color:red'> You are not admin </h3>");
			RequestDispatcher rd=req.getRequestDispatcher("/Login.html");
			rd.include(req, resp);
		}
		}catch(Exception e) {
			e.printStackTrace();
			out.println("<h3 Style='color:red'>"+e.getMessage()+"</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("/Login.html");
			rd.forward(req, resp);
		}
	}
	

}
