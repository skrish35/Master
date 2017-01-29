package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoComplete extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet() of AutoComplete Servlet");
		
		String action=request.getParameter("action");
		String SearchAreaText=request.getParameter("searchId");
		
		System.out.println("Search text area is : "+SearchAreaText);
		System.out.println("Action is : "+action);
		try{
			StringBuffer sb=new StringBuffer();
			if(action.equals("complete"));
			{
				if(!SearchAreaText.equals("")){
					AjaxUtility a=new AjaxUtility();
					response.setContentType("application/xml");
					String responseData=a.readData(SearchAreaText).toString();
					System.out.println("RESPONSE DATA :"+responseData);
					response.getWriter().write("<Products>"+responseData+"</Products>");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}