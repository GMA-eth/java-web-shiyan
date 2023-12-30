package main.java.com.canteens.service;

import com.google.gson.Gson;
import main.java.com.canteens.dao.UserDAO;
import main.java.com.canteens.model.User;
import main.java.com.canteens.util.DatabaseUtil;
import main.java.com.canteens.util.GsonUtil;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class UserService {
    private UserDAO userDAO;
    private Connection connection;
    private Gson gson;
    public UserService() {
        this.userDAO = new UserDAO();
        this.connection = DatabaseUtil.getConnection();
        this.gson =  new Gson();
    }

    public boolean createUser(User user) {
        return userDAO.insertUser(user);
    }

    public User userLogin(User user) {
        return userDAO.login(user);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public void register(PrintWriter out, User user) {

        if (connection != null) {
            boolean result = createUser(user);

            Map<String, String> resMap = new HashMap<>();
            if (result) {
                resMap.put("code", "0");
                resMap.put("msg", "User created successfully.");
            } else {
                resMap.put("code", "001");
                resMap.put("msg", "error");
            }

            String jsonRes = GsonUtil.objectToJsonStr(resMap);
            out.write(jsonRes);
            out.flush();

            tryConnection();
        }


    }

    public void login(PrintWriter out, User user) {
        if (connection != null) {
            User result = userLogin(user);

            String userData = null;
            if (result != null) {
                userData = GsonUtil.objectToJsonStr(result);
            }

            out.write(userData);
            out.flush();

            tryConnection();
        }
    }

    public void updateUserPassword(PrintWriter out, User user) {
        if (connection != null) {
            boolean result = updateUser(user);

            Map<String, String> userUpdate = new HashMap<>();
            if (result) {
                userUpdate.put("code", "0");
                userUpdate.put("msg", "Updated");
            } else {
                userUpdate.put("code", "001");
                userUpdate.put("msg", "error");
            }

            String jsonRes = GsonUtil.objectToJsonStr(userUpdate);
            out.write(jsonRes);
            out.flush();

            tryConnection();
        }
    }

    public void noService(PrintWriter out) {
        Map<String, String> noSer = new HashMap<>();

        noSer.put("code", "0");
        noSer.put("msg", "No Service");

        String noSerJson = GsonUtil.objectToJsonStr(noSer);
        out.write(noSerJson);
        out.flush();
        return;
    }

    public void tryConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
