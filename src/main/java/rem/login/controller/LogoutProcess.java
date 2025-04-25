package rem.login.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logoutProcess.do")
public class LogoutProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hSession = request.getSession();
		
		if(hSession != null && hSession.getAttribute("login") != null) {
			System.out.println("logout");
			hSession.removeAttribute("loginInfo");
			hSession.removeAttribute("login");
			response.sendRedirect(request.getContextPath() + "/mainPage.do");
		}
	}

}
