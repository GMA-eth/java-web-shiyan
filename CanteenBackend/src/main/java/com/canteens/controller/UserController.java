package main.java.com.canteens.controller;

import main.java.com.canteens.model.User;
import main.java.com.canteens.service.UserService;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import main.java.com.canteens.util.JsonObject;


@WebServlet(name = "UserController", urlPatterns = {"/user/create", "/user/update", "/user/login" })
public class UserController extends HttpServlet {
    private UserService userService;
    private JsonObject jsonObject;

    public UserController() {
        this.userService = new UserService();
        this.jsonObject = new JsonObject();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        pr.write("work done!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        User user = jsonObject.parseUserFromJson(request.getReader());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();

        if (path.equals("/user/create")) {
            userService.register(out, user);
        } else if (path.equals("/user/login")) {
            userService.login(out, user);
        } else if (path.equals("/user/update")) {
            userService.updateUserPassword(out, user);
        }
    }
}