<%-- 
    Document   : mobRegistration
    Created on : Oct 17, 2015, 8:06:45 AM
    Author     : india
--%>

<%@page import="java.util.Map"%>
<%@page import="bi.JDBC"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>

<%

    try {

        Map<String, String[]> parameters = request.getParameterMap();
        for (String parameter : parameters.keySet()) {
            System.out.println(parameter+"---"+parameters.get(parameter).toString());            
        }

        String name = request.getParameter("name");
        String area = request.getParameter("area");
        String pin = request.getParameter("pin");
        String email = request.getParameter("email");
        String mob = request.getParameter("mob");
        String age = request.getParameter("age");
        String un = request.getParameter("un");
        String pw = request.getParameter("pw");
        String bloodType = request.getParameter("bt");
        
        if(bloodType.trim().contains("-")){            
        }else{
            bloodType = bloodType.trim()+"+";
        }
        
        String sex = request.getParameter("sex");

        JDBC dbfunc = new JDBC();
        dbfunc.createConnection();

        String query = "INSERT INTO users("
                + "  `username` ,\n"
                + "  `password`,\n"
                + "  `name`,\n"
                + "  `area`,\n"
                + "  `pin`,\n"
                + "  `email`,\n"
                + "  `mob`,\n"
                + "  `age`,\n"
                + "  `bloodtype`,\n"
                + "  `sex`\n"
                + ") VALUES ('" + un.trim() + "'"
                + ",'" + pw.trim() + "'"
                + ",'" + name.trim() + "'"
                + ",'" + area.trim() + "'"
                + ",'" + pin.trim() + "'"
                + ",'" + email.trim() + "'"
                + ",'" + mob.trim() + "'"
                + ",'" + age.trim() + "'"
                + ",'" + bloodType.trim() + "'"
                + ",'" + sex.trim() + "'"
                + ")";

        System.out.println("Query : " + query);

        if (dbfunc.updateRecord(query)) {
            out.print("YES$$$Registration Success");
        } else {
            out.print("NO$$$Registration Failed");
        }

        dbfunc.closeConnection();

    } catch (Exception ex) {
        out.println("NO$$$Exception:" + ex.toString());
        System.out.println("-------" + ex.toString());
    }

%>
