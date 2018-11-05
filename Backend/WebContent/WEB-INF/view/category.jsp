<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categories</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<table id="myTable" class= " table table-striped">
<tr>
				<th>Name</th>
			
</tr>
<c:forEach var="tempCategory" items="${categories}">
<c:url var ="deleteLink" value ="categories/delete">

<c:param name ="id" value ="${tempCategory.id}"/>
</c:url> 
				<tr>
					<td> ${tempCategory.name}</td>
					<td><a href="${deleteLink}" onClick="if(!(confirm('Are you sure you want to delete this category ?'))) return false">Delete</a>
				    </td>
				</tr>

			</c:forEach>
			</table>
			
			<div class="modal fade" id="modalSubscriptionForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Add Category</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body mx-3">
                <div class="md-form mb-5">
                    <i class="fa fa-user prefix grey-text"></i>
                    <input type="text" id="form3" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="form3">Category Name</label>
                </div>
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <button id="add"class="btn btn-indigo">Add Category<i class="fa fa-paper-plane-o ml-1"></i></button>
            </div>
        </div>
    </div>
</div>

<div class="text-center">
    <a href="" class="btn btn-default btn-rounded mb-4" data-toggle="modal" data-target="#modalSubscriptionForm">Add New Category </a>
</div>
<script>
$("#add").on("click",function(){
	var name = document.getElementById('form3');
	
	window.location="categories/add?name="+name.value;
});
</script>
</body>
</html>