
<% 
	String errorMessage = (String)session.getAttribute("error");
	if(errorMessage!=null){
%>
<div class="container mt-4">
	<div class="alert alert-warning" role="alert">
	  <%=errorMessage %>
	</div>
</div>
<% } session.setAttribute("error", null);  %>
