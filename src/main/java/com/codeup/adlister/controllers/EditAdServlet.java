package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/ads/edit")
public class EditAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // is the below necessary?
        Long adId = (Long) request.getSession().getAttribute("id");
//        String title = (String) request.getSession().getAttribute("title");
//        String description = (String) request.getSession().getAttribute("description");
//        Long userId = (Long) request.getSession().getAttribute("user_id");
//        System.out.println(userId);

        request.setAttribute("ad", DaoFactory.getAdsDao().singleAd(adId));
        request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set parameters to pass into editAd method
        Long id = Long.parseLong(request.getParameter("id"));
        request.getSession().setAttribute("id", id);
        String title = request.getParameter("title");
        request.getSession().setAttribute("title", title);
        String description = request.getParameter("description");
        request.getSession().setAttribute("description", description);
        Long userId = Long.parseLong(request.getParameter("user_id"));
        request.getSession().setAttribute("user_id", userId);



        // execute the edit
        // DaoFactory.getAdsDao().editAd(title, description, id);

        // after editing, redirect back to profile
        response.sendRedirect("/ads/edit");
    }
}
