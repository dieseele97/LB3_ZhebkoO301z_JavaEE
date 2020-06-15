package forlaba;
import datapackage.Disciplines;
import datapackage.Lecturers;
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


@WebServlet(name = "SearchLecturers", urlPatterns = {"/SearchLecturers"})
public class SearchLecturers extends HttpServlet {
private static EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("laba1-warPU");
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
        
    }   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    
                  }     
           @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        String field = request.getParameter("field");
        String sfield = request.getParameter("sfield"); 
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT l FROM Lecturers l WHERE l.surname =:sfield ");           
        query.setParameter("sfield", sfield);
        List<Lecturers> lecturers = query.getResultList();                      
        request.setAttribute("lecturers", lecturers);                                    
        
        for(Lecturers l:lecturers){
        Query query2 = em.createNativeQuery("SELECT * FROM Disciplines  where lectid="+l.lectid+"", Disciplines.class);
        List disciplines = query2.getResultList();                      
        request.setAttribute("disciplines", disciplines); 
       }
       em.close();            
       getServletContext().getRequestDispatcher("/showsearchl.jsp").forward(request, response);               
       
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}