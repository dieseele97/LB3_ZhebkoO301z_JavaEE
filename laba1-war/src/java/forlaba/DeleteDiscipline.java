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

@WebServlet(name = "DeleteDiscipline", urlPatterns = {"/DeleteDiscipline"})
public class DeleteDiscipline extends HttpServlet {
private static EntityManagerFactory entityManagerFactory =
          Persistence.createEntityManagerFactory("laba1-warPU");
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
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Disciplines d WHERE d.disid = :disid ");
        query.setParameter("disid", disid);
        int rowsDeleted = query.executeUpdate();      
        em.getTransaction().commit();
        em.close();        
        response.sendRedirect(request.getContextPath() + "/ShowDisciplines");  
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
