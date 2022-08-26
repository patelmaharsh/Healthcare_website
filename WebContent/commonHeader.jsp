
	<%@page import="com.pmaharsh.onlineDoctorAppointmentSystem.model.Doctor"%>
<%@page import="com.pmaharsh.onlineDoctorAppointmentSystem.model.User"%>
<%
	User userLogins = (User)session.getAttribute("loginUser");
	Doctor doctorLogins = (Doctor)session.getAttribute("loginDoctor");
	%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/OnlineDoctorAppointmentSystem"><strong>MediCare</strong></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
      	<% if(userLogins!=null){ %>
        <a class="nav-link" href="/OnlineDoctorAppointmentSystem/welcome.jsp">Home</a>
        <%} else if(doctorLogins!=null){ %>
        <a class="nav-link" href="/OnlineDoctorAppointmentSystem/doctorhome.jsp">Home</a>
        <%} %>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">About</a>
      </li>
      <% if(userLogins!=null){ %>
      <li class="nav-item active ml-4"> 
      	<a class="nav-link"><strong>Welcome <%=userLogins.getName() %></strong></a>
      </li>
      <%} else if(doctorLogins!=null){ %>
      <li class="nav-item active ml-4">
      	<a class="nav-link" ><strong>Welcome <%= doctorLogins.getName() %></strong></a>
      </li>
      <%} %>
    </ul>
    <% if(userLogins!=null || doctorLogins!=null) {%>
    <form class="form-inline my-2 my-lg-0" action="logout">
      <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Logout</button>
    </form>
    <% } %>
  </div>
</nav>