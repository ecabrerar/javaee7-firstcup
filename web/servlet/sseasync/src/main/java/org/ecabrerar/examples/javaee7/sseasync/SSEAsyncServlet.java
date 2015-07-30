/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ecabrerar.examples.javaee7.sseasync;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ecabrerar
 */

// enable async in the servlet
@WebServlet(urlPatterns={"/sseasync"}, asyncSupported=true)
public class SSEAsyncServlet extends HttpServlet {

    @Resource
    private ManagedExecutorService managedExecutorService;
    
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
         // set content type
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");

        final String msg = request.getParameter("msg");

        // start async
        final AsyncContext ac = request.startAsync();

        final PrintWriter writer = response.getWriter();
        
        Runnable runnable;
        runnable = new Runnable() {
            @Override
            public void run() {
                // echo msg 5 times
                for (int i = 0; i < 5; i++) {
                    if (i == 4) { // last
                        // SSE event field
                        writer.write("event: close\n");
                    }
                    // SSE data field
                    // last field with blank new line
                    writer.write("data: " + msg + "\n\n");
                    writer.flush();

                    try {
                        Thread.sleep(2000);
                    } catch(InterruptedException iex) {
                        iex.printStackTrace();
                    }
                }

                // complete async
                ac.complete();
            }
        };

        // submit the runnable to managedExecutorService
        managedExecutorService.submit(runnable);

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
