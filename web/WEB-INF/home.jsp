<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.html"/>
<h1>Home Page</h1>
<p>Hello, ${user.username}</p>
<a href="/?message=Logged Out">Logout</a>
<c:import url="footer.html"/>
