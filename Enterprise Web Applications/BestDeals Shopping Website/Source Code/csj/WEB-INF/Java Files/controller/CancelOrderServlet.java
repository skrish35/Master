package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.dao.OrderDao;

public class CancelOrderServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int orderNum = Integer.parseInt(request.getParameter("cancelOrderNo"));
		System.out.println("doPost of cancel order servlet"+orderNum);
		OrderDao od=new OrderDao();

		od.deleteOrder(orderNum);
		System.out.println("END END");
		request.getRequestDispatcher("HomeScreen.html").include(request, response);
	}

}
