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



@WebServlet(name = "UpdateSelectDisciplines", urlPatterns = {"/UpdateSelectDisciplines"})
public class UpdateSelectDisciplines extends HttpServlet {
private static EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("laba1-warPU");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");      
    }   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
            int disid = Integer.parseInt(request.getParameter("disid"));
             EntityManager em = entityManagerFactory.createEntityManager();
           Query query = em.createQuery(
              "SELECT d FROM Disciplines d WHERE d.disid = :disid");
           query.setParameter("disid", disid);
           List disciplines = query.getResultList();
        for(Object d:disciplines){
                 request.setAttribute("disciplines", d);  
        }       
            em.close();        
           getServletContext().getRequestDispatcher("/updatedisciplines.jsp").forward(request, response);    
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
