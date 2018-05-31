package controllers;


import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;

import encryption.encryptionUtils;
import models.BeanLogin;
import utils.UserUtils;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private encryptionUtils md5 = new encryptionUtils();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		BeanLogin login = new BeanLogin();
		Boolean cookieFound = false;
	    try {

	    	BeanUtils.populate(login, request.getParameterMap());

			Cookie[] rememberCookieList = request.getCookies();

			if (rememberCookieList != null && rememberCookieList.length > 0){
				for(Cookie rememberCookie : rememberCookieList){
					String name = rememberCookie.getName();
					String value = rememberCookie.getValue();

					if(name.equals("user")) {
						cookieFound = true;

						System.out.println("Welcome Again!");
						Cookie cookie = new Cookie("user", value);
						cookie.setHttpOnly(true);
						cookie.setMaxAge(50);
						response.addCookie(cookie);

						RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginDone.jsp");
						dispatcher.forward(request, response);
						break;
					}
				}
			}

			if(!cookieFound){
				if (login.isComplete()) {
					if (UserUtils.checkUsernameAndPassword(login.getUser(), md5.encrypt(login.getPassword())).next()) {

						HttpSession session = request.getSession();
						session.setAttribute("user", login.getUser());
						session.setAttribute("password", login.getPassword());

						Cookie cookie = new Cookie("user", login.getUser());
						response.addCookie(cookie);

						RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginDone.jsp");
						dispatcher.forward(request, response);

					} else {
						request.getRequestDispatcher("ViewLoginForm.jsp").include(request, response);
					}
				}else {
					request.setAttribute("login",login);
					RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
					dispatcher.forward(request, response);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("DOPOST HERE.");
		doGet(request, response);
	}

}
