/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package murach.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class SQLUtil {

    public static String getHtmlTable(ResultSet result) throws SQLException {
       StringBuilder string = new StringBuilder();
       ResultSetMetaData meta = result.getMetaData();
       int columnCount = meta.getColumnCount();
       
       
       string.append("<table border='1'>");
       string.append("<tr>");
       for(int i=1;i<=columnCount;i++){
           string.append("<th>");
           string.append(meta.getColumnName(i));
           string.append("</th>");

       }

       while(result.next()){
                  string.append("<tr>");
           string.append("<tr>");
           for(int i=1;i<=columnCount;i++){
               string.append("<td>");
               string.append(result.getString(i));
               string.append("</td>");
           }
                  string.append("</tr>");
       }
       string.append("</table>");
        return string.toString();
       
    }
    
}
