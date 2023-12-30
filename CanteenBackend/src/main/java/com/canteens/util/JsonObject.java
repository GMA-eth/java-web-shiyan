package main.java.com.canteens.util;

import main.java.com.canteens.model.Canteen;
import main.java.com.canteens.model.Dish;
import main.java.com.canteens.model.Review;
import main.java.com.canteens.model.User;

import java.io.BufferedReader;
import java.util.stream.Collectors;

public class JsonObject {

    public JsonObject() {}

    public User parseUserFromJson(BufferedReader reader) {
        return GsonUtil.jsonStrToObject(reader.lines().collect(Collectors.joining()), User.class);
    }

    public Canteen parseCanteenFromJson(BufferedReader reader) {
        return GsonUtil.jsonStrToObject(reader.lines().collect(Collectors.joining()), Canteen.class);
    }

    public Dish parseDishFromJson(BufferedReader reader) {
        return GsonUtil.jsonStrToObject(reader.lines().collect(Collectors.joining()), Dish.class);
    }

    public Review parseReviewFromJson(BufferedReader reader) {
        return GsonUtil.jsonStrToObject(reader.lines().collect(Collectors.joining()), Review.class);
    }

}
