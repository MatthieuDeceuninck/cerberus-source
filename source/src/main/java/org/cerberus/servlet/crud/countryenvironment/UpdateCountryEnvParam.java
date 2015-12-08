/*
 * Cerberus  Copyright (C) 2013  vertigo17
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This file is part of Cerberus.
 *
 * Cerberus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cerberus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cerberus.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cerberus.servlet.crud.countryenvironment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cerberus.crud.entity.CountryEnvParam;
import org.cerberus.crud.entity.MessageGeneral;
import org.cerberus.enums.MessageGeneralEnum;
import org.cerberus.exception.CerberusException;
import org.cerberus.crud.factory.IFactoryCountryEnvParam;
import org.cerberus.crud.service.ICountryEnvParamService;
import org.cerberus.util.StringUtil;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
public class UpdateCountryEnvParam extends HttpServlet {

    private ICountryEnvParamService cepService;
    private IFactoryCountryEnvParam factoryCep;
     
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        cepService = appContext.getBean(ICountryEnvParamService.class);
        factoryCep = appContext.getBean(IFactoryCountryEnvParam.class);
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

        String id = policy.sanitize(request.getParameter("id"));
        String system = id.split("System&#61;")[1].split("&amp;")[0];
        String country = id.split("Country&#61;")[1].split("&amp;")[0];
        String env = id.split("Env&#61;")[1].split("&amp;")[0];
        String name = policy.sanitize(request.getParameter("columnName"));
        String value = policy.sanitize(request.getParameter("value"));

        response.setContentType("text/html");
            try {
            CountryEnvParam cep = cepService.findCountryEnvParamByKey(system, country, env);
            if (name != null && "Build".equalsIgnoreCase(name.trim())) {
                cep.setBuild(value);
            } else if (name != null && "Revision".equalsIgnoreCase(name.trim())) {
                cep.setRevision(value);
            } else if (name != null && "Chain".equalsIgnoreCase(name.trim())) {
                cep.setChain(value);
            } else if (name != null && "DistribList".equalsIgnoreCase(name.trim())) {
                cep.setDistribList(value);
            } else if (name != null && "EmailBodyRevision".equalsIgnoreCase(name.trim())) {
                cep.seteMailBodyRevision(value);
            } else if (name != null && "Type".equalsIgnoreCase(name.trim())) {
                cep.setType(value);
            } else if (name != null && "EmailBodyChain".equalsIgnoreCase(name.trim())) {
                cep.seteMailBodyChain(value);
            } else if (name != null && "EmailBodyDisableEnvironment".equalsIgnoreCase(name.trim())) {
                cep.seteMailBodyDisableEnvironment(value);
            } else if (name != null && "Active".equalsIgnoreCase(name.trim())) {
                cep.setActive(StringUtil.parseBoolean(value));
            } else if (name != null && "MaintenanceAct".equalsIgnoreCase(name.trim())) {
                cep.setMaintenanceAct(StringUtil.parseBoolean(value));
            } else if (name != null && "MaintenanceStr".equalsIgnoreCase(name.trim())) {
                cep.setMaintenanceStr(value);
            } else if (name != null && "MaintenanceEnd".equalsIgnoreCase(name.trim())) {
                cep.setMaintenanceEnd(value);
            } else {
                throw new CerberusException(new MessageGeneral(MessageGeneralEnum.NOT_IMPLEMEMTED));
            }
            cepService.update_deprecated(cep);
            response.getWriter().print(value);
        } catch (CerberusException ex) {
            response.getWriter().print(ex.getMessageError().getDescription());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
