package timeservlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(value = "/*")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<String> timeZones = new ArrayList<>();

        timeZones.add("UTC-12");
        timeZones.add("UTC-11");
        timeZones.add("UTC-10");
        timeZones.add("UTC-9");
        timeZones.add("UTC-8");
        timeZones.add("UTC-7");
        timeZones.add("UTC-6");
        timeZones.add("UTC-5");
        timeZones.add("UTC-3");
        timeZones.add("UTC-2");
        timeZones.add("UTC-1");
        timeZones.add("UTC");
        timeZones.add("UTC+1");
        timeZones.add("UTC+2");
        timeZones.add("UTC+3");
        timeZones.add("UTC+4");
        timeZones.add("UTC+5");
        timeZones.add("UTC+6");
        timeZones.add("UTC+8");
        timeZones.add("UTC+9");
        timeZones.add("UTC+10");
        timeZones.add("UTC+11");
        timeZones.add("UTC+12");
        timeZones.add("UTC+13");


        if (!req.getParameterMap().containsKey("timezone")) {
            chain.doFilter(req, res);
        }

        if (req.getParameterMap().containsKey("timezone")) {
            String utc = req.getParameter("timezone").replace(" ", "+");
            if (timeZones.contains(utc)) {
                chain.doFilter(req, res);
            } else {
                res.sendError(400, "Invalid timezone");
            }
        }
    }
}
