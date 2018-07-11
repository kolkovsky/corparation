<%@ page import="java.sql.ResultSet" %>
<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: Stanislav2619
  Date: 22.06.2018
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <title>Welcome!</title>
</head>
<body>

<%!
    String welcomeFname;
    String welcomeLname;
    HashSet<User>  userHashSet;
    User user;
%>

<%
    if(session.getAttribute("fname") == null &&
            session.getAttribute("lname")==null &&
            session.getAttribute("password")==null){
        response.sendRedirect("/loginManager.html");
    }else {
        welcomeFname = (String) session.getAttribute("fname");
        welcomeLname = (String) session.getAttribute("lname");
    }
%>

<h2>We are glad to see <%=welcomeFname%> <%=welcomeLname%></h2>
<%
    userHashSet =(HashSet<User>) session.getAttribute("hashset");
    Iterator<User> userIterator = userHashSet.iterator();

    while(userIterator.hasNext()){
        user = userIterator.next();
        if(user.getDepartment().equals("director")){
            continue;
        }else{
%>
<p><%=user.toString()%></p><br>
<%
        }
    }
%>

<form action="/logout" method="post">
    <input type="submit" value="Logout">
</form>

</body>
</html>
