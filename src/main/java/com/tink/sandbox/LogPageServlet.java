/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.sandbox;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shearer
 */
public class LogPageServlet extends HttpServlet {
    
    private static final String LOG_FR = "../logs/app.log";
    //private static final String LOG_TO = "/Users/shearer/Documents/sdbx/logs/log.txt";

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogPageServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Applog at " + request.getContextPath() + "</h1>");
            readLogfile(out);
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
    
    public void readLogfile(PrintWriter out){
        try{
            if(!Files.exists(Paths.get(LOG_FR))){
                Files.createFile(Paths.get(LOG_FR));
            }
            
            List<String> list = Files.readAllLines(Paths.get(LOG_FR), Charset.forName("UTF-8"));
            for(String s : list){
                //Files.write(Paths.get("/Users/shearer/Documents/sdbx/logs"), bytes, options)
                out.println(s);
                out.println("<br>");
            }
            
            //Files.write(Paths.get(LOG_TO), list, Charset.forName("UTF-8"));
            
        }catch( Exception e){
            out.println(e.getStackTrace());
            out.println(Paths.get(".").toAbsolutePath());
            e.printStackTrace();
        }
        
    }
}
