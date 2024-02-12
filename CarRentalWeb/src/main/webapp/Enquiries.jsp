<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.incapp.modals.DAO"%>
<%
	String name=(String)session.getAttribute("name");
	if(name==null){
		session.setAttribute("msg", "Please Login First!");
		response.sendRedirect("index.jsp");
	}else{
%>


<!DOCTYPE html>
<html>

<head>
  <title>CarRental</title>
  <link rel="icon" href="resources/logo.png" />

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />

  <!-- font-awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
    integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/js/all.min.js"
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
  <nav class="navbar navbar-expand-sm container my-3">
    <a href="AdminHome.jsp" class="navbar-brand">
      <img src="resources/logo.png" height="35px" alt="">
      <span>CarRental</span>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#my-navbar">
      <i class="fa-solid fa-bars"></i>
    </button>
    <div class="collapse navbar-collapse" id="my-navbar">
      <ul class="navbar-nav mx-auto p-2">
        <li>
          <a href="AdminHome.jsp">Home</a>
        </li>
        <li>
          <a href="Enquiries.jsp">Enquiries</a>
        </li>
      </ul>
      <label class="bg-primary text-white p-2 rounded mt-2" href=""> Welcome: <b><%= name %></b> </label>
      <a class="bg-danger text-white p-2 rounded" href="Logout"> Logout </a>
    </div>
  </nav>
  <section class="container p-4">
    	<h3 class="bg-info p-3 text-center text-white">All Enquiries:</h3>
    	<%
		DAO db=new DAO();
    		ArrayList<HashMap> enquiries=db.getAllEnquiries();
    		db.closeConnection();
    		for(HashMap<String,String> enquiry: enquiries){
    	%>
		    	<p class="bg-warning p-3 m-2"> 
		    	ID: <b><%= enquiry.get("id")%></b> 
		    	Name: <b><%= enquiry.get("name")%></b>
		    	Phone: <b><%= enquiry.get("phone")%></b>
		    	Status: <b><%= enquiry.get("status")%></b>
		    	<%	
		    		String status=(String)enquiry.get("status");
		    		if(status.equalsIgnoreCase("Pending")){
		    %>
		    			<a class="btn btn-sm btn-danger" href="ChangeEnquiryStatus?id=<%=enquiry.get("id")%>">Done</a>
		    <%			
		    		}
		    	%>
		    	</p>	
    	<%		
    		}
	%>
  </section>
  <footer class="bg-dark p-5 text-center">
    <a href="index.jsp">
      <img src="resources/logo.png" height="30px" alt="">
      <span>CarRental</span>
    </a>
    <br />
    <p>&copy Rights Reserved</p>
    <p>
      <a href="https://www.facebook.com/incapp"><i class="fa-brands fa-facebook"></i></a>
      <a href="https://www.instagram.com/incapp.in"><i class="fa-brands fa-instagram"></i></a>
      <a href="https://www.youtube.com/incapp"><i class="fa-brands fa-youtube"></i></a>
    </p>
  </footer>
  <label id="top-button"><i class="fa-solid fa-circle-up fa-2x"></i></label>

  <!-- Modal -->
  <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header bg-light">
          <h5 class="modal-title" id="exampleModalLabel">Get In Touch</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form >
            <div class="row">
              <div class="col-sm mt-2">
                <input class="form-control" type="text" name="Name" pattern="[a-zA-Z ]+" maxlength="20"
                  placeholder="Your Name" required />
              </div>
              <div class="col-sm mt-2">
                <input class="form-control" type="tel" name="Phone" pattern="[0-9]+" maxlength="10" minlength="10"
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
  <div class="modal fade" id="AdminModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header bg-success">
          <h5 class="modal-title text-white" id="exampleModalLabel">Admin Login</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
                <input class="form-control" type="password" name="password" maxlength="20" 
                  placeholder="Password" required />
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

<%
}
%>