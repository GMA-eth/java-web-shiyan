package main.java.com.canteens.controller;

import main.java.com.canteens.dao.UserDAO;
import main.java.com.canteens.model.User;
import main.java.com.canteens.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import main.java.com.canteens.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserController", value = "/UserController")
public class UserController extends HttpServlet {
    private UserService userService;
    Connection connection = DatabaseUtil.getConnection();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        User user = parseUserFromJson(request.getReader());

        if (connection != null) {
            UserDAO userDAO = new UserDAO(connection);
            boolean result = userService.createUser(user);
            Gson gson = new Gson();

            Map<String, String> resMap = new HashMap<>();

            if (result) {
                resMap.put("code", "0");
                resMap.put("msg", "User created successfully.");
            } else {
                resMap.put("code", "001");
                resMap.put("msg", "error");
            }

            String jsonRes = gson.toJson(resMap);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(jsonRes);
            out.flush();

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private User parseUserFromJson(BufferedReader reader) {
       Gson gson = new Gson();
       return gson.fromJson(reader, User.class);
    }
}
