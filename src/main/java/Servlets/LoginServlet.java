package Servlets;

import Users.User;
import Users.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginServlet extends BaseServlet {
    private final UserService userService;

    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getTemplateName() {
        return "login.ftl";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        render(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            Optional<User> user = userService.getUserByEmail(email);
            if(user.isPresent() && BCrypt.checkpw(password, user.get().PASSWORD)){
                Cookie c = new Cookie("UUID", user.get().ID);
                c.setMaxAge(60 * 60 * 24);
                resp.addCookie(c);
                resp.sendRedirect("/users");
                return;
            }
            resp.sendRedirect("/login");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
