<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="head-meta.jsp"></c:import>
<title>Blog Content</title>
<style type="text/css">
body {
	background: url(${pageContext.request.contextPath}/resources/images/bk4.jpg) no-repeat center center fixed;
}
</style>

</head>
<script type="text/javascript">
{
var  myapp = angular.module('myapp' , []);
/* 
myapp.factory('UserService',['$http','$q', function($http, $q){
	return
	{
		updateLikes: function(item)
		{
			return $http.post('http://localhost9001/buzzflock/updateLikes',item).then
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
	}
	}
]);
 */
 myapp.factory('UserService', ['$http', '$q', function($http, $q){
		
	    return {
	    	
	    	fetchcomment: function() 
			{
									return $http
											.post('http://localhost:9001/buzzflock/fetchcomment/')
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
	    	
	    	updateLike: function(item) 
			{
									return $http
											.post('http://localhost:9001/buzzflock/updateLike/',item)
											.then(
													function(response) 
													{
														return response.data;
														console.log(response.data);
														
														
													},
													function(errResponse) 
													{
														console.error('Error while sending data');
														return $q.reject(errResponse);
													});
								
			}
			,
		
	    
	    submitComment: function(item) 
		{
								return $http
										.post('http://localhost:9001/buzzflock/submitComment/',item)
										.then(
												function(response) 
												{
													return response.data;
													console.log(response.data);
													
													
												},
												function(errResponse) 
												{
													console.error('Error while sending data');
													return $q.reject(errResponse);
												});
							
		}
	
	}
}]);
myapp.controller("abc",['$scope','UserService',function($scope,$UserService)
		{
			$scope.mydata=${mydata};
			console.log($scope.mydata);
		
			
			$scope.currentUser = '${pageContext.request.userPrincipal.name}';
			$scope.check=false;
			$scope.frequest;
			$scope.validationcheck=false;
		
			
			$scope.data;
			
		
			$scope.CommentValue='';
			
			
			console.log("on jsp page"+$scope.mydata.Contentid);
			console.log("user logged in "+ $scope.currentUser );
			var t2='${Username}';
			var t1 = $scope.currentUser;
			console.log(t1);
			console.log(t2);
			
			if((t1 == t2)) 
			{
			    $scope.validationcheck=true;
			}
			console.log($scope.validationcheck);
			
	 	console.log('this is liked or not overall'+$scope.alreadyliked )
	 	 
	 	$scope.updateLike = function(blogid)
			{
				$scope.frequest={"BlogID": blogid};
				console.log($scope.frequest);
				
				$UserService.updateLike(JSON.stringify($scope.frequest))
				.then
				(
					function(response)
					{
						//console.log(response);
						//console.log(response.length);
						
						console.log("$scope.mydata.length   "+$scope.mydata.length);
						//console.log("$scope.mydata.Contentid"+ ${mydata});
						console.log("response.id"+response.id);
						
						if(response.status=="Updated")
							{
	
								for(i=0;i<=$scope.mydata.length ; i++)
								{
								if($scope.mydata[i].Contentid == response.id)
								{
									console.log("this is in update like  "+$scope.mydata[i]);
									console.log($scope.mydata[i].Contentid);
									console.log($scope.mydata[i].length12);
									$scope.mydata[i].length12 = angular.copy(response.length);
									$scope.mydata[i].check1=angular.copy(response.check1);
									
									//$scope.alreadyliked=true;
									break;
								}
							}
							
								console.log($scope.mydata[i].length12);
								
								
								}
							
							
						console.log(response.status);
 							console.log($scope.alreadyliked);
 		
 
							}
					,
					 
	                function(errResponse)
	                	{
	                		console.error('Error while Updating User.');
	                	}	 
					 );
				
			};
			
		    $UserService.fetchcomment().then
		    (
		    					function(response) 
		    					{
		    						$scope.datafc = response;
		    						
		                			console.log("ths is in fetch comment "+$scope.datafc);
		    					}
		    			,
		    					function(errResponse)
		    					{
		    						console.error('Error while Sending Data.');
		    					}
		    );
		    	
			
			
			$scope.submitComment =function(CommentValue,Contentid)
			{
				
				$scope.data = { "CommentValue" : CommentValue,"CommentID":Contentid};
        		console.log(JSON.stringify($scope.data));
				$UserService.submitComment(JSON.stringify($scope.data)).then
				(
					function(response)
					{
						if(response.status=="updated")
						{
							console.log(response);
							console.log(response.status);
							console.log(response.Contentid );
							/* for( i = 0 ; i < $scope.datafc.length ; i++ )
 								{
 								if( $scope.datafc[i].Contentid == response.Contentid )
 									{
 										console.log($scope.datafc[i].Contentid);
 									//	$scope.datafc=angular.copy(response);
 										break;
 									}
 								} */
 								
							$UserService.fetchcomment().then
						    (
						    					function(response) 
						    					{
						    						$scope.datafc = response;
						    						
						                			console.log("ths is in fetch comment "+$scope.datafc);
						    					}
						    			,
						    					function(errResponse)
						    					{
						    						console.error('Error while Sending Data.');
						    					}
						    );
						}
					
					},
					function(eRRresponse)
					{
						console.error('Error while updating user');
					}
					
				);
				
				
			}
		
		}]);
}


</script>
<body ng-app="myapp" ng-controller="abc">
<c:import url="head.jsp"></c:import>
<br><br>

<div class="container" >

		<div class="row" style="color: black;  background-color: rgba(255,255,255,0.8); padding:20px; ">
				<h1 style=" text-transform: uppercase"><b>${Username} 's</b> Blog </h1>
				<h2><b>Topic Name :</b>&nbsp;${Topicname}</h2>
				<h3>Description :&nbsp;${Description}</h3> 
				<div ng-if="validationcheck">
		<form:form action="${pageContext.request.contextPath}/addcontent" method="post" modelAttribute="blogcontent">
		<form:input type="hidden" path="BlogID" value="${BlogId}" /><br><br>
			<div>
				<form:textarea class="form-control" rows="5"  path="value" placeholder="Write a Blog........." />
			</div><br><br>	
			<div>
				<input type="submit" class="btn btn-success btn-center" value="Post Blog" />
			</div>
		</form:form>
</div>				
			</div>
			
	<br><br>
	
<%-- <div ng-if="validationcheck">
		<form:form action="${pageContext.request.contextPath}/addcontent" method="post" modelAttribute="blogcontent">
		<form:input type="hidden" path="BlogID" value="${BlogId}" /><br><br>
			<div>
				<form:textarea class="form-control" rows="5"  path="value" placeholder="Write a Blog........." />
			</div><br><br>	
			<div>
				<input type="submit" class="btn btn-success btn-center" value="Post Blog" />
			</div>
		</form:form>
</div>	 --%>			
<br><br><br>
<div ng-repeat="x in mydata"  style="background-color: rgba(255,255,255,0.8);">
		<div>
				<div style="font-size:30px; text-transform: capitalize;">${Username} Wrote:</div><span  class="pull-right glyphicon glyphicon-time">{{x.Timestamp}}</span>
				<div style="font-size:18px; background-color: #D3D3D3; padding:10px;">{{x.Value}}</div>
				<br>
      	</div>
      	
      	
      	<div ng-if="!validationcheck">
	    		<button  ng-show="x.check1=='false'" type="button"  class="btn btn-info btn-lg center"  ng-click="updateLike(x.blogid);">Like</button>
	    	   	<button  ng-show="x.check1=='true'" type="button"  class="btn btn-info btn-lg center" >UnLike </button>
    	   		<label>Total Likes : {{x.length12}}</label>
    	  
		</div>
		<br>
		<div class="row">		
				<div class="col-sm-10" >
   						<textarea class="form-control"  value="{{CommentValue}}"  ng-model="CommentValue"   placeholder="Write a Comment" ></textarea>
				</div>
				<div class="col-sm-2">	
						<button class="btn btn-success btn-center" ng-click="submitComment(CommentValue,x.Contentid);" >Comment</button>
				</div>
				<div></div>
		</div>
		<br><br>
<div style="  padding:15px;" ng-if="x.Contentid==y.Contentid" ng-repeat="y in datafc">
		
		<div class="row ">
				<div  class="col-sm-10" style=" font-size:30px; font-family:Tahoma; border-radius: 10px; border-style:groove;  background-color: lightblue;">{{y.CommentValue}}</div>
				</<span  class="pull-right glyphicon glyphicon-time">{{y.CommentTimeStamp}}</span>
				<div class="col-sm-2" style="font-size:15px; text-transform: capitalize;">{{y.OwnerName}}</div>
				
				<br><br>
				
		</div>	
</div>
</div> 
</div>
<br><br><br><br>
</body> 
</html>