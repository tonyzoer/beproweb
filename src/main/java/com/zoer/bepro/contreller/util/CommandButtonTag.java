package com.zoer.bepro.contreller.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by zoer on 31.01.17.
 */
public class CommandButtonTag extends SimpleTagSupport {

    private String method = "get";
    private String item = "";
    private String action = "/Controller";
    private String command = "";

    public void setAction(String action) {
        this.action = action;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    StringWriter sw = new StringWriter();

    public void setCommand(String cmd) {
        this.command = cmd;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print(" <form method=\"" + method + "\" action=\"" + action + "\">");
        if (!command.equals("")) {
            out.print("<INPUT type=\"hidden\" name=\"command\" value=\"" + command + "\"/>");
        }
        getJspBody().invoke(sw);
        if (!item.equals("")) {
            out.print("<input hidden type=\"text\" name=\"item\" value=\"" + item + "\">");
        }
        out.print("<button type=\"submit\" class=\"btn-info btn-lg\">");
        getJspContext().getOut().println(sw.toString());
        out.print("</button>");
        out.print("</form>");
    }
}
