package main.java.com.canteens.controller;

import main.java.com.canteens.model.Review;
import main.java.com.canteens.service.ReviewService;
import main.java.com.canteens.util.GsonUtil;
import main.java.com.canteens.util.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReviewController", urlPatterns = { "/review/create", "/review/update", "/review/delete", "/review/all", "/review/param" })
public class ReviewController extends HttpServlet {
    private ReviewService reviewService;
    private JsonObject jsonObject;

    public ReviewController() {
        this.reviewService = new ReviewService();
        this.jsonObject = new JsonObject();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String path = request.getServletPath();

        if (path.equals("/review/all")) {
            reviewService.getAllReviews(out);
        } else if (path.equals("/review/param")) {
            int reviewId = Integer.parseInt(request.getParameter("id"));
            reviewService.getOneReview(out, reviewId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String path = request.getServletPath();
        Review review = jsonObject.parseReviewFromJson(request.getReader());

        if (path.equals("/review/create")) {
            reviewService.createReview(review, out);
        } else if (path.equals("/review/update")) {
            reviewService.updateReviewInfo(review, out);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int reviewId = Integer.parseInt(request.getParameter("id"));
        reviewService.deleteReview(out, reviewId);
    }
}
