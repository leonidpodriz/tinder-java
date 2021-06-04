package Servlets;

import Users.User;
import Users.UserService;
import Utils.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsersServlet extends BaseServlet {
    private final Map<String, String> data = new HashMap<>();
    private final UserService userService;

    public UsersServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getTemplateName() {
        return "like-page.ftl";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Optional<Cookie> cookie = CookieHelper.getCookie(req, "UUID");
            Optional<User> user = userService.getUserForLike(cookie.get().getValue());
            if (user.isPresent()) {
                data.put("user_name", user.get().NAME);
                data.put("user_avatar", user.get().AVATAR_URI);
                data.put("id", user.get().ID);
                render(data, resp);
                return;
            }
            resp.sendRedirect("/liked");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Cookie> cookie = CookieHelper.getCookie(req, "UUID");
        String id = req.getParameter("id");
        String likeStr = req.getParameter("like");
        try {
            userService.setLike(cookie.get().getValue(), id, Boolean.parseBoolean(likeStr));
            resp.sendRedirect("/users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
