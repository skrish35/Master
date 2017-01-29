package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.bean.UserReview;
import persistance.dao.MongoDBDataStoreUtilities;

public class ViewReview extends HttpServlet {
	      
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MongoDBDataStoreUtilities mDB=new MongoDBDataStoreUtilities();
		UserReview u;
		ArrayList<UserReview> outReviews;
		outReviews=mDB.selectReview();
		PrintWriter out = response.getWriter();
		
		String html="<!doctype html> <html> <head> <title> Best Deals </title> <style> table {     border-collapse: collapse;     width: 100%; }  th, td {     text-align: left;     padding: 8px; }  tr:nth-child(even){background-color: #f2f2f2}  th {     background-color: #4CAF50;     color: white; } </style> </head>  <body> <table> <tr><th>ProductName</th> <th>Rating</th> <th>Text</th> <th>U</th> </tr>";
		for(int i=0;i<outReviews.size();i++)
		{
			u=outReviews.get(i);
			System.out.println(u.getPname());
			System.out.println(u.getRating());
			System.out.println(u.getRtext());
			System.out.println(u.getUid());
			html=html+"<tr> <td>"+u.getPname()+"</td> <td>"+u.getRating()+"</td> <td>"+u.getRtext()+"</td> <td>"+u.getUid()+"</td> </tr>";
		}
		
		out.println(html);
		
	}

}
