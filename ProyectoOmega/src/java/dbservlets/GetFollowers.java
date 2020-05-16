/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Mike
 */
@WebServlet(name = "GetFollowers", urlPatterns = {"/GetFollowers"})
public class GetFollowers extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()){
            
            /* TODO output your page here. You may use following sample code. */
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            if(username != null){
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyFirstDatabase", "root", "root");
                Statement query = con.createStatement();
                ResultSet rs = query.executeQuery("Select FOLLOWER_NAME from ROOT.FOLLOWING where FOLLOWED_NAME='"+username+"'");
                
                JSONArray names = new JSONArray();
                JSONObject jsonResponse = new JSONObject();
                
                while(rs.next()){
                    names.add(rs.getString("FOLLOWER_NAME"));
                }
                
                jsonResponse.put("followers", names);
                out.write(jsonResponse.toString());
                
            }else{
                response.sendError(401);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GetFollowers.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("index.jsp");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetFollowers.class.getName()).log(Level.SEVERE, null, ex);
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
