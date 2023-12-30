package main.java.com.canteens.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {

    private static final Gson gson = new Gson();

    public static <T> String objectToJsonStr(T object) {
        return gson.toJson(object);
    }

    public static <T> String objectsToJsonStr(List<T> objects) {
        return gson.toJson(objects);
    }

    public static <T> T jsonStrToObject(String jsonStr, Class<T> cla) {
        return gson.fromJson(jsonStr, cla);
    }

    public static <T> List<T> jsonStrToObjects(String jsonStr, Class<T> cla) {
        try {
            TypeToken<List<T>> token = (TypeToken<List<T>>) TypeToken.getParameterized(List.class, cla);
            return gson.fromJson(jsonStr, token.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
