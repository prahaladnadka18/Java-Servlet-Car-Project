package com.jsp.servlet.cardbproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DisplayCarServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int carId = Integer.parseInt(req.getParameter("carid"));
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_cardb","root","root123");

			PreparedStatement pst = conn.prepareStatement("SELECT * FROM car WHERE carId=?");
			pst.setInt(1, carId);

			ResultSet result = pst.executeQuery();
			PrintWriter out = res.getWriter();
			int count=0;
			while(result.next()) {
				count++;
				int id=result.getInt("carId");
				String carModel=result.getString("carModel");
				String carBrand=result.getString("carBrand");
				int carPrice=result.getInt("carPrice");
				out.print("<h2>"+id+"</h2>");
				out.print("<h2>"+carModel+"</h2>");
				out.print("<h2>"+carBrand+"</h2>");
				out.print("<h2>"+carPrice+"</h2>");

			}

			if(count>0) {
				out.print("No Data Present...!");
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
