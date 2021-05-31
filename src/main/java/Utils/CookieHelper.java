package Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CookieHelper {
    public static Optional<Cookie> getCookie(HttpServletRequest req, String name){
        return Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("UUID")).findFirst();

    }
}
