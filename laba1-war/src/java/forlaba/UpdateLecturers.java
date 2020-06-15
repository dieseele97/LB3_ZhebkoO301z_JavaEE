package forlaba;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateLecturers", urlPatterns = {"/UpdateLecturers"})
public class UpdateLecturers extends HttpServlet {
private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laba1-warPU");
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");  
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
       
        int lectid = Integer.parseInt(request.getParameter("lectid"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        EntityManager em = entityManagerFactory.createEntityManager();
      em.getTransaction().begin();      
      Query query = em.createQuery("UPDATE Lecturers l SET l.name=:name,l.surname=:surname WHERE l.lectid=:lectid ");
      query.setParameter("name", name);
      query.setParameter("surname", surname);
      query.setParameter("lectid", lectid);
      int rowsUpdated = query.executeUpdate();    
      em.getTransaction().commit();
      em.close();     
     response.sendRedirect(request.getContextPath() + "/ShowLecturers");      
            }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
