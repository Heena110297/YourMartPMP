<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Your Mart PMP</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown" id="koibhi">
					Sort <span class="caret"></span>
				</button>
				<ul id="drop_list" class="dropdown-menu">
					<li id="mrp"><a href="#">Sort by MRP</a></li>
					<li id="ssp"><a href="#">Sort by SSP</a></li>
					<li id="ymp"><a href="#">Sort by YMP</a></li>
					<li id="created"><a href="#">Sort by Date Added</a></li>
				</ul>
			</div>
			<br> <br>
		</div>


		<table id="myTable">
			<tr>
				<th></th>
				<th>Id</th>
				<th>Thumbnail</th>
				<th>Name</th>
				<th>Seller</th>
				<th>Category</th>
				<th>Status</th>
				<th>MRP</th>
				<th>SSP</th>
				<th>YMP</th>
				<th>Date Added</th>
			</tr>
			<c:forEach var="tempProduct" items="${products}">
				<tr>

					<td><input type="checkbox" name="idValue"
						value="${tempProduct.id}"></td>
					<td>${tempProduct.id}</td>
					<td><img
						src="${pageContext.request.contextPath}/resources/images/${tempProduct.primaryImg}"
						height="50" width="50"></td>
					<td>${tempProduct.name}</td>
					<td>${tempProduct.seller.name}</td>
					<td>${tempProduct.category.name}</td>
					<td>${tempProduct.status}</td>
					<td>${tempProduct.mrp}</td>
					<td>${tempProduct.ssp}</td>
					<td>${tempProduct.ymp}</td>
					<td>${tempProduct.created}</td>
				</tr>

			</c:forEach>

		</table>

	</div>
	</div>
	<script>
		$("#mrp").on("click", function() {
			sortTable(7);
		});

		$("#ssp").on("click", function() {

			sortTable(8);
		});

		$("#ymp").on("click", function() {

			sortTable(9);
		});

		$("#created").on("click", function() {

			sortTable(10);
		});

		function sortTable(index) {
			var table, rows, switching, i, x, y, shouldSwitch;
			table = document.getElementById("myTable");
			switching = true;
			/*Make a loop that will continue until
			no switching has been done:*/
			while (switching) {
				//start by saying: no switching is done:
				switching = false;
				rows = table.rows;
				/*Loop through all table rows (except the
				first, which contains table headers):*/
				for (i = 1; i < (rows.length - 1); i++) {
					//start by saying there should be no switching:
					shouldSwitch = false;
					/*Get the two elements you want to compare,
					one from current row and one from the next:*/
					x = rows[i].getElementsByTagName("TD")[index];
					y = rows[i + 1].getElementsByTagName("TD")[index];
					//check if the two rows should switch place:
					if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
						//if so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				}
				if (shouldSwitch) {
					/*If a switch has been marked, make the switch
					and mark that a switch has been done:*/
					rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
					switching = true;
				}
			}
		}
	</script>
</body>
</html>