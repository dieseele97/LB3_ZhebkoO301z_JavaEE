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


@WebServlet(name = "Add", urlPatterns = {"/Add"})
public class Add extends HttpServlet {
private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laba1-warPU");
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String data1 = request.getParameter("data1");   
        String data2[] = request.getParameterValues("data2");        
               
       EntityManager em = entityManagerFactory.createEntityManager();
      em.getTransaction().begin(); 
      for(int i=0;i<data2.length;i++){
      Query query = em.createNativeQuery("UPDATE DISCIPLINES SET LECTID="+data1+" WHERE DISID="+data2[i]+"");
      int rowsUpdated = query.executeUpdate(); 
      }
      em.getTransaction().commit();
      em.close();  
        
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<center><h1>Викладач успішно закріплений за дисципліною!</h1>");
      out.println("<p><a href=menupage.jsp> Повернутися на головну</a></p></center>");     
   
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}