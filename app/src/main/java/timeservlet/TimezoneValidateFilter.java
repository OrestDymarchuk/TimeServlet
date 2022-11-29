package timeservlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/*")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req.getParameterMap().containsKey("timezone")) {
            String utc = req.getParameter("timezone").replace(" ", "+");
            if (utc.equals("UTC+2")) {
                chain.doFilter(req, res);
            } else {
                res.sendError(400, "Invalid timezone");
            }
        }
        chain.doFilter(req, res);
    }
}
