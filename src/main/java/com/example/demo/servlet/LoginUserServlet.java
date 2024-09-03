package com.example.demo.servlet;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.repository.SqlUsersRepository;
import com.example.demo.service.UsersService;
import com.example.demo.service.UsersServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginUserServlet extends HttpServlet {
  private static final int MAX_ATTEMPTS = 3;
  private static final long LOCK_TIME = 60 * 1000;

  private UsersService usersService;

  public void init() {
    usersService = new UsersServiceImpl(new SqlUsersRepository());
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    Long lockTime = (Long) session.getAttribute("lockTime");
    if (lockTime != null) {
      if (System.currentTimeMillis() >= lockTime) {
        session.removeAttribute("lockTime");
        session.removeAttribute("attempts");
      } else {
        request.setAttribute("errorMessage", "Your account is locked. Please try again later.");
        request.setAttribute("disabled", "true");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
        return;
      }
    }

    UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
    try {
      Optional<UserProjectRelation> user = usersService.authenticateUser(userLoginDTO);

      if (user.isPresent()) {
        session.removeAttribute("attempts");
        session.removeAttribute("lockTime");
        request.getSession().setAttribute("userRole", user.get().getRoles().get(0));
        response.sendRedirect(request.getContextPath() + "/welcomePage.jsp");
      } else {
        Integer attempts = (Integer) session.getAttribute("attempts");
        if (attempts == null) {
          attempts = 0;
        }
        attempts++;
        session.setAttribute("attempts", attempts);

        if (attempts >= MAX_ATTEMPTS) {
          session.setAttribute("lockTime", System.currentTimeMillis() + LOCK_TIME);
          request.setAttribute("errorMessage", "Your account has been locked for 5 minutes due to too many failed login attempts.");
          request.setAttribute("disabled", "true");
        } else {
          request.setAttribute("errorMessage", "Invalid login credentials. You have " + (MAX_ATTEMPTS - attempts) + " attempt(s) left.");
          request.setAttribute("disabled", "false");
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      request.setAttribute("errorMessage", "An error occurred. Please try again.");
      request.setAttribute("disabled", "false");
      request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
  }
}
