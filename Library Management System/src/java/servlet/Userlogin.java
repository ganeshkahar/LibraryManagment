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
import java.sql.ResultSet;
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
@WebServlet(name = "Userlogin", urlPatterns = {"/Userlogin"})
public class Userlogin extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out = res.getWriter();
          res.setContentType("text/html");
        String s = req.getParameter("user_id");
        String s1 = req.getParameter("password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/library", "root", "root");
  out.printf("error");
            PreparedStatement pst = con.prepareStatement("select * from usersingnup where name=? and password=? ");

            pst.setString(1, s);
            pst.setString(2, s1);
          
            ResultSet r = pst.executeQuery();

            if (r.next()) {
              
                    out.print("<h2 style='color:yellow'> Login Successfull</h2>");

                RequestDispatcher d = req.getRequestDispatcher("/userport.html");
                d.forward(req, res);

            } else {
                
                out.print("<h2 style='color:red'> Name Phone no did'n match</h2>");
                RequestDispatcher d1 = req.getRequestDispatcher("/additem.html");
                d1.forward(req, res);}
        }
         catch (Exception ex) {
            out.printf("error");
        }

    }

}
