<%-- 
    Document   : mobBloodInfo
    Created on : Oct 18, 2015, 12:36:14 PM
    Author     : india
--%>

<%@page import="bi.Configurations"%>
<%@page import="bi.JDBC"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=Configurations.projectTitle%></title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href="css/tooplate_style.css" rel="stylesheet" type="text/css" />

        <link rel="stylesheet" href="css/coda-slider.css" type="text/css" charset="utf-8" />

        <script src="js/jquery-1.2.6.js" type="text/javascript"></script>
        <script src="js/jquery.scrollTo-1.3.3.js" type="text/javascript"></script>
        <script src="js/jquery.localscroll-1.2.5.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/jquery.serialScroll-1.2.1.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/coda-slider.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/jquery.easing.1.3.js" type="text/javascript" charset="utf-8"></script>

    </head>
    <body>

        <div id="slider">
            <div id="tooplate_wrapper">
                <div>
<%

    try {     
        JDBC dbfunc = new JDBC();
        dbfunc.createConnection();
        
        String query = "select * from feeds";
        System.out.println("---"+query);
        
        ResultSet rset = dbfunc.queryRecord(query);
        ResultSetMetaData rsmd = rset.getMetaData();
        int numColumns = rsmd.getColumnCount();

        String resultHtml = "";

        resultHtml += "<font color='black'><table border=1><caption><h3>User FeedBacks</font></h3></caption><thead>";
        resultHtml += "<tr>";

        for (int i = 1; i <= numColumns-1; i++) {
                resultHtml += "<div align=\"center\"><th scope=\"row\">" + rsmd.getColumnName(i).toUpperCase() + "</th></div>";            
        }
        resultHtml += "</tr></thead><tbody>";

        boolean found = rset.next();
        //out.println("<br><b>Sql Result</b>+"+query+"==>"+found);
        if (found) {
            rset.last();
            int count = rset.getRow();
            int row = 0;
            String line = "";
            rset.beforeFirst();
            while (rset.next()) {
                row++;                
                    resultHtml += "<tr>";                    
                    for (int i = 1; i <= numColumns-1; i++) {                             
                                resultHtml += "<div align=\"center\"><td scope=\"row\" >" + rset.getString(i) + "</td></div>";                                                                            
                    }
                    resultHtml += "</tr>";
                 
            }
        }
        //System.out.println(resultHtml);
        out.println(resultHtml+"</table></font>");
    } catch (Exception ex) {
        out.println(ex.toString());
    }

%>

                </div>

                <div class="cleaner"></div>
            </div>
        </div> <!-- end of content -->

        <div id="footer">
        </div>

        </div>
    </body>
</html>