<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search New Friend</title>

<c:import url="head-meta.jsp"></c:import>
</head>
<script type="text/javascript">
	var myApp = angular.module('myApp', []);
	
	myApp.factory('UserService', ['$http', '$q', function($http, $q){
		
	    return {
	    	
			fetchAllItems: function() 
			{
				return $http
						.post('http://localhost:9001/buzzflock/fetchAllItems/')
						.then(
						function(response) 
							{
								return response.data;
							},
						function(errResponse) 
							{
								console.error('Error while sending data');
								return $q.reject(errResponse);
							});
								
		}			
	
	    
	    ,
	    AddFriend: function(item){
            return $http.post('http://localhost:9001/buzzflock/AddFriend/', item)
                    .then(
                            function(response)
                            {
                                return response.data;
                            }, 
                            function(errResponse){
                                console.error('Error while updating User');
                                return $q.reject(errResponse);
                            }
                    );                    
		}
	    };
	 
	}]);
	
	
	myApp.controller("abc", ['$scope','UserService',function($scope, $UserService) 
			{
			$scope.data;
			$scope.currentUser = '${pageContext.request.userPrincipal.name}';
			$scope.FriendName;
			$scope.frequest;	
			$scope.ProfileID;
			
			$UserService.fetchAllItems().then(
					function(response) 
					{
						$scope.data = response;
						console.log($scope.data);
					}
			,
					function(errResponse)
					{
						console.error('Error while Sending Data.');
					});
		
	  $scope.AddFriend = function(ProfileID, ProfileName )
		{
		  
		  
			/* $scope.SwitchFlag(true);
			$scope.stateDisabled = true;
			 */
			 
			//$scope.frequest = { "currentUser" : currentUser , "FriendName" : FriendName };
			 $scope.frequest = {"ProfileID" : ProfileID ,"FriendID": ProfileName};
				console.log($scope.frequest);
			$UserService.AddFriend(JSON.stringify($scope.frequest))
         .then(
         		function(response)
         		{
         			console.log( response.status );
    				
         			if( response.status == "Updated" )
         			{
         				
         				console.log( response.status );
         			}
         			
         		/* 	$scope.SwitchFlag(false);
	    			$scope.stateDisabled = false;
	    		 */		
	    				
         		}
	            , 
	                function(errResponse)
	                {
	                	console.error('Error while Updating User.');
	                } 
     	 
     	 );
			
		};
		
			
			
		}
	
 	]);
</script>

<body ng-app="myApp" ng-controller="abc">
<c:import url="head.jsp"></c:import>
<br><br><br><br><br><br>
	<div class="container">
	
		<table class="table " ng-repeat="data in data">
	
			<tbody>
				<tr>
				
				<input type="hidden" value="{{ data.ProfileID}}" />
				<td>
				<img ng-src="{{data.ProfileImage}}" class="img-rounded" height=" 150px" width="200px" />
				</td>
				<td>UserName :{{data.ProfileName}}<br>Email ID : {{data.ProfileEmail}}<br>{{data.ProfileID}}</td>
				<td>
				<button type="button" class="btn btn-success" ng-click="AddFriend(data.ProfileID,data.ProfileName);">Add Friend</button>
				</td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>