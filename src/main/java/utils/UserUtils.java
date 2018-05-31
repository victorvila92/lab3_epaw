package utils;

import models.DAO;

import java.sql.ResultSet;

public class UserUtils {
	
	private static final String USER_EMAIL_IN_DB = "User or email already in DB";
	private static ResultSet result;
	
    public static ResultSet getUsers() throws Exception {
        DAO dao = new DAO();
        result = dao.executeSQL(Querys.getUsersQuery());
        return result;
    }

    public static ResultSet getUser(String name) throws Exception {
        DAO dao = new DAO();
        result = dao.executeSQL(Querys.getUserQueryFromName(name));
        return result;
    }
    
    public static ResultSet checkMail(String mail) throws Exception {
        DAO dao = new DAO();
        result = dao.executeSQL(Querys.getUserQueryFromEmail(mail));
        return result;
    }
    
    public static ResultSet checkMailAndUsername(String mail, String username) throws Exception {
    	DAO dao = new DAO();
    	result = dao.executeSQL(Querys.getUserQueryFromEmailAndUsername(username, mail));
    	return result;
    }
    
    public static ResultSet checkUsernameAndPassword(String username, String password) throws Exception {
    	DAO dao = new DAO();
    	result = dao.executeSQL(Querys.getUserQueryFromUsernameAndPassword(username, password));
    	return result;
    }

    public static void insertUser(String username, String mail, String password) throws Exception {
		if(!checkMailAndUsername(mail, username).next()){
			DAO dao = new DAO();
   	     	dao.execute(Querys.insertUserQuery(username,mail,password));
		}else {
        	throw new Exception(USER_EMAIL_IN_DB);
    	}
    }
}