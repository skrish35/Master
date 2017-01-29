package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistance.bean.User;
import persistance.dao.UserDao;

public class SignUp extends HttpServlet {

	UserDao userDao;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");

		req.getRequestDispatcher("2_SignUp.html").include(req, resp);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("login");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailId = request.getParameter("emailId");
		String phoneNum = request.getParameter("phoneNum");
		String userType = "Customer";

		userDao = new UserDao();
		User user = userDao.getUserByUserName(userName);

		if ((user == null)||(user!=null && user.getUserName() == null)) {
			user = new User(firstName, lastName, userName, emailId, phoneNum, password, userType);
			userDao.addUser(user);
			request.getRequestDispatcher("1_login.html").include(request, response);
		} else {
			out.print("Sorry, username already exists");
			request.getRequestDispatcher("2_SignUp.html").include(request, response);
		}
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("login");
		if (name != null && (userDao.getUserByUserName(name)).getUserType().equals("SalesMan")) {
			request.getRequestDispatcher("2_SignUp.html").include(request, response);
		} else if(name != null) {
			request.getRequestDispatcher("1_login.html").include(request, response);
		}

	}

}
