package com.zoer.bepro.contreller;

import org.apache.log4j.*;
import com.zoer.bepro.contreller.command.CommandDispatcher;
import com.zoer.bepro.contreller.command.DefaultCommandDispatcher;
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
    private final static Logger logger = Logger.getLogger(MainController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseRequest(req, resp);
    }

    private void parseRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jspUrl=null;
        RequestWrapper requestWrapper=new RequestWrapperImpl(req);
        try {
            jspUrl = commandDispatcher.executeRequest(requestWrapper);
            req.getRequestDispatcher(jspUrl).forward(req, resp);
        } catch (InsufficientPermissionsException e) {
            JspMessagesSetter.setOutputError(requestWrapper, JspMessagesSetter.JspError.INSUFFICIENT_PERMISSIONS);
            logger.debug(e.getMessage()+jspUrl);
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        } catch (NotFoundException e) {
            logger.debug(e.getMessage()+jspUrl);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e){
            logger.error(e.getMessage()+jspUrl);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

