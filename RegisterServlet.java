import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RegisterServlet extends HttpServlet{



    static char[] OTP(int len)
    {
        System.out.println("Generating OTP using random() : ");
        System.out.print("You OTP is : ");
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++)
        {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;

    }




  public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
  {
  res.setContentType("Rtext/html");
  PrintWriter out=res.getWriter();

  String a=req.getParameter("name");
  String b=req.getParameter("fname");
  String c=req.getParameter("Pancard");
  String d=req.getParameter("Aadhaar");
  String e=req.getParameter("Email");
  String f=req.getParameter("Contact");
  String g=req.getParameter("Address");
  String h=req.getParameter("gender");
  String i=req.getParameter("bday");




    try
               {

                Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","System");
		Statement s=con.createStatement();

     int x=s.executeUpdate("create table register(name varchar(20),fname varchar(20),pancard varchar(20),aadhaar number,email varchar(20),contact number,address varchar(100),gender varchar(10),badteOfBirdth varchar(12))");
		// x=s.executeUpdate("insert into register values('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"','"+i+"')");

ResultSet rs=s.executeQuery("select *from register");

		PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?,?)");
		ps.setString(1,a);
		ps.setString(2,b);
		ps.setString(3,c);
        ps.setString(4,d);
        ps.setString(5,e);
        ps.setString(6,f);
    	ps.setString(7,g);
		ps.setString(8,h);
		ps.setString(9,i);


int j=ps.executeUpdate();
if(j>0)
{
	out.print(" You are successfully registered...");
                                       out.println("WELCOME User" +a);
}






   	//RequestDispatcher dispatcher=req.getRequestDispatcher("Register.html");
     // dispatcher.include(req,res);
		      }catch (Exception e2)
              {
               System.out.println(e2);
               }


out.close();

                        JOptionPane.showMessageDialog(null,"Your Data has been Recieved...","Message",JOptionPane.WARNING_MESSAGE);
                                    RequestDispatcher rd=req.getRequestDispatcher("/Home.html");
                                    rd.forward(req,res);
                       out.println("WELCOME " +a);



        int length = 4;
        System.out.println(OTP(length));



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
