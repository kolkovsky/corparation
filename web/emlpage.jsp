<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page import="javax.swing.text.html.HTML" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashSet" %>
<%--
  Created by IntelliJ IDEA.
  User: Stanislav2619
  Date: 22.06.2018
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Welcome!</title>
    <link rel="stylesheet" type="text/css" href="emlpageStyle.css">
</head>
<body>


<%!
    String welcomeFname;
    String welcomeLname;
    HashSet<User>  userHashSet;
    User user;
    User worker;
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
        if(user.getFname().equals(welcomeFname)
                && user.getLname().equals(welcomeLname)){
            worker = user;
            break;
        }
    }
%>
<%
    if(worker!= null){
%>
<div class="grid-container">

    <div class="grid1">
        <img src="466667-200.png" >
    </div>

    <div class="grid2">
        <p>ID: <%=worker.getId()%></p> <br>
        <p>First Name: <%=worker.getFname()%></p><br>
        <p>Last Name: <%=worker.getLname()%></p><br>
        <p>Salary: <%=worker.getSalary()%></p><br>
        <p>Email: <%=worker.getEmail()%></p><br>
        <p>Status vacation: <% if(worker.getVacation()==0){ %>
            In vacation
            <%
        }else{%>
            Not vacation
            <%
        }%></p><br>
        <p></p>
    </div>

    <div class="grid3">

    </div>

    <div class="grid4">
        <p>Request of Vacation</p><br>
        <textarea name="text" style="width: 500px; height: 150px;" maxlength="1000" form="sendreq">
          Enter your request of vacation...
        </textarea>
        <form  id="sendreq" action="/update" method="post">
            <input type="submit" value="Send request">
        </form>
    </div>

</div>

<%
    session.setAttribute("worker",worker);
    }
%>


<form action="/logout" method="post">
    <input type="submit" value="Logout">
</form>


</body>
</html>
