package com.codeup.adlister.controllers;

import com.cedarsoftware.util.io.JsonWriter;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        // add list catgeories for select
        // set attribute that is list of categories
        List<Category> catList;
        catList = DaoFactory.getCatsDao().all();
        request.setAttribute("categories", catList);
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<String> multiCat = new ArrayList<>();

        String cat1 = request.getParameter("category");
        String cat2 = request.getParameter("categoryTwo");
        String catSubmit;

        if (cat2 == null){
           catSubmit = cat1;

        } else {
            catSubmit = cat1 + ", " + cat2;
        }



        Ad ad = new Ad(
                user.getId(),
                request.getParameter("title"),
                request.getParameter("description"),
                catSubmit

        );
        System.out.print(catSubmit);
        DaoFactory.getAdsDao().insert(ad);
        response.sendRedirect("/profile");
    }
}
