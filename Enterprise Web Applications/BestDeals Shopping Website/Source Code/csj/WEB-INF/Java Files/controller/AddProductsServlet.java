package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.bean.Products;
import persistance.dao.ProductDao;

public class AddProductsServlet extends HttpServlet {
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("Id");
		String Category = request.getParameter("Category");
		String name = request.getParameter("Name");
		String condition=request.getParameter("Condition");
		int price=Integer.parseInt(request.getParameter("Price"));
		String retailer="";
		String image="";

		Products product = new Products(id, name, retailer, image, condition, price);
		ProductDao pd= new ProductDao();
		boolean success= pd.addProduct(product);
		if(success==true){
			System.out.println("Successfully added");
			/*RequestDispatcher r=request.getRequestDispatcher("AddProducts.html");
			request.setAttribute("message", "Product Sucessfully Added");
			r.forward(request,response);*/
			RequestDispatcher r=request.getRequestDispatcher("HomeScreen.html");
			request.setAttribute("message", "Product Sucessfully Deleted");
			r.forward(request,response);
		}
	}

}
