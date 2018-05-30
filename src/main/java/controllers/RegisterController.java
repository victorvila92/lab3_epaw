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

	   //System.out.println("RegisterController.");
	   BeanUser user = new BeanUser();


	   RequestDispatcher dispatcher = null;
	   

	   
	   
	   try {
		
		   BeanUtils.populate(user, request.getParameterMap());
	
		   //System.out.println("USER: " + user.getUser());
		   //System.out.println("USERPASSWORD: " + user.getPassword());
		   //System.out.println("REQUEST: " + request.getParameterMap());
		   
		   
		   if (user.isComplete()) {
			   /*
			    * 
			    *  System.out.println("INSERTING into DB");
			   UserService.insertUser(user.getUser(),user.getMail(),user.getPassword());
			   response.getWriter().append(buildResponseForm(UserService.getUsers()));
			    */
			   //System.out.println("INSERTING into DB");
			   UserUtils.insertUser(user.getUser(),user.getMail(),user.getPassword());
			   response.getWriter().append(HtmlUtils.buildResponseForm(UserUtils.getUsers()));

			   
			   //dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
			   //dispatcher.forward(request, response);
		   
		   } 
		   else {
		
			   request.setAttribute("user",user);
			   dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
			   dispatcher.forward(request, response);
		   
		   }
	   
	   } catch (Exception exception) {
		   if(exception.getMessage() == "User already in DB."){
				int[] error = {1,0};
				user.setError(error);
				request.setAttribute("user",user);
				if(dispatcher != null){
					dispatcher.forward(request, response);
				}
			}
			else if(exception.getMessage() == "Mail already in use.") {
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
