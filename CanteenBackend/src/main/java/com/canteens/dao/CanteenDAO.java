package main.java.com.canteens.dao;

import main.java.com.canteens.model.Canteen;
import main.java.com.canteens.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CanteenDAO {

    private Connection connection;

    public CanteenDAO() {
        this.connection = DatabaseUtil.getConnection();
    }

    public boolean insertCanteen(Canteen canteen) {
        String sql = "INSERT INTO canteens (CanteenName, Location, BusinessHours, Description, CreationTime) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, canteen.getCanteenName());
            statement.setString(2, canteen.getLocation());
            statement.setString(3, canteen.getBusinessHours());
            statement.setString(4, canteen.getDescription());
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Canteen> allCanteens() {
        ArrayList<Canteen> resCanteen = new ArrayList<>();
        String sql = "SELECT * FROM canteens";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Canteen canteen = extractCanteenFromResultSet(resultSet);
                resCanteen.add(canteen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resCanteen;
    }

    public Canteen getOneCanteen(int canteenId) {
        Canteen resCanteen = null;
        String sql = "SELECT * FROM canteens WHERE CanteenID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, canteenId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               resCanteen = extractCanteenFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resCanteen;
    }

    public boolean updateCanteen(Canteen canteen) {
        String sql = "UPDATE Canteens SET CanteenName=?, Location=?, BusinessHours=?, Description=? WHERE CanteenID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, canteen.getCanteenName());
            statement.setString(2, canteen.getLocation());
            statement.setString(3, canteen.getBusinessHours());
            statement.setString(4, canteen.getDescription());
            statement.setInt(5, canteen.getCanteenId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private Canteen extractCanteenFromResultSet(ResultSet resultSet) throws SQLException {
        Canteen canteen = new Canteen();
        canteen.setCanteenId(resultSet.getInt("CanteenID"));
        canteen.setCanteenName(resultSet.getString("CanteenName"));
        canteen.setLocation(resultSet.getString("Location"));
        canteen.setBusinessHours(resultSet.getString("BusinessHours"));
        canteen.setDescription(resultSet.getString("Description"));
        canteen.setCreationTime(resultSet.getTimestamp("CreationTime"));
        return canteen;
    }


}
