package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistance.bean.Products;

public class RemoveCartServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String productId=request.getParameter("productId");
		HttpSession session = request.getSession();
		List<String> CartList=(List<String>)session.getAttribute("prodIdList");
		System.out.println("Size before remove"+CartList.size());
		CartList.remove(productId);
		System.out.println("Size After remove"+CartList.size());
		
		session.setAttribute("prodIdList", CartList);
		
		RequestDispatcher r=request.getRequestDispatcher("ProductServlet");
		r.forward(request,response);
	
		System.out.println(productId);
	}

	
	
}
