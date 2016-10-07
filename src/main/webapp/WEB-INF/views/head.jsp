<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover:not(.active) {
    background-color: green;
}

</style>    

</head>
</html>
    <body>
    <navbar class="navbar navbar-fixed-top">
  <div class="container-fluid">
    
    
    <div class="collapse navbar-collapse" id="myNavbar">
       <ul class="nav navbar-nav navbar-right">
       <li class="dropdown">
      <li><a href="${pageContext.request.contextPath}/profile" class="btn ">Home</a></li>
  <%--       <li><a href="${pageContext.request.contextPath}/signup" class="btn ">Signup</a></li>
        <li><a href="${pageContext.request.contextPath}/loginpage" class="btn" >LogIn</a></li>
   --%>
  	<c:choose>
		<c:when test="${not empty pageContext.request.userPrincipal}">
			<li><a href="${pageContext.request.contextPath}/profile">${pageContext.request.userPrincipal.name}</a></li>
			<li><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>

		</c:when>

		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/signup"><span
					class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			<li><a href="${pageContext.request.contextPath}/loginpage"><span
					class="glyphicon glyphicon-log-in"></span> Login</a></li>
		</c:otherwise>
	</c:choose>
  
      
      </ul>
      </div>
  </div>
  
</navbar>

<br><br><br>
</body>
</html>