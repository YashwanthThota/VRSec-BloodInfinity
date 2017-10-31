<%-- 
    Document   : mobLogin
    Created on : Oct 18, 2015, 9:28:30 AM
    Author     : india
--%>

<%@page import="bi.Configurations"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="bi.JDBC"%>
<%
    
    try {
        String username = request.getParameter("un");
        String password = request.getParameter("pw");
        
        JDBC dbfunc = new JDBC();
        dbfunc.createConnection();

        String query = "select * from users where username='" + username.trim() + "' and password='" + password.trim() + "'";
        System.out.println(query);

        ResultSet rsett = dbfunc.queryRecord(query);
        boolean found = rsett.next();        
        
        if (found) {             
             String name = rsett.getString("name");
             String area = rsett.getString("area");                
             String pin = rsett.getString("pin");                
             String email = rsett.getString("email");
             String mobile = rsett.getString("mob");
             String age = rsett.getString("age");
             String bloodType = rsett.getString("bloodtype");
             String sex = rsett.getString("sex");
             
             String avail = Configurations.getUserAvail(username);
             
             String result = "Name:"+name+"\nArea:"+area+"\nPin:"+pin+"\nEmail:"+email+"\nMobile"+mobile
                             +"\nAge:"+age+"\nBloodType:"+bloodType+"\nGender:"+sex+"\nAvailability:"+avail;
             
             out.println("YES$$$"+result);
             System.out.println("YES$$$"+result);
             
        } else {
            out.println("NO$$$Invalid User Details");
        }
        
        dbfunc.closeConnection();     
        
    } catch (Exception ex) {
        out.println("NO$$$"+ex.toString());        
        System.out.println("NO$$$"+ex.toString());
    }
%>