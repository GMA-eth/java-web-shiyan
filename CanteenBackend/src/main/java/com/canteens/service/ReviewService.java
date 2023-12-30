package main.java.com.canteens.service;

import main.java.com.canteens.dao.ReviewDAO;
import main.java.com.canteens.model.Review;
import main.java.com.canteens.util.DatabaseUtil;
import main.java.com.canteens.util.GsonUtil;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReviewService {
    private ReviewDAO reviewDAO;
    private Connection connection;

    public ReviewService() {
        this.reviewDAO = new ReviewDAO();
        this.connection = DatabaseUtil.getConnection();
    }

    public void createReview(Review review, PrintWriter out) {
        if (connection != null) {
            boolean result = reviewDAO.insertReview(review);
            resultFormat(result, out, "Review Created");
            tryConnection();
        }
    }

    public void getAllReviews(PrintWriter out) {
        if (connection != null) {
            ArrayList<Review> result = reviewDAO.allReviews();
            String reviewData = null;
            if (result != null) {
                reviewData = GsonUtil.objectToJsonStr(result);
            }
            out.write(reviewData);
            out.flush();
            tryConnection();
        }
    }

    public void getOneReview(PrintWriter out, int reviewId) {
        if (connection != null) {
            Review result = reviewDAO.getOneReview(reviewId);
            String oneReviewData = null;
            if (result != null) {
                oneReviewData = GsonUtil.objectToJsonStr(result);
            }
            out.write(oneReviewData);
            out.flush();
            tryConnection();
        }
    }

    public void updateReviewInfo(Review review, PrintWriter out) {
        if (connection != null) {
            boolean result = reviewDAO.updateReview(review);
            resultFormat(result, out, "Review Updated");
            tryConnection();
        }
    }

    public void deleteReview(PrintWriter out, int reviewId) {
        if (connection != null) {
            boolean result = reviewDAO.deleteReview(reviewId);
            resultFormat(result, out, "Review Deleted");
            tryConnection();
        }
    }

    /**
     * Review Service Utility Methods
     */
    private void resultFormat(boolean result, PrintWriter out, String message) {
        Map<String, String> resMap = new HashMap<>();
        if (result) {
            resMap.put("code", "0");
            resMap.put("msg", message);
        } else {
            resMap.put("code", "001");
            resMap.put("msg", "Error occurred");
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

