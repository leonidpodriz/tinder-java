package Servlets;

import Users.UserService;
import Utils.CookieHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LikedServlet extends BaseServlet {
    private final UserService userService;
    private final Map<String, Object> data = new HashMap<>();

    public LikedServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getTemplateName() {
        return "homepage.ftl";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Optional<Cookie> cookie = CookieHelper.getCookie(req, "UUID");
            if (cookie.isPresent()) {
                data.put("users", userService.getLikedUsersByID(cookie.get().getValue()));
                render(data,resp);
                return;
            }
            resp.sendRedirect("/login");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("error");
        }

    }
}
