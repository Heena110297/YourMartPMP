<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sellers</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
html, body{
	margin-left:15px; margin-right:15px; 
	padding:0px; 
	font-family:Verdana, Arial, Helvetica, sans-serif;
}

#outer
{
    width:100%;
 
}
.inner
{
    display: inline-block;
}

table {   
	border-bottom:1px solid gray;
	font-family: Tahoma,Verdana,Segoe,sans-serif;
	width:100%;
	
}
 


 .modal-header, h4, .close {
      background-color: #5cb85c;
      color:white !important;
      text-align: center;
      font-size: 30px;
  }
  .modal-footer {
      background-color: #f9f9f9;
  }
input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}


 
#wrapper {width: 100%; margin-top: 0px; }
#header {width: 100%; background: #09c332; margin-top: 0px; padding:15px 0px 15px 15px;}
#header h2 {width: 100%; margin:auto; color: #FFFFFF;}
#container {width: 100%; margin:auto}
#container h3 {color: #000;}
#container #content {margin-top: 20px;}

.add-button {
	border: 1px solid #666; 
	border-radius: 5px; 
	padding: 4px; 
	font-size: 12px;
	font-weight: bold;
	width: 120px; 
	padding: 5px 10px; 
	
	margin-bottom: 15px;
	background: #cccccc;
}

.tab {
    float: left;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
    width: 30%;
    height: 300px;
}

/* Style the buttons that are used to open the tab content */
.tab button {
    display: block;
    background-color: inherit;
    color: black;
    padding: 22px 16px;
    width: 100%;
    border: none;
    outline: none;
    text-align: left;
    cursor: pointer;
    transition: 0.3s;
}

* {box-sizing: border-box}
body {font-family: "Lato", sans-serif;}

/* Change background color of buttons on hover */
.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current "tab button" class */
.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    float: left;
    padding: 0px 12px;
    border: 1px solid #ccc;
    width: 70%;
    border-left: none;
    height: 300px;
}
</style>
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
				placeholder="search by company's name , owner's name , contact number">
			<input type="submit" value="Search">

		</form>
	</div>

	<div id="container">
		<div id="content">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown" id="koibhi">
					Sort <span class="caret"></span>
				</button>
				<ul id="drop_list" class="dropdown-menu">
					<li id="id"><a href="#">Sort by Id</a></li>
					<li id="reg"><a href="#">Sort by registration date</a></li>
				</ul>
			</div>
			<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown" id="koibhi">
							Change Status <span class="caret"></span>
						</button>
						<ul id="drop_list" class="dropdown-menu">
							<li id="a"><a href="#">Approved</a></li>
							<li id="n"><a href="#">Need_Approval</a></li>
							<li id="r"><a href="#">Rejected</a></li>
						</ul>
					</div>
			<br> <br>
		</div>
		<table id="myTable" class= " table table-striped">
			<tr>
				<th></th>
				<th>ID</th>
				<th>Name</th>
				<th>Company</th>
				<th>Status</th>
				<th>Owner</th>
				<th>Contact Number </th>
				<th>Registration Date</th>
			</tr>
			<c:forEach var="tempSeller" items="${sellers}">

				<c:url var="detail" value="/detail-seller">
					<c:param name="id" value="${tempSeller.id}" />
				</c:url>
				<tr>
					<td><input type="checkbox" name="cbSellers"
						value="${tempSeller.id}"></td>
					<td><a href="${pageContext.request.contextPath}/seller?sellerId=${tempSeller.id}">${tempSeller.id}</a></td>
					<td>${tempSeller.name}</td>

					<td>${tempSeller.companyName}</td>

					<td>${tempSeller.status}</td>

					<td>${tempSeller.owner}</td>
                     <td> ${tempSeller.telephone }</td>
					<td>${tempSeller.registeredOn}</td>
				</tr>

			</c:forEach>

		</table>

	</div>
	
	<script>
	$("#n").on("click", function() {
		checkedIds = retrieveChecked();
		window.location="/change?checkedIds="+checkedIds+"&status=Need_Approval";
	});
	$("#a").on("click", function() {
		checkedIds =retrieveChecked();
		window.location="/change?checkedIds="+checkedIds+"&status=Approved";
	});
	$("#r").on("click", function() {
		checkedIds =retrieveChecked();
		window.location="/change?checkedIds="+checkedIds+"&status=Rejected";
	});
		$("#id").on("click", function() {
			sortTable(0);
		});

		$("#reg").on("click", function() {

			sortTable(6);
		});
		
		function retrieveChecked(){
			var checkboxes = document.getElementsByName('cbSellers');
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