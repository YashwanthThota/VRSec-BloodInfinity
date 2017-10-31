<%-- 
    Document   : mobDonationUpdate
    Created on : Oct 18, 2015, 11:24:39 AM
    Author     : india
--%>

<%@page import="java.util.Map"%>
<%@page import="bi.JDBC"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>

<%

    try {     
        String un = request.getParameter("un");
        String date = request.getParameter("date");        

        JDBC dbfunc = new JDBC();
        dbfunc.createConnection();

        String query = "INSERT INTO donations("                
                + "  `username`,\n"                
                + "  `donatedDate`\n"
                + ") VALUES ('" + un.trim() + "'"                
                + ",STR_TO_DATE('"+date+"', '%m-%d-%Y')"
                + ")";

        System.out.println("Query : " + query);

        if (dbfunc.updateRecord(query)) {
            out.print("YES$$$Donation Update Success");
        } else {
            out.print("NO$$$Donation Update Failed");
        }

        dbfunc.closeConnection();

    } catch (Exception ex) {
        out.println("NO$$$Exception:" + ex.toString());
        System.out.println("-------" + ex.toString());
    }

%>

