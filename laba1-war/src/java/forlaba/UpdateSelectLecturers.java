package forlaba;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


@WebServlet(name = "UpdateSelectLecturerst", urlPatterns = {"/UpdateSelectLecturers"})
public class UpdateSelectLecturers extends HttpServlet {
private static EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("laba1-warPU");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");      
    }   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
           int lectid = Integer.parseInt(request.getParameter("lectid"));
           EntityManager em = entityManagerFactory.createEntityManager();
           Query query = em.createQuery(
              "SELECT l FROM Lecturers l WHERE l.lectid = :lectid");
           query.setParameter("lectid", lectid);
           List lecturers = query.getResultList();
        for(Object l:lecturers){
                 request.setAttribute("lecturers", l);  
        }       
            em.close();           
           getServletContext().getRequestDispatcher("/updatelecturers.jsp").forward(request, response);         
    
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
