package forlaba;
import datapackage.Disciplines;
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


@WebServlet(name = "SearchDiscipline", urlPatterns = {"/SearchDiscipline"})
public class SearchDiscipline extends HttpServlet {
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
        String sfield = request.getParameter("sfield");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT d FROM Disciplines d WHERE d.discipline =:sfield ");           
        query.setParameter("sfield", sfield);
        List<Disciplines> disciplines = query.getResultList();              
        for(Disciplines d:disciplines){
        Query query2 = em.createQuery("SELECT l FROM Lecturers l where l.lectid="+d.lectid.lectid+"");
        List lecturers = query2.getResultList();       
        request.setAttribute("lecturers", lecturers); 
        }
        em.close();           
        request.setAttribute("disciplines",disciplines);        
        getServletContext().getRequestDispatcher("/searchdiscipline.jsp").forward(request, response);              
            
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
