<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <title>login</title>
 
  
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

<br><br><br><br><br><br>

<div class="container" id="wrap">
		
		 <div class="row">
        <div class="col-md-6 col-md-offset-3">
            
              
            
            <form action="login" method="post">
					    		
					    		
					    		<c:if test="${param.error != null}">
	                                <div class="alert alert-danger">
	                                    <p>Invalid Username and password.</p>
	                                </div>
	                            </c:if>
	                            <c:if test="${param.logout != null}">
	                                <div class="alert alert-success">
	                                    <p>You have been logged out successfully.</p>
	                                </div>
	                            </c:if>
            
            <legend>LOGIN</legend>
                    
                    <div class="row">
                        <div class="col-xs-6 col-md-6">
                            <input path="username" type="text" name="username" class="form-control input-lg" placeholder="User Name"  />
                            </div>
                        <div class="col-xs-6 col-md-6">
                            <input path="password" type="password" name="password" value="" class="form-control input-lg" placeholder="Password"  />
                            </div>
                            
                    </div>
                    
                   
                    			  <br />
          
                    <button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">
                        Login my account</button>
                        <br><br>
            </form>          
          </div>
</div>            
</div>

</body>
</html>