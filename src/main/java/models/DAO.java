package models;

import java.sql.*;

public class DAO {

    private Connection connection;
    private Statement statement;
    private static final String DB_USER_ROOT = "root";
    private static final String DB_ROOT_PSW = "1020";


    public DAO() throws Exception{
    	
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/ts1?" +
                        "user=" + DB_USER_ROOT + "&password="+ DB_ROOT_PSW);

        statement = connection.createStatement();
    }

    public ResultSet executeSQL(String query) throws Exception{
        return statement.executeQuery(query);
    }

    public void execute(String query) throws Exception{
        statement.executeUpdate(query);
    }

    public void disconnectDb() throws Exception{
        statement.close();
        connection.close();
    }
}