package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bussiness.ReadProducts;
import persistance.bean.Order;
import persistance.bean.Payment;
import persistance.bean.Products;
import persistance.bean.User;
import persistance.dao.OrderDao;
import persistance.dao.PaymentDao;

public class OrderServlet extends HttpServlet {
	
	PaymentDao paymentDao;
	OrderDao orderDao;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		paymentDao = new PaymentDao();
		orderDao = new OrderDao();
		
		System.out.println("Inside mt");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

          

		// request.getRequestDispatcher("link.html").include(request, response);
		System.out.println("SACHIN SACHIN");
		System.out.println("credentials");
			//out.print("Welcome, " + name);
			HttpSession session = request.getSession();
			String name = (String) session.getAttribute("name");
			String button = (String) request.getAttribute("commit");
			if(name == null)
				request.getRequestDispatcher("1_login.html").include(request, response); 
			
			String orderNumber = request.getParameter("orderNum");
			if(button!= null && button.equals("Cancel Order")){
				paymentDao.deletePayment(Integer.valueOf(orderNumber));
				orderDao.deleteOrder(Integer.valueOf(orderNumber));
			}
			System.out.println("-------Calling dao");
			List<Order> orders = orderDao.getOrderById(Integer.valueOf(orderNumber));
			String[] prodList = new String[orders.size()];
			for (int i = 0; i < orders.size(); i++) {
				prodList[i] = orders.get(i).getProdId();
			}
			
			Payment payment = paymentDao.getPaymentById(Integer.valueOf(orderNumber));
			
			Products prod=new Products();
			
			System.out.println("Prod map read b4");
			 
			ReadProducts r =new ReadProducts(getServletContext().getRealPath("ProductCatalog.xml"));
			Map<String,Products> productMapPrint=r.printPretty();
			
			System.out.println("Prod map read after4");
			
			Set<Map.Entry<String, Products>> entries=productMapPrint.entrySet();
			String hiddenInputValue="";
			
			List<String> prodIdList = new ArrayList<String>();
			String html="<!doctype html> <html> <head> <title> Best Deals </title> <style>#cancelOrderBtn{width: 300px;     height: 50px;     background-color: green;} table {     border-collapse: collapse;     width: 100%; }  th, td {     text-align: left;     padding: 8px; }  tr:nth-child(even){background-color: #f2f2f2}  th {     background-color: #4CAF50;     color: white; } </style> </head>  <body> <form action='CancelOrderServlet' method ='post'><table> <tr><th>Products</th> <th>Brand Name</th> <th>Condition</th> <th>Price</th> </tr>";
			for(Map.Entry<String, Products> prodMap:entries){
				System.out.println("FR loop");
				Products product=prodMap.getValue();
				if(isSelectedValue(product.getId(),prodList)){
				System.out.println("console #"+ product.getName() +":");
            	System.out.println("\t\t retailer: " + product.getImage());
            	System.out.println("\t\t name: " + product.getPrice());
            	System.out.println(product.getCondition());
            	System.out.println(product.getImage());
            	System.out.println(product.getId());
            	prodIdList.add(product.getId());
            	html=html+"<tr> <td><img src='"+product.getImage()+"' style='width:100px;height:100px;'></td> <td>"+product.getName()+"</td> <td>"+product.getCondition()+"</td> <td>"+product.getPrice()+"</td> </tr>";
				}
			}
			if(payment != null && payment.getName() != null){
			//html=html+"<tr> <td></td> <td></td> <td>Grand Total : </td> <td>"+payment.getValue()+"</td> </tr><tr><td></td><td></td><td></td><td><button type='submit' value='"+orderNumber+"' name='cancelOrderNo' id='cancelOrderBtn'>cancel order</button></td></tr>";
			html=html+"<tr> <td></td> <td></td> <td>Order : </td> <td>canceled</td> </tr>";
			}else{
			html=html+"<tr> <td></td> <td></td> <td>Order : </td> <td>canceled</td> </tr>";
			//html=html+"<tr> <td></td> <td></td> <td>Grand Total : </td> <td>"+payment.getValue()+"</td> </tr><tr><td></td><td></td><td></td><td><button type='submit' value='"+orderNumber+"' name='cancelOrderNo' id='cancelOrderBtn'>cancel order</button></td></tr>";
			}
			
			html=html+"</table></form> <body> </html>";
			//<button id='commit' name='commit'  type='submit' value='OK' style='float: right;width: 300px;height: 50px;background-color: green;' > OK </button>
			session.setAttribute("prodIdList", prodIdList);
			out.println(html);
			
		out.close();
	}
	
	private boolean isSelectedValue(String id, String[] prodList) {
		for (String string : prodList) {
			if(id.equals(string))
				return true;
		}
		return false;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		request.getRequestDispatcher("OrderSearch.html").include(request, response); 
		//request.getRequestDispatcher("examples.html").include(request, response); 
		
	}

}
