package com.zoer.bepro.utils;

/**
 * Created by zoer on 26.01.17.
 */
import com.zoer.bepro.model.domain.Courses;
import org.apache.log4j.Logger;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MyCourseraApi {
private static final String courseraurl="https://www.coursera.org/courses?languages=en&query=";

private final static Logger logger = Logger.getLogger(MyCourseraApi.class);
    private MyCourseraApi() {
    }
    public static List<Courses> firstNCoursesByName(String query){
        List<Courses> courses=new LinkedList<>();
        try {
            Document doc=Jsoup.connect(courseraurl+query).get();
            Elements links=doc.select("div[data-reactid=120]>div>a[href]");
            for (Element link : links) {
                Courses course=new Courses();
                course.setSpecName(link.text());
                course.setUrl(link.attr("abs:href"));
                courses.add(course);
            }
        } catch (IOException e) {
           logger.debug(e);
        }
        return courses;
    }
    private static String format(String msg, Object... args) {
        return (String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }


}
