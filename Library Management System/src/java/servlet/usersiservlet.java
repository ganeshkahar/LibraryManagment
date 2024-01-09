/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ganesh
 */
@WebServlet(name = "usersiservlet", urlPatterns = {"/usersiservlet"})
public class usersiservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out=res.getWriter();
        String s= req.getParameter("name");
          String s1= req.getParameter("email");
            String s2= req.getParameter("password");

        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/library","root","root");  
           
    
 PreparedStatement pst=con.prepareStatement("insert into  usersingnup values(?,?,?)"); 
out.print("conn");
	       pst.setString(1,s);
		pst.setString(2, s1);
                pst.setString(3,s2);
               
             int a= pst.executeUpdate();
            if(a!=0){
			res.setContentType("text/html");
			out.print("Register succesfully"+" "+s);
			RequestDispatcher d=req.getRequestDispatcher("/Userlogin.html");
                        d.forward(req, res);
		
		}
		else
			System.out.println("else");
			
		}
           
        
        
        catch(Exception ex){
        out.printf("error");}
     
    }


}
