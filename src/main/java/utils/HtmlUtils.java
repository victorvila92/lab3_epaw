package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HtmlUtils {

    public HtmlUtils(){}

    public static String buildResponseForm(ResultSet result) throws SQLException {
        StringBuilder sb = new StringBuilder();

        sb.append(HtmlUtils.buildHeader("Current Users"));
        sb.append(HtmlUtils.buildTable());

        while (result.next()){
            Integer id = result.getInt("id");
            String name = result.getString("username");
            String mail = result.getString("mail");
            String password = null;
            if(result.getString("password") != null){
                password = result.getString("password");
            }
            String description = result.getString("description");
            Integer phoneNumber = result.getInt("phoneNumber");

            sb.append(HtmlUtils.insertRow(id, name, mail, password , description, phoneNumber));
        }
        sb.append(HtmlUtils.closeTable());
        return sb.toString();
    }

    private static String buildHeader(String title){

        return "<!DOCTYPE html>" +
                "<html>" +
                "<title>" + title + "</title>" +
                "<meta charset=UTF-8>";
    }


    private static String buildTable(){
        return  "    <style>\n" +
                "        table,\n" +
                "        th,\n" +
                "        td {\n" +
                "            border: 1px solid black;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "<body>\n" +
                "<table style=\"width:100%\">\n" +
                "    <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>Username</th>\n" +
                "        <th>Mail</th>\n" +
                "        <th>Password</th>\n" +
                "        <th>Description</th>\n" +
                "        <th>PhoneNumber</th>\n" +
                "    </tr>\n";
    }


    private static String insertRow(Integer id, String name, String mail, String password, String description, Integer phoneNumber){
        return "    <tr>\n" +
                "        <td>"+ id +"</td>\n" +
                "        <td>" + name + "</td>\n" +
                "        <td>" + mail + "</td>\n" +
                "        <td>" + password + "</td>\n" +
                "        <td> " + description + " </td>\n" +
                "        <td> " + phoneNumber + " </td>\n" +
                "    </tr>\n";
    }

    private static String closeTable(){
        return "</table>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}