<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title>buzzflock signup</title>

  
 <c:import url="head-meta.jsp" />
<style>

body {
   background: url(resources/images/Dark-wooden-website-background.jpg) no-repeat center center fixed; 
}
 #wrap{

background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #FFFFFF), color-stop(1, #D3D8E8));
/* Webkit (Chrome 11+) */ 
background-image: linear-gradient(to bottom, #FFFFFF 0%, 	#8B4513 100%);
}  
legend{
 color:black;
 font-size:25px;
 font-weight:bold;
 text-align: center;
}
.signup-btn {
  background: #79bc64;
  background-image: -webkit-linear-gradient(top, #79bc64, #578843);
 
  -webkit-border-radius: 4;
  border-radius: 4px;
  text-shadow: 0px 1px 0px #898a88;
  -webkit-box-shadow: 0px 0px 0px #a4e388;
  box-shadow: 0px 0px 0px #a4e388;
  font-family: Arial;
  color: #ffffff;
  font-size: 20px;
  padding: 10px 20px 10px 20px;
  border: solid #3b6e22  1px;
  text-decoration: none;
}

.signup-btn:hover {
  background: #79bc64;
  background-image: -webkit-linear-gradient(top, #79bc64, #5e7056);
  background-image: linear-gradient(to bottom, #79bc64, #5e7056);
  text-decoration: none;
}

.form .form-control { margin-bottom: 10px; }
@media (min-width:768px) {
 #home{
  margin-top:50px;
 }
 
}

</style>

</head>
<body>

<br>
<br><br><br><br><br><br>

<div class="container" id="wrap">
		
		<!--  -->
		<c:if test="${not empty passwordmismatch}">
		<br>
			<label class="alert alert-danger">Password Mismatch</label>
		<br>
		</c:if>

		<c:if test="${not empty useralreadyexists}">
		<br>
			<label class="alert alert-danger">Username Error</label>
		<br>
		</c:if>
		
		<c:if test="${not empty success}">
		<br>
			<label class="alert alert-success">User Created Successfully</label>
		<br>
		</c:if>
		<!--  -->
		
	  <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form:form action="${pageContext.request.contextPath}/insertuser" method="post" modelAttribute="newuser" accept-charset="utf-8" class="form">   
            <legend>Sign Up</legend>
                    <h4>It's free and always will be.</h4>
                    
                    <div class="row">
                        <div class="col-xs-6 col-md-6">
                            <form:input path="username" type="text" name="username" class="form-control input-lg" placeholder="User Name"  />
                            <form:errors path="username" class="text text-danger" />
                        </div>
                        <div class="col-xs-6 col-md-6">
                            <form:input path="phone" type="text" name="phone" value="" class="form-control input-lg" placeholder="Phone Number"  />
                            </div>
                            <form:errors path="phone" class="text text-danger" />
                    </div>
                    
                    <form:input path="email" type="text" name="email" value="" class="form-control input-lg" placeholder="Your Email"  />
                    <form:errors path="email" class="text text-danger"/>
                    
                    <form:input path="location" type="text" name="loaction" value="" class="form-control input-lg" placeholder="Your Location"  />
                    <form:errors path="location" class="text text-danger"/>
                    
                    <label>Gender : </label>  
                    <form:radiobutton path="gender" value="M"/>Male
                    <form:radiobutton path="gender" value="F"/>Female
                    
                    
                    
                    <form:input path="password" type="password" name="password" value="" class="form-control input-lg" placeholder="Password"  />
                    <form:errors path="password" class="text text-danger"/>
                    
                    <form:input path="cPassword" type="password" name="confirm_password" value="" class="form-control input-lg" placeholder="Confirm Password"  /> 
                   	<form:errors path="cPassword" class="text text-danger"/>
                    			  <br />
          
          
          
          
                    <button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">
                        Create my account</button>
                        <br><br>
            </form:form>          
          </div>
</div>            
</div>



</body>
</html>