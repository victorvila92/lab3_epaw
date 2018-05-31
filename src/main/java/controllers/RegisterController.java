package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import models.BeanUser;
import utils.HtmlUtils;
import utils.UserUtils;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   BeanUser user = new BeanUser();
	   RequestDispatcher dispatcher = null;

	   try {
		
		   BeanUtils.populate(user, request.getParameterMap());

		   if (user.isComplete()) {
			   UserUtils.insertUser(user.getUser(),user.getMail(),user.getPassword());
			   System.out.println("User: " + user.getUser() + " inserted.");
			   response.getWriter().append(HtmlUtils.buildResponseForm(UserUtils.getUsers()));
		   
		   } 
		   else {
			   request.setAttribute("user",user);
			   dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
			   dispatcher.forward(request, response);
		   
		   }
	   
	   } catch (Exception exception) {
		   if(exception.getMessage().equals("User already in DB.")){
				int[] error = {1,0};
				user.setError(error);
				request.setAttribute("user",user);
				if(dispatcher != null){
					dispatcher.forward(request, response);
				}
			}
			else if(exception.getMessage().equals("Mail already in use.")) {
				int[] error = {1,1};
				user.setError(error);
				request.setAttribute("user",user);
				if(dispatcher != null){
					dispatcher.forward(request, response);
				}
			}
			exception.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	   }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
