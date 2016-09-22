<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 <c:import url="head-meta.jsp" />


</head>

<script type="text/javascript">

var myApp = angular.module('myApp',[]);

myApp.controller("abc",function($scope)
{
	$scope.data = ${data};
	
	$scope.search = '${param.item}';
});

myApp.directive('fileModel', ['$parse', function ($parse) {
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
///////////////////////////////////////
myApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, paramuser, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        //fd.append('user','vasudev89');
        return $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined , user: paramuser}
        })
        .then(
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
</script>
<body ng-app="myApp" ng-controller="abc">

<c:choose>
	      					<c:when test="${not empty pageContext.request.userPrincipal}">
	      						<li><a href="${pageContext.request.contextPath}/index">${pageContext.request.userPrincipal.name}</a></li>
	      						<li><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>
	      						
	      					</c:when>
	      					
	      					<c:otherwise>
	      						<li><a href="${pageContext.request.contextPath}/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        						<li><a href="${pageContext.request.contextPath}/loginpage"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        						<li><a href="#"></a></li>
	      					</c:otherwise>
	   
	     				</c:choose>

<table class="table" >
 	<tr ng-repeat="x in data " >
 	
 		<td align="center"><img src="${pageContext.request.contextPath}/{{ x.ProductImage }}" class="img-rounded" width="250px" ></td>
 </tr>
 </table>


</body>
</html>