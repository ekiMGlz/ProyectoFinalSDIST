/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Mike
 */
@WebServlet(name = "PostMessage", urlPatterns = {"/PostMessage"})
public class PostMessage extends HttpServlet {


    
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            HttpSession http_session = request.getSession();
            String username = (String) http_session.getAttribute("username");
            if(username != null){
                // Conseguir followers del usuario
                java.sql.Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyFirstDatabase", "root", "root");
                Statement query = con.createStatement();
                ResultSet rs = query.executeQuery("Select FOLLOWER_NAME from ROOT.FOLLOWING where FOLLOWED_NAME='"+username+"'");
                
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
                connectionFactory.setTrustAllPackages(true);
                Connection connection = connectionFactory.createConnection();
                connection.start();
                
               

                Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);

               
                
                String message = request.getParameter("message");
                System.out.println("Message= " + message);
                Chirrup ch = new Chirrup(username, message);
                ObjectMessage om = session.createObjectMessage(ch);
                
                String follower;
                MessageProducer mp;
                Destination destination;
                
                while(rs.next()){
                    follower = rs.getString("FOLLOWER_NAME");
                    destination = session.createQueue(follower);
                    mp = session.createProducer(destination);
                    mp.send(om);
                    mp.close();
                }
                
                session.close();
                connection.close();
                
                
                // Que contestar?
            }else{
                response.sendError(401);
            }
            
            
            
            
            
        } catch (JMSException ex) {
            Logger.getLogger(PostMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PostMessage.class.getName()).log(Level.SEVERE, null, ex);
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
