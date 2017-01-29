package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.bean.Products;
import persistance.dao.MongoDBDataStoreUtilities;
import persistance.dao.OrderDao;
import persistance.dao.ProductDao;

public class Trending extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MongoDBDataStoreUtilities m=new MongoDBDataStoreUtilities();
		
		
		PrintWriter out=response.getWriter();
		//m.FiveMostLikedProducts();
		String html="<html> <head> <title> Best Deal </title> <link rel='stylesheet' type='text/css' href='1_login.css'> <link rel='stylesheet' href='styles.css' type='text/css' /></head>  <body style='background:#c1bdba'> <h3 style='background:#1AB188'> <img src='BestDeal.png' alt='BestDeal' style='width:200px;height:100px'></h3> <div id='topNavigation'>     <nav>     	<ul>         			<li class=''><a href='OrderServlet'>Order Search </a></li> 			<li class=''><a href='AddReview.html'>Add Reviews </a></li> 			<li class=''><a href='ViewReview'>View Reviews </a></li> 			<li class=''><a href='Trending'>Trending</a></li> 			<li class=''><a href='LogoutServlet'>Logout</a></li> 		</ul>     </nav> </div>";
		
		//String tableHTML="<table><tr><td colspan='2'><b>Top Five Liked products</b></td></tr><tr><th>Product Name</th><th>Review Rating</th></tr>"+m.FiveMostLikedProducts()+"</table>";
			
		String tableHTML="<table><tr><td colspan='2'><b>Top Five Liked products</b></td></tr><tr><td>Product Name</td><td>Review Rating</td></tr>"+m.FiveMostLikedProducts()+"</table>";
		OrderDao od=new OrderDao();

		
		String t3HTML="<hr><table><tr><td colspan='2'><b>Top Five ordered products</b></td></tr><tr><td>Product Name</td><td>Product Id<td>Product Count</td></tr>"+od.getTrendingItems()+"</table>";
		
		String tabHTML="<hr><table><tr><td colspan='2'><b>Top Five Zip Codes</b></td></tr><tr><td>Zip Code</td><td>Number of Products</td></tr>"+m.TopFiveZipCode()+"</table></body></html>";
		html=html+tableHTML+tabHTML+t3HTML;
		
		
		out.print(html);
		
		
	}


}
