package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import persistance.bean.User;
import persistance.dao.ProductDao;
import persistance.dao.UserDao;
import bussiness.ReadProducts;

public class LoginServlet extends HttpServlet {
	
	List<Products> prodList;
	UserDao userDao;
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside mt");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		File file = new File(getServletContext().getRealPath("Users"));
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		System.out.println("MAPMAPMAP mt");

		// request.getRequestDispatcher("link.html").include(request, response);

		String name = request.getParameter("login");
		String password = request.getParameter("password");
		String submit = request.getParameter("commit");
		userDao = new UserDao();
		User user = userDao.getUserByUserName(name);
		HttpSession session = request.getSession();
		String userLogged = (String) session.getAttribute(name);
		
		System.out.println("credentials");
		if ((user != null &&
				password.equals(user.getPassword()) && submit.equals("Sign In"))
				|| (submit.equals("OK") && userLogged!=null) ) {
			//out.print("Welcome, " + name);
			
			
			if(name.equals("StoreMgr")&& password.equals("storemgr")){
				System.out.println("OOOOOOOOOOOOOOOOOOOO");
				RequestDispatcher r=request.getRequestDispatcher("StoreMgr.html");
				r.forward(request,response);
			}
			
			if(name.equals("SalesMan")&& password.equals("salesman")){
				
				RequestDispatcher r=request.getRequestDispatcher("2_SignUp.html");
				r.forward(request,response);
			}
			
			
			session.setAttribute("name", name);
			session.setAttribute("user", user);
			
			
			if(user.getUserType().equals("Customer")){
				
				Products prod=new Products();
				ProductDao p=new ProductDao();
				
				System.out.println("Prod map read b4");
				 
				ReadProducts r =new ReadProducts(getServletContext().getRealPath("ProductCatalog.xml"));
				Map<String,Products> productMapPrint=r.printPretty();				
				
				//Map<String,Products> productMapPrint=p.getAllProducts();
				
				System.out.println("Prod map read after4");
				
			
			Set<Map.Entry<String, Products>> entries=productMapPrint.entrySet();
			
			String hiddenInputValue="";
			
			String html="<!doctype html> <html> <head> <title> Best Deals </title><link rel='stylesheet' href='styles.css' type='text/css' /> <style> table {     border-collapse: collapse;     width: 100%; }  th, td {     text-align: left;     padding: 8px; }  tr:nth-child(even){background-color: #f2f2f2}  th {     background-color: #4CAF50;     color: white; } </style> </head>  <body> <h3 style='background:#1AB188'> <img src='BestDeal.png' alt='BestDeal' style='width:200px;height:100px'></h3><div name='autofillForm' id='autofillForm'><input type='text' value='' id='searchAreaText' name='searchAreaText' oninput='doCompletion()' placeholder='Search for products'/><div id='autoRow'><table id='AutoCompleteTable' class='gridTable'></table></div></div><div id='topNavigation'>     <nav>     	<ul>         			<li class=''><a href='OrderServlet'>Order Search </a></li> 			<li class=''><a href='AddReview.html'>Add Reviews </a></li> 			<li class=''><a href='ViewReview'>View Reviews </a></li> 			<li class=''><a href='Trending'>Trending</a></li> 	<li class=''><a href='DealMatchesUtility'>DealMatches</a></li>		<li class=''><a href='LogoutServlet'>Logout</a></li> 		</ul>     </nav> </div><div id='formDiv'><form action='ProductServlet' method = 'post'><table> <tr><th>Products</th> <th>Brand Name</th> <th>Condition</th> <th>Description</th> <th>Price</th> </tr>";
			for(Map.Entry<String, Products> prodMap:entries){
				
				System.out.println("FR loop");
				Products product=prodMap.getValue();
				p.addProduct(product);
				System.out.println("console #"+ product.getName() +":");
            	System.out.println("\t\t retailer: " + product.getImage());
            	System.out.println("\t\t name: " + product.getPrice());
            	System.out.println(product.getCondition());
            	System.out.println(product.getImage());
            	System.out.println(product.getId());
            	html=html+"<tr> <td><img src='"+product.getImage()+"' style='width:100px;height:100px;'></td> <td>"+product.getName()+"</td> <td>"+product.getCondition()+"</td> <td>"+product.getPrice()+"</td> <td><input type='checkbox' name='checkbox' value='"+product.getId()+"'></input></td> </tr>";
            	
			}
			
			session.setAttribute("prodMap", productMapPrint);
			html=html+"</table><button type='submit' name='commit' id='commit' style='float: right;width: 300px;height: 50px;background-color: green;'> Add to cart</button></form></div></body> <script type='text/javascript'	src='./AutoComplete.js'></script></html>";
			out.println(html);
			
			
			} /*else if(user.getUserType().equals("SalesMan")){
				request.getRequestDispatcher("2_SignUp.html").include(request,
						response);
			} else {
				request.getRequestDispatcher("StoreMgr.html").include(request,
						response);
			}*/
		} else {
			out.print("Sorry, username or password error!");
			request.getRequestDispatcher("1_login.html").include(request,
					response);
		}
		out.close();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		request.getRequestDispatcher("1_login.html").include(request, response); 
		//request.getRequestDispatcher("examples.html").include(request, response); 
		
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
