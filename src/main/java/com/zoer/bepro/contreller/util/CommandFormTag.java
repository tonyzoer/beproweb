package com.zoer.bepro.contreller.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by zoer on 31.01.17.
 */
public class CommandFormTag extends SimpleTagSupport {
    String method = "get";

    String action = "/Controller";
    String item=null;

    public void setItem(String item) {
        this.item = item;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    String command = null;


    StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws IOException, JspException {
        JspWriter out = getJspContext().getOut();
        out.print(" <form method=\"" + method + "\" action=\"" + action + "\">");
        if (command!=null)
            out.print("<INPUT type=\"hidden\" name=\"command\" value=\"" + command + "\"/>");
        if (item!=null)
            out.print("<INPUT type=\"hidden\" name=\"item\" value=\"" + item + "\"/>");
        getJspBody().invoke(sw);
        getJspContext().getOut().println(sw.toString());
        out.print("</form>");
    }
}
