package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.bean.Products;
import persistance.dao.ProductDao;

public class DealMatchesUtility extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDao p=new ProductDao();
		
		HashMap<String, Products> m=new HashMap();
		m=p.getAllProducts();
		PrintWriter out = response.getWriter();
		Set<Map.Entry<String, Products>> entries=m.entrySet();
		
		File file =new File(getServletContext().getRealPath("DealMatches.txt"));
	    Scanner in = null;
	    //in = new Scanner(file);
	    
	    String html="<!doctype html> <html> <head> <title> Best Deals </title><link rel='stylesheet' href='styles.css' type='text/css' /> <style> table {     border-collapse: collapse;     width: 100%; }  th, td {     text-align: left;     padding: 8px; }  tr:nth-child(even){background-color: #f2f2f2}  th {     background-color: #4CAF50;     color: white; } </style> </head>  <body> <h3 style='background:#1AB188'> <img src='BestDeal.png' alt='BestDeal' style='width:200px;height:100px'></h3><div name='autofillForm' id='autofillForm'><input type='text' value='' id='searchAreaText' name='searchAreaText' oninput='doCompletion()' placeholder='Search for products'/><div id='autoRow'><table id='AutoCompleteTable' class='gridTable'></table></div></div><div id='topNavigation'>     <nav>     	<ul>         			<li class=''><a href='OrderServlet'>Order Search </a></li> 			<li class=''><a href='AddReview.html'>Add Reviews </a></li> 			<li class=''><a href='ViewReview'>View Reviews </a></li> 			<li class=''><a href='Trending'>Trending</a></li> 	<li class=''><a href='DealMatchesUtility'>DealMatches</a></li>		<li class=''><a href='LogoutServlet'>Logout</a></li> 		</ul>     </nav> </div><div id='formDiv'><form action='ProductServlet' method = 'post'><table> <tr><th>Products</th> <th>Product Name</th> <th>Price</th> </tr>";
	    int countFlag=0;
	    String rowhtml="";
	    String tweetHtml="";
		for(Map.Entry<String, Products> prodMap:entries){
			
			Products product=prodMap.getValue();
			//System.out.println("DealMatches"+ product.getName() +":");
			
			  try {
			        in = new Scanner(file);
			        
				        while(in.hasNext())
				        {
				            String line=in.nextLine();
					            if(line.contains(product.getName())){
					            	countFlag++;
					            	if(countFlag<=2){	
					                System.out.println(line);
					                tweetHtml=tweetHtml+"</br>"+line;
					                rowhtml=rowhtml+"<tr> <td><img src='"+product.getImage()+"' style='width:100px;height:100px;'></td> <td>"+product.getName()+"</td> <td>"+product.getPrice()+"</td> </tr>";
					            	}
					            }
				        } 
		    } catch (Exception e) {
		    		e.printStackTrace();
		    }
		}
		html=html+tweetHtml+rowhtml+"</table></body><script type='text/javascript'	src='./AutoComplete.js'></script>";
		out.println(html);
	}
}