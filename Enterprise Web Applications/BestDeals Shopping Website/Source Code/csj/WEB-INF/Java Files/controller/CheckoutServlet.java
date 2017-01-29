package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistance.bean.Order;
import persistance.bean.Payment;
import persistance.dao.OrderDao;
import persistance.dao.PaymentDao;

public class CheckoutServlet extends HttpServlet {
	
	PaymentDao paymentDao;
	OrderDao orderDao;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] prodList = request.getParameterValues("checkbox");

		System.out.println("Inside mt");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


		paymentDao = new PaymentDao();
		int count = paymentDao.getPaymentCount();
		int orderNum = 0;
		if(count > 0){
			orderNum = paymentDao.getMaxOrderNum();
		}
		orderNum++;
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		int value = (int) session.getAttribute("value");
		List<String> prodIdList = (List<String>) session.getAttribute("prodIdList");
		Payment payment = new Payment(orderNum,name,value);
		paymentDao.addPayment(payment);
		
		orderDao = new OrderDao();
		for (String prodId : prodIdList) {
			Order order = new Order(orderNum, prodId);
			orderDao.addOrder(order);
		}
        
		Date d=new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String orderDate = dateFormat.format(d);		
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +14);
        Date ad = cal.getTime();    
        String deliveryDate = dateFormat.format(ad);
        
		String s1 = "   ";
		String s2 = "       ";
		String html = "<!doctype html> <html> <head> <title> Best Deals </title> <link rel='stylesheet' href='styles.css' type='text/css' /> <style> table {     border-collapse: collapse;     width: 100%; }  th, td {     text-align: left;     padding: 8px; }  tr:nth-child(even){background-color: #f2f2f2}  th {     background-color: #4CAF50;     color: pink; } </style> </head>  <body> <header>     	<h1 style='background:#1AB188'><image src='BestDeal.png' alt='BestDeal' style='width:200px;height:100px'/><span></br><p style='font-size:20px'>Best Products,Best Price </span>         </h1>         	</header> 	<div name='autofillForm'> 		 		<div id='autoRow'> 			<table id='AutoCompleteTable' class='gridTable'></table> 		</div> 	</div> 	<div id='topNavigation'>     <nav>     	<ul>         			<li class=''><a href='OrderServlet'>Order Search </a></li> 			<li class=''><a href='AddReview.html'>Add Reviews </a></li> 			<li class=''><a href='ViewReview'>View Reviews </a></li> 			<li class=''><a href='Trending'>Trending</a></li> 			<li class=''><a href='LogoutServlet'>Logout</a></li> 		</ul>     </nav> 	<form action='Welcome' method = 'post'><h3>Order Sucessfull</h3>";
		html = html + "<p>OrderNumber : CD0000" + orderNum + "</p>";
		html = html + "<p>UserName"+s1+" : " + name + "</p>";
		html = html + "<p>Cost"+s2+" : " + value + "</p>";
		html = html + "<p>Delivery Date"+s2+" : " + deliveryDate + "</p>";
		html = html + "<h4> Thank You ... !!!</h4S>";
		html=html+"<button id='commit' name='commit' type='submit'style='float: right;width: 300px;height: 50px;background-color: green;' > OK </button>";
		
		html = html + "</form> <body> </html>";
		
		
		out.print(html);

		out.close();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		request.getRequestDispatcher("1_login.html").include(request, response);
		// request.getRequestDispatcher("examples.html").include(request,
		// response);

	}

	public PaymentDao getPaymentDao() {
		return paymentDao;
	}

	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
}
