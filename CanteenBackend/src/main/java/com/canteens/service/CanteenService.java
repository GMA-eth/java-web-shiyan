package main.java.com.canteens.service;

import main.java.com.canteens.dao.CanteenDAO;
import main.java.com.canteens.dao.UserDAO;
import com.google.gson.Gson;
import main.java.com.canteens.model.Canteen;
import main.java.com.canteens.model.User;
import main.java.com.canteens.util.DatabaseUtil;
import main.java.com.canteens.util.GsonUtil;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CanteenService {
    private CanteenDAO canteenDAO;
    private Connection connection;
    private Gson gson;
    public CanteenService() {
        this.canteenDAO = new CanteenDAO();
        this.connection = DatabaseUtil.getConnection();
        this.gson =  new Gson();
    }


    public void createCanteen(Canteen canteen, PrintWriter out) {
        if (connection != null) {
            boolean result = canteenDAO.insertCanteen(canteen);

            resultFormat(result, out, "Canteen Created");

            tryConnection();
        }
    }

    public void getAllCanteens(PrintWriter out) {
        if (connection != null) {
           ArrayList<Canteen> result =  canteenDAO.allCanteens();

           String canteenData = null;

           if (result != null) {
               canteenData = GsonUtil.objectToJsonStr(result);
           }

           out.write(canteenData);
           out.flush();

           tryConnection();
        }
    }

    public void getOneCanteen(PrintWriter out, int canteenId) {
        if (connection != null) {
            Canteen result = canteenDAO.getOneCanteen(canteenId);

            String oneCanteenData = null;

            if (result != null) {
                oneCanteenData = GsonUtil.objectToJsonStr(result);
            }

            out.write(oneCanteenData);
            out.flush();

            tryConnection();
        }
    }

    public void updateCanteenInfo(Canteen canteen, PrintWriter out) {
        if (connection != null) {
            boolean result = canteenDAO.updateCanteen(canteen);

            resultFormat(result, out, "Canteen Updated");
            tryConnection();
        }
    }



    /**
     *  Canteen Service Utility Methods
     * */
    public void resultFormat(boolean result, PrintWriter out, String success) {
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

    public void tryConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}