
//For UserLogin HTML Page

import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class UserServlet extends HttpServlet{


static char[] OTP(int userlen)
    {
        System.out.println("Generat OTP For AHPSC Bank UserLogin : ");
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
  res.setContentType("Personaltext/html");
  PrintWriter out=res.getWriter();

  String userid=req.getParameter("uname");
    String userpassword=req.getParameter("psw");
//String code=req.getParameter("otp");




    try
               {

                Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","System");
		Statement s=con.createStatement();
		int x=s.executeUpdate("create table userdata(uname varchar(20),psw varchar(20))");
		//x=s.executeUpdate("insert into userdata values('"+userid+"','"+userpassward+"')");

		ResultSet rs=s.executeQuery("select *from userdata");

		PreparedStatement ps=con.prepareStatement("insert into userdata values(?,?)");
		ps.setString(1,userid);
		ps.setString(2,userpassword);
//		ps.setString(3,code);

		int j=ps.executeUpdate();
     	if(j>0)
		out.print(" You are successfully registered...");

         }catch (Exception e2)
              {
               System.out.println(e2);
              }




        int length = 4;
        System.out.println(OTP(length));





                         if(userid.equals("User")&&userpassword.equals("123")){
                                    RequestDispatcher rd=req.getRequestDispatcher("/Home.html");
                                    rd.forward(req,res);
                                             out.println("<div class='wlc'>");
                                               out.println("WELCOME " +userid);
                                               out.println("</div>");
                                 }else{
                                        JOptionPane.showMessageDialog(null,"Authentication Error...","AlertMessage",JOptionPane.WARNING_MESSAGE);
                                         RequestDispatcher dispatcher=req.getRequestDispatcher("");
                                 dispatcher.forward(req,res);




                                 }

                                 /*           out.println("<div class='wlc1'>");
                                               out.println("WELCOME User" +userid);
                                               out.println("</div>");
                                    RequestDispatcher rd=req.getRequestDispatcher("/Costinfo.html");
                                    rd.forward(req,res);
*/
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
