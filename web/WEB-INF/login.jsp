<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.html"/>
<h1>Remember Me Login Page</h1>
<form method="post" action="login">
    <input type="text" name="user" value="${rememberUsername}"> <br>
    <input type="password" name="pass"> <br>
    <input type="submit" value="Login"> <br>
    <input type="checkbox" name="remember" ${rememberUsername!=null?"checked":""}> Remember Me? <br>
</form>
${message}
<c:import url="footer.html"/>
