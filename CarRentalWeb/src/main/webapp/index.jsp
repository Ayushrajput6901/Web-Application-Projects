<!DOCTYPE html>
<html>

<head>
<title>CarRental</title>
<link rel="icon" href="resources/logo.png" />

<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />

<!-- font-awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
	integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/js/all.min.js"
	integrity="sha512-rpLlll167T5LJHwp0waJCh3ZRf7pO6IT1+LZOhAyP6phAirwchClbTZV3iqL3BMrVxIYRbzGTpli4rfxsCK6Vw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<!-- Lightbox CSS & Script -->
<script src="resources/lightbox/ekko-lightbox.js"></script>
<link rel="stylesheet" href="resources/lightbox/ekko-lightbox.css" />

<!-- AOS CSS & Script -->
<script src="resources/aos/aos.js"></script>
<link rel="stylesheet" href="resources/aos/aos.css" />

<!-- custom css -->
<link rel="stylesheet" href="resources/custom.css">

<meta name="author" content="" />
<meta name="description" content="This is a website for Car Rental." />
<meta name="keywords" content="" />
</head>

<body>

	<%
		String msg=(String)session.getAttribute("msg");
		if(msg!=null){
	%>
	<p class="p-2 bg-warning text-center">
		<%=msg%>
	</p>
	<%	
			session.setAttribute("msg", null);
		}
	%>

	<nav class="navbar navbar-expand-sm container my-3">
		<a href="index.jsp" class="navbar-brand"> <img
			src="resources/logo.png" height="35px" alt=""> <span>CarRental</span>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#my-navbar">
			<i class="fa-solid fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="my-navbar">
			<ul class="navbar-nav mx-auto p-2">
				<li><a href="index.jsp">Home</a></li>
				<li><a href="" data-toggle="modal" data-target="#AdminModal">Admin</a>
				</li>
				<li><a href="CarOwner.jsp">CarOwner</a></li>
				<li><a href="User.jsp">User</a></li>
				<li><a href="" data-toggle="modal" data-target="#myModal">Get
						In Touch</a></li>
			</ul>
			<a id="call" href="tel:8076479572"><i
				class="fa-solid fa-mobile-screen-button"></i> 8076479572</a>
		</div>
	</nav>
	<div class="container-fluid text-center text-white bg-primary p-3">
		<form action="SearchCar.jsp" method="post">
			<label>Enter Location:</label> <input type="search" name="location"
				required />
			<button class="btn btn-sm btn-light">Search</button>
		</form>
	</div>
	<section>
		<div id="carouselExampleControls" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="resources/banner1.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="resources/banner2.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="resources/banner3.jpg" class="d-block w-100" alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-target="#carouselExampleControls" data-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-target="#carouselExampleControls" data-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</button>
		</div>
	</section>
	<section class="container p-4">
		<h1 class="">Rent a car at low prices</h1>
		<p class="text-secondary">At our Car Rental Company, we believe
			that every journey should be accessible, enjoyable, and affordable.
			We're excited to announce our commitment to providing you with the
			best wheels for your adventures at prices that won't break the bank!</p>
	</section>
	<section class="bg-warning pt-2">
		<section class="container text-center my-5" id="dishes"
			data-aos="zoom-in" data-aos-duration="1000">
			<h3>Our Offers</h3>
			<div class="row">
				<div class="col-sm p-2">
					<div class="c-dishes-card">
						<img src="resources/img1.jpg" alt="">
						<h6> Budget-Friendly Option</h6>
						<p>Lorem ipsum dolor sit amet consectetur.</p>
					</div>
				</div>
				<div class="col-sm p-2">
					<div class="c-dishes-card">
						<img src="resources/img2.jpg" alt="">
						<h6> Transparent Pricing</h6>
						<p>Lorem ipsum dolor sit amet consectetur.</p>
					</div>
				</div>
				<div class="col-sm p-2">
					<div class="c-dishes-card">
						<img src="resources/img3.jpg" alt="">
						<h6>Special Discounts</h6>
						<p>Lorem ipsum dolor sit amet consectetur.</p>
					</div>
				</div>
				<div class="col-sm p-2">
					<div class="c-dishes-card">
						<img src="resources/img4.jpg" alt="">
						<h6>Flexible Dates</h6>
						<p>Lorem ipsum dolor sit amet consectetur.</p>
					</div>
				</div>
			</div>
			<button class="btn btn-primary my-5 c-get-in-touch"
				data-toggle="modal" data-target="#myModal">
				Get In Touch <i class="fa-solid fa-right-long"></i>
			</button>
		</section>
	</section>
	<section id="sec-1" class="p-5">
		<p>Insurance for customer.</p>
		<h6>
			Lorem ipsum dolor sit amet consectetur <br /> adipisicing elit
			Beatae, iure.
		</h6>
		<div class="my-4" data-aos="fade-down" data-aos-duration="1000">
			<p>
				<i class="fa-solid fa-car fa-2x"></i> <br /> <label>Dates</label>
			</p>
			<p>
				<i class="fa-solid fa-car-side fa-2x"></i> <br /> <label>Cars</label>
			</p>
			<p>
				<i class="fa-solid fa-key fa-2x"></i> <br /> <label>Extra</label>
			</p>
			<p class="border-0">
				<i class="fa-regular fa-id-card fa-2x"></i> <br /> <label>Customer Info</label>
			</p>
		</div>
		<button data-aos="fade-right" data-aos-duration="1000"
			class="btn btn-primary c-get-in-touch" data-toggle="modal"
			data-target="#myModal">
			Get In Touch <i class="fa-solid fa-right-long"></i>
		</button>
	</section>
	<section id="gallery" class="container my-5">
		<h3 class="mb-4">Lorem ipsum dolor sit amet.</h3>
		<a href="resources/banner1.jpg" data-toggle="lightbox"
			data-gallery="my-gallery"><img src="resources/banner1.jpg" alt=""></a>
		<a href="resources/banner2.jpg" data-toggle="lightbox"
			data-gallery="my-gallery"><img src="resources/banner2.jpg" alt=""></a>
		<a href="resources/banner3.jpg" data-toggle="lightbox"
			data-gallery="my-gallery"><img src="resources/banner3.jpg" alt=""></a>
		<a href="resources/banner4.jpg" data-toggle="lightbox"
			data-gallery="my-gallery"><img src="resources/banner4.jpg" alt=""></a>
		<a href="resources/banner5.jpg" data-toggle="lightbox"
			data-gallery="my-gallery"><img src="resources/banner5.jpg" alt=""></a>
	</section>
	<section class="container-fluid">
		<iframe
			src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3507.1933115583884!2d77.51309501414025!3d28.473724398003522!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390cea7e051fd949%3A0xefccd5003c9032b6!2sINCAPP!5e0!3m2!1sen!2sin!4v1673682797644!5m2!1sen!2sin"
			width="100%" height="300" style="border: 0;" allowfullscreen=""
			loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
	</section>
	<footer class="bg-dark p-5 text-center">
		<a href="index.jsp"> <img src="resources/logo.png" height="30px"
			alt=""> <span>CarRental</span>
		</a> <br />
		<p>&copy Rights Reserved</p>
		<p>
			<a href="https://www.facebook.com/incapp"><i
				class="fa-brands fa-facebook"></i></a> <a
				href="https://www.instagram.com/incapp.in"><i
				class="fa-brands fa-instagram"></i></a> <a
				href="https://www.youtube.com/incapp"><i
				class="fa-brands fa-youtube"></i></a>
		</p>
	</footer>
	<label id="top-button"><i class="fa-solid fa-circle-up fa-2x"></i></label>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-light">
					<h5 class="modal-title" id="exampleModalLabel">Get In Touch</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="AddEnquiry" method="post">
						<div class="row">
							<div class="col-sm mt-2">
								<input class="form-control" type="text" name="name"
									pattern="[a-zA-Z ]+" maxlength="20" placeholder="Your Name"
									required />
							</div>
							<div class="col-sm mt-2">
								<input class="form-control" type="tel" name="phone"
									pattern="[0-9]+" maxlength="10" minlength="10"
									placeholder="Your Phone" required />
							</div>
							<div class="col-sm mt-2">
								<button class="btn btn-primary">Submit</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="AdminModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-success">
					<h5 class="modal-title text-white" id="exampleModalLabel">Admin
						Login</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="AdminLogin" method="post">
						<div class="row">
							<div class="col-sm mt-2">
								<input class="form-control" type="text" name="id" maxlength="20"
									placeholder="Admin ID" required />
							</div>
							<div class="col-sm mt-2">
								<input class="form-control" type="password" name="password"
									maxlength="20" placeholder="Password" required />
							</div>
							<div class="col-sm mt-2">
								<button class="btn btn-primary">Login</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
  AOS.init();
  //script for scroll to top
  $("#top-button").click(function () {
    $("html, body").animate({ scrollTop: 0 }, 1000);
  });
  //script for light box
  $(document).on('click', '[data-toggle="lightbox"]', function (event) {
    event.preventDefault();
    $(this).ekkoLightbox();
  });
</script>
</script>

</html>