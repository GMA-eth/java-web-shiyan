package main.java.com.canteens.controller;

import main.java.com.canteens.model.Dish;
import main.java.com.canteens.service.DishService;
import main.java.com.canteens.util.GsonUtil;
import main.java.com.canteens.util.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DishController", urlPatterns = { "/dish/create", "/dish/update", "/dish/delete", "/dish/all", "/dish/param" })
public class DishController extends HttpServlet {
    private DishService dishService;
    private JsonObject jsonObject;

    public DishController() {
        this.dishService = new DishService();
        this.jsonObject = new JsonObject();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String path = request.getServletPath();

        if (path.equals("/dish/all")) {
            dishService.getAllDishes(out);
        } else if (path.equals("/dish/param")) {
            int dishId = Integer.parseInt(request.getParameter("id"));
            dishService.getOneDish(out, dishId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String path = request.getServletPath();
        Dish dish = jsonObject.parseDishFromJson(request.getReader());

        if (path.equals("/dish/create")) {
            dishService.createDish(dish, out);
        } else if (path.equals("/dish/update")) {
            dishService.updateDishInfo(dish, out);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int dishId = Integer.parseInt(request.getParameter("id"));
        dishService.deleteDish(out, dishId);
    }
}
