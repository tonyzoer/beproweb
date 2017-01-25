package com.zoer.bepro.contreller;

import com.zoer.bepro.contreller.command.CommandDispatcher;
import com.zoer.bepro.contreller.command.impl.DefaultCommandDispatcher;
import com.zoer.bepro.contreller.exeptions.InsufficientPermissionsException;
import com.zoer.bepro.contreller.exeptions.NotFoundException;
import com.zoer.bepro.contreller.util.JspMessagesSetter;
import com.zoer.bepro.contreller.util.RequestWrapper;
import com.zoer.bepro.contreller.util.impl.RequestWrapperImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {
    private final transient CommandDispatcher commandDispatcher = DefaultCommandDispatcher.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseRequest(req, resp);
    }

    private void parseRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jspUrl;
        RequestWrapper requestWrapper=new RequestWrapperImpl(req);
        try {
            jspUrl = commandDispatcher.executeRequest(requestWrapper);
            req.getRequestDispatcher(jspUrl).forward(req, resp);
        } catch (InsufficientPermissionsException e) {
            JspMessagesSetter.setOutputError(requestWrapper, JspMessagesSetter.JspError.INSUFFICIENT_PERMISSIONS);
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        } catch (NotFoundException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

