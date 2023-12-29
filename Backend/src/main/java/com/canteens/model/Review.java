package main.java.com.canteens.model;
import java.security.Timestamp;


public class Review {
    private int reviewId;
    private int userId;
    private int dishId;
    private int rating;
    private String comment;
    private Timestamp creationTime;

    public Review(int reviewId, int userId, int dishId, int rating, String comment, Timestamp creationTime) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.dishId = dishId;
        this.rating = rating;
        this.comment = comment;
        this.creationTime = creationTime;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
