
//For  AdminLogin

import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AdminServlet extends HttpServlet{


static char[] OTP(int userlen)
    {
        System.out.println("Generat OTP For AHPSC Bank AdminLogin : ");
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[userlen];
        for (int i = 0; i < userlen; i++)
        {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
            System.out.print( otp[i]);
        }
        return otp;

    }



  public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
  {
  res.setContentType("text/html");
  PrintWriter out=res.getWriter();
  String a=req.getParameter("uname");
  String b=req.getParameter("psw");

    try
      {
          Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","System");
		Statement s=con.createStatement();
/*

		ResultSet rs=s.executeQuery("Select a,b from singlebank");

*/
      int x=s.executeUpdate("create table singlebank(username varchar(20),password varchar(20))");
		//x=s.executeUpdate("insert into singlebank values('"+a+"','"+b+"')");
       ResultSet rs=s.executeQuery("select *from singlebank");
    	PreparedStatement ps=con.prepareStatement("insert into singlebank values(?,?)");
		ps.setString(1,a);
		ps.setString(2,b);

		int j=ps.executeUpdate();
    }catch (Exception e2){
               System.out.println(e2);
               }

 int length = 4;
        System.out.println(OTP(length));


                         if(a.equals("Admin")&&b.equals("ankit123")){
                                    RequestDispatcher rd=req.getRequestDispatcher("/Costinfo.html");
                                    rd.include(req,res);
                                             out.println("<div class='wlc'>");
                                               out.println("WELCOME " +a);
                                               out.println("</div>");
                                 }else{
                                        JOptionPane.showMessageDialog(null,"Authentication Error ///...","AlertMessage",JOptionPane.WARNING_MESSAGE);
                                         RequestDispatcher dispatcher=req.getRequestDispatcher("");
                                 dispatcher.forward(req,res);




                                 }
                            
                            
        }







  public void destroy()
	{
	}

	public String getServletInfo()
	{
		return(null);
	}
	public ServletConfig getServletConfig()
	{
		return(null);
	}




}
