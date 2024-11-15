package com.jsp.servlet.cardbproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DeletCarServlet extends GenericServlet{
	
	// Extracting values comming from UI
		@Override
		public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			int carId = Integer.parseInt(req.getParameter("carid"));

			//JDBC Logic
			Connection conn=null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_cardb","root","root123");

				PreparedStatement pst = conn.prepareStatement("DELETE FROM car WHERE carId=?");
				pst.setInt(1, carId);

				int rowsInserted = pst.executeUpdate();
				PrintWriter out = res.getWriter();

				if(rowsInserted>0) {
					out.print("data deleted...");
				}
				else {
					out.print("data not deleted...");
				}



			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			finally {

				if(conn!=null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}


		}


}
