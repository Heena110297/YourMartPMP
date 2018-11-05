<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Detail</title>
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
  <h2>${product.id}: ${product.name}</h2>
  <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block" src='${product.galleryImages[0]}' width= "500px" height="200px" alt="First slide">
    </div>
    <div class="carousel-item">
      <img class="d-block"  src='${product.galleryImages[1]}' width= "500px" height="200px"alt="Second slide">
    </div>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
<br>
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
  <div class="card">
    <div class="card-body">${product.shortDescription}
    <div class= "card-text">${product.longDescription}</div>
    <img src='${product.galleryImages[0]}' width= "300px" height="200px"/>
	<div class="card-text"><p>MRP:  ${product.mrp}<br>
							SSP:  ${product.ssp}<br>
							YMP:	${product.ymp}<br>
							Seller Name:	${product.seller.name}<br>
							Category:	${product.category.name}<br>	
							Status: ${product.status}																				
	</p>
	</div>    
    </div>
  </div>
</div>
<div>
		<form action="product/addComment">
			<input type="text" id="comment" name="comment" value="${comment}">
			<input type ="hidden" id="productId" name="productId" value="${product.id}">
			<input type="submit" value="Add Comment">

		</form>
	</div>

<script>
	$("#n").on("click", function() {
		window.location="product/s?checkedIds="+${product.id}+"&status=REVIEW";
	});
	$("#a").on("click", function() {
		
		window.location="product/s?checkedIds="+${product.id}+"&status=NEW";
	});
	$("#r").on("click", function() {
		window.location="product/s?checkedIds="+${product.id}+"&status=REJECTED";
	});
	</script>
</body>

</html>