package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.AccountService;
import user.User;


public class LoginServlet extends HttpServlet {


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String logout = request.getParameter("logout");
    if (logout != null) {
      HttpSession session = request.getSession();
      session.invalidate();
      request.setAttribute("message", "You have successfully logged out.");
      getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
              .forward(request, response);
    } else {
      HttpSession session = request.getSession();
      String username = (String) session.getAttribute("username");
      if (username != null) {
        response.sendRedirect(request.getContextPath() + "/home");
      } else {
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    
    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
      request.setAttribute("message", "Username and password are required.");
      getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
              .forward(request, response);
    }
    else {
     
        AccountService accountService = new AccountService();
      User user = accountService.login(username, password);
      
      if (user != null) {
       
          HttpSession session = request.getSession();
        session.setAttribute("username", user.getUsername());
        response.sendRedirect(request.getContextPath() + "/home");
        
      }
      else {
        request.setAttribute("message", "Invalid username or password.");
        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
      }
    }
  }
}
