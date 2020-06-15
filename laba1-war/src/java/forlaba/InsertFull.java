 package forlaba;
import datapackage.Disciplines;
import datapackage.Lecturers;
import ejbpackage.HttpSessionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
    
@WebServlet(name = "InsertFull", urlPatterns = {"/InsertFull"})
public class InsertFull extends HttpServlet {
private static EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("laba1-warPU");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
       
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   EntityManager em = entityManagerFactory.createEntityManager();                   
        HttpSession session = request.getSession();
        session.setAttribute("url", "google.com");
        session.removeAttribute("url");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");                
            out.println("</head>");           
            out.println("<body>");
            out.println("<center>");
            out.println("<h1>Закріплення викладача за дисципліною</h1>");
            out.println("<h2>Оберіть викладача:</h2>");
            out.println("<form action=Add method=post>"); 
     Query query = em.createQuery(
              "SELECT l FROM Lecturers l");   
        List<Lecturers> lecturers = query.getResultList();
            for(Lecturers l:lecturers){
               out.println("<p><input type=radio value="+l.lectid+" name=data1>"+l.name+" "+l.surname+"</p>");  
             }        
            out.println("<h2>Оберіть дисципліну:</h2>");  
        Query query1 = em.createQuery(
              "SELECT d FROM Disciplines d");
        List<Disciplines> disciplines = query1.getResultList();
          for(Disciplines d:disciplines){
               out.println("<p><input type=checkbox value="+d.disid+" name=data2>"+d.discipline+"</p>");  
             }
          out.println("<input type=submit name=send value=Закріпити>");
            em.close();
              out.println("<p>"+HttpSessionManager.getActiveSessionsCount() + " користувачів присутні на сайті.");
        out.println("<p><a href=menupage.jsp> Повернутися на головну</a></p>");
        out.println("</form></center></body></html>"); 
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
