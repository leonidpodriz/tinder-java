package Servlets;

import Users.User;
import Users.UserService;
import Utils.CookieHelper;
import Utils.MessagesHelper;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChatServlet extends BaseServlet {
    private final UserService userService;
    private final Map<String, Object> data = new HashMap<>();

    public ChatServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getTemplateName() {
        return "chat.ftl";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        try {
            Optional<User> userByID = userService.getUserByID(id);

            if (userByID.isEmpty()) {
                resp.sendRedirect("/liked");
                return;
            }

            Optional<Cookie> cookie = CookieHelper.getCookie(req, "UUID");
            Optional<User> ownerUser = userService.getUserByID(cookie.get().getValue());
            data.put("user_name", userByID.get().NAME);
            data.put("user_avatar_uri", userByID.get().AVATAR_URI);
            data.put("own_user_avatar_uri", ownerUser.get().AVATAR_URI);
            data.put("own_user_name", ownerUser.get().NAME);
            data.put("to_user", userByID.get().ID);
            data.put("messages", MessagesHelper.getMessages(ownerUser.get().ID, userByID.get().ID));
            render(data, resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String to_user = req.getParameter("to_user");
        String from_user = CookieHelper.getCookie(req, "UUID").get().getValue();
        try {
            MessagesHelper.senMessage(from_user, to_user, message, LocalDateTime.now());
            resp.sendRedirect("/chat/?id=" + to_user);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
