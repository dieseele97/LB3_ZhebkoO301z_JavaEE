package forlaba;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteLecturers", urlPatterns = {"/DeleteLecturers"})
public class DeleteLecturers extends HttpServlet {
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
      try{int lectid = Integer.parseInt(request.getParameter("lectid"));
      EntityManager em = entityManagerFactory.createEntityManager();
      em.getTransaction().begin();
      Query query = em.createQuery("DELETE FROM Lecturers l WHERE l.lectid = :lectid ");
      query.setParameter("lectid", lectid);
      int rowsDeleted = query.executeUpdate();      
      em.getTransaction().commit();
      em.close();     
      response.sendRedirect(request.getContextPath() + "/ShowLecturers");  
    }
      catch(javax.persistence.PersistenceException e){
          PrintWriter out = response.getWriter();
       out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");                
            out.println("</head>");           
            out.println("<body>");
            out.println("<center>");            
            out.println("<h1>Виникла помилка!</h1>");
            out.println("<h2>Видаліть спочатку дисципліну закріплену за даним викладачом!</h2>");
            out.println("<p><a href=menupage.jsp> Повернутися на головну</a></p>"); 
            out.println("</body>");
            out.println("</html>");
      }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
