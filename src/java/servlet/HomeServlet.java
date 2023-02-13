package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HomeServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("username") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
    } else {
      request.setAttribute("username", session.getAttribute("username"));
      getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
              .forward(request, response);
    }
  }
}
