package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class Servlet extends HttpServlet {

    String text = "<!DOCTYPE html><html> " +
            "<head><meta charset=utf-8>" +
            "<title>Results</title>" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/index.css\">" +
            "</head><body>" +
            "<p id = \"title\" >Results</p>" +
            "<hr align=\"left\" color=#777c7c size=3 width=\"310\">" +
            "<p>The total number of respondents is ";
    private static int count;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        HttpSession hs = req.getSession();
        Statistica st = (Statistica) hs.getAttribute("stat");
        if (st == null) {
            st = Statistica.getInstance();
            hs.setAttribute("stat", st);
        }

        st.addVoice(req.getParameter("age"), st.getVoiceAge());
        st.addVoiceCity(req.getParameter("city"), st.getVoiceCity());
        st.addVoice(req.getParameter("experience"), st.getVoiceExp());
        st.addVoice(req.getParameter("english"), st.getVoiceEng());

        String [] languages = req.getParameterValues("language");
        st.addVoiceLang(languages, st.getVoiceLang());
        if (languages != null) {
            st.addVoice(Integer.toString(languages.length), st.getVoiceLangCount());
        } else {
            st.addVoice("no one", st.getVoiceLangCount());
        }
        count++;

        PrintWriter pw = resp.getWriter();
        String answer = text + count + "</p>" + st.toString() + "<br><a href=\"../index.html\">new response</a></body></html>";
        pw.println(answer);
        pw.close();
    }
}