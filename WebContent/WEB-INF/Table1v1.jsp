<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	
	<body>
		<table>
			<tr>
				<th>Jeux</th>
				<th>Joueur</th>
				<th>ELO</th>
			</tr>
			
		</table>
		
		<%
			Object[][] data = (Object[][]) request.getAttribute("data");
			
			for(Object[] line : data) {
				out.println("<tr>");
				
				for(Object object : line) {
					out.println("<td>");
					out.println(object.toString());
					out.println("<td>");
				}
				
				out.println("</tr>");
			}
		%>
	</body>
</html>