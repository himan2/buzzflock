<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search New Friend</title>

<c:import url="head-meta.jsp"></c:import>

<style type="text/css">

body {
	background: url(resources/images/web.jpg) no-repeat center center fixed;
}

</style>

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
														console.log(response.data);
														//$scope.data = angular.copy( response );
														
													},
													function(errResponse) 
													{
														console.error('Error while sending data');
														return $q.reject(errResponse);
													});
								
			},
		AcceptRequest : function(item)
		{
			return $http.post('http://localhost:9001/buzzflock/AcceptRequest/',item)
				.then
					(
						function(response)
						{
							return response.data;
						},
						function(errResponse)
						{
							console.error('Error while sending data');
							return $q.reject(errResponse);
						}	
					);
		}
		,
	    AddFriend: function(item)
	    {
            					return $http.post('http://localhost:9001/buzzflock/AddFriend/', item)
                    						.then
                    						(
                            					function(response)
                            					{
                                					return response.data;
                            					}, 
                            						function(errResponse)
                            					{
                                					console.error('Error while updating User');
                                					return $q.reject(errResponse);
                            					}
                    						);
         }
	
	,
	 	Delete: function(item)
		{
								return $http.post('http://localhost:9001/buzzflock/Delete/', item)
									.then(
											function(response)
										{
											return response.data;
										},		
											function(errResponse)
										{
											return $q.reject(errResponse);
										}
										);
	
		}
	
	}
}]);
	
	
	myApp.controller("abc", ['$scope','UserService',function($scope, $UserService) 
			{
			$scope.data;
			$scope.currentUser = '${pageContext.request.userPrincipal.name}';
			$scope.FriendName;
			$scope.frequest;
			$scope.cfrequest;
			$scope.ProfileID;
			$scope.toggle = false;
			
$UserService.fetchAllItems().then
(
					function(response) 
					{
						$scope.data = response;
						console.log($scope.data);
					}
			,
					function(errResponse)
					{
						console.error('Error while Sending Data.');
					}
);
		
$scope.AddFriend = function(ProfileID, ProfileName )
{
			$scope.frequest = {"ProfileID" : ProfileID ,"FriendID": ProfileName};
		 	console.log($scope.frequest);
			$UserService.AddFriend(JSON.stringify($scope.frequest))
         		.then
         		(
         			function(response)
         				{
         				$scope.ProfileAssociation = response.ProfileAssociation;
         				console.log(response.ProfileAssociation);
            	/* 		
         				$scope.data.ProfileAssociation = angular.copy(response.ProfileAssociation);
         				console.log(data.ProfileAssociation);
            	 */		
         					
            	 if( response.status == "Updated" )
					{
					
						for( i = 0 ; i < $scope.data.length ; i++ )
						{
							if( $scope.data[i].ProfileID == response.ProfileID )
							{
								$scope.data[i].ProfileAssociation = response.ProfileAssociation;
								
								break;
							}
						}
						
						console.log( $scope.data )
					}
            				window.setTimeout(function()
            				{
            					$scope.$apply( $scope.ProfileAssociation = '' );
            				},3000);
         				}
	            , 
	                function(errResponse)
	                	{
	                		console.error('Error while Updating User.');
	                	}	 
					 );
			
};
			$scope.updateOverall = function()
			{
				$scope.overallValidationCheck = $scope.toggle;
			};
$scope.AcceptRequest = function(ProfileID , ProfileName)
		{
				$scope.frequest = {"ProfileID" : ProfileID , "FriendName":ProfileName}
				
				$UserService.AcceptRequest(JSON.stringify($scope.frequest))
					.then(
							function(response)
								{
								if(response.status =="Updated")
								{
								for( i = 0 ; i < $scope.data.length ; i++ )
         						{
         							if( $scope.data[i].ProfileID == response.ProfileID )
         							{
         								$scope.data[i].ProfileAssociation = response.ProfileAssociation;
         								break;
         							}
         						}
         		
									console.log(response.status);
								}
			        				window.setTimeout(function()
			        				{
			        					$scope.$apply( $scope.ProfileAssociation = '' );
			        				},3000);
									
								},
							function(errResponse)
								{
									console.error('Error while Updating Error');
								}
							);
		};
$scope.Delete = function(ProfileID, ProfileName )
{
	$scope.cfrequest = {"ProfileID" : ProfileID ,"FriendID": ProfileName};
	console.log($scope.cfrequest);
	$UserService.Delete(JSON.stringify($scope.cfrequest))
			.then(
      			function(response)
      				{
      					console.log( response.status );
      					if( response.status == "Deleted" )
      					{
 							for( i = 0 ; i < $scope.data.length ; i++ )
     						{
     							if( $scope.data[i].ProfileID == response.ProfileID )
     							{
     								$scope.data[i].ProfileAssociation = response.ProfileAssociation;
     								break;
     							}
     						}
     		
 							$scope.deleterequest = response.status;
      						console.log( response.status );
      						
      					}
 						$scope.toggle = false;
      				}, 
	             function(errResponse)
	                {
	                	console.error('Error while Updating User.');
	                } 
  	 				);
};
}]);
</script>

<body ng-app="myApp" ng-controller="abc">
<c:import url="head.jsp"></c:import>
<br><br><br><br><br><br>
	<div class="container">
	
		<table class="table " ng-repeat="data in data">
			<tbody>
				<tr>
				<input type="hidden" value="{{ data.ProfileID}}" />
				<td><img ng-src="{{data.ProfileImage}}" class="img-rounded" height=" 150px" width="200px" /></td>
				<td>UserName :{{data.ProfileName}}<br>Email ID : {{data.ProfileEmail}}<br>{{data.ProfileID}}</td>
			
			
				<td>
	 			<button type="button"  class="btn btn-success" ng-show="data.ProfileAssociation =='notfriend'"  ng-click="AddFriend(data.ProfileID,data.ProfileName);">Add friend</button>
				<button type="button"  class="btn btn-success"  ng-show="data.ProfileAssociation=='pendingrequest'" ng-click="AcceptRequest(data.ProfileID ,data.ProfileName)">Accept Request</button>
				<button type="button"  class="btn btn-success"  ng-show="data.ProfileAssociation=='pendingrequest'" >Ignore</button>
				<button type="button" class="btn btn-success" ng-model= "data.ProfileAssociation"  value="{{data.ProfileAssociation}}" ng-show="data.ProfileAssociation=='Sent'" ng-click="Delete(data.ProfileID,data.ProfileName);">Cancel Request(click to undo)</button>
			 	<button type="button" class="btn btn-success" ng-show="data.ProfileAssociation=='Friend'" >Friends</button>
			 	<td>
			 		<label class="alert alert-success" ng-show="data.ProfileAssociation =='Sent'">Updated</label>
				<label class="alert alert-success" ng-show="data.ProfileAssociation =='Friend'">Updated</label>
					
			</td>	      
			 	<a href= "${ pageContext.request.contextPath}/viewprofile/{{data.ProfileName}}" type="button" class="btn btn-success pull-right">View Profile</a>
				
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>