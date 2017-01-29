package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistance.bean.Products;
import persistance.dao.ProductDao;


public class UpdateProductsGetDetailsServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int productId=Integer.parseInt(request.getParameter("Id"));
		Products p;
		ProductDao pd=new ProductDao();

		p=pd.getProductById(request.getParameter("Id"));
		
		String html="<html> <head> <title> Best Deal </title> <link rel='stylesheet' type='text/css' href='1_login.css'> </head>  <body style='background:#c1bdba'> <h3 style='background:#1AB188'> <img src='BestDeal.png' alt='BestDeal' style='width:200px;height:100px'></h2>  <form action='UpdateProductsDisplayServlet' method='post'>  <div class='div-in'>  <p class='create-account-callout'> Product Details </p>  <label for='login_field' class='label-data'> Id </label> <input class='input-data' type='text' name='Id' value='"+p.getId()+"'></input>  <label for='login_field' class='label-data'> Name </label> <input class='input-data' type='text' name='Name' value='"+ p.getName()+"'></input>  <label for='login_field' class='label-data'> Condition </label> <input class='input-data' type='text' name='Condition' value='"+p.getCondition()+"'</input>  <label for='login_field' class='label-data'> Price </label> <input class='input-data' type='text' name='Price' value='"+p.getPrice()+"'</input>  <input class='input-button' name='commit' id='commit' tabindex='3' type='submit' value='Save'>    </div>  </form> </body> </html> ";
	
		PrintWriter out = response.getWriter();
		
		out.println(html);
	}

}
