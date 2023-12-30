package main.java.com.canteens.dao;

import main.java.com.canteens.model.Dish;
import main.java.com.canteens.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;

public class DishDAO {

    private Connection connection;

    public DishDAO() {
        this.connection = DatabaseUtil.getConnection();
    }

    public boolean insertDish(Dish dish) {
        String sql = "INSERT INTO Dishes (DishName, Price, DishType, CreationTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dish.getDishName());
            statement.setDouble(2, dish.getPrice());
            statement.setString(3, dish.getDishType());
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Dish> allDishes() {
        ArrayList<Dish> dishes = new ArrayList<>();
        String sql = "SELECT * FROM Dishes";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Dish dish = extractDishFromResultSet(resultSet);
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishes;
    }

    public Dish getOneDish(int dishId) {
        Dish dish = null;
        String sql = "SELECT * FROM Dishes WHERE DishID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dishId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dish = extractDishFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }

    public boolean updateDish(Dish dish) {
        String sql = "UPDATE Dishes SET CanteenID=?, DishName=?, Price=?, DishType=? WHERE DishID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dish.getCanteenId());
            statement.setString(2, dish.getDishName());
            statement.setDouble(3, dish.getPrice());
            statement.setString(4, dish.getDishType());
            statement.setInt(5, dish.getDishId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDish(int dishId) {
        String sql = "DELETE FROM Dishes WHERE DishID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dishId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Dish extractDishFromResultSet(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setDishId(resultSet.getInt("DishID"));
        dish.setCanteenId(resultSet.getInt("CanteenID"));
        dish.setDishName(resultSet.getString("DishName"));
        dish.setPrice(resultSet.getDouble("Price"));
        dish.setDishType(resultSet.getString("DishType"));
        dish.setCreationTime(resultSet.getTimestamp("CreationTime"));
        return dish;
    }
}
