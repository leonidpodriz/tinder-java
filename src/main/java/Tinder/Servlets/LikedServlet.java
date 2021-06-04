package Tinder.Servlets;

import Tinder.Users.UserService;
import Tinder.Utils.CookieHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
            data.put("users", userService.getLikedUsersByID(CookieHelper.getCookie(req, "UUID").get().getValue()));
            render(data, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("error");
        }

    }
}
