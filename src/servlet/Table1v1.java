package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import structs.GameStructure.OneVsOne;

public class Table1v1 extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		request.setAttribute( "data", OneVsOne.getAll1Vs1Game());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Table1v1.jsp").forward(request, response);
	}
}