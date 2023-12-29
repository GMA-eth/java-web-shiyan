package main.java.com.canteens.model;
import java.math.BigDecimal;
import java.security.Timestamp;

public class Dishes {
    private int dishId;
    private int canteenId;
    private String dishName;
    private BigDecimal price;
    private String dishType;
    private Timestamp creationTime;

    public Dishes(int dishId, int canteenId, String dishName, BigDecimal price, String dishType, Timestamp creationTime) {
        this.dishId = dishId;
        this.canteenId = canteenId;
        this.dishName = dishName;
        this.price = price;
        this.dishType = dishType;
        this.creationTime = creationTime;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
