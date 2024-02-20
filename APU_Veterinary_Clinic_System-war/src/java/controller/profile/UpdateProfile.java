/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.profile;

import entity.ClinicUser;
import entity.ClinicUserFacade;
import validator.ClinicUserValidator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static constant.EndpointConstant.UPDATE_PROFILE;
import static constant.EndpointConstant.VIEW_PROFILE;

/**
 *
 * @author Jackson Tai
 */
@WebServlet(name = "UpdateProfile", urlPatterns = {UPDATE_PROFILE})
public class UpdateProfile extends HttpServlet {

    @EJB
    private ClinicUserFacade clinicUserFacade;

    private ClinicUser clinicUser;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        this.clinicUser = (ClinicUser) session.getAttribute("clinicUser");
        request.setAttribute("clinicUser", clinicUser);
        request.getRequestDispatcher(UPDATE_PROFILE + ".jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     *                 594064
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");

        ClinicUserValidator clinicUserValidatorValidator = new ClinicUserValidator(clinicUserFacade);
        Map<String, String> errorMessages = new HashMap<>();

        // check if it's other's data
        if (!fullName.equals(clinicUser.getFullName())) {
            errorMessages.putAll(clinicUserValidatorValidator.validateDuplicateFullName(fullName));
        }
        if (!email.equals(clinicUser.getEmail())) {
            errorMessages.putAll(clinicUserValidatorValidator.validateDuplicateEmail(email));
        }
        if (!phoneNumber.equals(clinicUser.getPhoneNumber())) {
            errorMessages.putAll(clinicUserValidatorValidator.validateDuplicatePhoneNumber(phoneNumber));
        }

        // if it's not repeated check if it's empty or invalid
        errorMessages.putAll(ClinicUserValidator.validateFullName(fullName));
        errorMessages.putAll(ClinicUserValidator.validateEmail(email));
        errorMessages.putAll(ClinicUserValidator.validatePhoneNumber(phoneNumber));

        if (!errorMessages.isEmpty()) {
            errorMessages.forEach(request::setAttribute);
            request.getRequestDispatcher(UPDATE_PROFILE + ".jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + VIEW_PROFILE + ".jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
