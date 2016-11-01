<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="head-meta.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Forum</title>
</head>
<body>
	<c:import url="head.jsp"></c:import>
		<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
<div class="container">

				
<form:form action="${pageContext.request.contextPath}/updateforum" method="post" modelAttribute="forum" enctype="multipart/form-data">
		<div class="container">
			<form:input type="hidden" path="ForumID" />
			<table>
				<tr>
					<td>Topic Name:</td>
					<td><form:input path="Topicname" /></td>
				</tr>
				
				<tr>
					<td>Description:</td>
					<td><form:input path="Description" /></td>
				</tr>
				
				<tr>
					<td><form:label path="Image" for="productImage">Image:</form:label></td>
					<td><label class="form-control"><span
							id="file_display1">Choose Image</span>
							<span
							style="position: relative;"><form:input path="productFile"
									onchange="changeFileDisplay1();" type="file" style="opacity:0;"
									class="form-control" id="imageFile1" /></span></label> 
									<script type="text/javascript">
										function changeFileDisplay1() {
											document
													.getElementById("file_display1").innerHTML = $(
													'#imageFile1').val();
											;
										}
									</script></td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="submit" class="btn btn-success btn-center" value="Save" /></td>
				</tr>
			</table>
		</div>
	</form:form>
	<br>
	<br>
</div>
	

</body>
</html>