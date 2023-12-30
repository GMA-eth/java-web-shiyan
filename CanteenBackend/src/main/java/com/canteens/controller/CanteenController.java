package main.java.com.canteens.controller;

import main.java.com.canteens.model.Canteen;
import main.java.com.canteens.model.User;
import main.java.com.canteens.service.CanteenService;
import main.java.com.canteens.service.UserService;
import main.java.com.canteens.util.GsonUtil;
import main.java.com.canteens.util.JsonObject;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(name = "CanteenController", urlPatterns = { "/canteen/create", "/canteen/update", "/canteen/all", "/canteen/param" })
public class CanteenController extends HttpServlet {
    private CanteenService canteenService;
    private JsonObject jsonObject;

    public CanteenController() {
        this.canteenService = new CanteenService();
        this.jsonObject = new JsonObject();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String path = request.getServletPath();

        if (path.equals("/canteen/all")) {
            canteenService.getAllCanteens(out);
        } else if (path.equals("/canteen/param")) {
            int canteenId = Integer.parseInt(request.getParameter("id"));
            canteenService.getOneCanteen(out, canteenId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Canteen canteen = jsonObject.parseCanteenFromJson(request.getReader());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();


        if (path.equals("/canteen/create")) {
            canteenService.createCanteen(canteen, out);
        } else  if (path.equals("/canteen/update")) {
            canteenService.updateCanteenInfo(canteen, out);
        }
    }
}
