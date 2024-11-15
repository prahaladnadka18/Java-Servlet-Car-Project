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

public class UpdateCarServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		int carId = Integer.parseInt(req.getParameter("carid"));
		String carModel = req.getParameter("carmodel");
		int carPrice = Integer.parseInt(req.getParameter("carprice"));

		//JDBC Logic
		Connection conn=null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_cardb","root","root123");

			PreparedStatement pst = conn.prepareStatement("UPDATE car SET carModel=?,carPrice=? WHERE carId=?");
			pst.setInt(3, carId);
			pst.setString(1, carModel);
			pst.setInt(2, carPrice);

			int rowsInserted = pst.executeUpdate();
			PrintWriter out = res.getWriter();

			if(rowsInserted>0) {
				out.print("data Updated...");
			}
			else {
				out.print("data not upDated...");
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
