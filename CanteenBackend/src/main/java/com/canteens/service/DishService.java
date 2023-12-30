package main.java.com.canteens.service;

import main.java.com.canteens.dao.DishDAO;
import main.java.com.canteens.model.Dish;
import main.java.com.canteens.util.DatabaseUtil;
import main.java.com.canteens.util.GsonUtil;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DishService {
    private DishDAO dishDAO;
    private Connection connection;


    public DishService() {
        this.dishDAO = new DishDAO();
        this.connection = DatabaseUtil.getConnection();
    }

    public void createDish(Dish dish, PrintWriter out) {
        if (connection != null) {
            boolean result = dishDAO.insertDish(dish);
            resultFormat(result, out, "Dish Created");
            tryConnection();
        }
    }

    public void getAllDishes(PrintWriter out) {
        if (connection != null) {
            ArrayList<Dish> result = dishDAO.allDishes();
            String dishData = null;
            if (result != null) {
                dishData = GsonUtil.objectToJsonStr(result);
            }
            out.write(dishData);
            out.flush();
            tryConnection();
        }
    }

    public void getOneDish(PrintWriter out, int dishId) {
        if (connection != null) {
            Dish result = dishDAO.getOneDish(dishId);
            String oneDishData = null;
            if (result != null) {
                oneDishData = GsonUtil.objectToJsonStr(result);
            }
            out.write(oneDishData);
            out.flush();
            tryConnection();
        }
    }

    public void updateDishInfo(Dish dish, PrintWriter out) {
        if (connection != null) {
            boolean result = dishDAO.updateDish(dish);
            resultFormat(result, out, "Dish Updated");
            tryConnection();
        }
    }

    public void deleteDish(PrintWriter out, int dishId) {
        if (connection != null) {
            boolean result = dishDAO.deleteDish(dishId);
            resultFormat(result, out, "Dish Deleted");
            tryConnection();
        }
    }

    /**
     * Dish Service Utility Methods
     */
    private void resultFormat(boolean result, PrintWriter out, String success) {
        Map<String, String> resMap = new HashMap<>();
        if (result) {
            resMap.put("code", "0");
            resMap.put("msg", success);
        } else {
            resMap.put("code", "001");
            resMap.put("msg", "error");
        }

        String jsonRes = GsonUtil.objectToJsonStr(resMap);
        out.write(jsonRes);
        out.flush();
    }

    private void tryConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
