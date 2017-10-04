package servlets;

import beans.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        request.setAttribute("message",message);
        Cookie rememberUsername = getCookie(request,"rememberUsername");
        if(rememberUsername!=null){
            request.setAttribute("rememberUsername", rememberUsername.getValue());
        }
        if("Logged Out".equals(message)){
            request.getSession().invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else if (request.getSession().getAttribute("user")!=null){
            response.sendRedirect("/home");
        }
        else{
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user").trim();
        String password = request.getParameter("pass").trim();
        if(username.isEmpty() || password.isEmpty()){
            response.sendRedirect("/?message=Both Fields Required");
        }
        else{
            User user = new User(username,password);
            if(UserService.login(user)){
                if (request.getParameter("remember")!=null){
                    Cookie c = new Cookie("rememberUsername", username);
                    c.setMaxAge(-1);
                    response.addCookie(c);
                }
                else{
                    Cookie c = getCookie(request,"rememberUsername");
                    if (c!=null){
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
                user.setPassword(null);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("/home");
            }
            else{
                response.sendRedirect("/?message=Invalid Login");
            }
        }
    }
    
    private Cookie getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if (cookies==null)
            return null;
        for (Cookie cookie : cookies)
            if (cookie.getName().equals(name))
                return cookie;
        return null;
    }
    
}
