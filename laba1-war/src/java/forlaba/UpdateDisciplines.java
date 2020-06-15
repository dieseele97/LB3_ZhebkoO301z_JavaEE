package forlaba;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
@WebServlet(name = "UpdateDisciplines", urlPatterns = {"/UpdateDisciplines"})
public class UpdateDisciplines extends HttpServlet {
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
        int disid = Integer.parseInt(request.getParameter("disid"));
        String discipline = request.getParameter("discipline");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();      
        Query query = em.createQuery("UPDATE Disciplines d SET d.discipline=:discipline WHERE d.disid=:disid");
        query.setParameter("discipline", discipline);   
        query.setParameter("disid", disid);
        int rowsUpdated = query.executeUpdate();    
        em.getTransaction().commit();
        em.close();      
        response.sendRedirect(request.getContextPath() + "/ShowDisciplines");
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
