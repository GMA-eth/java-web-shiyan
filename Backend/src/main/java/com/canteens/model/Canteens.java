package main.java.com.canteens.model;
import java.security.Timestamp;

public class Canteens {
    private int canteenId;
    private String canteenName;
    private String location;
    private String businessHours;
    private String description;
    private Timestamp creationTime;

    public Canteens(int canteenId, String canteenName, String location, String businessHours, String description, Timestamp creationTime) {
        this.canteenId = canteenId;
        this.canteenName = canteenName;
        this.location = location;
        this.businessHours = businessHours;
        this.description = description;
        this.creationTime = creationTime;
    }


    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
