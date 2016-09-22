<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="head-meta.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<script >

	var myApp = angular.module('myApp', []);

	myApp.controller("abc", function($scope)
	{
		$scope.data = ${data};
		
		$scope.myerror = "";
		
		$scope.CheckValidFileType = function( inp )
		{
			console.log(inp);
			
			if( inp != ".jpg" )
				$scope.$apply( $scope.myerror = "error" );
			else
				$scope.$apply( $scope.myerror = "" );
			
			console.log($scope.myerror);
		}
		
		$("#ffub").click(function()
		{
			$("#ffu").trigger('click');
		});
				
		$("#ffu").on('change',function(e)
		{
			var filename = e.target.files[0].name;
			//alert( filename.substring( filename.indexOf('.') , filename.length ) );
			$scope.CheckValidFileType( filename.substring( filename.indexOf('.') , filename.length ) );
		});
	});
</script>


<body ng-app="myApp" ng-controller="abc">
profile page

<c:choose>
					<c:when test="${not empty pageContext.request.userPrincipal}">
						<li><a href="${pageContext.request.contextPath}/index">${pageContext.request.userPrincipal.name}</a></li>
						<li><a href="${pageContext.request.contextPath}/logout">Log
								Out</a></li>

					</c:when>

					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/signup"><span
								class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li><a href="${pageContext.request.contextPath}/loginpage"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</c:otherwise>
				</c:choose>




						
<table class="table ">

<tbody>

<button id="ffub" class="btm btn-link">Choose Image</button>

<input type="file" id="ffu" style="opacity: 0;"/>

<div class="text text-danger" ng-if=" myerror =='error' " >Invalid Image Type</div>

<tr>
<td>
<img id="profileImage" ng-src="{{data.ProfileImage}}"
 height=" 150px" width="200px">
</td>
</tr>

</tbody>
</table>

</body>

</html>