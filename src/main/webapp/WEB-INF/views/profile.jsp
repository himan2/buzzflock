<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="head-meta.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">

body {
	background: url(resources/images/bk6.jpg) no-repeat center center fixed;
}

</style>

</head>

<script >
	var myApp = angular.module('myApp', []);
	
	myApp.service('fileUpload', ['$http', function ($http) 
	                   	      {
	                   	    		this.uploadFileToUrl = function(file ,paramuser, uploadUrl)
	                   	    		{
	                   	        	var fd = new FormData();
	                   	        	fd.append('file', file);
	                   	    
	                   	   	     	return $http.post(uploadUrl, fd, 
	                   	   	     {
	                   	            		transformRequest: angular.identity,
	                   	            headers: {'Content-Type': undefined , user: paramuser}
	                   	        }).then(
	                                       function(response){
	                                           return response.data;
	                                       }, 
	                                       function(errResponse){
	                                           console.error('Error while updating User');
	                                           return "error";
	                                       }
	                               );
	                   	    }
	                   	}]);
	                   	
	                   	/* myApp.directive('fileModel', ['$parse', function ($parse) {
	                   	    return {
	                   	        restrict: 'A',
	                   	        link: function(scope, element, attrs) {
	                   	            var model = $parse(attrs.fileModel);
	                   	            var modelSetter = model.assign;
	                   	            
	                   	            element.bind('change', function(){
	                   	                scope.$apply(function(){
	                   	                    modelSetter(scope, element[0].files[0]);
	                   	                });
	                   	            });
	                   	        }
	                   	    };
	                   	}]);
	                   	 */
	
	
	myApp.factory('UserService', ['$http', '$q', function($http, $q){
	    return {
	    			getUserDetails: function(){
	    				 return $http.post('http://localhost:9001/buzzflock/getUserDetails/')
	                            .then(
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
	    
	    updateUserDetails: function(item){
            return $http.post('http://localhost:9001/buzzflock/updateUserDetails/', item)
                    .then(
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
	    updatePassword: function(item)
	    {
            return $http.post('http://localhost:9001/buzzflock/updatePassword/', item)
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
	    updateImage: function(item)
	    {
            return $http.post('http://localhost:9001/buzzflock/updateImage/', item)
                   .then
                    (
                            function(response)
                            {
                            	console.log($scope.updateImage);
                                return response.data;
                            }, 
                            function(errResponse)
                            {
                                console.error('Error while updating User');
                                return $q.reject(errResponse);
                            }
                    );
		}
};}]);
	
	
	
	myApp.controller("abc",['$scope', 'UserService', 'fileUpload' , function($scope , $UserService , $fileUpload ) {
		$scope.data = ${data};
		$scope.myerror = "";
		
		$scope.userdata;
		$scope.userdatatemp;
		
		$scope.updated;
		$scope.edit = false;
		$scope.password=false;
		
		$scope.UserPasswordDetails = 	
		{ 
				
				OldPassword: "",
				NewPassword: "",
				ConfirmNewPassword: ""
		};
		
		
		$scope.genderCheck = false;
		$scope.CheckForGender = function()
		{
			if( $scope.userdatatemp.ProfileGender != 'M' && $scope.userdatatemp.ProfileGender != 'F' )
				$scope.genderCheck = true;
			else
				$scope.genderCheck = false;
		
			$scope.updateOverall();
		}
		
		$scope.updateOverall = function()
		{
			$scope.overallValidationCheck = $scope.genderCheck;
			$scope.overallValidationCheck = $scope.emailCheck;	
			$scope.overallValidationCheck = $scope.phoneCheck;
			$scope.overallValidationCheck = $scope.oldpasswordCheck;
		}
		
		$scope.CheckForEmail = function()
		{
			var reg = /\S+@\S+\.\S+/;
			
			if ($scope.emailCheck=!reg.test( $scope.userdatatemp.ProfileEmail ))
			$scope.emailCheck = true;
			else
			$scope.emailCheck = false;
			$scope.updateOverall();
		}
		
		$scope.CheckForPhone = function()
		{
			var reg = /^[7-9][0-9]{9}$/;
		
			if ($scope.phoneCheck = !reg.test( $scope.userdatatemp.ProfilePhone ))
				$scope.phoneCheck=true;
			else
				$scope.phoneCheck=false;
				$scope.updateOverall();
		}
		
		$scope.CheckForOldPassword = function()
		{
			var reg = /^.{6,15}$/;
		
			if ($scope.oldpasswordCheck = !reg.test( $scope.UserPasswordDetails.OldPassword))
				
				$scope.oldpasswordCheck=true;
			else
				$scope.oldpasswordCheck=false;
			
				$scope.updateOverallPassword();
		}
		
		$scope.CheckForNewPassword = function()
		{
			var reg = /^.{6,15}$/;
		
			if ($scope.newpasswordCheck = !reg.test( $scope.UserPasswordDetails.NewPassword))
				
				$scope.newpasswordCheck=true;
			else
				$scope.newpasswordCheck=false;
			
				$scope.updateOverallPassword();
		}
	 
	 $scope.CheckPassword = function()
	 {
		if ($scope.UserPasswordDetails.NewPassword != $scope.UserPasswordDetails.ConfirmNewPassword) 
	 	
			$scope.matchpasswordCheck=true;
		else
			$scope.matchpasswordCheck=false;
	 }
	 
	 $scope.updateOverallPassword = function()
	 {
		 $scope.overallValidationPasswordCheck = $scope.oldpasswordCheck || $scope.matchpasswordCheck || $scope.newpasswordCheck;
		 
	 }
		
		$UserService.getUserDetails().then(
				
				function(response)
				{
					$scope.userdata = response;
					$scope.userdatatemp = angular.copy( response );
					console.log( $scope.userdata );
					console.log( $scope.userdatatemp );
				}
				,
				function(errResponse)
				{
					console.log('Error in getting User Data');
				}
		);
		
		$scope.setFile = function(e)
		{
			
			$scope.fileforupload = e.files[0];
			
			console.log($scope.fileforupload);
			
			/* var file = $scope.fileforupload;
			
			console.log($scope.fileforupload);
			
				var uploadUrl = "http://localhost:9001/buzzflock/updateProfilePicture/";
		  	        
	   		    var res = $fileUpload.uploadFileToUrl(file ,uploadUrl).then
		  	        
	   		    (
		            		function(response)
		            		{
		            			$scope.response = response.status;
		            			$scope.imagesrc = response.imagesrc;
		            			
		            			//console.log( $scope.response );
		            			//console.log( $scope.imagesrc );
		            			
		            			if( $scope.response == "Uploaded" )
		            			{
		            				$scope.picUpdated = true;
		            				$scope.currentImage = '${pageContext.request.contextPath}/' + $scope.imagesrc;
		            				
		            				
		            			}
		            			else
		            			{
									$scope.picUpdatedWithError = true;
		            				
		            	}
		            			
			    				
		            		}
			            , 
			                function(errResponse)
			                {
			                	console.error('Error while Updating User.');
			                } 
		        	); */
			
			//console.log("File:");
			//console.log(e.files[0]);
		};
		
		$scope.toggleUpdatePassword = function(response)
		{
			console.log(JSON.stringify($scope.UserPasswordDetails));
			
			$UserService.updatePassword(JSON.stringify($scope.UserPasswordDetails)).then
			(
			function(response)
			{
				if(response.status =="Updated")
					{
					console.log("Success");
					
					$scope.updated = response.status;
    				
    				window.setTimeout(function()
    				{
    					$scope.$apply( $scope.updated = '' );
    				},3000);
					
					}
				else
				{
					
					console.log("Incorrect Password")
				
					$scope.updated = response.status;
    				
    				window.setTimeout(function()
    				{
    					$scope.$apply( $scope.updated = '' );
    				},3000);
				}
					
			  }
		 ,
		 function(errResponse)
		 {
				console.log('Error in updating User Data');
			}
			
			)}
		,
		
		$scope.toggleChangeUpdate = function(response)
		{  
			console.log( JSON.stringify($scope.userdatatemp) );
			
			$UserService.updateUserDetails(JSON.stringify($scope.userdatatemp)).then(
					
					function(response)
					{
						if( response.status == "Updated" )
						{
							$scope.userdata = angular.copy( $scope.userdatatemp );
						}
						
					}
					,
					function(errResponse)
					{
						console.log('Error in updating User Data');
					}
			);
		}
		
		$scope.fileforupload = null;
		
		$scope.letitbe = function()
		{
			
			console.log( $scope.userdata );
			console.log( $scope.userdatatemp );
		}
		
		
			$scope.CheckValidFileType = function(inp) 
		{
			console.log(inp);

			if (inp != ".jpg")
				$scope.$apply($scope.myerror = "error");
			else
				$scope.$apply($scope.myerror = "");

			console.log($scope.myerror);
		}

			$("#ffub").click(function() {
				$("#ffu").trigger('click');
			});
			
			$scope.fileforupload=null;
			$("#ffu").on('change',function(e) 
			{
						var filename = e.target.files[0].name;
						
						$scope.fileforupload = e.target.files[0];
						
						var file = $scope.fileforupload;
						
						console.log($scope.fileforupload);
						
							var uploadUrl = "http://localhost:9001/buzzflock/updateProfilePicture/";
					  	        
				   		    var res = $fileUpload.uploadFileToUrl(file ,$scope.userdata.ProfileName , uploadUrl).then
					  	        
				   		    (
					            		function(response)
					            		{
					            			$scope.response = response.status;
					            			$scope.imagesrc = response.imagesrc;
					            			
					            			
					            			$scope.userdata.ProfileImage = angular.copy(response.imagesrc);
					            			console.log( $scope.response );
					            			console.log( $scope.imagesrc );
					            			
					            			if( $scope.response == "Uploaded" )
					            			{
					            				$scope.picUpdated = true;
					            				$scope.currentImage = '${pageContext.request.contextPath}/' + $scope.imagesrc;
					            		
					            			}
					            			else
					            			{
												$scope.picUpdatedWithError = true;
					            				
					            	}
					            			
						    				
					            		}
						            , 
						                function(errResponse)
						                {
						                	console.error('Error while Updating User.');
						                } 
					        	);
					  			
				$scope.CheckValidFileType(filename.substring(filename.indexOf('.'), filename.length));
						
			});
	}]);
</script>


<body ng-app="myApp" ng-controller="abc">
<c:import url="head.jsp"/>

	<div>
<a href= "${ pageContext.request.contextPath}/searchnewfriend" type="button" class="btn btn-success pull-right">All Profile</a>
</div><br><br>


<div class="container">
<div class="row">
<div class="col-sm-6">

<table class="table " style="background-color: rgba(255,255,255,0.8);">

	
			<tbody>

			<tr>
				<td> <br>
					 <img id="profileImage" ng-src="{{userdata.ProfileImage}}"height=" 150px" width="200px">	
					 <button id="ffub" class="btm btn-link">Choose Image</button>
					 <input type="file" id="ffu" style="opacity:0"  />
					 
					 <div class="text text-danger" ng-if=" myerror =='error' " >Invalid Image Type</div>
				</td>
			</tr>

			<tr>
						<td>User Name:</td>
						<td>
							<label ng-show="!change">{{userdata.ProfileName}}</label>
								</td>
					</tr>
				
				<tr>
					<td>Gender:</td>
					<td>
					<label ng-if="!edit">{{userdata.ProfileGender}}</label>
					<input type="text" class="form-control" value="{{userdatatemp.ProfileGender}}" ng-model="userdatatemp.ProfileGender" ng-if="edit" ng-change="CheckForGender();"/>							
					<label ng-if="genderCheck" class="text text-danger">Invalid Gender. Only 'M' or 'F' allowed.</label>
					</td>
				</tr>
				
				<tr>
					<td>Email:</td>
					<td><label ng-if="!edit">{{userdata.ProfileEmail}}</label>
					<input type="text" class="form-control" value="{{userdatatemp.ProfileEmail}}" ng-model="userdatatemp.ProfileEmail" ng-if="edit" ng-change="CheckForEmail();"/>							
					<label ng-if="emailCheck" class="text text-danger">Email Id not correct</label>
					</td>
				</tr>
				<tr>
				
					<td>Contact No:</td>
					<td><label ng-if="!edit">{{userdata.ProfilePhone}}</label>
					<input type="text" class="form-control" value="{{userdatatemp.ProfilePhone}}" ng-model="userdatatemp.ProfilePhone" ng-if="edit" ng-change="CheckForPhone();"/>
					<label ng-if="phoneCheck">Incorrect Phone Number</label>
					</td>
					</tr>
					
					<tr>
					<td>Location :</td>
					<td><label ng-if="!edit">{{userdata.ProfileLocation}}</label>
					<input type="text" class="form-control" value="{{userdatatemp.ProfileLocation}}" ng-model="userdatatemp.ProfileLocation" ng-if="edit"/>
					</td>
												
				</tr>
				
				
			<tr>
			
				<td>
					<button class="btn btn-link" ng-click="letitbe(); edit = !edit;">
							<span ng-if="!edit">Edit</span>
							<span ng-if="edit">Let It Be</span>
					</button>
				</td>
				
				<td>
							<button class="btn btn-success" ng-if="edit" ng-disabled="overallValidationCheck" >
								<span ng-click="toggleChangeUpdate();">Update</span>
							</button>
				</td>
			</tr>
		</tbody>
	 </table>
	 
</div>

<div class="col-sm-6">
<table style="width: 80%;" class="table">
					
					<tr>
						<ul style="font-style: italic;font-weight: bold;font-size: 16px;font-family: Segoe UI, Tahoma, sans-serif; color: #333333; padding: 5px; opacity: 0.8; line-height: 20px;" class="list-group">
							<a href="${pageContext.request.contextPath}/blog/" class="list-group-item profile-list-group-item"><li > Blogs</li></a>
							<a href="${pageContext.request.contextPath}/forum/{{userdata.ProfileName}}" class="list-group-item profile-list-group-item"><li> Forums</li></a>
							<a href="${pageContext.request.contextPath}/gallery/{{userdata.ProfileName}}" class="list-group-item profile-list-group-item"><li> Gallery</li></a>							
						</ul>
					</tr>
									  	
					<br>
									  	
				</table>
</div>
</div>
</div>

	 
<div class="col-sm-6">
<table class=table style="background-color: rgba(255,255,255,0.8);">
<tbody ng-if="password">

					<tr>
					<td>Old Password</td>
					<td>
					<input type="text" class="form-control"  ng-if="password" value="{{UserPasswordDetails.OldPassword}}" ng-model="UserPasswordDetails.OldPassword" ng-change="CheckForOldPassword();"/>
					<label ng-if="oldpasswordCheck">Password Should be betweeen 6 to 15 Character</label>							
					</td>
					</tr>
					
					<tr>
					<td>New Password</td>
					<td><input type="text" class="form-control"  ng-if="password" value="{{UserPasswordDetails.NewPassword}}" ng-model="UserPasswordDetails.NewPassword" ng-change="CheckForNewPassword();"/>
					<label ng-if="newpasswordCheck">Password Should be betweeen 6 to 15 Character</label>
					</td>
					</tr>
					
					<tr>
					<td>Confirm New Password</td>
					<td><input type="text" class="form-control" ng-if="password" value="{{UserPasswordDetails.ConfirmNewPassword}}" ng-model="UserPasswordDetails.ConfirmNewPassword" ng-change="CheckPassword();"/>							
					<label ng-if="matchpasswordCheck">New Password and Confirm Password Mismatch</label>
					</td>
					</tr>
					
					


<tr>
						
</tbody>
</table>
</body>
					<button class="btn btn-danger pull-right" ng-click="password=!password;">
							<span ng-if="!password">Change Password</span>
							<span ng-if="password">Let It Be</span>
					</button>
							<button class="btn btn-success" ng-if="password" ng-disabled="overallValidationPasswordCheck" >
							
								<span ng-click="toggleUpdatePassword();">Save</span>
								<label class="alert alert-success" style="position: absolute; top: 490px; left: 530px; " ng-show="updated=='Updated'">Updated</label>
								<label class="alert alert-danger" style="position: absolute; top: 490px; left: 530px; " ng-show="updated=='Password Incorrect'">Incorrect Password</label>
							</button>
		 </div>
		 </div>
		 </div>
 <br><br><br>
 
 </table>
	 
 
 
 </html>