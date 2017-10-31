<%-- 
    Document   : mobFeedBack
    Created on : Oct 17, 2015, 3:44:28 PM
    Author     : india
--%>

<%@page import="java.util.Map"%>
<%@page import="bi.JDBC"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>

<%

    try {     
        String un = request.getParameter("un");
        String feeds = request.getParameter("feeds");        

        JDBC dbfunc = new JDBC();
        dbfunc.createConnection();

        String query = "INSERT INTO feeds("                
                + "  `comment`,\n"        
                + "  `username`,\n"        
                + "  `commentDate`\n"
                + ") VALUES ('" + feeds.trim() + "'"                
                + ",'"+un+"'"
                + ",now()"
                + ")";

        System.out.println("Query : " + query);

        if (dbfunc.updateRecord(query)) {
            out.print("YES$$$Feedback Success");
        } else {
            out.print("NO$$$Feedback Failed");
        }

        dbfunc.closeConnection();

    } catch (Exception ex) {
        out.println("NO$$$Exception:" + ex.toString());
        System.out.println("-------" + ex.toString());
    }

%>


