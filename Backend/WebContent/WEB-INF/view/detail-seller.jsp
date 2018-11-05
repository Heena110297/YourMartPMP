<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller Detail</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <style>
  .carousel-inner{
  width:500px;
  max-height: 200px !important;
}
  </style>
</head>
<body>
<div class="container">
  <h2>${seller.id}: ${seller.name}</h2>
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
   <div class="card">
    <div class="card-body">${seller.owner}
    <div class= "card-text">Contact No: ${seller.telephone}</div>
	<div class="card-text"><p>GST:     ${seller.gstNumber}<br>
							Address:   ${seller.address}<br>
							Email:	   ${seller.email}<br>
							Registered On:	${seller.registeredOn}<br>
							Status: ${seller.status}																				
	</p>
	</div>    
    </div>
  </div>

<script>
$("#n").on("click", function() {
	
	window.location="seller/change?checkedIds="+${seller.id}+"&status=Need_Approval";
});
$("#a").on("click", function() {
	
	window.location="seller/change?checkedIds="+${seller.id}+"&status=Approved";
});
$("#r").on("click", function() {

	window.location="seller/change?checkedIds="+${seller.id}+"&status=Rejected";
});
</script>
</body>
</html>