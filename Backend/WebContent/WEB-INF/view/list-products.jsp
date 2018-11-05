<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
<link   rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/WebContent/resources/css/style.css">
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
	<div>
		<form action="search">
			<input type="text" id="searchText" name="searchText"
				value="${searchText}"
				placeholder="search by product's id , name , sellers's name">
			<input type="submit" value="Search">

		</form>
	</div>
	<div id="container">
		<div id="content">
			<div id="outer">
				<div class="inner">
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
				</div>
				<div class="inner">
					<button type="button" class="btn btn-primary" id="filterBtn">Filter</button>
				</div>
				<div class="inner">
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown" id="koibhi">
							Change Status <span class="caret"></span>
						</button>
						<ul id="drop_list" class="dropdown-menu">
							<li id="a"><a href="#">NEW</a></li>
							<li id="n"><a href="#">REVIEW</a></li>
							<li id="r"><a href="#">REJECTED</a></li>
						</ul>
					</div>
				</div>
				<br> <br>
			</div>



			<table id="myTable" class="table table-striped table-bordered table-dark ">
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

						<td><input type="checkbox" name="cbProducts"
							value="${tempProduct.id}"></td>
						<td><a href="${pageContext.request.contextPath}/product?productId=${tempProduct.id}">${tempProduct.id}</a></td>
						<td>
							<img class='imagem_artigo' src='${tempProduct.primaryImg}' alt='IMG DESC' width="50" height='50'>
							</td>
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
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content" style="display: grid;">
				<div class="modal-header" style="padding: 30px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-lock"></span> Filter
					</h4>
				</div>
				<div class="modal-body">
					<div class="tab">
						<button class="tablinks" onclick="openCity(event, 'status')"
							id="defaultOpen">Status</button>
						<button class="tablinks" onclick="openCity(event, 'sellerId')">Seller
							Id</button>
						<button class="tablinks"
							onclick="openCity(event, 'sellerCompanyName')">Seller's
							Company Name</button>
						<button class="tablinks" onclick="openCity(event, 'categories')">Category</button>
					</div>

					<div id="status" class="tabcontent">
						<h3>Status</h3>

						<input type="checkbox" name="status" value="Approved">
						Approved</input> <br> <input type="checkbox" name="status"
							value="Rejected"> Rejected</input> <br> <input
							type="checkbox" name="status" value="Need Approval">
						Need Approval </input>
					</div>

					<div id="sellerId" class="tabcontent">
						<h3>Seller Id</h3>
						<c:forEach var="seller" items="${sellers}">
							<input type="checkbox" name="sellerId" value="${seller.id}"> ${seller.id}
						<br>
						</c:forEach>
					</div>

					<div id="sellerCompanyName" class="tabcontent">
						<h3>Seller's Company Name</h3>
						<c:forEach var="company" items="${companies}">
							<input type="checkbox" name="sellerCompany" value="${company}">${company}
						<br>
						</c:forEach>
					</div>

					<div id="categories" class="tabcontent">
						<h3>Category</h3>
						<c:forEach var="category" items="${categories}">
							<input type="checkbox" name="categories" value="${category.name}">${category.name}
						<br>
						</c:forEach>
					</div>


				</div>
				<div class="modal-footer" style="padding: 30px 50px;">
				
				<button type="submit" id="filter"class="btn btn-success"
						data-dismiss="modal">
					Filter
					</button>
					<button type="submit" class="btn btn-danger btn-default pull-left"
						data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span> Cancel
					</button>
				</div>
			</div>
		</div>
	</div>
	<script>
	$("#n").on("click", function() {
		checkedIds = retrieveChecked();
		window.location="s?checkedIds="+checkedIds+"&status=REVIEW";
	});
	$("#a").on("click", function() {
		checkedIds =retrieveChecked();
		window.location="s?checkedIds="+checkedIds+"&status=NEW";
	});
	$("#r").on("click", function() {
		checkedIds =retrieveChecked();
		window.location="s?checkedIds="+checkedIds+"&status=REJECTED";
	});
	
	$("#filter").on("click",function(){
		retrieveCheckedFilters();
	});
	function retrieveChecked(){
		var checkboxes = document.getElementsByName('cbProducts');
		console.log(checkboxes);
		var checkedIds= [] ;
		for (var i=0, n=checkboxes.length;i<n;i++) 
		{
		    if (checkboxes[i].checked) 
		    {
		        checkedIds.push(checkboxes[i].value);
		    }
		}
		
		return checkedIds;
	}
	
	function retrieveCheckedFilters(){
		var checkboxStatus = document.getElementsByName('status');
		var checkboxSellerId = document.getElementsByName('sellerId');
		var checkboxSellerCompany = document.getElementsByName('sellerCompany');
		var checkboxCategory = document.getElementsByName('categories');
		
		var checkedSellerId= [] ;
		var checkedStatus= [] ;
		var checkedSellerCompany= [] ;
		var checkedCategory= [] ;
		for (var i=0, n=checkboxStatus.length;i<n;i++) 
		{
		    if (checkboxStatus[i].checked) 
		    {
		    	checkedStatus.push(checkboxStatus[i].value);
		    }
		}
		
		for (var i=0, n=checkboxSellerId.length;i<n;i++) 
		{
		    if (checkboxSellerId[i].checked) 
		    {
		        checkedSellerId.push(checkboxSellerId[i].value);
		    }
		}
		for (var i=0, n=checkboxSellerCompany.length;i<n;i++) 
		{
		    if (checkboxSellerCompany[i].checked) 
		    {
		        checkedSellerCompany.push(checkboxSellerCompany[i].value);
		    }
		}
		for (var i=0, n=checkboxCategory.length;i<n;i++) 
		{
		    if (checkboxCategory[i].checked) 
		    {
		        checkedCategory.push(checkboxCategory[i].value);
		    }
		}
		if(checkedSellerId.length==0 && checkedStatus.length==0 && checkedCategory.length==0 && checkedSellerCompany.length==0)
			{
			location.reload()
			}
		else{
		window.location="filter?checkedSellerId="+checkedSellerId+"&checkedStatus="+checkedStatus+"&checkedSellerCompany="+checkedSellerCompany+"&checkedCategory="+checkedCategory;
		}
	}
	
		function openCity(evt, cityName) {
			var i, tabcontent, tablinks;
			tabcontent = document.getElementsByClassName("tabcontent");
			for (i = 0; i < tabcontent.length; i++) {
				tabcontent[i].style.display = "none";
			}
			tablinks = document.getElementsByClassName("tablinks");
			for (i = 0; i < tablinks.length; i++) {
				tablinks[i].className = tablinks[i].className.replace(
						" active", "");
			}
			document.getElementById(cityName).style.display = "block";
			evt.currentTarget.className += " active";
		}

		// Get the element with id="defaultOpen" and click on it
		document.getElementById("defaultOpen").click();
		$(document).ready(function() {
			$("#filterBtn").click(function() {
				$("#myModal").modal();
			});
		});
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
			while (switching) {
				switching = false;
				rows = table.rows;
				for (i = 1; i < (rows.length - 1); i++) {
					shouldSwitch = false;
					x = rows[i].getElementsByTagName("TD")[index];
					y = rows[i + 1].getElementsByTagName("TD")[index];
					if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
						shouldSwitch = true;
						break;
					}
				}
				if (shouldSwitch) {
					rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
					switching = true;
				}
			}
		}
	</script>
</body>
</html>