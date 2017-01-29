package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistance.bean.Products;

public class AllProductsPageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Products> prodList;
		HttpSession session = request.getSession();
		Map<String,Products> productMapPrint = (Map<String,Products>) session.getAttribute("prodMap");
		Set<Map.Entry<String, Products>> entries=productMapPrint.entrySet();
		PrintWriter out = response.getWriter();
		
		String html="<!doctype html> <html> <head> <title> Best Deals </title><link rel='stylesheet' href='styles.css' type='text/css' /> <style> table {     border-collapse: collapse;     width: 100%; }  th, td {     text-align: left;     padding: 8px; }  tr:nth-child(even){background-color: #f2f2f2}  th {     background-color: #4CAF50;     color: white; } </style> </head>  <body> <h3 style='background:#1AB188'> <img src='BestDeal.png' alt='BestDeal' style='width:200px;height:100px'></h3><div name='autofillForm' id='autofillForm'><input type='text' value='' id='searchAreaText' name='searchAreaText' oninput='doCompletion()' placeholder='Search for products'/><div id='autoRow'><table id='AutoCompleteTable' class='gridTable'></table></div></div><div id='formDiv'><form action='ProductServlet' method = 'post'><table> <tr><th>Products</th> <th>Brand Name</th> <th>Condition</th> <th>Description</th> <th>Price</th> </tr>";
		for(Map.Entry<String, Products> prodMap:entries){
			
			System.out.println("FR loop");
			Products product=prodMap.getValue();
			//p.addProduct(product);
			System.out.println("console #"+ product.getName() +":");
        	System.out.println("\t\t retailer: " + product.getImage());
        	System.out.println("\t\t name: " + product.getPrice());
        	System.out.println(product.getCondition());
        	System.out.println(product.getImage());
        	System.out.println(product.getId());
        	html=html+"<tr> <td><img src='"+product.getImage()+"' style='width:100px;height:100px;'></td> <td>"+product.getName()+"</td> <td>"+product.getCondition()+"</td> <td>"+product.getPrice()+"</td> <td><input type='checkbox' name='checkbox' value='"+product.getId()+"'></input></td> </tr>";
        	
		}
		
		session.setAttribute("prodMap", productMapPrint);
		html=html+"</table><button type='submit' name='commit' id='commit' style='float: right;width: 300px;height: 50px;background-color: green;'> Add to cart</button></form></div><body> <script type='text/javascript'	src='./AutoComplete.js'></script></html>";
		out.println(html);
	}

}
