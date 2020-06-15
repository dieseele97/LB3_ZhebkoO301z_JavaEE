package forlaba;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ShowLecturers", urlPatterns = {"/ShowLecturers"})
public class ShowLecturers extends HttpServlet {
private static EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("laba1-warPU");
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8"); 
            
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT l FROM Lecturers l");          
        List lecturers = query.getResultList();                      
        request.setAttribute("lecturers", lecturers);        
        em.close();           
               
        getServletContext().getRequestDispatcher("/showlecturers.jsp").forward(request, response);       
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
