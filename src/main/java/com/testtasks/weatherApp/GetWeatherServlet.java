package com.testtasks.weatherApp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet
public class GetWeatherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("weatherService") != null && request.getParameter("city") != null) {

            ArrayList<Cookie> cookies = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("weatherService") && c.getName().equals("city")).collect(Collectors.toCollection(ArrayList::new));
            if(!cookies.isEmpty()) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("weatherService")) {
                        if (!cookie.getValue().equals(request.getParameter("weatherService")))
                            cookie.setValue(request.getParameter("weatherService"));
                    }
                    if (cookie.getName().equals("city")) {
                        if (!cookie.getValue().equals(request.getParameter("city")))
                            cookie.setValue(request.getParameter("city"));
                    }
                }
            }
            else {
                response.addCookie(new Cookie("weatherService", request.getParameter("weatherService")));
                response.addCookie(new Cookie("city", request.getParameter("city")));
            }

            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("weatherService", request.getParameter("weatherService"));
            requestParams.put("city", request.getParameter("city"));

            String encodedURL = requestParams.keySet().stream()
                    .map(key -> key + "=" + encodeValue(requestParams.get(key)))
                    .collect(Collectors.joining("&", request.getContextPath() + "/weather?", ""));

            response.sendRedirect(encodedURL);
        }
    }

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
