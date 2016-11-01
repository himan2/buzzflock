<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>forum</title>
<c:import url="head-meta.jsp"></c:import>
</head>
<script>
{
	var myApp = angular.module('myApp',[]);
	
	/*  myApp.factory('UserService',['$http','$q',function($http,$q)
	    {
		return
		{
			addForum: function(item)
			{
				return $http.post('http://localhost:9001/buzzflock/addForum/',item)
				.then(
					function(response)
					{
						console.log("");
						return response.data;
					},function(errResponse)
					{
						console.error("Error while sending data");
						return $q.reject(errResponse);
					}
					
					);
				}
		 */
	 
	
		myApp.factory('UserService', ['$http', '$q', function($http, $q){
		    return {
		    	addForum: function(item)
				{
					return $http.post('http://localhost:9001/buzzflock/addForum/',item)
					.then(
						function(response)
						{
							console.log("");
							return response.data;
						},function(errResponse)
						{
							console.error("Error while sending data");
							return $q.reject(errResponse);
						}
						
						);
					}
			
 	    
		 };}]);
		
		
		
		
	
	myApp.controller("abc",['$scope','UserService', function($scope,$UserService)
		{
		
			$scope.data=${data};	
			$scope.check =function()
			{
				if($scope.currentUser=$scope.mydata.Username)
					{
						check:true;
						
					}
			}
			
			
			$scope.addForum=function(Topicname,Description)
			{
			$scope.data={"Topicname":Topicname , "Description":Description}
			console.log(JSON.stringify($scope.data));
			$UserService.addForum(JSON.stringify($scope.data)).then
			(
			function(response)
			{
				console.log(response.status);
			}
			,
			function(errResponse)
			{
				console.log("error in up data");
			}
		)
				
				
			};
			
			
			
		}])
};	
</script>

 <body ng-app="myApp" ng-controller="abc">
<c:import url="head.jsp"></c:import>

<br><br><br><br><br><br><br><br>

<div class="container">




<!-- 
 <div>
  		<button type="button" class="btn btn-info btn-sm pull-left" data-toggle="modal" data-target="#myModal">Change Password</button>
  		<div class="modal fade" id="myModal" role="dialog">
    		<br><br><br><br><br><br><br>
    		<div class="modal-dialog">
      			
      			<div class="modal-content">
        			
        			<div style="color: black;" class="modal-header">
         					<button type="button" class="close" data-dismiss="modal">&times;</button>
          			 		<h4 class="modal-title">Add Forum</h4>
        			</div>
        
        <div class="modal-body">
			<div class="row">
 				<div>
 					<div class="col-xs-6">Topic Name</div>
 					<div class="col-xs-6">	
							<input type="text"  value={{Topicname}} ng-model="Topicname" placeholder="Topic Name" />
					</div> 
			</div>
			<br>
			<div>
				<div class="col-xs-6">Description</div>
 				<div class="col-xs-6">
						<input type="text" value={{Description}} ng-model="Description" placeholder="Description" />
				</div>
  			</div>
  		
  	<br>
  	
  	<div>
  		<div class="col-xs-6">Upload Image</div>
  			<div class="col-xs-6">
  					<label path="Image" for="productImage">Image:</label>
  							
  					<label class="form-control">
  							<span id="file_display1">Choose Image</span>
							<span style="position: relative;">
							<input path="productFile" ng-model="Topicimage" onchange="changeFileDisplay1();" style="opacity:0; "type="file"  class="form-control" id="imageFile1" />
							</span>
					</label> 
									
								<script type="text/javascript">
										function changeFileDisplay1() 
										{
											document.getElementById("file_display1").innerHTML = $('#imageFile1').val();;
										}
									</script>
					
  			</div>
  	</div>
 
	</div>
	</div>
  
  
  
  
  </div>
 
       
        <div class="modal-footer">
			<div>
				<button class="btn btn-success" ng-click="addForum(Topicname,Description);">Add Blog</button>
				     <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				     <label class="alert alert-text"></label>	        	
        	 </div>
</div>
</div>
 </div>
</div>
 -->
 
 <div>
		<a href="${pageContext.request.contextPath}/addforum/" type="button" class="btn-center btn-success btn pull-right" align="right">Add Blog</a>
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
				<tr ng-repeat="x in data" ng-if="check">
					<td><img alt="No Image" ng-src="${pageContext.request.contextPath}/{{x.ForumImage}}" width="100px" height="100px"></td>
					<td><a href="${pageContext.request.contextPath}/forumcontent/{{x.ForumID}}"/>{{x.Topicname}}</td>
					<td>{{x.Description}}</td>
					<td>{{x.Dateandtime}}</td>
					<td><div><a type="button" href="${pageContext.request.contextPath}/viewforum/{{x.OwnerID}}" class="btn btn-success ">VIEW</a></div>
							<div><a href="${pageContext.request.contextPath}/updateforum/{{x.ForumID}}" class="btn btn-danger">UPDATE</a></div>
							<div><a href="${pageContext.request.contextPath}/delete/{{x.ForumID}}" class="btn btn-danger">DELETE</a></div>
				 </td>
				</tr>
			</tbody>
</table>	

</div>

</body>
</html> 