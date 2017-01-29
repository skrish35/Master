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
import persistance.bean.Products;
import persistance.bean.User;

public class ProductServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			String[] prodList = request.getParameterValues("checkbox");
			
			System.out.println("Inside mt");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
	
			File file = new File(getServletContext().getRealPath("CheckOutList"));
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);

			HttpSession session = request.getSession();
			String name = (String) session.getAttribute("name");
			
			Products prod=new Products();
			System.out.println("Prod map read b4");
			ReadProducts r =new ReadProducts(getServletContext().getRealPath("ProductCatalog.xml"));
			Map<String,Products> productMapPrint=r.printPretty();
			
			System.out.println("Prod map read after4");
			
			Set<Map.Entry<String, Products>> entries=productMapPrint.entrySet();
			String hiddenInputValue="";
			
			List<String> prodIdList;
			if(session.getAttribute("prodIdList")!=null){
				prodIdList=(List<String>)session.getAttribute("prodIdList");
			}
			else{
				prodIdList = new ArrayList<String>();
			}
			
			int value = 0;
			
			String html="<!doctype html> <html> <head> <title> Best Deals </title> <link rel='stylesheet' href='styles.css' type='text/css' /> <style> table {     border-collapse: collapse;     width: 100%; }  th, td {     text-align: left;     padding: 8px; }  tr:nth-child(even){background-color: #f2f2f2}  th {     background-color: #4CAF50;     color: white; } </style> </head>  <body> <header>     	<h1 style='background:#1AB188'><image src='BestDeal.png' alt='BestDeal' style='width:200px;height:100px'/><span></br><p style='font-size:20px'>Best Products,Best Price </span>         </h1>         	</header> 	<div name='autofillForm'> 		<div id='autoRow'> 			<table id='AutoCompleteTable' class='gridTable'></table> 		</div> 	</div> 	<div id='topNavigation'>     <nav>     	<ul>         			<li class=''><a href='OrderServlet'>Order Search </a></li> 			<li class=''><a href='AddReview.html'>Add Reviews </a></li> 			<li class=''><a href='ViewReview'>View Reviews </a></li> 			<li class=''><a href='Trending'>Trending</a></li> 			<li class=''><a href='LogoutServlet'>Logout</a></li> 		</ul>     </nav> </div>	 <form action='BeforeCheckoutServlet' method ='post'><table><tr><td><p>"+name+"</p></td></tr> <tr><th>Productssss</th> <th>Brand Name</th> <th>Condition</th> <th>Price</th> </tr>";
			for(Map.Entry<String, Products> prodMap:entries){
				//System.out.println("FR loop");
				Products product=prodMap.getValue();
				if(isSelectedValue(product.getId(),prodList)){
				System.out.println("console #"+ product.getName() +":");
            	System.out.println("\t\t retailer: " + product.getImage());
            	System.out.println("\t\t name: " + product.getPrice());
            	value = value+product.getPrice();
            	System.out.println(product.getCondition());
            	System.out.println(product.getImage());
            	System.out.println(product.getId());
            	prodIdList.add(product.getId());
            	html=html+"<tr> <td><img src='"+product.getImage()+"' style='width:100px;height:100px;'></td> <td>"+product.getName()+"</td> <td>"+product.getCondition()+"</td> <td>"+product.getPrice()+"</td> <td><button type='button' name='removeProduct'><a href='RemoveCartServlet?productId="+product.getId()+"'>Remove</a></button></td> </tr>";
            	bw.append("\n"+name+","+product.getId()+","+product.getName()+","+product.getPrice());
				}
			}
			bw.close();
			html=html+"<tr> <td></td> <td></td> <td>Grand Total : </td> <td>"+value+"</td> </tr>";
			session.setAttribute("value", value);
			
			
			html=html+"</table><button type='submit'style='float: right;width: 300px;height: 50px;background-color: green;' > Proceed to payment</button></form> <body> </html>";
			
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
		
		//request.getRequestDispatcher("1_login.html").include(request, response); 
		
			//String[] prodList = request.getParameterValues("checkbox");
			
			System.out.println("Inside mt");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
	
			HttpSession session = request.getSession();
			String name = (String) session.getAttribute("name");
			
			List<String> prodIdList=(List<String>)session.getAttribute("prodIdList");
			System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLL:"+prodIdList.size());
			String[] prodList=new String[11];
			for(int i=0;i<prodIdList.size();i++){
				System.out.println("TTTTTTTTT"+prodIdList.get(i));
				prodList[i]=prodIdList.get(i);
			}
				
			Products prod=new Products();
			ReadProducts r =new ReadProducts(getServletContext().getRealPath("ProductCatalog.xml"));
			Map<String,Products> productMapPrint=r.printPretty();
			System.out.println("IIIIIIIIIIIIIIIIIIIIIIIII:"+productMapPrint.size());
			Set<Map.Entry<String, Products>> entries=productMapPrint.entrySet();
			
			int value = 0;
			
			String html="<!doctype html> <html> <head> <title> Best Deals </title> <link rel='stylesheet' href='styles.css' type='text/css' /> <style> table {     border-collapse: collapse;     width: 100%; }  th, td {     text-align: left;     padding: 8px; }  tr:nth-child(even){background-color: #f2f2f2}  th {     background-color: #4CAF50;     color: white; } </style> </head>  <body> <header>     	<h1 style='background:#1AB188'><image src='BestDeal.png' alt='BestDeal' style='width:200px;height:100px'/><span></br><p style='font-size:20px'>Best Products,Best Price </span>         </h1>         	</header> 	<div name='autofillForm'> 		<div id='autoRow'> 			<table id='AutoCompleteTable' class='gridTable'></table> 		</div> 	</div> 	<div id='topNavigation'>     <nav>     	<ul>         			<li class=''><a href='OrderServlet'>Order Search </a></li> 			<li class=''><a href='AddReview.html'>Add Reviews </a></li> 			<li class=''><a href='ViewReview'>View Reviews </a></li> 			<li class=''><a href='Trending'>Trending</a></li> 			<li class=''><a href='LogoutServlet'>Logout</a></li> 		</ul>     </nav> </div>	 <form action='BeforeCheckoutServlet' method ='post'><table><tr><td><p>"+name+"</p></td></tr> <tr><th>Productssss</th> <th>Brand Name</th> <th>Condition</th> <th>Price</th> </tr>";
			//String html="<!doctype html> <html> <head> <title> Best Deals </title> <style> table {     border-collapse: collapse;     width: 100%; }  th, td {     text-align: left;     padding: 8px; }  tr:nth-child(even){background-color: #f2f2f2}  th {     background-color: #4CAF50;     color: white; } </style> </head>  <body> <form action='BeforeCheckoutServlet' method ='post'><table><tr><td><p>"+name+"</p></td></tr> <tr><th>Productssss</th> <th>Brand Name</th> <th>Condition</th> <th>Price</th> </tr>";
			for(Map.Entry<String, Products> prodMap:entries){
				Products product=prodMap.getValue();
				System.out.println("AAAA"+product.getName());
				
				if(isSelectedValue(product.getId(),prodList)){
	            	value = value+product.getPrice();
	            	prodIdList.add(product.getId());
	            	html=html+"<tr> <td><img src='"+product.getImage()+"' style='width:100px;height:100px;'></td> <td>"+product.getName()+"</td> <td>"+product.getCondition()+"</td> <td>"+product.getPrice()+"</td> <td><button type='button' name='removeProduct'><a href='RemoveCartServlet?productId="+product.getId()+"'>Remove</a></button></td> </tr>";
				}
				
			}
			html=html+"<tr> <td></td> <td></td> <td>Grand Total : </td> <td>"+value+"</td> </tr>";
			session.setAttribute("value", value);
			
			html=html+"</table><button type='submit'style='float: right;width: 300px;height: 50px;background-color: green;' > Proceed to payment</button></form> </body> <script type='text/javascript'	src='./AutoComplete.js'></script> </html>";
			
			//session.setAttribute("prodIdList", prodIdList);
			out.println(html);
			
		out.close();
		
	}

}
