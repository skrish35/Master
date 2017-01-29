package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.bean.UserReview;
import persistance.dao.MongoDBDataStoreUtilities;

public class Review extends HttpServlet {
       
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ggggggggg");
 		String pmname = request.getParameter("ProductModelName");
		String pcategory = request.getParameter("ProductCategory");
		String pprice = request.getParameter("ProductPrice");
		String rname = request.getParameter("RetailerName");
		String rzip = request.getParameter("RetailerZip");
		String rcity = request.getParameter("RetailerCity");
		String rstate = request.getParameter("RetailerState");
		String psale = request.getParameter("ProductonSale");
		String mname = request.getParameter("ManufacturerName");
		String mrebate = request.getParameter("ManufacturerRebate");
		String uid = request.getParameter("UserId");
		String uage = request.getParameter("UserAge");
		String ugender = request.getParameter("UserGender");
		String uoccupation = request.getParameter("UserOccupation");
		int rating = Integer.parseInt(request.getParameter("ReviewRating"));
		String rdate = request.getParameter("ReviewDate");
		String rtext = request.getParameter("ReviewText");
		
		System.out.println(pmname+pcategory+pprice);
		
		String submit = request.getParameter("commit");
		
		MongoDBDataStoreUtilities mDB=new MongoDBDataStoreUtilities();
		mDB.Insert(pmname,pcategory,pprice,rname,rzip,rcity,rstate,psale,mname,mrebate,uid,uage,ugender,uoccupation,rating,rdate, rtext);
		
		System.out.println("Reading reviews");
		
		request.getRequestDispatcher("HomeScreen.html").include(request,response);
		
		
	}

}
