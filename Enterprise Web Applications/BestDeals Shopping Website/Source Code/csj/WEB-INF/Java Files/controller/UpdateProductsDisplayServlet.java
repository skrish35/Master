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

public class UpdateProductsDisplayServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("Id");
		String Retailor = "";
		String name=request.getParameter("Name");
		String condition=request.getParameter("Condition");
		int price= Integer.parseInt(request.getParameter("Price"));
		String image="";
		System.out.println(id +Retailor +name +condition +price);

		ProductDao pd= new ProductDao();
		Products updateproduct=new Products(id,name,Retailor,image,condition,price);
        
		System.out.println("Product Updated Successfully");
		
		pd.updateProduct(updateproduct);
		RequestDispatcher r=request.getRequestDispatcher("HomeScreen.html");
		request.setAttribute("message", "Product successfully updated");
		r.forward(request,response);  
	}

}
