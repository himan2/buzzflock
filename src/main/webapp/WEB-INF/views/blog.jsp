<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Blogs</title>
<c:import url="head-meta.jsp"></c:import>
</head>
<script>
{
	var myApp = angular.module('myApp',[]);
	
	myApp.controller("abc",function($scope)
		{
		
			$scope.data=${data};	
			
		})
};	
</script>
<body ng-app="myApp" ng-controller="abc">
<c:import url="head.jsp"></c:import>

<br><br><br><br><br><br><br><br>

<div class="container">
	<div>
		<a href="${pageContext.request.contextPath}/addblog/" type="button" class="btn-center btn-success btn pull-right" align="right">Add Blog</a>
	</div>	
<table class="table ">
			<thead>
				<tr>
					<th>Image</th>
					<th>Topic Name</th>
					<th>Description</th>
					<th>Posted On</th>
					<th>Button</th>
				</tr>
			</thead>
			
			<tbody>
				<tr ng-repeat="x in data">
					<td><img alt="No Image" ng-src="${pageContext.request.contextPath}/{{x.BlogImage}}" width="100px" height="100px"></td>
					<td>{{x.Topicname}}{{x.OwnerID}}</td>
					<td>{{x.Description}}</td>
					<td>{{x.Dateandtime}}</td>
					<td>	<div><a type="button" href="${pageContext.request.contextPath}/viewblog/{{x.OwnerID}}" class="btn btn-success ">VIEW</a></div>
							<div><a href="${pageContext.request.contextPath}/update/" class="btn btn-danger">UPDATE</a></div>
							<div><a href="${pageContext.request.contextPath}/delete/" class="btn btn-danger">DELETE</a></div>
					</td>
				</tr>
			</tbody>
</table>	

</div>

</body>
</html> 