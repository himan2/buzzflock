<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="head-meta.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


	
<script type="text/javascript">
{
var  myapp = angular.module('myapp' , []);
myapp.controller("abc",function($scope)
		{
			$scope.mydata=${mydata};
			console.log($scope.mydata);	
		})
};
</script>


<body ng-app="myapp" ng-controller="abc">
<c:import url="head.jsp"></c:import>

		<table class="table " ng-repeat="x in mydata">
			<tbody  >
			<tr>
				<td> <br>
					 <img id="profileImage" ng-src="{{x.ProfileImage}}"height=" 150px" width="200px" align="center">	
				</td>
			</tr>
			<tr>
				<td>User Name:</td>
				<td>
							<label>{{x.ProfileName}}</label>
				</td>
			</tr>
			
		
			<tr>
				<td>Gender:</td>
				<td>
					<label>{{x.ProfileGender}}</label>
				</td>
			</tr>
			
			
				
 			<tr>
				<td>Email:</td>
				<td>
							<label>{{x.ProfileEmail}}</label>
							</td>
			</tr>
 					
			<tr>
				<td>Address:</td>
				<td><label ng-if="!edit">{{x.ProfileAddress}}</label>
				</td>
			</tr>
			<tr>
				<td>Contact No:</td>
				<td><label>{{x.ProfilePhone}}</label>
				</td>							
			</tr>
				
				
			</tbody>
	 
	 </table>
	</body>
 </html>