/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bi;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author india
 */
public class Configurations {
    public static String projectTitle = "Blood-Infinity";
    
    public static String getDbDriver() {
        return "com.mysql.jdbc.Driver";
    }

    public static String getDbUser() {
        return "root";
    }

    public static String getDbPassword() {
        return "123456";
    }

    public static String getDbUrl() {
        return "jdbc:mysql://localhost/bdb";
    }
    
    public static String getMyIp() {
        InetAddress ipa;
        String ip = "";
        try {

            ipa = InetAddress.getLocalHost();
            ip = ipa.getHostAddress();
            //System.out.println("Current IP address : " + ip);

        } catch (UnknownHostException e) {
            return e.toString();
        }        
        return ip;
    }

    public static String getUserAvail(String username) throws Exception {
        JDBC dbfunc = new JDBC();
        dbfunc.createConnection();

        String query = "select * from donations where username='" + username.trim() + "' and period_diff(date_format(now(), '%Y%m'), date_format(donatedDate, '%Y%m'))<3 order by donatedDate desc";
        System.out.println(query);

        ResultSet rsett = dbfunc.queryRecord(query);
        boolean found = rsett.next();        
        
        if (found) {             
            return "NO";
        }else{
            return "YES";
        }
    }
}
