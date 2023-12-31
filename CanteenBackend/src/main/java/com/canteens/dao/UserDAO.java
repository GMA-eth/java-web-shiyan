package main.java.com.canteens.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.com.canteens.model.User;
import main.java.com.canteens.util.DatabaseUtil;

public class UserDAO {
    private Connection connection;
    private final String ADMIN = "0[U;4H*o366e[N!qj97Y?c2#3{=hTNtwQZ><]0w?3?]Sc)9M*5";
    private final String USER = "5&|F<cpus]:G{-);+5yoB!;h4v=-&sj£t-r.j({R!8ZcHm7F2[";
    private final String MERCHANT = "Us2Vq4P$}X6mK+(O7~X[ek5G2x6U?Yu:V~U0h;+Y7WN{}Dz|vc";

    public UserDAO() {
        this.connection = DatabaseUtil.getConnection();
    }
    public boolean insertUser(User user) {
        String sql = "INSERT INTO Users (Email, Password, Role, CreationTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(User user) {
        User resUser = null;
        String sql = "SELECT * FROM Users WHERE Email= ? and Password=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resUser = extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resUser;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET Password = ? WHERE Email=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("UserID"));
        user.setEmail(resultSet.getString("Email"));
        user.setRole(resultSet.getString("Role"));
        user.setCreationTime(resultSet.getTimestamp("CreationTime"));

        if (user.getRole().equals("admin")) {
            user.setRole(ADMIN);
        } else if (user.getRole().equals("user")) {
            user.setRole(USER);
        } else if (user.getRole().equals("merchant")) {
            user.setRole(MERCHANT);
        }

        return user;
    }
}
