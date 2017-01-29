package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.dao.ProductDao;

public class DeleteProductsServlet extends HttpServlet {   
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("Id");

		ProductDao pd= new ProductDao();


		boolean success= pd.deleteProduct(id);
		if(success==true){
			System.out.println("Successfully Deleted");
			RequestDispatcher r=request.getRequestDispatcher("HomeScreen.html");
			request.setAttribute("message", "Product Sucessfully Deleted");
			r.forward(request,response);
		}
	}

}
