package main.java.com.canteens.dao;

import main.java.com.canteens.model.Review;
import main.java.com.canteens.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDAO {

    private Connection connection;

    public ReviewDAO() {
        this.connection = DatabaseUtil.getConnection();
    }

    public boolean insertReview(Review review) {
        String sql = "INSERT INTO Reviews (UserID, DishID, Rating, Comment, CreationTime) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, review.getUserId());
            statement.setInt(2, review.getDishId());
            statement.setInt(3, review.getRating());
            statement.setString(4, review.getComment());
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Review> allReviews() {
        ArrayList<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Reviews";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Review review = extractReviewFromResultSet(resultSet);
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public Review getOneReview(int reviewId) {
        Review review = null;
        String sql = "SELECT * FROM Reviews WHERE ReviewID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reviewId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                review = extractReviewFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    public boolean updateReview(Review review) {
        String sql = "UPDATE Reviews SET UserID=?, DishID=?, Rating=?, Comment=? WHERE ReviewID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, review.getUserId());
            statement.setInt(2, review.getDishId());
            statement.setInt(3, review.getRating());
            statement.setString(4, review.getComment());
            statement.setInt(5, review.getReviewId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteReview(int reviewId) {
        String sql = "DELETE FROM Reviews WHERE ReviewID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reviewId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Review extractReviewFromResultSet(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setReviewId(resultSet.getInt("ReviewID"));
        review.setUserId(resultSet.getInt("UserID"));
        review.setDishId(resultSet.getInt("DishID"));
        review.setRating(resultSet.getInt("Rating"));
        review.setComment(resultSet.getString("Comment"));
        review.setCreationTime(resultSet.getTimestamp("CreationTime"));
        return review;
    }
}
