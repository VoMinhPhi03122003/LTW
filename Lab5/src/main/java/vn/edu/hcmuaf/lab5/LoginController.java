package vn.edu.hcmuaf.lab5;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        User user = UserService.getInstance().checkLogin(email,pass);
        if(user!=null){
            HttpSession session = request.getSession();
            session.setAttribute("auth",user);

            response.sendRedirect("index.jsp");
        }else{
            request.setAttribute("error", "Bạn nhập sai Email hoặc Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }
}
