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
         Long adId = Long.parseLong(request.getParameter("editAdId"));
        request.setAttribute("ad", DaoFactory.getAdsDao().searchByUserID(adId));
        request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set parameters to pass into editAd method
        Long id = Long.parseLong(request.getParameter("editAdId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // execute the edit
        DaoFactory.getAdsDao().editAd(id, title, description);

        // after editing, redirect back to profile
        response.sendRedirect("/profile");
    }
}
