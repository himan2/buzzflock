<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Blogs</title>
<c:import url="head-meta.jsp"></c:import>
<style type="text/css">
body {
	background: url(${pageContext.request.contextPath}/resources/images/bk7.jpg) no-repeat center center fixed;
}
.container{
background-color: rgba(255,0,0, 0.5);
}
</style>
</head>
<script>
{
	var myApp = angular.module('myApp',[]);
	
	myApp.controller("abc",function($scope)
		{
		
			$scope.data=${data};	
			$scope.check =function()
			{
				if($scope.currentUser=$scope.mydata.Username)
					{
						check:true;
						
					}
			}
		})
};	
</script>
<body ng-app="myApp" ng-controller="abc">
<c:import url="head.jsp"></c:import>

<br><br><br>

<div class="container" >
	<br><br><div>
		<a href="${pageContext.request.contextPath}/addblog/" type="button" class="btn-center btn-success btn pull-right" align="right">Add Blog</a>
	</div>	<br><br><br>
<table class="table "  style="background-color: rgba(255,255,255,0.8);" >
			<thead>
				<tr>
					<th>Image</th>
					<th>Topic Name</th>
					<th>Description</th>
					<th>Posted On</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<tr ng-repeat="x in data" ng-if="check">
					<td><img alt="No Image" ng-src="${pageContext.request.contextPath}/{{x.BlogImage}}" width="100px" height="100px"></td>
					<td><a href="${pageContext.request.contextPath}/blogcontent/{{x.BlogID}}"/>{{x.Topicname}}</td>
					<td>{{x.Description}}</td>
					<td>{{x.Dateandtime}}</td>
					
					<td>	<div><a type="button" href="${pageContext.request.contextPath}/viewblog/{{x.OwnerID}}" class="btn  ">VIEW</a>
							<a href="${pageContext.request.contextPath}/updateblog/{{x.BlogID}}" class="btn ">UPDATE</a>
							<a href="${pageContext.request.contextPath}/delete/{{x.BlogID}}" class="btn ">DELETE</a></div>
					</td>
				</tr><br><br>
			</tbody>
</table>


</div>

</body>
</html> 